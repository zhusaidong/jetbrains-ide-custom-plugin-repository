package cn.zhusaidong.idea.plugins.repositories.api.controller;

import cn.zhusaidong.idea.plugins.repositories.api.domain.dto.BuildDTO;
import cn.zhusaidong.idea.plugins.repositories.api.service.PluginsService;
import cn.zhusaidong.idea.plugins.repositories.api.util.BuildUtils;
import cn.zhusaidong.idea.plugins.repositories.common.xml.PluginsXml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@Slf4j
@RestController
@RequestMapping("/")
public class PluginsController {
    @Resource
    private PluginsService pluginsService;

    @GetMapping(value = {"/","/plugins/updatePlugins.xml"}, produces = "application/xml")
    public PluginsXml updatePlugins(String build) {
        log.info("updatePlugins=>build={}", build);

        BuildDTO buildDTO = BuildUtils.parse(build);
        log.info("buildDTO={}", buildDTO);

        return pluginsService.updatePlugins(buildDTO);
    }
}
