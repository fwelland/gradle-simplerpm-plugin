package org.fhw.gradle.simplerpm

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.MavenPlugin

class SimpleRPMPlugin implements Plugin<Project> {
        
    @Override
    void apply(Project project) {        
        //project.plugins.apply(BasePlugin)
        project.plugins.apply(MavenPlugin)
        project.extensions.create("simplerpm", SimpleRPMPluginExtension)        
        project.task('rpm', type: MakeRPMTask)
    }       
}
