package train.android.kotlin.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import train.android.kotlin.model.PhotoAlbumModel
import train.android.kotlin.model.UsersModel
import train.android.kotlin.model.FavPhotoModel

/**
 * Created by nlwd on 1/16/2018.
 */
@Database(entities = [UsersModel::class, PhotoAlbumModel::class, FavPhotoModel::class], version = 1)

abstract class SosRoom : RoomDatabase(){
    companion object {
        fun getAppDatabase(context: Context): SosRoom =
                Room.databaseBuilder(context, SosRoom::class.java,"db_app")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
    }
    abstract fun callAppDao(): AppDao

}