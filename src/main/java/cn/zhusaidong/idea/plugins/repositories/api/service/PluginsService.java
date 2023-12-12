package cn.zhusaidong.idea.plugins.repositories.api.service;

import cn.zhusaidong.idea.plugins.repositories.api.domain.dto.BuildDTO;
import cn.zhusaidong.idea.plugins.repositories.common.xml.PluginsXml;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
public interface PluginsService {
    PluginsXml updatePlugins(BuildDTO buildDTO);
}
