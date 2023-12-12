package cn.zhusaidong.idea.plugins.repositories.common.configuration;

import cn.zhusaidong.idea.plugins.repositories.common.configuration.properties.store.LocalProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LocalProperties localProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/admin/");

        if (localProperties != null && localProperties.getPluginDir() != null) {
            File file = new File(localProperties.getPluginDir());
            if (file.exists()) {
                String absolutePath = file.getAbsolutePath();
                registry.addResourceHandler("/files/plugins/**")
                        .addResourceLocations("file:" + absolutePath + "/");
            }
        }
    }
}
