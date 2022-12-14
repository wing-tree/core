package com.wing.tree.bruni.core.useCase

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultCoroutineDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IOCoroutineDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainCoroutineDispatcher
