package train.android.kotlin.module.album

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import train.android.kotlin.httpmanager.AllServiceAPI
import train.android.kotlin.httpmanager.AppHttpManager
import train.android.kotlin.model.AlbumModel

/**
 * Created by nack_ on 7/2/2561.
 */
class albumpre(var view: albumcontect.Albumview,var Id: Int):albumcontect.albumPresenter {


    private var mainAllAPI: AllServiceAPI?=null


    fun albumpre(view: albumcontect.Albumview) {
        this.view = view
        mainAllAPI = AppHttpManager.getAPI()
//        view.showTitle("All Star Wars Films")
    }


    override fun getAllAlbum() {

        mainAllAPI = AppHttpManager.getAPI()
        mainAllAPI!!.getAlbums(Id).enqueue(object : Callback<List<AlbumModel>> {
            override fun onResponse(call: Call<List<AlbumModel>>, response: Response<List<AlbumModel>>) {

                    var data=response.body()!!
                    view.showTitle(data)



            }

            override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {
                view.showMessage(t.message!!)
            }
        })
    }


}