package com.ezmobdev.furatest.repository

import com.ezmobdev.furatest.models.FuraResponse
import io.reactivex.Observable

interface IPointsRepo{
    fun getFuraResponse(): Observable<FuraResponse>
}