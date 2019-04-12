package com.ezmobdev.furatest.viewModels

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.ezmobdev.furatest.App
import com.ezmobdev.furatest.repository.LocationRepository
import com.ezmobdev.furatest.models.FuraResponse
import com.ezmobdev.furatest.repository.ILocalRepo
import com.ezmobdev.furatest.repository.IPointsRepo
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var furaRepo: IPointsRepo

    @Inject
    lateinit var localRepo: ILocalRepo

    val myLocData: MutableLiveData<LatLng> by lazy { MutableLiveData<LatLng>() }
    val furaPointersData: MutableLiveData<FuraResponse> by lazy { MutableLiveData<FuraResponse>() }
    val isLoading = ObservableField(false)

    @SuppressLint("CheckResult")
    fun loadData() {
        isLoading.set(true)
        furaRepo.getFuraResponse().subscribe {
            furaPointersData.value = it
            isLoading.set(false)
        }
    }

    @SuppressLint("CheckResult")
    fun loadLocation() {
        localRepo.getLocation()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                myLocData.value = LatLng(it.latitude, it.longitude)
            }

    }

}