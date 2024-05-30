package com.example.kiosk.model

import android.media.Image
import android.os.Parcel
import android.os.Parcelable

data class CafeData (
    var type:Int,
    var coffeeid : Int,
    var dialogid:Int,
    var dialogfreeiceid:Int,
    var dialogfreehotid:Int,
    var name : String,
    var src : Int,
    var count:Int,
    var price:Int,
    var finalprice:Int,
    var payoption:Array<Int>,
    var ice:Int,
    var size:Int,
    var hotfreeoption:Array<Int>,
    var icefreeoption:Array<Int>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createIntArray()!!.toTypedArray(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createIntArray()!!.toTypedArray(),
        parcel.createIntArray()!!.toTypedArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        parcel.writeInt(coffeeid)
        parcel.writeInt(dialogid)
        parcel.writeInt(dialogfreeiceid)
        parcel.writeInt(dialogfreehotid)
        parcel.writeString(name)
        parcel.writeInt(src)
        parcel.writeInt(count)
        parcel.writeInt(price)
        parcel.writeInt(finalprice)
        parcel.writeIntArray(payoption.toIntArray())
        parcel.writeInt(ice)
        parcel.writeInt(size)
        parcel.writeIntArray(hotfreeoption.toIntArray())
        parcel.writeIntArray(icefreeoption.toIntArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CafeData> {
        override fun createFromParcel(parcel: Parcel): CafeData {
            return CafeData(parcel)
        }

        override fun newArray(size: Int): Array<CafeData?> {
            return arrayOfNulls(size)
        }
    }
}