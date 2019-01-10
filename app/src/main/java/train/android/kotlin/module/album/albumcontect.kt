package train.android.kotlin.module.album

import train.android.kotlin.model.AlbumModel

/**
 * Created by nack_ on 7/2/2561.
 */
public interface albumcontect {
    interface Albumview{



        fun showMessage(message: String)
         fun showTitle(title: List<AlbumModel>)
    }
    interface albumPresenter {
        fun getAllAlbum()
    }

}