package com.wing.tree.bruni.core.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameter: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                Result.Success(execute(parameter))
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }

    protected abstract suspend fun execute(parameter: P): R
}
