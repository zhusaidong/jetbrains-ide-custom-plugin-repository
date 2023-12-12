package cn.zhusaidong.idea.plugins.repositories.common.configuration;

import cn.zhusaidong.idea.plugins.repositories.common.configuration.properties.store.LocalProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.util.Optional;

/**
 * web配置类
 *
 * @author zhusaidong
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LocalProperties localProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/admin/");

        Optional.ofNullable(localProperties)
                .map(LocalProperties::getPluginDir)
                .map(File::new)
                .filter(File::exists)
                .ifPresent(file ->
                        registry.addResourceHandler(LocalProperties.PLUGIN_PATH + "/**")
                                .addResourceLocations("file:" + file.getAbsolutePath() + "/")
                );
    }
}
