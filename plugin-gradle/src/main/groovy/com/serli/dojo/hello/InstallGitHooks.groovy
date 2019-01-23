package com.serli.dojo.hello

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class InstallGitHooks extends DefaultTask {

    @Input
    Iterable<String> hookNames = [
            "applypatch-msg",
            "commit-msg",
            "post-applypatch",
            "post-checkout",
            "post-commit",
            "post-merge",
            "post-receive",
            "post-rewrite",
            "post-update",
            "pre-applypatch",
            "pre-auto-gc",
            "pre-commit",
            "pre-push",
            "pre-rebase",
            "pre-receive",
            "prepare-commit-msg",
            "push-to-checkout",
            "sendemail-validate",
            "update"
    ]

    @OutputDirectory
    File hookDir = project.file("${project.projectDir}/.git/hooks")

    @TaskAction
    void install() {
        def gitDir = project.file("${project.projectDir}/.git")
        if (!gitDir.exists()) {
            throw new GradleException("No git directory found at ${gitDir.absolutePath}.")
        }

        if (!hookDir.exists()) {
            project.logger.info("No hook directory found at ${hookDir.absolutePath}. A directory will be created ...")
            hookDir.mkdir()
        }

        def hooks = hookNames.collect { new File(hookDir, it) }

        hooks.forEach {
            it.text = '''#!/bin/sh

hookName=`basename "\$0"`
gitParams="\$*"

./gradlew $hookName -Pparams="$gitParams"
    '''
        }

    }
}
