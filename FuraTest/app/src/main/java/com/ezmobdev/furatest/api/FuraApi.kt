package com.ezmobdev.furatest.api

import com.ezmobdev.furatest.models.FuraResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FuraApi {
    @GET("/api/test/places")
    fun getPoints(): Observable<FuraResponse>
}