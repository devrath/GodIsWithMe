package com.istudio.godiswithme.common.usecases

interface ResultUseCase<T> {
    suspend operator fun invoke(): Result<T>
}

interface ResultUseCaseWithParam<P, T> {
    suspend operator fun invoke(param: P): Result<T>
}