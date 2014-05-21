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

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This plugin is responsible for making sure that the Javadocs you generate using Gradle are not
 * vulnerable to frame injection, as described in http://www.kb.cert.org/vuls/id/225657
 *
 * Applying this plugin will guarantee that all generated javadoc is fixed, as well as groovydoc,
 * independently of the JDK being used to compile the project.
 *
 * @author Julien Ponge
 * @author Cedric Champeau
 */
class Antlr4Plugin implements Plugin<Project> {
    void apply(Project project) {
        project.configurations {
            antlr4
        }
        project.dependencies {
            antlr4 'org.antlr:antlr4:4.2.2'
        }

        project.task('antlr4', type:Antlr4Task)
    }
}
