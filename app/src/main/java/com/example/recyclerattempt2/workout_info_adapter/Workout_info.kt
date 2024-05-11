package com.example.recyclerattempt2.workout_info_adapter

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.time.LocalDate

data class Workout_info(
    var name: String?,
    var data: String?,
    val date: String?)
//    val type: String? = "0")
    : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(data)
        dest.writeString(date)
//        dest.writeString(type)
    }

    companion object CREATOR : Parcelable.Creator<Workout_info> {
        override fun createFromParcel(parcel: Parcel): Workout_info {
            return Workout_info(parcel)
        }

        override fun newArray(size: Int): Array<Workout_info?> {
            return arrayOfNulls(size)
        }
    }

}
