package org.fhw.gradle.simplerpm

import spock.lang.Specification
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project

class BaseTaskSpec extends Specification {

    
    def "Test get artifact path default case "()
    {
        given: 
            Project project = ProjectBuilder.builder().build()
            project.apply plugin: 'simplerpm'
            
        when:
            String  art = project.tasks.rpm.getArtifactPath()
            
        then:  
            ! art
    }    
           
    def "Test get artifact path"()
    {
        given: 
            Project project = ProjectBuilder.builder().build()
            project.apply plugin: 'simplerpm'
            project.simplerpm.artifact_to_include = '/opt/yar'
            
        when:
            String  art = project.tasks.rpm.getArtifactPath()
            
        then:  
            art == '/opt/yar'
    }        
}

