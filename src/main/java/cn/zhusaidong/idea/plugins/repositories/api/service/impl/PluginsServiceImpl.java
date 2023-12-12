package cn.zhusaidong.idea.plugins.repositories.api.service.impl;

import cn.zhusaidong.idea.plugins.repositories.admin.controller.AdminPluginController;
import cn.zhusaidong.idea.plugins.repositories.api.domain.dto.BuildDTO;
import cn.zhusaidong.idea.plugins.repositories.api.enums.IdeEnum;
import cn.zhusaidong.idea.plugins.repositories.api.service.PluginsService;
import cn.zhusaidong.idea.plugins.repositories.api.util.BuildUtils;
import cn.zhusaidong.idea.plugins.repositories.common.configuration.properties.store.LocalProperties;
import cn.zhusaidong.idea.plugins.repositories.common.xml.IdeaPluginXml;
import cn.zhusaidong.idea.plugins.repositories.common.xml.IdeaVersionXml;
import cn.zhusaidong.idea.plugins.repositories.common.xml.PluginXml;
import cn.zhusaidong.idea.plugins.repositories.common.xml.PluginsXml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@Slf4j
@Service
public class PluginsServiceImpl implements PluginsService {
    @Resource
    private LocalProperties localProperties;

    @Override
    public PluginsXml updatePlugins(BuildDTO build) {
        Map<String, IdeaPluginXml> ideaPluginXmlMap = AdminPluginController.ideaPluginXmlMap;

        List<PluginXml> pluginXmlList = new ArrayList<>();
        ideaPluginXmlMap.forEach((fileName, ideaPluginXml) -> {
            //根据build版本筛选正确的插件
            if (compareBuildVersion(build, ideaPluginXml.getIdeaVersionXml())) {
                pluginXmlList.add(ideaPluginToPlugin(fileName, ideaPluginXml));
            }
        });

        PluginsXml pluginsXml = new PluginsXml();
        pluginsXml.setPluginXmlList(pluginXmlList);

        return pluginsXml;
    }

    private PluginXml ideaPluginToPlugin(String fileName, IdeaPluginXml ideaPluginXml) {
        PluginXml pluginXml = new PluginXml();

        BeanUtils.copyProperties(ideaPluginXml, pluginXml);
        pluginXml.setUrl(localProperties.getHost() + LocalProperties.PLUGIN_PATH + fileName);

        ///todo 系统计算的评分信息
        //pluginXml.setRating("5");

        return pluginXml;
    }

    private boolean compareBuildVersion(BuildDTO conditionBuild, IdeaVersionXml ideaVersionXml) {
        if (conditionBuild == null) {
            return true;
        }

        BuildDTO sinceBuildDTO = BuildUtils.parse(ideaVersionXml.getSinceBuild());

        return (sinceBuildDTO.getType() == IdeEnum.STAR || sinceBuildDTO.getType() == conditionBuild.getType()) &&
                (sinceBuildDTO.getVersion().compareTo(conditionBuild.getVersion()) >= 0);
    }
}
