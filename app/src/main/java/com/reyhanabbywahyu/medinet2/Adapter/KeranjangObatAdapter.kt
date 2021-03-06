package com.reyhanabbywahyu.medinet2.Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat

class KeranjangObatAdapter(var dataItem : MutableList<Obat>? ,) : RecyclerView.Adapter<KeranjangObatAdapter.ViewHolder>() {
   class ViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
       val tvDetailJudulObat : TextView  = view.findViewById(R.id.tvDetailJudulObat)
       val tvDetailPenjelasanOBat : TextView =view.findViewById(R.id.tvDetailPenjelasanObat)
       val tvDetailHargaObat : TextView = view.findViewById(R.id.tvDetailHargaObat)
       val etKeranjangTotalObat : EditText =view.findViewById(R.id.etKeranjangTotalObat)

   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangObatAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.content_keranjang,parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: KeranjangObatAdapter.ViewHolder, position: Int) {
        val obat : Obat = dataItem?.get(position)!!

        holder.tvDetailJudulObat.text =obat.nama
        holder.tvDetailPenjelasanOBat.text = obat.jumlahdijual
        holder.tvDetailHargaObat.text = obat.harga.toString()
        holder.etKeranjangTotalObat.setText( obat.quantity.toString() )

        holder.view.setOnLongClickListener {
            AlertDialog.Builder(holder.view.context).
            setTitle("Dihapus Aja ini ?").
            setMessage("Weh ini mau dihapus mamang ?").setPositiveButton("Ya",DialogInterface.OnClickListener{dialog,which ->
                dataItem?.remove(obat)
                notifyDataSetChanged()
                dialog.dismiss()
            }).setNegativeButton("Engga",DialogInterface.OnClickListener{ dialog, which ->
                dialog.dismiss()

            }).show()
            true
        }

    }
    override fun getItemCount(): Int {
        return dataItem?.size!!
    }

}