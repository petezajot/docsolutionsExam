package com.example.myapplication.interfaces

import android.content.Context
import org.json.JSONObject

interface MainIInterface {
    fun storeData(json: JSONObject, ctx: Context)
}