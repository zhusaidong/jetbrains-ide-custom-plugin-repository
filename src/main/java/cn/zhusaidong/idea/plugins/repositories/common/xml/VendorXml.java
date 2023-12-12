package cn.zhusaidong.idea.plugins.repositories.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 插件作者信息
 *
 * @author zhusaidong
 * @since 2023/12/6
 */
@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VendorXml {
    /**
     * The vendor's email address.
     */
    @JacksonXmlProperty(localName = "email", isAttribute = true)
    private String email;
    /**
     * The link to the vendor's homepage.
     */
    @JacksonXmlProperty(localName = "url", isAttribute = true)
    private String url;
    @JacksonXmlText
    private String name;
}
