package cn.zhusaidong.idea.plugins.repositories.common.configuration.properties;

import cn.zhusaidong.idea.plugins.repositories.common.enums.StoreTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static cn.zhusaidong.idea.plugins.repositories.common.configuration.Constants.PROPERTIES_PREFIX;

/**
 * @author zhusaidong
 * @since 2023/12/7
 */
@Data
@Component
@ConfigurationProperties(prefix = PROPERTIES_PREFIX)
public class PluginRepositoriesProperties {
    private StoreTypeEnum storeType;
    private String username;
    private String password;

    //todo 需要根据storeType返回对应Properties
}
