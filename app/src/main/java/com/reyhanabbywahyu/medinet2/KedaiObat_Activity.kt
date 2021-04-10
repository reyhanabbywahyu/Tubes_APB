package com.reyhanabbywahyu.medinet2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reyhanabbywahyu.medinet2.Adapter.*
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.activity.Detail_ObatActivity
import com.reyhanabbywahyu.medinet2.activity.Toko_Obat_Activity
import com.reyhanabbywahyu.medinet2.ui.Obat.ObatViewModel
import java.io.Serializable

class KedaiObat_Activity : AppCompatActivity() {
    private lateinit var obatViewModel: ObatViewModel
    private lateinit var recyclerView : RecyclerView
    lateinit var obat_Search : androidx.appcompat.widget.SearchView
    lateinit var  textViewJudulObat : TextView
    private var user : User = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kedai_obat)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        recyclerView = findViewById(R.id.recycler_obat_container)

        user = intent.getSerializableExtra("EXTRA_USER") as User
        var kategori = intent.getStringExtra("EXTRA_KATEGORI")
        textViewJudulObat = findViewById(R.id.Obat_kategori_ganti)
        fun gotoDetail(item : Obat) {

            var intent = Intent(applicationContext, Detail_ObatActivity::class.java)
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
            var linearManager_covid = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            obat_Search = findViewById(R.id.Obat_search)
            recyclerView = findViewById(R.id.recycler_obat_container)
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
            obat_Search = findViewById(R.id.Obat_search)
            recyclerView = findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
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
            obat_Search = findViewById(R.id.Obat_search)
            recyclerView = findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(applicationContext))

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
            obat_Search = findViewById(R.id.Obat_search)
            recyclerView = findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(applicationContext))

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
            obat_Search = findViewById(R.id.Obat_search)
            recyclerView = findViewById(R.id.recycler_obat_container)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

            recyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
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

        val btn_Covid: CardView = findViewById(R.id.btn_Covid)
        val btn_Demam: CardView = findViewById(R.id.btn_Demam)
        val btn_SakitKepala: CardView = findViewById(R.id.btn_SakitKepala)
        val btn_Flu: CardView = findViewById(R.id.btn_Flu)
        val btn_Maag: CardView = findViewById(R.id.btn_Maag)
        val btn_lainnya : CardView = findViewById(R.id.btn_lainnya)

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

            var intent = Intent(applicationContext, Toko_Obat_Activity::class.java )
            intent.putExtra("EXTRA_USER",user)
            startActivity(intent)
        }
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_beranda ->{
                    val intent_beranda = Intent(this,BerandaActivity::class.java)
                    intent_beranda.putExtra("EXTRA_USER",user as Serializable)
                    startActivity(intent_beranda)
                }
                R.id.nav_biodata ->{
                    val intent_biodata = Intent(this,BiodataActivity::class.java)
                    intent_biodata.putExtra("EXTRA_USER",user as Serializable)
                    startActivity(intent_biodata)
                }
            }
            false
        }
    }
}