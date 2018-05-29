package com.miandroidchallenge.ucoppp.io18test.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import java.util.*

@Entity(tableName = "user")
data class User(
        @PrimaryKey
        @ColumnInfo(name = "delivery_id")
        val user_id: String = UUID.randomUUID().toString(),
        @NonNull
        @ColumnInfo(name = "name")
        val name: String?,
        @NonNull
        @ColumnInfo(name = "age")
        val age: Int?
)