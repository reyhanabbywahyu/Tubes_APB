package com.reyhanabbywahyu.medinet2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.Adapter.KeranjangObatAdapter
import java.io.Serializable

class Keranjang_Activity : AppCompatActivity() {
    lateinit var recyclerViewKeranjang : RecyclerView
    lateinit var tvKeranjangAlamat : TextView
    lateinit var tvKeranjangCatatan : TextView
    lateinit var  RbtnKeranjangTunai : RadioButton
    lateinit var  RbtnKeranjangDompet : RadioButton
    lateinit var cvKeranjangPesanSekarang : CardView
    lateinit var tvKeranjangIsiDompet : TextView
    lateinit var tvKeranjangTotal : TextView
    lateinit var imgPrevKeranjang : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        //init dulu bang
        recyclerViewKeranjang = findViewById(R.id.recyclerView)
        tvKeranjangAlamat = findViewById(R.id.tvKeranjangAlamat)
        tvKeranjangCatatan = findViewById(R.id.tvKeranjangCatatan)
        RbtnKeranjangDompet = findViewById(R.id.RbtnKeranjangDompet)
        RbtnKeranjangTunai = findViewById(R.id.RbtnKeranjangTunai)
        cvKeranjangPesanSekarang = findViewById(R.id.cvKeranjangPesanSekarang)
        tvKeranjangIsiDompet = findViewById(R.id.tvKeranjangIsiDompet)
        tvKeranjangTotal = findViewById(R.id.tvKeranjangTotal)
        imgPrevKeranjang = findViewById(R.id.cardViewKeranajgg)

        var user : User  = intent.getSerializableExtra("EXTRA_USER") as User
        var Obat : Obat = intent.getSerializableExtra("EXTRA_OBAT") as Obat

        imgPrevKeranjang.setOnClickListener {
          // intent = Intent(applicationContext, UtamaActivity::class.java)
            intent = Intent(applicationContext, Detail_ObatActivity::class.java)
            intent.putExtra("EXTRA_USER",user as Serializable)
            intent.putExtra("EXTRA_OBAT",Obat as Serializable)
           startActivity(intent)
        }
        val adapter = KeranjangObatAdapter(user.item)
        recyclerViewKeranjang.adapter = adapter
        recyclerViewKeranjang.scrollToPosition(recyclerViewKeranjang.adapter?.itemCount!! - 1)
        tvKeranjangTotal.text = calculateHarga(user).toString()

        tvKeranjangIsiDompet.text = user.balance.toString()

        cvKeranjangPesanSekarang.setOnClickListener {
            if(tvKeranjangIsiDompet.text.toString().toDouble() < tvKeranjangTotal.text.toString().toDouble()) {
                Toast.makeText(this,"Ndak bisa bang Kurang duid",Toast.LENGTH_LONG).show()
                Log.d("PESANAN","DUIT KURANG BLOK")
            }
            else {
                Log.d("PESANAN DATANG","PESANAN ABNDA SEDANG ANJING")
            }
        }


    }
    fun calculateHarga(User:User) : Double {
        var total = 0.0
        User.item.forEach{
            total = total + ( it.quantity * it.harga)

        }

        return total
    }
}