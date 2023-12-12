package cn.zhusaidong.idea.plugins.repositories.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 插件版本信息
 *
 * @author zhusaidong
 * @since 2023/12/6
 */
@Data
public class IdeaVersionXml {
    /**
     * The lowest IDE version compatible with the plugin.
     */
    @JacksonXmlProperty(localName = "since-build", isAttribute = true)
    private String sinceBuild;
    /**
     * The highest IDE version compatible with the plugin.
     * Undefined value declares compatibility with all the IDEs since the version specified by the since-build
     * (also with the future builds what may cause incompatibility errors).
     */
    @JacksonXmlProperty(localName = "until-build", isAttribute = true)
    private String untilBuild;
}
