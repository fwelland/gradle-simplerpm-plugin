package org.fhw.gradle.simplerpm
import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class PluginTest {
    
    @Test
    public void addPluginTest()
    {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'simplerpm'
        assertTrue(project.tasks.rpm instanceof MakeRPMTask)
    }
	
}

