package org.fhw.gradle.simplerpm

import org.gradle.api.Project 

class SimpleRPMPluginConvention {
    final Project project 
    
    String artifact_to_include
    String spec_file
    Map rpm_macro_map
    String rpm_version 
}
