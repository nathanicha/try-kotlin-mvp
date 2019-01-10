package train.android.kotlin.module.todo

/**
 * Created by nack_ on 26/1/2561.
 */
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import train.android.kotlin.R

class Checkholder(var view:View) : RecyclerView.ViewHolder(view){
    val useridchk = view.findViewById(R.id.check_id) as TextView
    val idchk = view.findViewById(R.id.name_id) as TextView
    val titlechk = view.findViewById(R.id.check_title) as TextView
    val completechk = view.findViewById(R.id.user_completed) as TextView
    var relativeLayoutchk = view.findViewById(R.id.relLayout_check) as RelativeLayout
}