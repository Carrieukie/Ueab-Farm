package com.karis.utamukitchen.models

import android.R
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@Entity(tableName = "cart")
data class FoodDataBinding(
        var id: Int = 0,
        var image: String? = "",
        var name: String? = "",
        var price: Int? = -1,
        var quantity: Int? = 1,
        var numberOfItem: Int? = 1,
        var category: String? = "",
        var description: String? = "",
){
        companion object {
                @JvmStatic
                @BindingAdapter("foodImage")
                fun loadImage(view: ImageView, profileImage: String) {
                        Glide.with(view.context)
                                .load(profileImage)
                                .circleCrop()
                                .into(view)
                }
        }
}

@Entity(tableName = "cart")
data class Food(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var image: String? = "",
        var name: String? = "",
        var price: Int? = -1,
        var quantity: Int? = 1,
        var numberOfItem: Int? = 1,
        var category: String? = "",
        var description: String? = "",
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(image)
                parcel.writeString(name)
                parcel.writeValue(price)
                parcel.writeValue(quantity)
                parcel.writeValue(numberOfItem)
                parcel.writeString(category)
                parcel.writeString(description)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Food> {
                override fun createFromParcel(parcel: Parcel): Food {
                        return Food(parcel)
                }

                override fun newArray(size: Int): Array<Food?> {
                        return arrayOfNulls(size)
                }
        }
}