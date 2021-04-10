package com.reyhanabbywahyu.medinet2.`class`

import java.io.Serializable

class Obat : Serializable{
    constructor()
    constructor(id: Int, nama: String, harga: Double, kategori: String, infoPendek: String, infoDetail: String) {
        this.id = id
        this.nama = nama
        this.harga = harga
        this.kategori = kategori
        this.jumlahdijual = infoPendek
        this.informasiumum = infoDetail

    }
    constructor (id : Int, nama : String, harga : Double, kategori : String, jumlahdijual : String, informasiumum : String, informasidetail: String, peringatan : String, dosis : String) {
        this.id = id
        this.nama = nama
        this.harga = harga
        this.kategori = kategori
        this.jumlahdijual = jumlahdijual
        this.informasiumum = informasiumum
        this.quantity = 1
        this.informasiDetail = informasidetail
        this.Peringatan = peringatan
        this.Dosis = dosis
    }

    var id : Int =0
    var nama : String? =null
    var harga : Double=0.0
    var kategori : String?="umum"
    var jumlahdijual : String = "Belum Diketahui"
    var informasiumum : String = "Yow Panjang banget"
    var quantity : Int = 1
    var informasiDetail : String = "masih kosng"
    var Peringatan : String = "Kosong bang"
    var Dosis : String = "500 kaplet per hari"

}