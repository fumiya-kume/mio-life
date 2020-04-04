package kuu.nagoya.resources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking


class FlowObserver<T>(val flow: Flow<T>) {

    companion object {
        fun <T> of(flow: Flow<T>): FlowObserver<T> {
            return FlowObserver(flow)
        }
    }

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