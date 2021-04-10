package com.reyhanabbywahyu.medinet2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.R.menu.bottom_nav_menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reyhanabbywahyu.medinet2.Adapter.Imun_Beranda_Adapter
import com.reyhanabbywahyu.medinet2.Adapter.Rekomendasi_Beranda_Adapter
import com.reyhanabbywahyu.medinet2.Adapter.TokoObatAdapter
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.activity.Detail_ObatActivity
import com.reyhanabbywahyu.medinet2.activity.Toko_Obat_Activity
import com.reyhanabbywahyu.medinet2.ui.Beranda.BerandaViewModel
import com.reyhanabbywahyu.medinet2.ui.Obat.ObatFragment
import java.io.Serializable

class BerandaActivity : AppCompatActivity() {
    var user : User = User()
    private lateinit var berandaViewModel: BerandaViewModel
    private lateinit var recyclerView : RecyclerView
    lateinit var  btn_covid : CardView
    lateinit var  btn_demam : CardView
    lateinit var  btn_sakitkepala : CardView
    lateinit var  btn_maag : CardView
    lateinit var  btn_lainnya : CardView
    lateinit var  btn_flu : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        btn_covid = findViewById(R.id.cvbtnBerandaCovid)
        btn_demam = findViewById(R.id.cvbtnBerandaDemam)
        btn_sakitkepala = findViewById(R.id.cvbtnBerandaSakitKepala)
        btn_maag = findViewById(R.id.cvbtnBerandaMaag)
        btn_flu = findViewById(R.id.cvbtnBerandFlu)
        btn_lainnya = findViewById(R.id.cvbtnBerandaLainnya)

        user = intent.getSerializableExtra("EXTRA_USER") as User
        fun gotoObatFragment(kategori:String) : Unit{

            var intent = Intent(this,KedaiObat_Activity::class.java)
            intent.putExtra("EXTRA_KATEGORI",kategori)
            intent.putExtra("EXTRA_USER",user as Serializable)
            startActivity(intent)
        }

        btn_covid.setOnClickListener {
            gotoObatFragment("covid19")
        }

        btn_sakitkepala.setOnClickListener {
            gotoObatFragment("sakitkepala")
        }

        btn_demam.setOnClickListener {
            gotoObatFragment("demam")
        }
        btn_maag.setOnClickListener {
            gotoObatFragment("maag")
        }
        btn_flu.setOnClickListener {
            gotoObatFragment("flu")
        }
        btn_lainnya.setOnClickListener {
            var intent = Intent(this, Toko_Obat_Activity::class.java)
            intent.putExtra("EXTRA_USER",user as Serializable)
            startActivity(intent)
        }

        //RecyclerView Rekomendasi Beranda
        var linearManager_rekomendasi = LinearLayoutManager(applicationContext,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView = findViewById(R.id.recycler_beranda_rekomendasi)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Rekomendasi_Beranda_Adapter(object : TokoObatAdapter.onClickListener {
            override fun detail(item: Obat) {
                var intent = Intent(applicationContext, Detail_ObatActivity::class.java)
                intent.putExtra("EXTRA_USER",user as Serializable)
                intent.putExtra("EXTRA_OBAT",item )
                startActivity(intent)
            }
        })
        recyclerView.setLayoutManager(linearManager_rekomendasi)

        //RecyclerView Perkuat Imun Beranda
        var linearManager_imun = LinearLayoutManager(applicationContext,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView = findViewById(R.id.recycler_beranda_imun)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Imun_Beranda_Adapter(object : TokoObatAdapter.onClickListener {
            override fun detail(item: Obat) {
                var intent = Intent(applicationContext, Detail_ObatActivity::class.java)
                intent.putExtra("EXTRA_USER",user as Serializable)
                intent.putExtra("EXTRA_OBAT",item)
                startActivity(intent)
            }
        })
        recyclerView.setLayoutManager(linearManager_imun)
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_obat ->{
                    val intent_obat = Intent(this,KedaiObat_Activity::class.java)
                    intent_obat.putExtra("EXTRA_USER",user as Serializable)
                    startActivity(intent_obat)
                    finish()
                }
                R.id.nav_biodata ->{
                    val intent_biodata = Intent(this,BiodataActivity::class.java)
                    intent_biodata.putExtra("EXTRA_USER",user as Serializable)
                    startActivity(intent_biodata)
                    finish()
                }
            }
            true
        }

    }
}