package com.reyhanabbywahyu.medinet2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.reyhanabbywahyu.medinet2.DBHelper.DBHelper
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.User

class Daftar_Activity : AppCompatActivity() {
    lateinit var btnDaftar : Button
    lateinit var  etDaftarEmail : EditText
    lateinit var etDaftarNama : EditText
    lateinit var etDaftarPassword : EditText
    lateinit var etDaftarPasswordKonfirmasi : EditText
    lateinit var etDaftarTanggalLahir : EditText
    lateinit var database : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        btnDaftar = findViewById(R.id.btnDaftar)
        etDaftarTanggalLahir = findViewById(R.id.etDaftarTanggalLahir)
        etDaftarPasswordKonfirmasi = findViewById(R.id.etDaftarPasswordKonfirmasi)
        etDaftarPassword = findViewById(R.id.etDaftarPassword)
        etDaftarNama = findViewById(R.id.etDaftarNama)
        etDaftarEmail = findViewById(R.id.etDaftarEmail)
        database = DBHelper(this)
        btnDaftar.setOnClickListener {
            var user: User? = getData()
            if (user != null) {
                intent = Intent(this, Login_Activity::class.java)
                startActivity(intent)
            }
        }
    }
    fun getData() : User? {

        var namaLengkap : String = etDaftarNama.text.toString()
        var email : String = etDaftarEmail.text.toString()
        var password : String = etDaftarPassword.text.toString()
        var tglLahir : String = etDaftarTanggalLahir.text.toString()
        var konfirmasi : String = etDaftarPasswordKonfirmasi.text.toString()
        if(konfirmasi == password) {
            var user : User = User(email,password,namaLengkap,tglLahir)
            Log.d("DAFTAR",user.email.toString())
            database.insert_data(user)
            Toast.makeText(this,"Daftar Berhasil",Toast.LENGTH_LONG).show()
            return user
        }
        Toast.makeText(this,"Tah Tolol",Toast.LENGTH_LONG).show()
        return null

    }

}
