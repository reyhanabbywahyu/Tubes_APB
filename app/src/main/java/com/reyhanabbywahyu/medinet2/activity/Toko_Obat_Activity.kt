package com.reyhanabbywahyu.medinet2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.adapter.TokoObatAdapter
import java.io.Serializable

class Toko_Obat_Activity : AppCompatActivity() {
    lateinit var  tokoObatSearch : SearchView
    lateinit var  recyclerViewObat : RecyclerView
    var user : User?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toko__obat)

        var obat1 : Obat = Obat(0,"Parasetamol",900.0,"Kambing","per Strip","TOLOL anda")
        var obat2 : Obat = Obat(1,"Yang Sepi",9000.0,"Sapi daging","per Strip","TOLOL anda")
        var obat3 : Obat = Obat(2,"Pirisitimil",9000.0,"Kambing","per Box","TOLOL anda")
        var obat4 : Obat = Obat(3,"GOLBOK",900.0,"Kambing","per Strip","TOLOL anda")
        var obat5 : Obat = Obat(4,"MONTE",9000.0,"Sapi daging","per Strip","TOLOL anda")
        var obat6 : Obat = Obat(5,"MANDARIN",9000.0,"Kambing","per Box","TOLOL anda")
        var dataObat : List<Obat> = listOf(obat1,obat2,obat3,obat4,obat5,obat6)

        user  =  intent.getSerializableExtra("EXTRA_USER") as User
        Log.d("USERGOBLOK",user?.item?.size.toString())
        recyclerViewObat = findViewById(R.id.recyclerViewDetailObat)
        recyclerViewObat.adapter = TokoObatAdapter(dataObat,object : TokoObatAdapter.onClickListener {
            override fun detail(item: Obat) {
                intent  = Intent(applicationContext,Detail_ObatActivity::class.java)
                intent.putExtra("EXTRA_OBAT",item)
                intent.putExtra("EXTRA_USER",user as Serializable)
                startActivity(intent)
            }
        }, user!!)

        recyclerViewObat.scrollToPosition(recyclerViewObat.adapter?.itemCount!! - 1)


    }
}