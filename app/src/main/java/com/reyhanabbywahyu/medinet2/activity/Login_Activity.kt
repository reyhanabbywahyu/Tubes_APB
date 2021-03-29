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

class Login_Activity : AppCompatActivity() {
    lateinit  var user : User
    lateinit var btnMasuk : Button
    lateinit var etMasukEmail : EditText
    lateinit var etMasukPassword : EditText
    lateinit var  database : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = DBHelper(this)
        btnMasuk = findViewById(R.id.btnMasuk)
        etMasukPassword = findViewById(R.id.etMasukPassword)
        etMasukEmail = findViewById(R.id.etMasukEmail)
        btnMasuk.setOnClickListener {
            var etMasukEmailtext: String = etMasukEmail.text.toString()
            var usergetDatabase : User? = database.getDataBasedEmail(etMasukEmailtext)
            Log.d("LOGINLOGIN",usergetDatabase?.email.toString())
            if (usergetDatabase == null) {
                Toast.makeText(this, "Andak Tidak terdapatar atau password salah",Toast.LENGTH_LONG).show()
            }
            else {
                var etMasukPassword: String = etMasukPassword.text.toString()
                if (etMasukPassword == usergetDatabase.password.toString()) {
                    Toast.makeText(this, "BIsa Login ya bund",Toast.LENGTH_LONG).show()
                    loginSuccesfull()

                }
                else {
                    Toast.makeText(this, "PASSWORD MU SALAH MAS",Toast.LENGTH_LONG).show()
                }

            }

        }
    }

    fun loginSuccesfull() {
        intent = Intent(this,UtamaActivity::class.java)
        startActivity(intent)
    }
}