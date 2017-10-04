package com.coprtools.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GradlePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("demoSetting", CoprtPluginExtension.class);
        project.getTasks().create("demo", CoprtTask.class);
    }
}
