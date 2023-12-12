package cn.zhusaidong.idea.plugins.repositories.admin.controller;

import cn.zhusaidong.idea.plugins.repositories.admin.util.PluginXmlUtil;
import cn.zhusaidong.idea.plugins.repositories.common.configuration.properties.store.LocalProperties;
import cn.zhusaidong.idea.plugins.repositories.common.xml.IdeaPluginXml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@RestController
@RequestMapping("/admin/plugin")
@Slf4j
public class AdminPluginController {
    public static Map<String, IdeaPluginXml> ideaPluginXmlMap = new ConcurrentHashMap<>();

    @Resource
    private LocalProperties localProperties;

    @PostConstruct
    public void construct() {
        log.info("初始化仓库：{}", refresh());
    }

    @GetMapping("/refresh")
    public String refresh() {
        String pluginPath = localProperties.getPluginDir();
        if (!StringUtils.hasText(pluginPath)) {
            throw new IllegalArgumentException("插件目录不能为空");
        }

        File pluginDir = new File(pluginPath);
        if (!pluginDir.exists()) {
            if (pluginDir.mkdir()) {
                log.info("插件目录[{}]不存在，已自动创建", pluginDir.getAbsolutePath());
            }
        }
        File[] files = pluginDir.listFiles((File dir, String name) ->
                !ideaPluginXmlMap.containsKey(name) && !name.startsWith(".")
        );
        if (files == null) {
            return null;
        }

        List<String> newPluginList = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            IdeaPluginXml ideaPluginXml = PluginXmlUtil.getPluginInfo(file.getPath());
            if (ideaPluginXml != null) {
                ideaPluginXmlMap.put(file.getName(), ideaPluginXml);
                newPluginList.add(file.getName());
            }
        }

        return String.format("发现%s个新插件：%s", newPluginList.size(), String.join(",", newPluginList));
    }
}
