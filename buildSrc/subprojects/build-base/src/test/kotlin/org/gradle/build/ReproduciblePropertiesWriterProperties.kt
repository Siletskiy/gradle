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
