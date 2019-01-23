package com.serli.dojo.hello

import com.github.lalyos.jfiglet.FigletFont
import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        // Add the 'greeting' extension object
        def extension = project.extensions.create('greeting', GreetingPluginExtension)
        // Add a task that uses configuration from the extension object

        project.tasks.register("hello") {
            group = "Dojo"
            description = "Produces a greeting"

            doLast {
                println(FigletFont.convertOneLine(extension.message))
            }
        }
    }
}

class GreetingPluginExtension {
    String message = 'Hello from GreetingPlugin'
}
