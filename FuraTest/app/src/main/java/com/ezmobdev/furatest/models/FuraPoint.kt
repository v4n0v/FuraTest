package com.ezmobdev.furatest.models


data class FuraResponse(val pageSize: Int, val data: ArrayList<FuraPoint>) {
    fun getLast(): FuraPoint? {
        return data[pageSize-1]
    }
}

data class FuraPoint(val id: Int, val name: String, val lat: Double, val lng: Double, val categoryId: Int)