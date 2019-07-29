package com.made.movietv.view

import com.made.movietv.model.ModelMovie

interface MovieView {

    fun showDialog()
    fun hideDialog()
    fun addData(arrayItem: ArrayList<ModelMovie>)
}