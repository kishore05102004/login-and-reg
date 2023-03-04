package com.example.app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Data(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase) {
        val query = "CREATE TABLE $TABLE_NAME ($USERNAME_COL TEXT, $PASSWORD_COL TEXT, $EMAIL_COL TEXT)"
        p0.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun register(username: String, password: String, email: String) {
        val cv = ContentValues()
        cv.put(USERNAME_COL, username)
        cv.put(PASSWORD_COL, password)
        cv.put(EMAIL_COL, email)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, cv)
        db.close()
    }

    fun login(username: String, password: String) :Int {
        var result = 0
        val str : Array<String> = arrayOf(username, password)
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $USERNAME_COL =? AND $PASSWORD_COL =?", str)
        if(cursor.moveToFirst()) {
            cursor.close()
            result = 1
        }
        cursor.close()
        return result
    }
    companion object {
        const val TABLE_NAME = "users"
        const val USERNAME_COL = "username"
        const val PASSWORD_COL = "password"
        const val EMAIL_COL = "email"

    }
}