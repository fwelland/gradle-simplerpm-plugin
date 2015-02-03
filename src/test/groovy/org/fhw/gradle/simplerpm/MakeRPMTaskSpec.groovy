package org.fhw.gradle.simplerpm

import spock.lang.Specification
import org.gradle.testfixtures.ProjectBuilder
import java.nio.file.Path
import java.nio.file.Paths
import java.io.File
import org.gradle.api.Project

class MakeRPRTaskSpec extends Specification {
    
    def "Test basic case"()
    {
        given: 
            Project project = ProjectBuilder.builder().build()
            project.apply plugin: 'simplerpm'
            File ear = new File((URL)this.getClass().getResource("resources/test.ear"))
            project.simplerpm.artifact_to_include = ear.absolutePath
            project.simplerpm.spec_file = "${project.projectDir}/test/resources/test.spec"
        
        when:
            String  art = project.tasks.rpm.makerpm()
            
        then:  
            1 == 1 
    }    
}

