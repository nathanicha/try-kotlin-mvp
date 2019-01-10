package train.android.kotlin.module.main.adapter

import com.google.android.gms.maps.GoogleMap
import android.widget.TextView
import com.google.android.gms.maps.model.Marker
import android.view.View
import train.android.kotlin.R
import train.android.kotlin.module.main.fragment.FragmentOne


class CusInfoWindowAdapter(var context: FragmentOne) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        //set Infowindow
        val view:View = context.layoutInflater.inflate(R.layout.info_window, null)
        val text_name = view.findViewById(R.id.text_name) as TextView
        text_name.text = marker.title

        return view
    }
}