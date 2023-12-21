package cn.zhusaidong.idea.plugins.repositories.common.configuration;

import cn.zhusaidong.idea.plugins.repositories.common.configuration.properties.store.LocalProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 重新加入Jackson解析json的类，让其顺序排在xml解析的前面
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/admin/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false)
        ;
    }
}
