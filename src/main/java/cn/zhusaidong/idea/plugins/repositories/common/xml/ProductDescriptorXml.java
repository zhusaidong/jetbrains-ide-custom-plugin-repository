package cn.zhusaidong.idea.plugins.repositories.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 付费插件描述
 * <p>
 * only for paid or freemium plugins; ignored in additional config file
 * Do not add <product-descriptor> element in a free plugin.
 *
 * @author zhusaidong
 * @since 2023/12/7
 */
@Data
public class ProductDescriptorXml {
    /**
     * The plugin product code used in the JetBrains Sales System.
     * The code must be agreed with JetBrains in advance and follow the requirements.
     */
    @JacksonXmlProperty(localName = "code", isAttribute = true)
    private String code;
    /**
     * Date of the major version release in the YYYYMMDD format.
     */
    @JacksonXmlProperty(localName = "release-date", isAttribute = true)
    private String releaseDate;
    /**
     * A major version in a special number format.
     */
    @JacksonXmlProperty(localName = "release-version", isAttribute = true)
    private String releaseVersion;
    /**
     * The boolean value determining whether the plugin is a Freemium plugin.
     */
    @JacksonXmlProperty(localName = "optional", isAttribute = true)
    private boolean optional;
}
