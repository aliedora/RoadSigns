package com.example.roadsigns.models

import android.content.res.Resources

class SignModel (val id : Int, val description: String, val isSOS: Boolean){

    override fun toString() = "SignModel($id, \"$description\", $isSOS),"
}