package com.example.idle_rpg_project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "IdleRPGDataBase"
const val DATABASE_VERSION = 1
const val TABLE_NAME = "User"
const val COL_ID = "id"
const val COL_USERNAME = "username"
const val COL_NAME = "name"
const val COL_LASTNAME = "lastName"
const val COL_PASSWORD = "password"
const val COL_EMAIL = "email"
const val COL_URLIMAGE = "urlImage"
const val COL_IDGUILD = "idGuild"

class DataBaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERNAME + " VARCHAR(256)," +
                COL_NAME + " VARCHAR(256)," +
                COL_LASTNAME + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256)," +
                COL_EMAIL + " VARCHAR(256)," +
                COL_URLIMAGE + " VARCHAR(256)," +
                COL_IDGUILD + " INTEGER)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(user: User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERNAME, user.username)
        cv.put(COL_NAME, user.name)
        cv.put(COL_LASTNAME, user.lastName)
        cv.put(COL_EMAIL, user.email)
        cv.put(COL_PASSWORD, user.password)
        cv.put(COL_URLIMAGE, user.urlImage)
        cv.put(COL_IDGUILD, user.idGuild)
        var result = db.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    fun readData() : MutableList<User>{
        var list : MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.username = result.getString(result.getColumnIndex(COL_USERNAME))
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.lastName = result.getString(result.getColumnIndex(COL_LASTNAME))
                user.email = result.getString(result.getColumnIndex(COL_EMAIL))
                user.password = result.getString(result.getColumnIndex(COL_PASSWORD))
                user.urlImage = result.getString(result.getColumnIndex(COL_URLIMAGE))
                user.idGuild = result.getString(result.getColumnIndex(COL_IDGUILD)).toInt()
                list.add(user)
            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
}