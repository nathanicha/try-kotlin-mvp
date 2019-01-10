package train.android.kotlin.grid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import train.android.kotlin.R
import train.android.kotlin.httpmanager.AllServiceAPI
import train.android.kotlin.model.PhotoAlbumModel
import android.widget.GridView
import train.android.kotlin.room.SosRoom


class gridAlbumActivity : AppCompatActivity() {

    internal var mGridView: GridView? = null
     lateinit var adapter:gridAdaptervar
      var photoAlbumModel:List<PhotoAlbumModel>? = null

    val mHolder: gridAdaptervar.ViewHolder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_album)
        var id = intent.getIntExtra("Id",0)

         if(SosRoom.getAppDatabase(this).callAppDao().getCountAlbum(id)==0) {
             loadPhoto(id)
         }
//        adapter = gridAdaptervar(this@gridAlbumActivity, photoAlbumModel!!)
//        mGridView!!.adapter = adapter


        photoAlbumModel = SosRoom.getAppDatabase(this@gridAlbumActivity).callAppDao().getAllPhotoById(id)

        mGridView = this.findViewById(R.id.gallery_grid_view) as GridView
        adapter = gridAdaptervar(this@gridAlbumActivity, photoAlbumModel!!)
        mGridView!!.adapter = adapter





    }



     fun loadPhoto( id:Int)
    {


            val retrofit = Retrofit.Builder()
                    .baseUrl("http://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create<AllServiceAPI>(AllServiceAPI::class.java!!)
            val   apiCall = apiService.getPhotoAlbums(id)
           val appDatabase = SosRoom.getAppDatabase(this)
            apiCall.enqueue(object : Callback<List<PhotoAlbumModel>> {
                override fun onResponse(call: Call<List<PhotoAlbumModel>>?, response: Response<List<PhotoAlbumModel>>?){






                    appDatabase.callAppDao().insertPhotoAllAlbum(response!!.body()!!)

                    photoAlbumModel = SosRoom.getAppDatabase(this@gridAlbumActivity).callAppDao().getAllPhotoById(id)

                    mGridView = findViewById(R.id.gallery_grid_view) as GridView
                    adapter = gridAdaptervar(this@gridAlbumActivity, photoAlbumModel!!)
                    mGridView!!.adapter = adapter










//                    var aa = PhotoAlbumModel()
//                    aa.toggleFavorite()
//

                }
                override fun onFailure(call: Call<List<PhotoAlbumModel>>, t: Throwable) {
                    Log.i("Fail", "Fail " + t.toString())
                }
            })

        }


    }

