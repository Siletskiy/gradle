/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.build

import java.io.File
import java.io.StringWriter
import java.nio.charset.Charset

import java.util.*


object ReproduciblePropertiesWriter {

    private
    const val lineSeparator = "\n" // Use LF to have the same result on Windows and on Linux

    private
    val charset = Charset.forName("8859_1")

    /**
     * Writes [Map] of [data] as [Properties] to a [file] with the given optional [comment],
     * but without including the timestamp comment.
     */
    @JvmStatic
    @JvmOverloads
    fun store(data: Map<String, *>, file: File, comment: String? = null) {
        store(propertiesFrom(data), file, comment)
    }

    /**
     * Writes [Properties] to a [file] with the given optional [comment],
     * but without including the timestamp comment.
     */
    @JvmStatic
    @JvmOverloads
    fun store(properties: Properties, file: File, comment: String? = null) {
        val content = store(properties)
        writeTo(file, content, comment)
    }

    private
    fun writeTo(file: File, content: String, comment: String?) {
        file.parentFile.mkdirs()
        file.bufferedWriter(charset).use { writer ->
            comment?.let { comment ->
                writer.write("# $comment")
                writer.write(lineSeparator)
            }
            writer.write(content)
        }
    }

    fun store(data: Map<String, *>) = store(propertiesFrom(data))

    fun store(properties: Properties): String =
        toString(properties)
            .lineSequence()
            .filterNot { it.startsWith("#") || it.isBlank() }
            .sorted()
            .joinToString(lineSeparator)

    private
    fun toString(properties: Properties): String =
        StringWriter().also {
            properties.store(it, null)
        }.toString()

    private
    fun propertiesFrom(data: Map<String, Any?>): Properties =
        Properties().apply {
            data.forEach { key, value ->
                put(key, value ?: value.toString())
            }
        }
}
