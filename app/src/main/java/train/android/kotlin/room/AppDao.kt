package train.android.kotlin.room
import android.arch.persistence.room.*
import train.android.kotlin.model.PhotoAlbumModel
import train.android.kotlin.model.UsersModel
import train.android.kotlin.model.FavPhotoModel

/**
 * Created by nlwd on 1/16/2018.
 */

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(usersModel: UsersModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoAlbum(photoalbums: PhotoAlbumModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsersAll(usersModelAll :List<UsersModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoAllAlbum(photoAlbumModel :List<PhotoAlbumModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavPhoto(fav : FavPhotoModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(usersModel: UsersModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAlbum(photoalbum: PhotoAlbumModel)

    @Delete
    fun deleteUsers(usersModel: UsersModel)

    @Delete
    fun deleteFavPhoto(FavPhotoModel: FavPhotoModel)

    @Delete
    fun deleteAllUsers(list: List<UsersModel>)

    @Query("SELECT * FROM UsersModel")
    fun getUsersAll(): List<UsersModel>

    @Query("SELECT * FROM PhotoAlbumModel")
    fun getPhotoAll(): List<PhotoAlbumModel>

    @Query("SELECT * FROM FavPhotoModel")
    fun getFavPhotoAll(): List<FavPhotoModel>

    @Query("SELECT * FROM PhotoAlbumModel WHERE PhotoAlbumModel.albumid = :albumid")
    fun getAllPhotoById(albumid: Int): List<PhotoAlbumModel>

    @Query("SELECT title FROM PhotoAlbumModel WHERE PhotoAlbumModel.albumid = :albumid")
    fun getTitlePhotoById(albumid: Int?): String

    @Query("SELECT thumbnailUrl FROM PhotoAlbumModel WHERE PhotoAlbumModel.albumid = :albumid")
    fun getThumbnailUrlPhotoById(albumid: Int?): String

    @Query("SELECT COUNT(*) AS id FROM UsersModel")
    fun getCountAll(): Int

    @Query("SELECT * FROM UsersModel WHERE UsersModel.id = :usersId")
    fun getAllUsersById(usersId: Int): List<UsersModel>

    @Query("SELECT name FROM UsersModel WHERE UsersModel.id = :id")
    fun getNameById(id: Int): String

    @Query("SELECT username FROM UsersModel WHERE UsersModel.id = :id")
    fun getUsernameById(id: Int): String

    @Query("SELECT email FROM UsersModel WHERE UsersModel.id = :id")
    fun getEmailById(id: Int): String

    @Query("SELECT * FROM UsersModel WHERE UsersModel.email LIKE :email")
    fun getUsersByEmail(email: String): UsersModel

    @Query("SELECT lat FROM UsersModel WHERE UsersModel.id LIKE :id")
    fun getLatById(id: Int): Double

    @Query("SELECT lng FROM UsersModel WHERE UsersModel.id LIKE :id")
    fun getLngById(id: Int): Double

    @Query("DELETE FROM UsersModel")
    fun deleteTable()

    @Query("SELECT title FROM PhotoAlbumModel WHERE PhotoAlbumModel.id LIKE :id")
    fun getTitleAlbum(id: Int?): String

    @Query("SELECT COUNT(*) AS title FROM PhotoAlbumModel WHERE PhotoAlbumModel.id LIKE:id")
    fun getCountAlbum(id: Int): Int

    @Query("SELECT isFavorite FROM PhotoAlbumModel WHERE PhotoAlbumModel.id LIKE:id")
    fun getFavPhoto(id:Int?): Boolean

}