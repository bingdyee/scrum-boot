package io.github.scrumboot.gradle;

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin;
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;
import org.springframework.boot.gradle.plugin.SpringBootPlugin;
import org.springframework.boot.gradle.util.VersionExtractor;


/**
 * @author Bing D. Yee
 * @since 2022/04/18
 */
public class ScrumBootPlugin implements Plugin<Project> {

    static final String SCRUM_BOOT_VERSION = VersionExtractor.forClass(ScrumBootPlugin.class);

    static final String BOM_COORDINATES = "io.github.matrix:matrix-dependencies:" + SCRUM_BOOT_VERSION;

    @Override
    public void apply(Project project) {
        PluginContainer plugins = project.getPlugins();
        plugins.apply(DependencyManagementPlugin.class);
        plugins.apply(SpringBootPlugin.class);
        project.getExtensions().findByType(DependencyManagementExtension.class)
                .imports((importsHandler) -> importsHandler.mavenBom(ScrumBootPlugin.BOM_COORDINATES));
    }

}