package com.reyhanabbywahyu.medinet2.DBHelper

import android.app.Person
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.reyhanabbywahyu.medinet2.`class`.User


val DATABASE_VER = 1
val DATABASE_NAME = "cek2.db"

//table structure
val TABLE_NAME = "user"
val COL_ID ="Id"
val COL_NAMA = "Nama"
val COL_EMAIL = "Email"
val COL_PASSWORD= "Password"
val COL_BALANCE ="Balance"
val COL_ALAMAT = "Alamat"
val COL_TGLAHIR = "TglLahir"
class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VER){
    override fun onCreate(db: SQLiteDatabase?) {

        val Create_Table : String ="CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COL_NAMA VARCHAR(50) NOT NULL, $COL_EMAIL VARCHAR(50) NOT NULL, $COL_ALAMAT TEXT ," +
                "$COL_PASSWORD VARCHAR(30) NOT NULL, $COL_BALANCE INTEGER NOT NULL, $COL_TGLAHIR TEXT)"
        db!!.execSQL(Create_Table)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
//Get
    fun getAllData() : List<User> {
        val semuaUser = ArrayList<User>()
        val query = "SELECT * FROM $TABLE_NAME"
        val db : SQLiteDatabase = this.writableDatabase
        val cursor : Cursor = db.rawQuery(query,null)
            if (cursor.moveToFirst()) {

                do {
                    val person = User()
                        person.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                        person.nama = cursor.getString(cursor.getColumnIndex(COL_NAMA))
                        person.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                        person.balance = cursor.getFloat(cursor.getColumnIndex(COL_BALANCE))
                        person.password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
                        person.alamat = cursor.getString(cursor.getColumnIndex(COL_ALAMAT))

                    semuaUser.add(person)
                }while(cursor.moveToNext())
            }
        db.close()
        return semuaUser
    }
    //Insert data

    fun insert_data(person : User) {
        val db: SQLiteDatabase =this.writableDatabase
        val values = ContentValues()
        values.put(COL_BALANCE,person.balance)
        values.put(COL_NAMA,person.nama)
        values.put(COL_EMAIL,person.email)
        values.put(COL_PASSWORD,person.password)
        values.put(COL_TGLAHIR,person.tglLahir)
        values.put(COL_ALAMAT, "Blank")
        values.put(COL_BALANCE,0.0f)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun getDataBasedEmail(email : String)  : User{
        val db : SQLiteDatabase = this.readableDatabase
        var Query  : String = "SELECT * FROM $TABLE_NAME WHERE Email=$email"
        val cursor : Cursor = db.rawQuery(Query,null)
        var orangyangDicari : User = User()
        if(cursor.moveToFirst()) {
            do {
                orangyangDicari.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                orangyangDicari.balance = cursor.getFloat(cursor.getColumnIndex(COL_BALANCE))
                orangyangDicari.password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
                orangyangDicari.nama = cursor.getString(cursor.getColumnIndex(COL_NAMA))
                orangyangDicari.alamat = cursor.getString(cursor.getColumnIndex(COL_ALAMAT))
                orangyangDicari.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
            }while(cursor.moveToNext())
        }

        db.close()
        return orangyangDicari
    }

}