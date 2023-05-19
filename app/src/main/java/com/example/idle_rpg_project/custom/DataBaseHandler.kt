package com.example.idle_rpg_project.custom

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.idle_rpg_project.models.Usuario

const val DATABASE_NAME = "IdleRPGDataBase"
const val DATABASE_VERSION = 1
const val TABLE_NAME = "User"
const val COL_ID = "id"
const val COL_USERNAME = "username"
const val COL_NOMBRE = "nombre"
const val COL_APELLIDOS = "apellidos"
const val COL_CORREO = "correo"
const val COL_CONTRASENA = "contrasena"
const val COL_URLIMAGE = "urlImage"
const val COL_IDGREMIO = "idGremio"
const val COL_ESPERA = "espera"
const val COL_LIDER = "lider"


class DataBaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY," +
                COL_USERNAME + " VARCHAR(256)," +
                COL_NOMBRE + " VARCHAR(256)," +
                COL_APELLIDOS + " VARCHAR(256)," +
                COL_CORREO + " VARCHAR(256)," +
                COL_URLIMAGE + " VARCHAR(256)," +
                COL_IDGREMIO + " INTEGER," +
                COL_ESPERA + " BIT," +
                COL_LIDER + " BIT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(user: Usuario){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERNAME, user.username)
        cv.put(COL_NOMBRE, user.nombre)
        cv.put(COL_APELLIDOS, user.apellidos)
        cv.put(COL_CORREO, user.correo)
        cv.put(COL_URLIMAGE, user.urlImagen)
        cv.put(COL_IDGREMIO, user.idGremio)
        cv.put(COL_ESPERA, user.espera)
        cv.put(COL_LIDER, user.lider)
        var result = db.insert(TABLE_NAME, null, cv)
//        if(result == (-1).toLong())
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    fun readData() : MutableList<Usuario>{
        var list : MutableList<Usuario> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var user = Usuario(
                   id = result.getString(result.getColumnIndex(COL_ID)).toInt(),
                   username = result.getString(result.getColumnIndex(COL_USERNAME)),
                   nombre = result.getString(result.getColumnIndex(COL_NOMBRE)),
                   apellidos = result.getString(result.getColumnIndex(COL_APELLIDOS)),
                   correo = result.getString(result.getColumnIndex(COL_CORREO)),
                   urlImagen = result.getString(result.getColumnIndex(COL_URLIMAGE)),
                   idGremio = result.getString(result.getColumnIndex(COL_IDGREMIO)).toInt(),
                   espera = result.getString(result.getColumnIndex(COL_ESPERA)).toBoolean(),
                   lider = result.getString(result.getColumnIndex(COL_LIDER)).toBoolean()
                )
                list.add(user)
            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
}