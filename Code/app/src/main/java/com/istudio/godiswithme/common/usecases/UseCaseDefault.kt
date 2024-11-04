package com.istudio.godiswithme.common.usecases

interface UseCaseDefault<T> {
    operator fun invoke(): T
}

interface UseCaseDefaultWithParam<P, T> {
    operator fun invoke(param: P): T
}