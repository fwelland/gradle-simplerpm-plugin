package org.fhw.gradle.simplerpm

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.DefaultTask
import org.apache.tools.ant.taskdefs.condition.Os


class BaseTask extends DefaultTask {

    
    def getRpmVersion()
    {
        return project.simplerpm.rpm_version
    }    
    
    def getArtifactPath()
    {
        return project.simplerpm.artifact_to_include
    }

    def getSpecFilePath()
    {
        return project.simplerpm.spec_file
    }
    
    def execute(String ... commands){
        def cmds = []

        for(String s : commands)
        {           
            cmds.add(s)
        }
        ProcessBuilder builder = new ProcessBuilder( cmds )                                               
        builder.redirectErrorStream(true)
        Process process = builder.start()                
        InputStream stdout = process.getInputStream()
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout)) 
        def line
        while ((line = reader.readLine()) != null) 
        {
            logger.info  line
        }       
        return( process.waitFor() == 0)                 
    }    

    def getRpmbbuildMacroArgs() {
        def rpm_macro_map = project.simplerpm.rpm_macro_map
        if (!rpm_macro_map) { return [] }
        rpm_macro_map.collectMany { ['--define', "${it.key} ${it.value}"] }
    }

}
