package cn.zhusaidong.idea.plugins.repositories.api.domain.entity;

import lombok.Data;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@Data
public class Plugin {
    private String id;
    private String url;
    private String version;
    private String name;
    private String description;
    private String changeNotes;

    private Vendor vendor;
}
