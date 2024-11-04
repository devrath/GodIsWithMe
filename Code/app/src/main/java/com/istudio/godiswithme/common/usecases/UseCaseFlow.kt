package com.istudio.godiswithme.common.usecases

import kotlinx.coroutines.flow.Flow

fun interface UseCaseFlow<T> {
    operator fun invoke(): Flow<T>
}

fun interface UseCaseFlowWithParam<P, T> {
    operator fun invoke(param: P): Flow<T>
}

fun interface UseCaseSuspendFlowWithParam<P, T> {
    suspend operator fun invoke(param: P): Flow<T>
}