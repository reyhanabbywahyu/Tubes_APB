package com.reyhanabbywahyu.medinet2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.cardview.widget.CardView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import java.io.Serializable

class Detail_ObatActivity : AppCompatActivity() {
    lateinit var tvDetailJudul : TextView
    lateinit var tvDetailKeterangan : TextView
    lateinit var tvDetailHarga : TextView
    lateinit var btnKeranjangKurang : Button
    lateinit var  etKeranjangTotalObat : EditText
    lateinit var btnKeranjangTambah : Button

    lateinit var tvDetailInformasiUmum : TextView
    lateinit var tvDetailPeringatan : TextView
    lateinit var tvDetailDetail : TextView

    lateinit var  tvDetailHargaAkhir : TextView
    lateinit var  imgDetailPrev : ImageView
    lateinit var btnDetailTambah : TextView

    lateinit var  cvDetailObatBeliSekarang : CardView
     var User : User = User()
     var OBat : Obat = Obat()
     var total : Int = 1
    override fun onBackPressed() {
        //super.onBackPressed()
        //moveTaskToBack(true);

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_obat)

        tvDetailJudul = findViewById(R.id.tvDetailJudul)
        tvDetailKeterangan = findViewById(R.id.tvDetailKeterangan)
        tvDetailHarga = findViewById(R.id.tvDetailHarga)
        btnKeranjangKurang = findViewById(R.id.btnKeranjangKurang)
        btnKeranjangTambah = findViewById(R.id.btnKeranjangTambah)
        etKeranjangTotalObat = findViewById(R.id.etKeranjangTotalObat)
        tvDetailPeringatan = findViewById(R.id.tvDetailPeringatan)
        tvDetailDetail = findViewById(R.id.tvDetailDetail)
        tvDetailHargaAkhir = findViewById(R.id.tvDetailHargaAkhir)
        btnDetailTambah = findViewById(R.id.btnDetailTambah)
        tvDetailInformasiUmum = findViewById(R.id.tvDetailInformasiUmum)
        imgDetailPrev = findViewById(R.id.imgDetailPrev)
        cvDetailObatBeliSekarang = findViewById(R.id.cvDetailObatBeliSekarang)
        User = intent.getSerializableExtra("EXTRA_USER") as User
        var Obat : Obat = intent.getSerializableExtra("EXTRA_OBAT") as Obat

        tvDetailJudul.text = Obat.nama
        tvDetailKeterangan.text = Obat.jumlahdijual
        tvDetailHarga.text = Obat.harga.toString()
        tvDetailInformasiUmum.text = Obat.informasiumum
        etKeranjangTotalObat.setText( total.toString())
        tvDetailHargaAkhir.text = tvDetailHarga.text.toString()
        btnKeranjangKurang.setOnClickListener {
            etKeranjangTotalObat.setText(kurang().toString())
        }
        btnKeranjangTambah.setOnClickListener {
            etKeranjangTotalObat.setText(tambah().toString())
        }
        imgDetailPrev.setOnClickListener {
            intent = Intent(applicationContext, Toko_Obat_Activity::class.java)
            intent.putExtra("EXTRA_USER",User)
            startActivity(intent)
        }
        cvDetailObatBeliSekarang.setOnClickListener {
            Toast.makeText(this,"Obat Berhasil Ditambahkan",Toast.LENGTH_LONG).show()

            Obat.quantity = etKeranjangTotalObat.text.toString().toInt()
            User?.item?.add(Obat)
            Log.d("USERTOLOL",User?.item?.size.toString())
            intent = Intent(applicationContext,Keranjang_Activity::class.java )

            intent.putExtra("EXTRA_USER",User as Serializable)
            intent.putExtra("EXTRA_OBAT",Obat as Serializable)
            startActivity(intent)
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.isNotBlank() || s.isNotEmpty()) {
                        total = etKeranjangTotalObat.text.toString().toInt()
                        hitungHargaTotal()
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        }
        etKeranjangTotalObat.addTextChangedListener(textWatcher)
    }


    fun tambah() : Int{
        total = total + 1
        hitungHargaTotal()
        return total
    }
    fun hitungHargaTotal() : Unit{

        var hargatot = total * tvDetailHarga.text.toString().toDouble()
        tvDetailHargaAkhir.text = hargatot.toString()

    }
    fun kurang() : Int {
        if (total<1) {
            total = 0
            tvDetailHargaAkhir.text = "0"
        }
        else {
            total = total - 1
            hitungHargaTotal()
        }
        return total
    }
}