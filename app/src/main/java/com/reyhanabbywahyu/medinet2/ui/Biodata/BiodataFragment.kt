package com.reyhanabbywahyu.medinet2.ui.Biodata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reyhanabbywahyu.medinet2.DBHelper.DBHelper
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.User
import org.w3c.dom.Text

class BiodataFragment : Fragment() {

    private lateinit var biodataViewModel: BiodataViewModel

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
    var user : User ?= null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        biodataViewModel =
                ViewModelProvider(this).get(BiodataViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_biodata, container, false)
        database = DBHelper(root.context)
        user = database.getDataBasedEmail("ee")
        Log.d("USERAJ",user?.berat.toString())
        biodata_beratView = root.findViewById(R.id.tx_biodata_berat_ganti)
        biodata_tinggiView = root.findViewById(R.id.tx_biodata_tinggi_ganti)
        biodata_emailView = root.findViewById(R.id.tx_biodata_email)
        biodata_namaView = root.findViewById(R.id.tx_biodata_namaPengguna)

        biodata_nama_pengguna = root.findViewById(R.id.et_biodata_namapengguna)
        biodata_tanggallahir = root.findViewById(R.id.et_biodata_tanggallahir)
        btnSimpanbiodata = root.findViewById(R.id.btn_biodata_SimpanBioBio)

        biodata_email = root.findViewById(R.id.et_biodata_email)
        btnSimpanAkun = root.findViewById(R.id.btn_biodata_SimpanPerubahanPassword)
        biodata_passwordlama = root.findViewById(R.id.et_biodata_passwordLama)
        biodata_passwordBaru = root.findViewById(R.id.et_biodata_passwordBaru)
        biodata_ulangiPassword = root.findViewById(R.id.et_biodata_Ulangpassword)

        btnSimpanPersonal = root.findViewById(R.id.btn_biodata_SimpanDataPersonal)
        biodata_tinggi= root.findViewById(R.id.et_biodata_tinggi)
        biodata_berat = root.findViewById(R.id.et_biodata_berat)

        hapusAkun = root.findViewById(R.id.btn_biodata_HapusAKUN)

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
                Toast.makeText(context,"Password salah",Toast.LENGTH_LONG).show()
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
                Toast.makeText(context,"Password salah",Toast.LENGTH_LONG).show()
            }
        }



        return root
    }

    fun refreshData() {
        user = database.getDataBasedEmail(user?.email.toString())
        Log.d("USRASU",user?.tinggi.toString())
        biodata_namaView.text = user?.nama
        biodata_emailView.text = user?.email
        biodata_beratView.text = user?.berat.toString()
        biodata_tinggiView.text = user?.tinggi.toString()
    }
}