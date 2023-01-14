package com.cpadridev.carmonaadrian_finalproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Number (@SerializedName("id") @Expose var id: Int?,
                   @SerializedName("spanish") @Expose var spanish: String,
                   @SerializedName("english") @Expose var english: String,) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(spanish)
        parcel.writeString(english)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Number> {
        override fun createFromParcel(parcel: Parcel): Number {
            return Number(parcel)
        }

        override fun newArray(size: Int): Array<Number?> {
            return arrayOfNulls(size)
        }
    }
}