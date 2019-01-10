@file:Suppress("JoinDeclarationAndAssignment", "SimplifyBooleanWithConstants")

package train.android.kotlin.module.main.gallery.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import train.android.kotlin.R
import train.android.kotlin.model.FavPhotoModel
import train.android.kotlin.room.SosRoom

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
/**
 * Created by nack_ on 26/1/2561.
 */
class GridFavAdapter (var context: Context, var photoAlbum: List<FavPhotoModel>): BaseAdapter() {

    override fun getView(position: Int,convertView: View?, parent: ViewGroup): View {

        var convertView = convertView
        val mHolder: ViewHolder

        convertView = LayoutInflater.from(parent.context).inflate(R.layout.list_photo, parent, false)
        mHolder = ViewHolder(convertView)
        mHolder.favPhoto.setImageResource(if (photoAlbum.get(position).isFavorite) R.drawable.star_enabled else R.drawable.star_disabled)
        Log.i("testing","Fav: "+photoAlbum.get(position).isFavorite+", Title: "+photoAlbum.get(position).title)
        convertView!!.tag = mHolder

        mHolder.txtViewName.text = photoAlbum.get(position).title
        Glide.with(context)
                .load(photoAlbum.get(position).thumbnailUrl)
                .centerCrop()
                .crossFade()
                .into(mHolder.imgViewPhoto)

        mHolder.favPhoto.setOnClickListener(View.OnClickListener {

            if(  photoAlbum.get(position).isFavorite.equals(false))
                {

                    mHolder?.favPhoto?.setImageResource( R.drawable.star_enabled )
                    Toast.makeText(context,"favorite"+photoAlbum.get(position).isFavorite, Toast.LENGTH_SHORT).show()

                }
                else
                {
                    var users = FavPhotoModel()
                    users.albumId= photoAlbum.get(position).albumId
                    users.title =photoAlbum.get(position).title
                    users.id =photoAlbum.get(position).id
                    users.thumbnailUrl =photoAlbum.get(position).thumbnailUrl
                    users.isFavorite = false
                    mHolder?.favPhoto?.setImageResource( R.drawable.star_disabled )
                    photoAlbum.get(position).toggleFavorite()
                    SosRoom.getAppDatabase(context).callAppDao().deleteFavPhoto(users)
                    Toast.makeText(context,"favorite"+photoAlbum.get(position).isFavorite, Toast.LENGTH_SHORT).show()
                }
            Log.i("aaaaa", photoAlbum.get(position).isFavorite.toString()+photoAlbum.get(position).title)
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
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return photoAlbum.size
    }

}