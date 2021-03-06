package com.reyhanabbywahyu.medinet2.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User


class TokoObatAdapter(var dataObat : List<Obat>, val itemClick : onClickListener, var user : User)  : RecyclerView.Adapter<TokoObatAdapter.ViewHolder>(),Filterable {
    var FilterList : List<Obat> = dataObat

    init {
        FilterList = dataObat
    }
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
        val item : Obat = FilterList[position]
        holder.nama.text = item.nama
        holder.info.text = item.jumlahdijual
        holder.harga.text = item.harga.toString()
        holder.view.setOnClickListener {
            itemClick.detail(item)
        }
        holder.tambah.setOnClickListener {
            if(user.item.size != 0   ) {
                var ada : MutableList<Int> = mutableListOf()
                for (i in 0..(user.item.size - 1)) {
                     ada.add(user.item[i].id)
                     if (user.item[i].id == item.id && user.item[i].quantity == item.quantity) {
                        user.item[i].quantity = user.item[i].quantity + 1
                    }
                    else if (user.item[i].id == item.id && user.item[i].quantity != item.quantity) {
                         user.item[i].quantity = user.item[i].quantity + 1
                     }
                }
                if(!ada.contains(item.id)) {
                    user.item.add(item)
                }
            }
            else {
                Log.d("mas","MASOK SINI TEROS")
                user.item.add(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return FilterList.size
    }

    interface onClickListener {
        fun detail(item : Obat)
    }

    override fun getFilter(): Filter {
        return object : Filter () {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.isEmpty()) {
                   FilterList = dataObat
                }
                else {
                    val resutlList : MutableList<Obat> = mutableListOf()
                    for (row in dataObat) {

                        if (row.nama?.toLowerCase()?.contains(charSearch.toLowerCase()) == true) {

                            resutlList.add(row)
                        }
                    }
                    FilterList = resutlList
                }
                val filterResult = FilterResults()
                filterResult.values = FilterList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                FilterList = results?.values as MutableList<Obat>
                notifyDataSetChanged()
            }

        }
    }

}