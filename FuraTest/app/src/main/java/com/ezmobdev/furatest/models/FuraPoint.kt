package com.ezmobdev.furatest.models


data class FuraResponse(val pageSize:Int, val data: ArrayList<FuraPoint>)
data class FuraPoint(val id: Int, val name:String, val lat:Float, val lng:Float, val categoryId: Int)