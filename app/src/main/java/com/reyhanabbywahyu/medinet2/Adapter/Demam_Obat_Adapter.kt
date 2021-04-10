package com.reyhanabbywahyu.medinet2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat


var obat1Demam: Obat = Obat(0,"IsaidTheKintil",900.0,"Kambing","per Strip","TOLOL anda")
var obat2Demam : Obat = Obat(1,"YouKnoe",9000.0,"Sapi daging","per Strip","TOLOL anda")
var obat3Demam : Obat = Obat(2,"Functuari",9000.0,"Kambing","per Box","TOLOL anda")
var obat4Demam : Obat = Obat(3,"LikeASeed",900.0,"Kambing","per Strip","TOLOL anda")
var obat5Demam : Obat = Obat(4,"OverSail",9000.0,"Sapi daging","per Strip","TOLOL anda")
var obat6Demam : Obat = Obat(5,"IntheGlass",9000.0,"Kambing","per Box","TOLOL anda")
var dataObatDemam : List<Obat> = listOf(
        obat1Demam, obat2Demam,
        obat3Demam, obat4Demam,
        obat5Demam, obat6Demam)

class Demam_Obat_Adapter(val onclick : TokoObatAdapter.onClickListener) : RecyclerView.Adapter<Demam_Obat_Adapter.HolderData>(),Filterable {
    var FilterList : List<Obat> = dataObatDemam
    class HolderData(val view : View) : RecyclerView.ViewHolder(view) {
        val nama_obat : TextView = itemView.findViewById(R.id.tvDetailJudulObat)
        val jumlah_jual : TextView = itemView.findViewById(R.id.tvDetailPenjelasanObat)
        val harga : TextView = itemView.findViewById(R.id.tvDetailHargaObat)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_detail_obat, parent,false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        var obat : Obat = FilterList[position]
        holder.nama_obat.text = obat.nama
        holder.jumlah_jual.text = obat.jumlahdijual
        holder.harga.text = obat.harga.toString()
        holder.view.setOnClickListener {
            onclick.detail(obat)
        }
    }

    override fun getItemCount(): Int {
        return FilterList.size
    }

    override fun getFilter(): Filter {
       return object : Filter () {
           override fun performFiltering(constraint: CharSequence?): FilterResults {
              val charsearch = constraint.toString()
              if(charsearch.isEmpty())  {
                  FilterList = dataObatDemam
              }
               else {
                   val result : MutableList<Obat> = mutableListOf()
                  for (dat in dataObat) {
                      if (dat.nama?.toLowerCase()?.contains(charsearch.toLowerCase()) == true) {
                          result.add(dat)
                      }
                  }
                  FilterList = result
               }
               val filterResult = FilterResults()
               filterResult.values =  FilterList
               return filterResult
           }

           override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               FilterList = results?.values as MutableList<Obat>
               notifyDataSetChanged()
           }

       }
    }
}