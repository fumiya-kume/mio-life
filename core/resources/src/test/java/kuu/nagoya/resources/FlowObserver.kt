package kuu.nagoya.resources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class FlowObserver<T>(val flow: Flow<T>) {
    var values: List<T> = emptyList()

    init {
        runBlocking {
            flow.collect {
                values = values.toMutableList().apply {
                    this.add(it)
                }
            }
        }
    }
}