package com.ezmobdev.furatest.repository

import com.ezmobdev.furatest.api.ApiFactory
import com.ezmobdev.furatest.models.FuraResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FuraRepository(val api:ApiFactory) :IRepo{

    override fun getFuraResponse(): Observable<FuraResponse> {
       return api.getFuraApi().getPoints()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())

    }
}