/*
 * Copyright 2019 the original author or authors.
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

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith


@RunWith(JUnitQuickcheck::class)
class ReproduciblePropertiesWriterProperties {

    @Rule
    @JvmField
    val temporaryFolder = TemporaryFolder()

    @Property
    fun `entry order does not matter`(entries: List<String>) {
        assertEquals(
            storedTextFor(entries),
            storedTextFor(entries.asReversed())
        )
    }

    private
    fun storedTextFor(entries: List<String>): String =
        temporaryFolder.newFile().also { tempFile ->
            ReproduciblePropertiesWriter.store(
                entries.map { it to it }.toMap(),
                tempFile
            )
        }.readText(Charsets.ISO_8859_1)
}
