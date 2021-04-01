package com.reyhanabbywahyu.medinet2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User


class TokoObatAdapter(var dataObat : List<Obat>, val itemClick : onClickListener, var user : User)  : RecyclerView.Adapter<TokoObatAdapter.ViewHolder>() {
    class ViewHolder(val view : View) :RecyclerView.ViewHolder(view) {
        val nama : TextView = view.findViewById(R.id.tvDetailJudulObat)
        val info : TextView = view.findViewById(R.id.tvDetailPenjelasanObat)
        val harga : TextView = view.findViewById(R.id.tvDetailHargaObat)
        val tambah : Button = view.findViewById(R.id.btnDetailTambahObatRv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokoObatAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.content_detail_obat,parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: TokoObatAdapter.ViewHolder, position: Int) {
        val item : Obat = dataObat[position]
        holder.nama.text = item.nama
        holder.info.text = item.jumlahdijual
        holder.harga.text = item.harga.toString()
        holder.view.setOnClickListener {
            itemClick.detail(item)
        }
        holder.tambah.setOnClickListener {
            user.item.add(item)
        }
    }

    override fun getItemCount(): Int {
        return dataObat.size
    }

    interface onClickListener {
        fun detail(item : Obat)
    }
}