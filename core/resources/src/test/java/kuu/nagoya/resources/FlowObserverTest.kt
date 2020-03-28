package kuu.nagoya.resources

import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

internal class FlowObserverTest {
    @Test
    fun `正常系`() {
        listOf(
            listOf(1),
            listOf(0, 1, 2, 3),
            listOf("one", "two", "three")
        ).forEach { argument ->
            val flow = flow {
                argument.forEach { this.emit(it) }
            }

            val flowObserver = FlowObserver(flow)

            Assert.assertEquals(
                argument,
                flowObserver.values
            )

            Assert.assertEquals(
                argument.size,
                flowObserver.values.size
            )
        }
    }
}