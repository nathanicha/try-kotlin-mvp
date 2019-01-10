package train.android.kotlin.module.album

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import train.android.kotlin.R

/**
 * Created by nack_ on 25/1/2561.
 */
class HolderAlbum(var view: View) : RecyclerView.ViewHolder(view) {


    val id_album = view.findViewById(R.id.album_id) as TextView
    val title_album = view.findViewById(R.id.title_album) as TextView

    val holder_album = view.findViewById(R.id.holderalbum) as RelativeLayout

}