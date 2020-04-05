package kuu.nagoya.data.api

import org.koin.dsl.module

val apiModule = module {
    factory { IijMioAPiClientImpl(get()) as IijMioAPiClient }
}