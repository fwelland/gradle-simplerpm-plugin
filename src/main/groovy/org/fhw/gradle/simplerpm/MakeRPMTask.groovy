package org.fhw.gradle.simplerpm 

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.DefaultTask
import java.nio.file.Path
import org.apache.tools.ant.taskdefs.condition.Os


class MakeRPMTask extends BaseTask {
    	        
    @TaskAction
    def makerpm() {

        logger.info('make rpm here')
        def base = File('build/tmp')
        base.mkdirs()
        new File(base,'BUILD').mkdir()
        new File(base,'BUILDROOT').mkdir()
        new File(base,'RPMS').mkdir()
        new File(base,'SRPMS').mkdir()
        def targsrcdir = new File(base,'SOURCES')
        targsrcdir.mkdir()        
        def targspecdir = new File(base,'SPECS')
        targsepecdir.mkdir()                        
        Path src = Paths.get(getArtifactPath())
        Files.copy(src,targsrcdir)
        src = Paths.get()
        
    }
}



//task clean {
//  delete 'BUILD'
//  delete 'BUILDROOT'
//  delete 'RPMS'
//  delete 'SRPMS'
//  delete 'tmp'
//  delete 'SOURCES'
//}
//
//task copySources(type: Copy, dependsOn: ':main:ear:ear') {
//    from tasks.getByPath(':main:ear:ear').archivePath
//    into "SOURCES"
//}
//
//task build(type: Exec, dependsOn: copySources) {//  commandLine '/usr/bin/rpmbuild',
//    '--define', "_topdir ${project.projectDir}",
//    '--define', "VERSION ${project.version}",
//    '--define', '_tmppath  %{_topdir}/tmp',
//    '-bb', 'SPECS/rpm.spec'
//}


