package com.reyhanabbywahyu.medinet2

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reyhanabbywahyu.medinet2.DBHelper.DBHelper
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.activity.Login_Activity
import com.reyhanabbywahyu.medinet2.ui.Biodata.BiodataViewModel
import java.io.Serializable

class BiodataActivity : AppCompatActivity() {



    lateinit var  biodata_beratView : TextView
    lateinit var  biodata_tinggiView : TextView
    lateinit var  biodata_namaView : TextView
    lateinit var  biodata_emailView : TextView
    lateinit var biodata_nama_pengguna : EditText
    lateinit var  biodata_tanggallahir : EditText
    lateinit var biodata_passwordlama : EditText
    lateinit var  biodata_passwordBaru : EditText
    lateinit var  biodata_ulangiPassword: EditText
    lateinit var biodata_email : EditText
    lateinit var  biodata_berat : EditText
    lateinit var  biodata_tinggi : EditText

    lateinit var  btnSimpanPersonal : Button
    lateinit var  btnSimpanAkun : Button
    lateinit var btnSimpanbiodata : Button

    lateinit var  hapusAkun : Button
    lateinit var  database : DBHelper
    var user : User?= null

    fun refreshData() {
        user = database.getDataBasedEmail(user?.email.toString())
        biodata_namaView.text = user?.nama
        biodata_emailView.text = user?.email
        biodata_beratView.text = user?.berat.toString()
        biodata_tinggiView.text = user?.tinggi.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        user = intent.getSerializableExtra("EXTRA_USER") as User
        database = DBHelper(this)

        biodata_beratView = findViewById(R.id.tx_biodata_berat_ganti)
        biodata_tinggiView = findViewById(R.id.tx_biodata_tinggi_ganti)
        biodata_emailView = findViewById(R.id.tx_biodata_email)
        biodata_namaView = findViewById(R.id.tx_biodata_namaPengguna)

        biodata_nama_pengguna = findViewById(R.id.et_biodata_namapengguna)
        biodata_tanggallahir = findViewById(R.id.et_biodata_tanggallahir)
        btnSimpanbiodata = findViewById(R.id.btn_biodata_SimpanBioBio)

        biodata_email =findViewById(R.id.et_biodata_email)
        btnSimpanAkun = findViewById(R.id.btn_biodata_SimpanPerubahanPassword)
        biodata_passwordlama = findViewById(R.id.et_biodata_passwordLama)
        biodata_passwordBaru = findViewById(R.id.et_biodata_passwordBaru)
        biodata_ulangiPassword =findViewById(R.id.et_biodata_Ulangpassword)

        btnSimpanPersonal = findViewById(R.id.btn_biodata_SimpanDataPersonal)
        biodata_tinggi= findViewById(R.id.et_biodata_tinggi)
        biodata_berat = findViewById(R.id.et_biodata_berat)

        hapusAkun = findViewById(R.id.btn_biodata_HapusAKUN)

        refreshData()
        btnSimpanbiodata.setOnClickListener {

            var textNamaNew = biodata_nama_pengguna.text.toString()
            var textTglLahirNew = biodata_tanggallahir.text.toString()
            if(textNamaNew.isNotEmpty()) {
                user?.nama = textNamaNew
            }
            if(textTglLahirNew.isNotEmpty()) {
                user?.tglLahir = textTglLahirNew
            }
            Log.d("CEKBIODATA",user?.nama.toString())
            database.updateUserBiodata(user!!)
            refreshData()

        }

        btnSimpanAkun.setOnClickListener {

            var textEmailNew = biodata_email.text.toString()
            var passwordBaru= biodata_passwordBaru.text.toString()
            var passwordlama = biodata_passwordlama.text.toString()
            var ulangipassword = biodata_ulangiPassword.text.toString()

            if(textEmailNew.isNotEmpty()) {
                user?.email = textEmailNew
                database.updateUserAkun(user!!)
                refreshData()

            }
            if(passwordBaru == ulangipassword && passwordlama == user?.password) {
                user?.password = passwordBaru
                database.updateUserAkun(user!!)
                Log.d("Passwordnya",user?.password.toString())
                refreshData()
            }
            else {
                Toast.makeText(this,"Password salah", Toast.LENGTH_LONG).show()
            }
        }
        btnSimpanPersonal.setOnClickListener {
            var tinggiNew= biodata_tinggi.text.toString().toFloat()
            var beratNew= biodata_berat.text.toString().toFloat()

            if(tinggiNew > 150.0f && tinggiNew  < 300.0f) {
                user?.tinggi=tinggiNew
                database.updateUserPersonal(user!!)
                refreshData()
            }
            if(beratNew > 30.0f && beratNew < 300.0f) {

                user?.berat= beratNew
                database.updateUserPersonal(user!!)
                refreshData()
            }

            else {
                Toast.makeText(this,"Password salah", Toast.LENGTH_LONG).show()
            }


        }
        hapusAkun.setOnClickListener {
            AlertDialog.Builder(this@BiodataActivity).
            setTitle("Dihapus Aja ini ?").
            setMessage("Weh ini mau dihapus mamang ?").setPositiveButton("Ya",DialogInterface.OnClickListener(){ dialog, which ->
                database.deletePersonalData(user!!)
                Toast.makeText(this@BiodataActivity, "Dah kehapus", Toast.LENGTH_LONG).show()
                var intent = Intent(this@BiodataActivity,Login_Activity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }).setNegativeButton("Engga", DialogInterface.OnClickListener{ dialog, which ->
                dialog.dismiss()

            }).show()

        }
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_beranda ->{
                    val intent_beranda = Intent(this,BerandaActivity::class.java)
                    intent_beranda.putExtra("EXTRA_USER",user as Serializable)
                    startActivity(intent_beranda)
                }
                R.id.nav_obat ->{
                    val intent = Intent(this,KedaiObat_Activity::class.java)
                    intent.putExtra("EXTRA_USER",user as Serializable)
                    startActivity(intent)
                }

            }
            false
        }
    }
}