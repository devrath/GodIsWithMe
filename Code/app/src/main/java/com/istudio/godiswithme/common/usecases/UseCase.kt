package com.istudio.godiswithme.common.usecases

interface UseCase<T> {
    suspend operator fun invoke(): T
}

interface UseCaseWithParam<P, T> {
    suspend operator fun invoke(param: P): T
}