package com.binar.ariefaryudisyidik.challengesilverchapter4.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null
) : Parcelable


