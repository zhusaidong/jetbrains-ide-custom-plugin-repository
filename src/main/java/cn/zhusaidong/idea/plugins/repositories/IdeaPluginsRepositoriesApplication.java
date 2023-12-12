package cn.zhusaidong.idea.plugins.repositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://plugins.jetbrains.com/docs/intellij/custom-plugin-repository.html
 * <p>
 * https://plugins.jetbrains.com/docs/marketplace/plugin-update-download.html#2-download-a-specified-version-of-the-plugin
 */
@SpringBootApplication
public class IdeaPluginsRepositoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeaPluginsRepositoriesApplication.class, args);

    }
}
