package cn.zhusaidong.idea.plugins.repositories.common.configuration.properties.store;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static cn.zhusaidong.idea.plugins.repositories.common.configuration.Constants.PROPERTIES_PREFIX;

/**
 * @author zhusaidong
 * @since 2023/12/7
 */
@Data
@Component
@ConditionalOnProperty(prefix = PROPERTIES_PREFIX, value = "store-type", havingValue = "local")
@ConfigurationProperties(prefix = PROPERTIES_PREFIX + ".local")
public class LocalProperties {
    private String host;
    private String pluginDir;
}
