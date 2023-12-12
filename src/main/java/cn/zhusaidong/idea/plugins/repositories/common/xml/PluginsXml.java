package cn.zhusaidong.idea.plugins.repositories.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * 输出插件组信息
 *
 * @author zhusaidong
 * @since 2023/12/6
 */
@Data
@JacksonXmlRootElement(localName = "plugins")
public class PluginsXml {
    @JacksonXmlProperty(localName = "plugin")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PluginXml> pluginXmlList;
}
