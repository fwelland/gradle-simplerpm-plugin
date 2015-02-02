package org.fhw.gradle.simplerpm 

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.DefaultTask
import org.apache.tools.ant.taskdefs.condition.Os


class MakeRPMTask extends BaseTask {
    	        
    @TaskAction
    def makerpm() {

        logger.info('make rpm here')

    }
}


