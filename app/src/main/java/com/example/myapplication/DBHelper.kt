package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(ctx: Context): SQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {
    companion object{
        private val DB_VERSION = 1
        private val DB_NAME = "examen"
    }

    val TABLA_EMPLEADO: String = "empleados"
    val COLUMN_ID: String = "_Id"
    val COLUMN_NUM: String = "numEmpleado"
    val COLUMN_NAME: String = "nombre"
    val COLUMN_BIRTH: String = "fechaNac"
    val COLUMN_ADDRESS: String = "direccion"
    val COLUMN_PIC: String = "fotografia"

    override fun onCreate(db: SQLiteDatabase?) {
        tablaEmpleado(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLA_EMPLEADO; ")
    }

    private fun tablaEmpleado(db: SQLiteDatabase?) {
        val query = "CREATE TABLE IF NOT EXISTS $TABLA_EMPLEADO (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NUM VARCHAR, " +
                "$COLUMN_NAME VARCHAR, " +
                "$COLUMN_BIRTH VARCHAR, " +
                "$COLUMN_ADDRESS VARCHAR, " +
                "$COLUMN_PIC VARCHAR" +
                ");"
        db!!.execSQL(query)
    }
}