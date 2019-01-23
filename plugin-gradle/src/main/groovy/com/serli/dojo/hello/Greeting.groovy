package com.serli.dojo.hello

import com.github.lalyos.jfiglet.FigletFont
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

/**
 * https://docs.gradle.org/4.10/userguide/custom_plugins.html
 * see : https://docs.gradle.org/current/userguide/lazy_configuration.html#sec:lazy_configuration_reference
 */
class Greeting extends DefaultTask {
    @Input
    final Property<String> greeting = project.objects.property(String)
    @Input
    final Property<String> recipient = project.objects.property(String)

    // Read-only property calculated from the greeting
    @Internal
    final Provider<String> message = greeting.map { it + ' from Gradle' }

    @TaskAction
    void sayGreeting() {
        println(FigletFont.convertOneLine("${message.get()}, ${recipient.get()}!"))
    }
}
