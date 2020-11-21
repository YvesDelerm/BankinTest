package fr.ydelerm.bankintest

import android.app.Application
import fr.ydelerm.bankintest.di.DaggerApplicationGraph

class BankinApplication : Application() {
    lateinit var appGraph: DaggerApplicationGraph

    override fun onCreate() {
        super.onCreate()
        appGraph =
            DaggerApplicationGraph.builder()
                .application(this)
                .build() as DaggerApplicationGraph
    }
}