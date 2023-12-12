package cn.zhusaidong.idea.plugins.repositories;

import cn.zhusaidong.idea.plugins.repositories.admin.util.PluginXmlUtil;
import cn.zhusaidong.idea.plugins.repositories.common.xml.IdeaPluginXml;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class IdeaPluginsRepositoriesApplicationTests {

    @Test
    void testGetPluginJar() {
        String path = "Key-Promoter-X/lib/instrumented-Key Promoter X-2023.3.0.jar";
        String[] split = path.split("/");
        String folderName = split[0];
        String jarFileName = split[split.length - 1];
        boolean matches = jarFileName.toLowerCase().replace(" ", "-")
                .matches("(.*)" + folderName.toLowerCase() + "(.*).jar");
        Assert.isTrue(matches, "匹配不上");
    }

    @Test
    void testGetPluginInfo() {
        String path = "plugins/MybatisCodeHelperMarketPlaceNew-3.2.5.zip";
        IdeaPluginXml ideaPluginXml = PluginXmlUtil.getPluginInfo(path);
        System.out.println(ideaPluginXml);
    }
}
