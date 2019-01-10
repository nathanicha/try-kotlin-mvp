package train.android.kotlin.module.main.gallery.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import train.android.kotlin.R
import train.android.kotlin.module.main.gallery.adapter.GridFavAdapter
import train.android.kotlin.room.SosRoom

class GalleryActivity : AppCompatActivity() {

    private var mGridView: GridView? = null
    lateinit var adapter: GridFavAdapter
    var data = SosRoom.getAppDatabase(this).callAppDao().getFavPhotoAll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_album)
        setAdapter()
    }

    private fun setAdapter(){
        mGridView = findViewById(R.id.gallery_grid_view)
        adapter = GridFavAdapter(this@GalleryActivity, data)
        mGridView!!.adapter = adapter
    }
}
