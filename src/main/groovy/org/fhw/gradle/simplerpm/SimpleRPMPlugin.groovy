package org.fhw.gradle.simplerpm

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.plugins.BasePlugin

class SimpleRPMPlugin implements Plugin<Project> {
        
    @Override
    void apply(Project project) {        
        project.plugins.apply(BasePlugin.class)
        project.extensions.create("simplerpm", SimpleRPMPluginExtension)        
        project.task('rpm', type: MakeRPMTask)
    }       
}
