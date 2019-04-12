package com.ezmobdev.furatest.repository

import android.location.Location
import io.reactivex.Observable

interface ILocalRepo{
    fun getLocation(): Observable<Location>
}