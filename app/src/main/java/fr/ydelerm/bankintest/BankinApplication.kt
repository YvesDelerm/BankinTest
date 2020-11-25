package fr.ydelerm.bankintest

import android.app.Application
import fr.ydelerm.bankintest.di.DaggerApplicationGraph
import fr.ydelerm.bankintest.repository.Repository
import javax.inject.Inject

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