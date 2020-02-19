package com.ninovitale.employeecards.api

import com.ninovitale.employeecards.api.model.Gist
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GistsApi {
    @Headers("Accept: application/json")
    @GET("gists/{gist_id}")
    fun getGist(@Path("gist_id") gistId: String): Single<Gist>
}