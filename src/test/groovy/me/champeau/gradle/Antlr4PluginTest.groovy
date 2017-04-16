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

    @Test
    void defaultDependency() {
        Project project = ProjectBuilder.builder().build()
        project.with {
            apply plugin: 'antlr4'
        }
        project.evaluate()

        def antlr4config = project.getConfigurations().getByName('antlr4')
        def antlr4dependencies = antlr4config.getDependencies().toArray()
        assert antlr4dependencies.size() == 1
        assert antlr4dependencies[0].getGroup() == 'org.antlr'
        assert antlr4dependencies[0].getName() == 'antlr4'
        assert antlr4dependencies[0].getVersion() == '4.2.2'
    }

    @Test
    void usersDependency() {
        Project project = ProjectBuilder.builder().build()
        project.with {
            apply plugin: 'antlr4'
            dependencies {
                antlr4 'org.antlr:antlr4:4.2.3-dev'
            }
        }
        project.evaluate()

        def antlr4config = project.getConfigurations().getByName('antlr4')
        def antlr4dependencies = antlr4config.getDependencies().toArray()
        assert antlr4dependencies.size() == 1
        assert antlr4dependencies[0].getGroup() == 'org.antlr'
        assert antlr4dependencies[0].getName() == 'antlr4'
        assert antlr4dependencies[0].getVersion() == '4.2.3-dev'
    }
}
