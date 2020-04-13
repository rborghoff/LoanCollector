package com.example.loancollector.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "loan_table")
data class Loan (

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "aan")
    var aan:String,

    @ColumnInfo(name ="naam")
    var naam:String,

    @ColumnInfo(name = "amount")
    var amount:Double,

    @ColumnInfo(name = "image")
    var image: Uri,

    @ColumnInfo(name = "omschrijving")
    var omschrijving: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

):Parcelable
