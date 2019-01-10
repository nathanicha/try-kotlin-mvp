package train.android.kotlin.model

import android.arch.persistence.room.ColumnInfo
import com.google.android.gms.maps.model.LatLng

/**
 * Created by nlwd on 1/17/2018.
 */
class GeoModel {

    @ColumnInfo(name = "lat")
     var lat: String? = null

    @ColumnInfo(name = "lng")
     var lng: String? = null

}