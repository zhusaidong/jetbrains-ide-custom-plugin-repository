package cn.zhusaidong.idea.plugins.repositories.admin.util;

import cn.zhusaidong.idea.plugins.repositories.common.xml.IdeaPluginXml;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author zhusaidong
 * @since 2023/12/7
 */
@UtilityClass
@Slf4j
public class PluginXmlUtil {
    private static final String PLUGIN_XML = "META-INF/plugin.xml";
    private static ObjectMapper objectMapper = null;

    /**
     * 获取插件xml
     */
    public IdeaPluginXml getPluginInfo(String pluginPath) {
        URI uri = URI.create(pluginPath);
        boolean isUrl = "https".equals(uri.getScheme()) || "http".equals(uri.getScheme());

        File tempFile;
        if (isUrl) {
            //url
            tempFile = PluginXmlUtil.downloadPluginZip(pluginPath);
        } else {
            //local
            tempFile = new File(pluginPath);
        }

        log.info("tempFile={}", tempFile);
        String xml = PluginXmlUtil.readPluginZip(tempFile);
        if (isUrl && tempFile.delete()) {
            log.info("deleted[{}]", tempFile.getName());
        }

        if (xml == null) {
            return null;
        }

        return parsePluginInfo(xml);
    }

    /**
     * 解析插件xml
     */
    private IdeaPluginXml parsePluginInfo(String pluginXml) {
        IdeaPluginXml xmlObject = null;
        try {
            xmlObject = getObjectMapper().readValue(pluginXml, IdeaPluginXml.class);
        } catch (JsonProcessingException e) {
            log.error("parsePluginInfo error={}", e.getMessage());
        }
        return xmlObject;
    }

    /**
     * 获取xml解析器
     */
    private static ObjectMapper getObjectMapper() {
        if (objectMapper != null) {
            return objectMapper;
        }

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);

        return objectMapper = xmlMapper;
    }

    /**
     * 读取插件压缩包
     */
    private String readPluginZip(File file) {
        String xml = null;
        try (ZipFile zipFile = new ZipFile(file)) {
            ZipEntry zipEntry = zipFile.getEntry(PLUGIN_XML);
            log.debug("META-INF/plugin.xml={}", zipEntry);
            if (zipEntry != null) {
                xml = readPluginXml(zipFile.getInputStream(zipEntry));
            } else {
                //获取真正的plugin.xml：获取和目录同名的jar包
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                int entrySize = zipFile.size();
                if (entrySize == 1) {
                    zipEntry = entries.nextElement();
                } else {
                    while (entries.hasMoreElements()) {
                        zipEntry = entries.nextElement();
                        if (zipEntry.isDirectory()) {
                            continue;
                        }

                        log.info("zipEntry files={}", zipEntry);

                        String[] split = zipEntry.getName().split("/");
                        String folderName = split[0];
                        String jarFileName = split[split.length - 1];
                        if (jarFileName.contains(folderName)) {
                            break;
                        }
                        if (jarFileName.toLowerCase().matches("(.*)" + folderName.toLowerCase() + "(.*).jar")) {
                            break;
                        }
                    }
                }

                if (zipEntry != null) {
                    File tempFile = File.createTempFile("idea-plugins-", ".tmp");
                    FileUtils.copyToFile(zipFile.getInputStream(zipEntry), tempFile);

                    try (ZipFile zipFile1 = new ZipFile(tempFile)) {
                        ZipEntry entry = zipFile1.getEntry(PLUGIN_XML);
                        if (entry != null) {
                            xml = readPluginXml(zipFile1.getInputStream(entry));
                        }
                    }
                    if (tempFile.delete()) {
                        log.info("deleted[{}]", tempFile.getName());
                    }
                }
            }
        } catch (IOException e) {
            log.error("readPluginZip error={}", e.getMessage());
        }
        return xml;
    }

    /**
     * 下载插件压缩包
     */
    private File downloadPluginZip(String zipFileUrl) {
        return new RestTemplate().execute(zipFileUrl, HttpMethod.GET, null, response -> {
            File temp = File.createTempFile("idea-plugins-", ".tmp");
            FileUtils.copyToFile(response.getBody(), temp);
            return temp;
        });
    }

    /**
     * 读取plugin.xml
     */
    private String readPluginXml(InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String xml = bufferedReader.lines().collect(Collectors.joining("\n\t"));
        bufferedReader.close();
        return xml;
    }
}
