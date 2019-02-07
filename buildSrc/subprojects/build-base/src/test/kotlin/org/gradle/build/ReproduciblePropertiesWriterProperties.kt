package org.gradle.build

import com.pholser.junit.quickcheck.Property
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck

import org.junit.Assert.assertEquals
import org.junit.runner.RunWith


@RunWith(JUnitQuickcheck::class)
class ReproduciblePropertiesWriterProperties {

    @Property
    fun `entry order does not matter`(entries: List<String>) {
        assertEquals(
            ReproduciblePropertiesWriter.store(
                entries.map { it to it }.toMap()
            ),
            ReproduciblePropertiesWriter.store(
                entries.asReversed().map { it to it }.toMap()
            )
        )
    }
}
