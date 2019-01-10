package train.android.kotlin.module.topost


import android.view.View
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import train.android.kotlin.R

/**
 * Created by Nut on 1/29/2018.
 */
class CmViewHolder(itemView: View) : ChildViewHolder(itemView){
    var mBody_cm_text:TextView=itemView!!.findViewById(R.id.body_cm_text)
//    var mPostid_cm_text:TextView=itemView!!.findViewById(R.id.postid_cm_text)
//    var mName_cm_text:TextView=itemView!!.findViewById(R.id.name_cm_text)
//    var mId_cm_text:TextView=itemView!!.findViewById(R.id.id_cm_text)
//    var mEmail_cm_text:TextView=itemView!!.findViewById(R.id.email_cm_text)

}