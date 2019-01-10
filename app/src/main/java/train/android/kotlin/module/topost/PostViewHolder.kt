package train.android.kotlin.module.topost

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import train.android.kotlin.R

/**
 * Created by Nut on 1/29/2018.
 */
class PostViewHolder(itemView:View) : ParentViewHolder(itemView) {

    var mTitle_expand_arrow:ImageButton = itemView.findViewById(R.id.title_expand_arrow)

    var mTitle_post_text:TextView= itemView.findViewById(R.id.title_post_text)
//    var mBody_post_text:TextView = itemView.findViewById(R.id.body_post_text)
//    var mId_post_text:TextView= itemView.findViewById(R.id.id_post_text)
//    var mUserid_post_text:TextView= itemView.findViewById(R.id.userid_post_text)

}