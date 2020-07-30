package com.example.myapplication.interactor

import android.content.Context
import com.example.myapplication.DBHelper
import com.example.myapplication.interfaces.MainIInterface
import com.example.myapplication.interfaces.MainPInterface
import org.json.JSONObject

class MainInteractor(mainPInterface: MainPInterface): MainIInterface {
    var presenter: MainPInterface = mainPInterface

    override fun storeData(json: JSONObject, ctx: Context) {
        val name = json.getString("nombreEmpleado")
        val num = json.getString("numEmpleado")
        val date = json.getString("fechaNac")
        val address = json.getString("dirEmpleado")
        val pic = json.getString("picture")

        val hlp = DBHelper(ctx)
        val db = hlp.writableDatabase
        val query = "INSERT INTO ${hlp.TABLA_EMPLEADO} (" +
                "${hlp.COLUMN_NAME}, " +
                "${hlp.COLUMN_NUM}, " +
                "${hlp.COLUMN_BIRTH}, " +
                "${hlp.COLUMN_ADDRESS}, " +
                "${hlp.COLUMN_PIC}" +
                ")VALUES(" +
                "'$name', " +
                "'$num', " +
                "'$date', " +
                "'$address', " +
                "'$pic'" +
                ");"
        db.execSQL(query)
        presenter.saveData(1)
    }
}