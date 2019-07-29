package com.made.movietv.view

import com.made.movietv.model.ModelTv

interface TVView {
    fun showDialog()
    fun hideDialog()
    fun addData(arrayItem: ArrayList<ModelTv>)
}