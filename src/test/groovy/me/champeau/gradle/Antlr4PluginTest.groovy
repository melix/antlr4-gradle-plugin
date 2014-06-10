package me.champeau.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class Antlr4PluginTest {
    @Test
    void pluginIsApplied() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'me.champeau.gradle.antlr4'


        def task = project.tasks.findByName('antlr4')
        assert task instanceof Antlr4Task
        assert task.source == project.file("src/main/antlr4")
        assert task.output == project.file("$project.buildDir/generated-src")
    }
}