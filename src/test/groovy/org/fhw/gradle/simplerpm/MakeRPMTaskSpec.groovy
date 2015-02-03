package org.fhw.gradle.simplerpm

import spock.lang.Specification
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.util.Path
import org.gradle.api.Project

class MakeRPRTaskSpec extends Specification {

    
    def "Test basic case"()
    {
        given: 
            Project project = ProjectBuilder.builder().build()
            project.apply plugin: 'simplerpm'
            Path trsrc = Paths.get("${project.buildDir}" + '/resources')
            project.simplerpm.artifact_to_include = '${project.projectDir}' + 'test/resources'               
            project.simplerpm.spec_file = '${project.projectDir}' + 'test/resources'        
        
        when:
            String  art = project.tasks.rpm.makerpm()
            
        then:  
            1 == 1 
    }    
}

