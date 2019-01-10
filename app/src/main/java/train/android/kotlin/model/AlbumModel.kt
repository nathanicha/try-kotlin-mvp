package train.android.kotlin.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by nack_ on 25/1/2561.
 */


class AlbumModel() {

    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null

    @ColumnInfo(name = "name")
    var id: Int? = null

    @ColumnInfo(name = "username")
    var title: String? = null
}