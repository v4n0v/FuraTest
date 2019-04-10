package com.ezmobdev.furatest.viewModels

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.ezmobdev.furatest.models.FuraPoint
import com.ezmobdev.furatest.repository.IRepo
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var furaRepo: IRepo

    val furaPointersData: MutableLiveData<ArrayList<FuraPoint>> by lazy { MutableLiveData<ArrayList<FuraPoint>>() }

    val isLoading = ObservableField(true)

    @SuppressLint("CheckResult")
    fun loadData() {
        isLoading.set(true)
        furaRepo.getFuraResponse().subscribe {
            furaPointersData.value = it.data
            isLoading.set(false)
        }
    }

}