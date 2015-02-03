package org.fhw.gradle.simplerpm 

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.DefaultTask
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files
import java.io.File
import org.apache.tools.ant.taskdefs.condition.Os


class MakeRPMTask extends BaseTask {
    	        
    @TaskAction
    def makerpm() {

        logger.info('make rpm here')
        def base = new File("${project.buildDir}/tmp")
        base.mkdirs()
        new File(base,'BUILD').mkdir()
        new File(base,'BUILDROOT').mkdir()
        new File(base,'RPMS').mkdir()
        new File(base,'SRPMS').mkdir()
        def targsrcdir = new File(base,'SOURCES')
        targsrcdir.mkdir()        
        def targspecdir = new File(base,'SPECS')
println(targspecdir)        
        targspecdir.mkdir()                        
        Path src = Paths.get(getArtifactPath())
        Files.copy(src,Paths.get(targsrcdir.absolutePath))
        src = Paths.get(getSpecFilePath())
        Files.copy(src,targspecdir)

        execute(    'rpmbuild',
                    '--define', 
                    "_topdir ${project.buildDir}/tmp",
                    '--define',
                    "VERSION ${project.version}",
                    '--define',
                    '_tmppath  %{_topdir}/tmp',
                    '-bb',
                    "SPECS/${src.fileName}")
    }
}
