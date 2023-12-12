package cn.zhusaidong.idea.plugins.repositories.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 读取插件包信息
 */
@Data
@JacksonXmlRootElement(localName = "idea-plugin")
public class IdeaPluginXml {
    /**
     * The link to the plugin homepage displayed on the plugin page in the JetBrains Marketplace.
     */
    @JacksonXmlProperty(localName = "url", isAttribute = true)
    private String url;
    /**
     * The boolean value determining whether the plugin installation, update,
     * or uninstallation requires the IDE restart (see Dynamic Plugins for details).
     */
    @JacksonXmlProperty(localName = "require-restart", isAttribute = true)
    private boolean requireRestart;
    /**
     * Paid or Freemium plugin descriptor.
     */
    @JacksonXmlProperty(localName = "product-descriptor")
    private ProductDescriptorXml productDescriptor;
    /**
     * A unique identifier of the plugin.
     * It should be a fully qualified name similar to Java packages and must not collide with the ID of existing plugins.
     * The ID is a technical value used to identify the plugin in the IDE and JetBrains Marketplace.
     * Please use characters, numbers, and '.'/'-'/'_' symbols only and keep it reasonably short.
     */
    @JacksonXmlProperty(localName = "id")
    private String id;
    /**
     * The plugin version displayed in the Plugins settings dialog and in the JetBrains Marketplace plugin page.
     * Plugins uploaded to the JetBrains Marketplace must follow semantic versioning.
     */
    @JacksonXmlProperty(localName = "version")
    private String version;
    /**
     * The plugin's range of compatible IntelliJ-based IDE versions.
     */
    @JacksonXmlProperty(localName = "idea-version")
    private IdeaVersionXml ideaVersionXml;
    /**
     * The user-visible plugin display name (Title Case).
     */
    @JacksonXmlProperty(localName = "name")
    private String name;
    /**
     * The vendor name or organization ID (if created) in the Plugins settings dialog and in the JetBrains Marketplace plugin page.
     */
    @JacksonXmlProperty(localName = "vendor")
    private VendorXml vendorXml;
    /**
     * The plugin description displayed on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
     * <p>
     * Simple HTML elements, like text formatting, paragraphs, lists, etc.,
     * are allowed and must be wrapped into <![CDATA[... ]]> section.
     */
    @JacksonXmlProperty(localName = "description")
    @JacksonXmlCData
    private String description;
    /**
     * A short summary of new features, bugfixes,
     * and changes provided with the latest plugin version.
     * Change notes are displayed on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
     * <p>
     * Simple HTML elements, like text formatting, paragraphs, lists, etc.,
     * are allowed and must be wrapped into <![CDATA[... ]]> section.
     */
    @JacksonXmlProperty(localName = "change-notes")
    @JacksonXmlCData
    private String changeNotes;
}
