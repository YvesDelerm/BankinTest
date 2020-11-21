package fr.ydelerm.bankintest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.ydelerm.bankintest.BankinApplication
import fr.ydelerm.bankintest.R
import fr.ydelerm.bankintest.api.BankinApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var bankinApi: BankinApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as BankinApplication).appGraph.inject(this)
        setContentView(R.layout.activity_main)

        bankinApi.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { t1, t2 -> textView.text = "Found ${t1.resources.size}" }
    }
}