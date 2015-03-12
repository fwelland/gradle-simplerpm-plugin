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
            Project project = new ProjectBuilder().withName('test-rpm').build()
            project.version = '3.9.4'
            project.apply plugin: 'simplerpm'        
            project.simplerpm.artifact_to_include = ClassLoader.getSystemClassLoader().getResource("test.ear").toURI().getPath()
            project.simplerpm.spec_file = ClassLoader.getSystemClassLoader().getResource("test.spec").toURI().getPath()
        
        when:
            String  art = project.tasks.rpm.makerpm()
            
        then:  
            //need something more assertable
            1 == 1 
    }    
    
    
    def "test get rpm path default case"()
    {
        given: 
            Project project = new ProjectBuilder().withName('test-rpm').build()
            project.apply plugin: 'simplerpm'                
            project.version = '3.9.4'            
        when:
            String  art = project.tasks.rpm.rpmName
            
        then:  
            "test-rpm-3.9.4.rpm" == art
    }        
    
    def "test get rpm path override case"() {
        given: 
            Project project = new ProjectBuilder().withName('test-rpm').build()
            project.version = '3.9.4'
            project.apply plugin: 'simplerpm'                
        when:
            project.tasks.rpm.rpmName = "hector.rpm"
            String  art = project.tasks.rpm.rpmName
            
        then:  
            "hector.rpm" == art
            //"test-rpm-3.9.4.rpm" == art
    }            
}