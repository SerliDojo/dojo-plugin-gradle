package com.serli.dojo.hello

import com.github.lalyos.jfiglet.FigletFont
import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.register("hello") {
            group = "Dojo"
            description = "Produces a greeting"

            doLast {
                println(FigletFont.convertOneLine("Hello Serli Dojo"))
            }
        }
    }
}

