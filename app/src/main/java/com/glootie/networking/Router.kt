package com.glootie.networking

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Singleton

@Singleton
class Router {

    private val route = PublishSubject.create<Screen>()

    val screen: Observable<Screen> = route

    fun routeTo(screen: Screen){
        route.onNext(screen)
    }
}