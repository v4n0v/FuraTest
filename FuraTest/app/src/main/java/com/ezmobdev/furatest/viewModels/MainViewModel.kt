package com.ezmobdev.furatest.viewModels

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.ezmobdev.furatest.models.FuraResponse
import com.ezmobdev.furatest.repository.IRepo
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var furaRepo: IRepo

    val furaPointersData: MutableLiveData<FuraResponse> by lazy { MutableLiveData<FuraResponse>() }
    val isLoading = ObservableField(true)

    @SuppressLint("CheckResult")
    fun loadData() {
        isLoading.set(true)
        furaRepo.getFuraResponse().subscribe {
            furaPointersData.value = it
            isLoading.set(false)
        }
    }

}