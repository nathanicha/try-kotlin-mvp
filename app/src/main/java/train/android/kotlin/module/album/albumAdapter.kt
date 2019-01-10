package train.android.kotlin.module.album

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import train.android.kotlin.R
import train.android.kotlin.grid.gridAlbumActivity
import train.android.kotlin.model.AlbumModel

/**
 * Created by nack_ on 25/1/2561.
 */
class albumAdapter(var c: Context, var album: List<AlbumModel>) : RecyclerView.Adapter<HolderAlbum>() {



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HolderAlbum {
        var v = LayoutInflater.from(c)
                .inflate(R.layout.list_album, parent, false)
        val holdersAlbum = HolderAlbum(v)




        return holdersAlbum
    }

    override fun getItemCount(): Int {

        return album.size
    }

    override fun onBindViewHolder(holderAlbum: HolderAlbum, position: Int) {


        holderAlbum.id_album.text="AlbumModel id: "+album.get(position).id
        holderAlbum.title_album.text="AlbumModel title: "+album.get(position).title

        holderAlbum.holder_album.setOnClickListener(View.OnClickListener {
            Toast.makeText(c,"Click"+album.get(position).id, Toast.LENGTH_SHORT).show()
            val intent =  Intent(c, gridAlbumActivity::class.java)

            intent.putExtra("Id",album.get(position).id)

            c.startActivity(intent)
        })





    }



}