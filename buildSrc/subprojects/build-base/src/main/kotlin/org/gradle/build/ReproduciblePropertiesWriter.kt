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

import org.gradle.internal.util.PropertiesUtils

import java.io.File

import java.util.*


object ReproduciblePropertiesWriter {

    /**
     * Writes [Map] of [data] as [Properties] to a [file] without including the timestamp comment.
     */
    @JvmStatic
    @JvmOverloads
    fun store(data: Map<String, *>, file: File, comment: String? = null) {
        store(propertiesFrom(data), file, comment)
    }

    /**
     * Writes [Properties] to a [file] without including the timestamp comment.
     */
    @JvmStatic
    @JvmOverloads
    fun store(properties: Properties, file: File, comment: String? = null) {
        PropertiesUtils.store(properties, file, comment, Charsets.ISO_8859_1, "\n")
    }

    private
    fun propertiesFrom(data: Map<String, Any?>): Properties =
        Properties().apply {
            data.forEach { key, value ->
                put(key, value ?: value.toString())
            }
        }
}
