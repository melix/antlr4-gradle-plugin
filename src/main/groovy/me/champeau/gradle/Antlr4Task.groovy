/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.champeau.gradle

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class Antlr4Task extends JavaExec {
    @InputDirectory
    @Optional
    File source = project.file('src/main/antlr4')

    @OutputDirectory
    @Optional
    File output = project.file("${project.buildDir}/generated-src")

    @Input
    @Optional
    boolean listener = true

    @Input
    @Optional
    boolean visitor = true

    @Input
    @Optional
    List extraArgs

    Antlr4Task() {
    	main = 'org.antlr.v4.Tool'
        setClasspath(project.configurations.antlr4)
    }

    @TaskAction
    void exec() {
        args = antlr4Args()

        super.exec()
    }

    private List antlr4Args() {
        def args = ['-o', output]
        args << (listener ? '-listener' : '-no-listener')
        args << (visitor ? '-visitor' : '-no-visitor')
        if (extraArgs) {
            args.addAll(extraArgs)
        }
        args.addAll(project.fileTree(dir: source, include: '**/*.g4').collect { it.path })

        args
    }
}
