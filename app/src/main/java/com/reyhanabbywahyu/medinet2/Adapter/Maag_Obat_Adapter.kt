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

val nama_obat_Maag_List : MutableList<String> = mutableListOf("Brother", "me,Scott Jennifer","Sandra Adams","Trevor Hansen","Britta Holt")
val jumlah_jual_Maag_List : MutableList<String> = mutableListOf("Brunch this Weekend?", "Summer BBQ", "Oui Oui", "Order Confirmation", "Receipe to try")
val harga_Maag_List : MutableList<String> = mutableListOf("15m", "2h", "6h", "12h", "18h")
var dataObatMaag : MutableList<Obat> = mutableListOf(
        obat1Demam, obat2Demam,
        obat3Demam, obat4Demam,
        obat5Demam, obat6Demam)

class Maag_Obat_Adapter(val onclick : TokoObatAdapter.onClickListener) : RecyclerView.Adapter<Maag_Obat_Adapter.HolderData>(),Filterable {
    var FilterList = dataObatMaag
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
        val item : Obat = FilterList[position]
        holder.nama_obat.text = item.nama
        holder.jumlah_jual.text = item.jumlahdijual
        holder.harga.text = item.harga.toString()
        holder.view.setOnClickListener {
            onclick.detail(item)
        }
    }

    override fun getItemCount(): Int {
        return FilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchChar = constraint.toString()
                if(searchChar.isEmpty()) {
                    FilterList = dataObatMaag
                }
                else {
                    val result : MutableList<Obat> = mutableListOf()
                    for ( dat in dataObatMaag) {
                        if(dat.nama?.toLowerCase()?.contains(searchChar.toLowerCase()) == true) {
                            result.add(dat)
                        }
                    }
                    FilterList = result
                }
                val resultList =  FilterResults()
                resultList.values = FilterList
                return resultList
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                FilterList = results?.values as MutableList<Obat>
                notifyDataSetChanged()
            }

        }
    }
}