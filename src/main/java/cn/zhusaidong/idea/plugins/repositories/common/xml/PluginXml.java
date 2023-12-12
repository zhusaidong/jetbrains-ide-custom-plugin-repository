package cn.zhusaidong.idea.plugins.repositories.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

/**
 * 输出插件信息
 *
 * @author zhusaidong
 * @since 2023/12/6
 */
@Data
public class PluginXml {
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "url", isAttribute = true)
    private String url;
    @JacksonXmlProperty(localName = "version", isAttribute = true)
    private String version;
    @JacksonXmlProperty(localName = "idea-version")
    private IdeaVersionXml ideaVersionXml;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "vendor")
    private VendorXml vendorXml;
    @JacksonXmlProperty(localName = "rating")
    private String rating;
    @JacksonXmlProperty(localName = "description")
    @JacksonXmlCData
    private String description;
    @JacksonXmlProperty(localName = "change-notes")
    @JacksonXmlCData
    private String changeNotes;
    @JacksonXmlProperty(localName = "depends")
    private List<String> depends;
}
