package com.glootie.networking.base.di

import javax.inject.Provider

class ShortProvider<T: Any> : Provider<T> {

    override fun get(): T {
        TODO("Not yet implemented")
    }
}