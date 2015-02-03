package org.fhw.gradle.simplerpm 

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.DefaultTask
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files  
import java.nio.file.StandardCopyOption
import java.io.File
import org.apache.tools.ant.taskdefs.condition.Os


class MakeRPMTask extends BaseTask {
    	        
    @TaskAction
    def makerpm() {
        File base = new File("${project.buildDir}/tmp")
        base.mkdirs()
        new File(base,'BUILD').mkdir()
        new File(base,'BUILDROOT').mkdir()
        new File(base,'RPMS').mkdir()
        new File(base,'SRPMS').mkdir()
        new File(base,'SOURCES').mkdir()        
        new File(base,'SPECS').mkdir()
        
        Path targ_src_dir = Paths.get(base.absolutePath,'SOURCES') 
        Path targ_spec_dir = Paths.get(base.absolutePath,'SPECS') 
        Path src = Paths.get(getArtifactPath())     
        Files.copy(src,targ_src_dir.resolve(src.getFileName()),StandardCopyOption.REPLACE_EXISTING)
        src = Paths.get(getSpecFilePath())
        Files.copy(src,targ_spec_dir.resolve(src.getFileName()),StandardCopyOption.REPLACE_EXISTING)
        
        execute(    'rpmbuild',
                    '--define', 
                    "_topdir ${project.buildDir}/tmp",
                    '--define',
                    "VERSION ${project.version}",
                    '--define',
                    "_tmppath  %{_topdir}/tmp",
                    '-bb',
                    "${targ_spec_dir}/${src.fileName}")
    }
}



//task build(type: Exec, dependsOn: copySources) {
//  commandLine '/usr/bin/rpmbuild',
//    '--define', "_topdir ${project.projectDir}",
//    '--define', "VERSION ${project.version}",
//    '--define', '_tmppath  %{_topdir}/tmp',
//    '-bb', 'SPECS/rpm.spec'
//}