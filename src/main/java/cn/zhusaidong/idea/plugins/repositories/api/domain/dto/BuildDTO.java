package cn.zhusaidong.idea.plugins.repositories.api.domain.dto;

import cn.zhusaidong.idea.plugins.repositories.api.enums.IdeEnum;
import lombok.Data;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@Data
public class BuildDTO {
    private String version;
    private IdeEnum type;
}
