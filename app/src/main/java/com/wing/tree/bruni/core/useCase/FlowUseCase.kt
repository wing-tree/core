package com.wing.tree.bruni.core.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameter: P): Flow<Result<R>> = execute(parameter)
        .map <R, Result<R>>{ Result.Success(it) }
        .catch { cause: Throwable -> emit(Result.Failure(cause)) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameter: P): Flow<R>
}
