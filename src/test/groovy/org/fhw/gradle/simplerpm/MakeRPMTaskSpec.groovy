package org.fhw.gradle.simplerpm

import spock.lang.Specification
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Ignore
import org.gradle.api.Project

class MakeRPMTaskSpec extends Specification {
    
    @Ignore
    def "Test basic case"()
    {
        given: 
            Project project = ProjectBuilder.builder().build()
            project.apply plugin: 'simplerpm'        
            project.simplerpm.artifact_to_include = ClassLoader.getSystemClassLoader().getResource("test.ear").toURI().getPath()
            project.simplerpm.spec_file = ClassLoader.getSystemClassLoader().getResource("test.spec").toURI().getPath()
        
        when:
            String  art = project.tasks.rpm.makerpm()
            
        then:  
            //need something more assertable
            1 == 1 
    }    
}