package org.fhw.gradle.simplerpm 

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskExecutionException
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files  
import java.nio.file.StandardCopyOption
import java.io.File
import org.apache.tools.ant.taskdefs.condition.Os


class MakeRPMTask extends BaseTask {
    
    def String rpmName = "${->project.name}-${->project.version}.rpm"         
    
                       
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
        if (getArtifactPath()) {
            Path artifactSrc = Paths.get(getArtifactPath())     
            Files.copy(artifactSrc,targ_src_dir.resolve(artifactSrc.getFileName()),StandardCopyOption.REPLACE_EXISTING)
        }
        Path specSrc = Paths.get(getSpecFilePath())
        Files.copy(specSrc,targ_spec_dir.resolve(specSrc.getFileName()),StandardCopyOption.REPLACE_EXISTING)      
        
        def cmd = [ 'rpmbuild',
            '--define', 
            "_build_name_fmt ${->rpmName}",
            '--define',
            "_topdir ${project.buildDir}/tmp",
            '--define',
            "VERSION ${project.version}",
            '--define',
            "_tmppath  %{_topdir}/tmp",
            getRpmbbuildMacroArgs(),
            '-bb',
            "${targ_spec_dir}/${specSrc.fileName}"
        ].flatten()

        if(!execute(*cmd)) {
            throw new TaskExecutionException(this,new Exception('rpmbuild failed'))
        }
    }           
}
