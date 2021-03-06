package com.reyhanabbywahyu.medinet2.`class`

import androidx.versionedparcelable.ParcelField
import java.io.Serializable
import java.util.*

class User : Serializable{

    var email : String = "unknown"
    var id : Int=0
    var password : String ?= null
    var nama : String?=null
    var balance : Float = 0.0F
    var alamat : String?= null
    var tglLahir : String = "0-0-0000"
    var item : MutableList<Obat> = mutableListOf()
    var berat : Float = 45.0f
    var tinggi : Float = 0.0f
    constructor()
    constructor(email: String , password : String , nama_user : String, tglLahir : String) {
        this.nama =nama_user
        this.id=0
        this.password = password
        this.tglLahir = tglLahir
        this.email = email
        this.balance = 0.0F
    }
}