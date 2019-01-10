package train.android.kotlin.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded

/**
 * Created by nlwd on 1/17/2018.
 */
class AddressModel{
    @ColumnInfo(name = "street")
     var street: String? = null
    @ColumnInfo(name = "suite")
     var suite: String? = null
    @ColumnInfo(name = "city")
     var city: String? = null
    @ColumnInfo(name = "zipcode")
     var zipcode: String? = null
    @Embedded
     var geoModel:GeoModel?=null




}