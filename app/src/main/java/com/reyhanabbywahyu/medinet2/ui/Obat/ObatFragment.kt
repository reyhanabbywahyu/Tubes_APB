package com.reyhanabbywahyu.medinet2.ui.Obat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.Adapter.*
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.activity.Detail_ObatActivity
import com.reyhanabbywahyu.medinet2.activity.Toko_Obat_Activity
import java.io.Serializable

class ObatFragment : Fragment() {

    private lateinit var obatViewModel: ObatViewModel
    private lateinit var recyclerView : RecyclerView
    lateinit var obat_Search : androidx.appcompat.widget.SearchView
    lateinit var  textViewJudulObat : TextView
    private var user : User = User()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        obatViewModel =
                ViewModelProvider(this).get(ObatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_obat, container, false)
        var bundle = arguments
        user = bundle?.getSerializable("EXTRA_USER") as User
        var kategori = bundle?.getString("EXTRA_KATEGORI")
        textViewJudulObat = root.findViewById(R.id.Obat_kategori_ganti)
        fun gotoDetail(item : Obat) {

            var intent = Intent(activity, Detail_ObatActivity::class.java)
            intent.putExtra("EXTRA_USER",user as Serializable)
            intent.putExtra("EXTRA_OBAT",item )
            startActivity(intent)
        }
        fun makeCovid() {
            textViewJudulObat.text = "Covid-19"
            var adapter = Covid_Obat_Adapter(object : TokoObatAdapter.onClickListener {
                override fun detail(item: Obat) {
                    gotoDetail(item)
                }
            })
            var linearManager_covid = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            obat_Search = root.findViewById(R.id.Obat_search)
            recyclerView = root.findViewById(R.id.recycler_obat_container)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearManager_covid
            recyclerView.setHasFixedSize(true)

            obat_Search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)

                    return false
                }
            })
        }
        fun makeDemam(): Unit {

            textViewJudulObat.text = "Demam"
            var adapter = Demam_Obat_Adapter(object : TokoObatAdapter.onClickListener{
                override fun detail(item: Obat) {
                   gotoDetail(item)
                }
            })
            obat_Search = root.findViewById(R.id.Obat_search)
            recyclerView = root.findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(root.context))
            obat_Search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)

                    return false
                }
            })
        }

        fun makeFlu(): Unit {

            textViewJudulObat.text = "Flu"
            var adapter = Flu_Obat_Adapter(object  : TokoObatAdapter.onClickListener {
                override fun detail(item: Obat) {
                   gotoDetail(item)
                }
            })
            obat_Search = root.findViewById(R.id.Obat_search)
            recyclerView = root.findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(root.getContext()))

            obat_Search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            } )
        }

        fun makeMaag() {

            textViewJudulObat.text = "Maag"
            val adapter = Maag_Obat_Adapter(object : TokoObatAdapter.onClickListener {
                override fun detail(item: Obat) {
                   gotoDetail(item)
                }
            } )
            obat_Search = root.findViewById(R.id.Obat_search)
            recyclerView = root.findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(root.getContext()))
            obat_Search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            } )
        }

        fun makeSakitPala() {

            textViewJudulObat.text = "Sakit Kepala"
            val adapter = SakitKepala_Obat_Adapter(object : TokoObatAdapter.onClickListener {
                override fun detail(item: Obat) {
                   gotoDetail(item)
                }
            })
            obat_Search = root.findViewById(R.id.Obat_search)
            recyclerView = root.findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

            recyclerView.setLayoutManager(LinearLayoutManager(root.getContext()))
            obat_Search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            } )
        }

        val btn_Covid: CardView = root.findViewById(R.id.btn_Covid)
        val btn_Demam: CardView = root.findViewById(R.id.btn_Demam)
        val btn_SakitKepala: CardView = root.findViewById(R.id.btn_SakitKepala)
        val btn_Flu: CardView = root.findViewById(R.id.btn_Flu)
        val btn_Maag: CardView = root.findViewById(R.id.btn_Maag)
        val btn_lainnya : CardView = root.findViewById(R.id.btn_lainnya)

        when {
            kategori == "covid19" -> {
                makeCovid()
            }

            kategori == "demam" -> {
                //Demam
                makeDemam()
            }
            kategori == "flu" -> {
                //Flu
                makeFlu()
            }
            kategori == "maag" -> {
                //Maag
                makeMaag()
            }
            kategori == "sakitkepala" -> {
                //Sakit Kepala
                makeSakitPala()
            }
        }
        recyclerView.visibility = View.VISIBLE

        btn_Covid.setOnClickListener {
            makeCovid()
        }
        btn_Demam.setOnClickListener {
            makeDemam()
        }
        btn_Flu.setOnClickListener {
            makeFlu()
        }
        btn_Maag.setOnClickListener {
            makeMaag()
        }
        btn_SakitKepala.setOnClickListener {
            makeSakitPala()
        }
        btn_lainnya.setOnClickListener {

            var intent = Intent(context,Toko_Obat_Activity::class.java )
            intent.putExtra("EXTRA_USER",user)
            startActivity(intent)
        }
        return root
    }
}