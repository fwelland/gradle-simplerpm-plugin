package org.fhw.gradle.simplerpm

import org.gradle.api.Project
import org.gradle.api.Plugin

class SimpleRPMPlugin implements Plugin<Project> {
        
    @Override
    void apply(Project project) {        
                
        project.extensions.create("simplerpm", SimpleRPMPluginExtension)        
        project.task('rpm', type: MakeRPMTask)
    }       
}
