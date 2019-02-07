package com.serli.dojo.hook

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitHookPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        project.tasks.register("installGitHooks", InstallGitHooks)

        [
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
        ].forEach {
            project.tasks.create it
        }
    }

}
