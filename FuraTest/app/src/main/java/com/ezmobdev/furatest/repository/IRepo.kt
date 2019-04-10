package com.ezmobdev.furatest.repository

import com.ezmobdev.furatest.models.FuraResponse
import io.reactivex.Observable

interface IRepo{
    fun getFuraResponse(): Observable<FuraResponse>
}