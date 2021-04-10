package com.reyhanabbywahyu.medinet2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat

val nama_obat_List : MutableList<String> = mutableListOf("Ali Connors", "me,Scott Jennifer","Sandra Adams","Trevor Hansen","Britta Holt")
val jumlah_jual_List : MutableList<String> = mutableListOf("Brunch this Weekend?", "Summer BBQ", "Oui Oui", "Order Confirmation", "Receipe to try")
val harga_List : MutableList<String> = mutableListOf("15m", "2h", "6h", "12h", "18h")

var obat1imun : Obat = Obat(0,"Parasetamol",900.0,"Kambing","per Strip","TOLOL anda")
var obat2imun : Obat = Obat(1,"Yang Sepi",9000.0,"Sapi daging","per Strip","TOLOL anda")
var obat3imun : Obat = Obat(2,"Pirisitimil",9000.0,"Kambing","per Box","TOLOL anda")
var obat4imun : Obat = Obat(3,"GOLBOK",900.0,"Kambing","per Strip","TOLOL anda")
var obat5imun : Obat = Obat(4,"MONTE",9000.0,"Sapi daging","per Strip","TOLOL anda")
var obat6imun : Obat = Obat(5,"MANDARIN",9000.0,"Kambing","per Box","TOLOL anda")
var ListObatimun: List<Obat> = listOf(obat1,obat2,obat3,obat4,obat5,obat6)

class Imun_Beranda_Adapter(val onclick : TokoObatAdapter.onClickListener) : RecyclerView.Adapter<Imun_Beranda_Adapter.HolderData>() {
    class HolderData(val view : View) : RecyclerView.ViewHolder(view) {
        val nama_obat : TextView = itemView.findViewById(R.id.nama_obat)
        val jumlah_jual : TextView = itemView.findViewById(R.id.jumlah_jual)
        val harga : TextView = itemView.findViewById(R.id.harga)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_obat_beranda, parent,false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        val obat : Obat = ListObatimun[position]
        holder.nama_obat.text = obat.nama
        holder.jumlah_jual.text = obat.jumlahdijual
        holder.harga.text = obat.harga.toString()

        holder.view.setOnClickListener {
           onclick.detail(obat)
        }
    }

    override fun getItemCount(): Int {
        return ListObatimun.size
    }
}