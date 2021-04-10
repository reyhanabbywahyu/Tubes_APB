package com.reyhanabbywahyu.medinet2.Adapter

import android.util.Log
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

var obat1Cov : Obat = Obat(1, "Imboost Force 10 Kaplet", 120000.0, "covid", "per Strip", " Obat ini adalah vitamin untuk memperbaharui sistem imun", "Minum obat setelah makan","Jangan Overdosis" ,"1 Kali sehari")

var obat2Cov : Obat =  Obat(2, "Favipiravir", 120000.0, "covid", "per Strip", "adalah obat antivirus yang digunakan untuk mengatasi beberapa jenis virus influenza yang tergolong dalam jenis virus RNA. Salah satunya adalah virus influenza A yang menyebabkan flu burung dan flu babi.", "obat jenis ini hanya boleh digunakan sesuai anjuran dokter dan tidak diperuntukkan bagi ibu hamil.","Peringitin", "3 Kali sehari")
var obat3Cov : Obat = Obat(2,"Functuari",9000.0,"Kambing","per Box","TOLOL anda")
var obat4Cov : Obat = Obat(3,"LikeASeed",900.0,"Kambing","per Strip","TOLOL anda")
var obat5Cov : Obat = Obat(4,"OverSail",9000.0,"Sapi daging","per Strip","TOLOL anda")
var obat6Cov : Obat = Obat(5,"IntheGlass",9000.0,"Kambing","per Box","TOLOL anda")
var dataObat : List<Obat> = listOf(obat1Cov, obat2Cov, obat3Cov, obat4Cov, obat5Cov, obat6Cov)


class Covid_Obat_Adapter(val onclick : TokoObatAdapter.onClickListener ) : RecyclerView.Adapter<Covid_Obat_Adapter.HolderData>(),Filterable {
    var FilterList: List<Obat> = dataObat
    init {
        FilterList = dataObat
    }
    class HolderData(val view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val nama_obat : TextView = itemView.findViewById(R.id.tvDetailJudulObat)
        val jumlah_jual : TextView = itemView.findViewById(R.id.tvDetailPenjelasanObat)
        val harga : TextView = itemView.findViewById(R.id.tvDetailHargaObat)

        override fun onClick(v: View?) {
            Toast.makeText(itemView.context, "", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_detail_obat, parent,false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        val obat : Obat = FilterList[position]
        holder.nama_obat.text =  obat.nama
        holder.jumlah_jual.text = obat.jumlahdijual
        holder.harga.text = obat.harga.toString()
        holder.view.setOnClickListener {
            onclick.detail(obat)
        }
    }

    override fun getItemCount(): Int {
        return FilterList.size
    }
    override fun getFilter() : Filter {
        return object : Filter () {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charsearch = constraint.toString()
                if(charsearch.isEmpty()) {
                        FilterList = dataObat
                }
                else {
                    val resultList : MutableList<Obat> = mutableListOf()
                    for (dat in dataObat) {
                        if(dat.nama?.toLowerCase()?.contains(charsearch.toLowerCase()) == true) {
                            resultList.add(dat)
                        }
                    }
                   FilterList = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = FilterList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                FilterList = results?.values as MutableList<Obat>
                Log.d("FILTERED",FilterList.size.toString())
                notifyDataSetChanged()
            }

        }
    }
}