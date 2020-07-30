package com.example.myapplication.interfaces

import android.content.Context
import org.json.JSONObject

interface MainPInterface {
    fun saveData(i: Int)
    fun storeData(json: JSONObject, context: Context)
}