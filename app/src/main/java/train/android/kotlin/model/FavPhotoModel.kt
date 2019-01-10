package train.android.kotlin.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by nack_ on 31/1/2561.
 */
@Entity(tableName = "Favphoto")
class FavPhotoModel() {
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

    fun toggleFavorite() {
        isFavorite = !isFavorite
    }
    @ColumnInfo(name = "albumId")
    var albumId: Int?   = null
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    @ColumnInfo(name = "title")
    var title: String? = null
    @ColumnInfo(name = "url")
    var url:String? =null
    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl:String? =null


}