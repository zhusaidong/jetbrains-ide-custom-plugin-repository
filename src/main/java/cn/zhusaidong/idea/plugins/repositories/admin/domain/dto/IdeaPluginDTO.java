package cn.zhusaidong.idea.plugins.repositories.admin.domain.dto;

import cn.zhusaidong.idea.plugins.repositories.common.xml.IdeaVersionXml;
import cn.zhusaidong.idea.plugins.repositories.common.xml.ProductDescriptorXml;
import cn.zhusaidong.idea.plugins.repositories.common.xml.VendorXml;
import lombok.Data;

/**
 * 读取插件包信息
 */
@Data
public class IdeaPluginDTO {
    private String url;
    private ProductDescriptorXml productDescriptor;
    private String id;
    private String version;
    private IdeaVersionXml ideaVersionXml;
    private String name;
    private VendorXml vendorXml;
    private String description;
    private String changeNotes;
}
