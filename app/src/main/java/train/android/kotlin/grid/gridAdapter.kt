@file:Suppress("JoinDeclarationAndAssignment", "SimplifyBooleanWithConstants")

package train.android.kotlin.grid

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import train.android.kotlin.R
import train.android.kotlin.room.SosRoom
import train.android.kotlin.model.PhotoAlbumModel
import train.android.kotlin.model.FavPhotoModel

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
/**
 * Created by nack_ on 26/1/2561.
 */
class gridAdaptervar (var c: Context, var photoalbum: List<PhotoAlbumModel>): BaseAdapter() {


    lateinit var mProgress : RelativeLayout

    override fun getView(position: Int,convertView: View?, parent: ViewGroup): View {




        var convertView = convertView
         val mHolder: ViewHolder


        var idalbum = photoalbum.get(position).id

        Log.i("ans ","ans"+idalbum)





        convertView = LayoutInflater.from(parent.context).inflate(R.layout.list_photo, parent, false)

        mProgress=convertView.findViewById(R.id.gallery_progress)
        mProgress.visibility=VISIBLE

        mHolder = ViewHolder(convertView)
        mHolder.favPhoto.setImageResource(if (SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum)) R.drawable.star_enabled else R.drawable.star_disabled)
        convertView!!.tag = mHolder

        mHolder.txtViewName.text = photoalbum.get(position).title//photoAlbum.get(position).title
        Glide.with(c)
                .load(photoalbum.get(position).thumbnailUrl)
                .centerCrop()
                .crossFade()
                .into(mHolder.imgViewPhoto)

        Log.i("fav","fav: "+SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum)+"title: "+SosRoom.getAppDatabase(c).callAppDao().getTitleAlbum(idalbum!!))





        mHolder.favPhoto.setOnClickListener(View.OnClickListener {



            if( SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum).equals(false))
                {

                    var fav = FavPhotoModel()
                    fav.albumId= photoalbum.get(position).albumId
                    fav.title =photoalbum.get(position).title
                    fav.id =photoalbum.get(position).id
                    fav.thumbnailUrl =photoalbum.get(position).thumbnailUrl
                    fav.url =photoalbum.get(position).url
                    fav.isFavorite = true
                    SosRoom.getAppDatabase(c).callAppDao().insertFavPhoto(fav)

                    var fav2 = PhotoAlbumModel()
                    fav2.albumId= photoalbum.get(position).albumId
                    fav2.title =photoalbum.get(position).title
                    fav2.id =photoalbum.get(position).id
                    fav2.thumbnailUrl =photoalbum.get(position).thumbnailUrl
                    fav2.url =photoalbum.get(position).url
                    fav2.isFavorite = true
                    SosRoom.getAppDatabase(c).callAppDao().insertPhotoAlbum(fav2)


                    mHolder?.favPhoto?.setImageResource( R.drawable.star_enabled )
                    Log.i("fav","favtrue: "+SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum)+"title: "+SosRoom.getAppDatabase(c).callAppDao().getTitleAlbum(idalbum))
                    Toast.makeText(c,"favorite1"+SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum), Toast.LENGTH_SHORT).show()






                }
                else
                {
                    var users = PhotoAlbumModel()
                    users.albumId= photoalbum.get(position).albumId
                    users.title =photoalbum.get(position).title
                    users.id =photoalbum.get(position).id
                    users.thumbnailUrl =photoalbum.get(position).thumbnailUrl
                    users.isFavorite = false
                    SosRoom.getAppDatabase(c).callAppDao().insertPhotoAlbum(users)

                    var users2 = FavPhotoModel()
                    users2.albumId= photoalbum.get(position).albumId
                    users2.title =photoalbum.get(position).title
                    users2.id =photoalbum.get(position).id
                    users2.thumbnailUrl =photoalbum.get(position).thumbnailUrl
                    users2.isFavorite = false
                    mHolder?.favPhoto?.setImageResource( R.drawable.star_disabled )
                    SosRoom.getAppDatabase(c).callAppDao().deleteFavPhoto(users2)
                    Log.i("fav","favtrue: "+SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum)+"title: "+SosRoom.getAppDatabase(c).callAppDao().getTitleAlbum(idalbum))
                    Toast.makeText(c,"favorite1"+SosRoom.getAppDatabase(c).callAppDao().getFavPhoto(idalbum), Toast.LENGTH_SHORT).show()
                }
            Log.i("aaaaa", photoalbum.get(position).isFavorite.toString()+photoalbum.get(position).title)
           //mHolder.favPhoto.setImageResource(if (photoAlbum.get(position).isFavorite) R.drawable.star_enabled else R.drawable.star_disabled)

        })



        return convertView
    }

    class ViewHolder(view: View) {
        var txtViewName: TextView
        var imgViewPhoto: ImageView
        var favPhoto : ImageView









        init {



            favPhoto = view.findViewById(R.id.img_gallery_favorite) as ImageView
            txtViewName = view.findViewById(R.id.text_gallery_name) as TextView
            imgViewPhoto = view.findViewById(R.id.gallery_img_cover) as ImageView






        }
    }

    override fun getItem(position: Int): Any? {
        return null
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        return 0
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {

        return photoalbum.size
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}