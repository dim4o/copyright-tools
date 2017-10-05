package com.coprtools.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GradlePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("copyrightSetting", CopyrightPluginExtension.class);
        project.getTasks().create("demo", CopyrihtTask.class);
    }
}
