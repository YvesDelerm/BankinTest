package fr.ydelerm.bankintest.api

import fr.ydelerm.bankintest.model.ResourceResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BankinApi {
    @GET("categories.json")
    fun getUsers(): Single<ResourceResult>
}