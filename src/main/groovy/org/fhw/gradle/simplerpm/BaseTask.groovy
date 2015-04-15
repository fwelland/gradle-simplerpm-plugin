package org.fhw.gradle.simplerpm

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.apache.tools.ant.taskdefs.condition.Os


class BaseTask extends DefaultTask {

    def getRpmVersion()
    {
        if( ! project.simplerpm.rpmVersion )
        {
            project.simplerpm.rpmVersion = project.version
        }
        return project.simplerpm.rpmVersion
    }

    def getRpmBaseName()
    {
        return project.simplerpm.rpmBaseName
    }

    def getRpmRelease()
    {
        return project.simplerpm.rpmRelease
    }

    def getSpecFilePath()
    {
        return project.simplerpm.spec_file
    }

    def getArtifactPath()
    {
        return project.simplerpm.artifact_to_include
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

    def String exec(String ... commands )
    {
        def pb = new ProcessBuilder(commands)
        pb.redirectErrorStream(true)
        Process proc = pb.start()
        def StringBuffer output = new StringBuffer()
        proc.inputStream.eachLine { output.append(it) }
        if(proc.waitFor() != 0)
        {
            throw new GradleException("exec failed for command line " + commands + "; output from command includes [" + output + "]" )
        }
        return( output.toString() )
    }

    def getRpmbbuildMacroArgs() {
        def rpm_macro_map = project.simplerpm.rpm_macro_map
        if (!rpm_macro_map) { return [] }
        rpm_macro_map.collectMany { ['--define', "${it.key} ${it.value}"] }
    }

}
