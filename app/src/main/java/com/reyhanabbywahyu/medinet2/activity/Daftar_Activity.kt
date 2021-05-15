package com.reyhanabbywahyu.medinet2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import com.reyhanabbywahyu.medinet2.DBHelper.DBHelper
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.action.ResponseAction
import com.reyhanabbywahyu.medinet2.config.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Daftar_Activity : AppCompatActivity() {
    lateinit var btnDaftar : Button
    lateinit var etDaftarEmail : EditText
    lateinit var etDaftarNama : EditText
    lateinit var etDaftarPassword : EditText
    lateinit var etDaftarPasswordKonfirmasi : EditText
    lateinit var etDaftarTanggalLahir : EditText
//    lateinit var database : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        btnDaftar = findViewById(R.id.btnDaftar)
        etDaftarTanggalLahir = findViewById(R.id.etDaftarTanggalLahir)
        etDaftarPasswordKonfirmasi = findViewById(R.id.etDaftarPasswordKonfirmasi)
        etDaftarPassword = findViewById(R.id.etDaftarPassword)
        etDaftarNama = findViewById(R.id.etDaftarNama)
        etDaftarEmail = findViewById(R.id.etDaftarEmail)
//        database = DBHelper(this)
        btnDaftar.setOnClickListener {
            InsertData()
            intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()
            }
        }



    fun InsertData() : Unit {

        var namaLengkap : String = etDaftarNama.text.toString()
        var email : String = etDaftarEmail.text.toString()
        var password : String = etDaftarPassword.text.toString()
        var tglLahir : String = etDaftarTanggalLahir.text.toString()
        var konfirmasi : String = etDaftarPasswordKonfirmasi.text.toString()

        if(konfirmasi == password) {
            var networkUser : Call<ResponseAction> = NetworkModule.service().insertUser(namaLengkap,email,password,tglLahir)
            networkUser.enqueue(object : Callback<ResponseAction> {
                override fun onResponse(call: Call<ResponseAction>, response: Response<ResponseAction>) {
                    Log.d("ResponseInsert",response.toString())
                    Toast.makeText(applicationContext,"Berhasil Mendaftar",Toast.LENGTH_LONG).show()

                }

                override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                    Toast.makeText(applicationContext,"Terjadi Kesalahan saat mendaftar",Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    fun Login(view: View) {
        val intent_login = Intent(this, Login_Activity::class.java)
        startActivity(intent_login)
        finish()
    }

}
