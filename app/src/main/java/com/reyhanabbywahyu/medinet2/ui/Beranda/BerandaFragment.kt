package com.reyhanabbywahyu.medinet2.ui.Beranda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reyhanabbywahyu.medinet2.Adapter.Imun_Beranda_Adapter
import com.reyhanabbywahyu.medinet2.Adapter.Rekomendasi_Beranda_Adapter
import com.reyhanabbywahyu.medinet2.Adapter.TokoObatAdapter
import com.reyhanabbywahyu.medinet2.R
import com.reyhanabbywahyu.medinet2.`class`.Obat
import com.reyhanabbywahyu.medinet2.`class`.User
import com.reyhanabbywahyu.medinet2.activity.Detail_ObatActivity
import com.reyhanabbywahyu.medinet2.activity.Toko_Obat_Activity
import com.reyhanabbywahyu.medinet2.activity.UtamaActivity
import com.reyhanabbywahyu.medinet2.ui.Obat.ObatFragment
import java.io.Serializable

class BerandaFragment : Fragment() {

    var user : User = User()
    private lateinit var berandaViewModel: BerandaViewModel
    private lateinit var recyclerView : RecyclerView
    lateinit var  btn_covid : CardView
    lateinit var  btn_demam : CardView
    lateinit var  btn_sakitkepala : CardView
    lateinit var  btn_maag : CardView
    lateinit var  btn_lainnya : CardView
    lateinit var  btn_flu : CardView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        berandaViewModel =
                ViewModelProvider(this).get(BerandaViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_beranda, container, false)
        btn_covid = root.findViewById(R.id.cvbtnBerandaCovid)
        btn_demam = root.findViewById(R.id.cvbtnBerandaDemam)
        btn_sakitkepala = root.findViewById(R.id.cvbtnBerandaSakitKepala)
        btn_maag = root.findViewById(R.id.cvbtnBerandaMaag)
        btn_flu = root.findViewById(R.id.cvbtnBerandFlu)
        btn_lainnya = root.findViewById(R.id.cvbtnBerandaLainnya)
        var argument = arguments
        if(argument != null) {
            user = argument.getSerializable("EXTRA_USER") as User
            Log.d("NAMAAAMYA",user.nama.toString())
        }
        else {
            Log.d("ADASD",user.nama.toString())
        }

        fun gotoObatFragment(kategori:String) : Unit{
            val obatFragment = ObatFragment()
            var bundle = Bundle()
            bundle.putString("EXTRA_KATEGORI",kategori)
            bundle.putSerializable("EXTRA_USER",user as Serializable)
            obatFragment.arguments = bundle
            var fr = fragmentManager?.beginTransaction()
            fr?.replace(R.id.nav_host_fragment,obatFragment)?.commit()
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
           var intent = Intent(context,Toko_Obat_Activity::class.java)
            intent.putExtra("EXTRA_USER",user as Serializable)
            startActivity(intent)
        }

        //RecyclerView Rekomendasi Beranda
        var linearManager_rekomendasi = LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView = root.findViewById(R.id.recycler_beranda_rekomendasi)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Rekomendasi_Beranda_Adapter(object : TokoObatAdapter.onClickListener {
            override fun detail(item: Obat) {
                var intent = Intent(activity,Detail_ObatActivity::class.java)
                intent.putExtra("EXTRA_USER",user as Serializable)
                intent.putExtra("EXTRA_OBAT",item )
                startActivity(intent)
            }
        })
        recyclerView.setLayoutManager(linearManager_rekomendasi)

        //RecyclerView Perkuat Imun Beranda
        var linearManager_imun = LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView = root.findViewById(R.id.recycler_beranda_imun)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Imun_Beranda_Adapter(object : TokoObatAdapter.onClickListener {
            override fun detail(item: Obat) {
              var intent = Intent(activity, Detail_ObatActivity::class.java)
              intent.putExtra("EXTRA_USER",user as Serializable)
              intent.putExtra("EXTRA_OBAT",item)
              startActivity(intent)
            }
        })
        recyclerView.setLayoutManager(linearManager_imun)
        return root
    }
}