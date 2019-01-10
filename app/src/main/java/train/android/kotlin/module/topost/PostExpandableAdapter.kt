package train.android.kotlin.module.topost

import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import train.android.kotlin.R


/**
 * Created by Nut on 1/29/2018.
 */
class PostExpandableAdapter(var context: Context?, postList : List<PostObj>) : ExpandableRecyclerAdapter<PostViewHolder, CmViewHolder>(context, postList) {



    override fun onCreateChildViewHolder(p0: ViewGroup?): CmViewHolder {

        var v = LayoutInflater.from(context)
                .inflate(R.layout.list_item_comment, p0, false)
        return CmViewHolder(v)
    }

    override fun onCreateParentViewHolder(p0: ViewGroup?): PostViewHolder {

        var v = LayoutInflater.from(context)
                .inflate(R.layout.list_item_post, p0, false)
        return PostViewHolder(v)
    }

    override fun onBindParentViewHolder(p0: PostViewHolder?, p1: Int, p2: Any?) {
        var mPostObj = p2 as PostObj
        p0?.mTitle_post_text?.text = mPostObj.title

    }

    override fun onBindChildViewHolder(p0: CmViewHolder?, p1: Int, p2: Any?) {
        val mCmObj = p2 as CmObj
        p0?.mBody_cm_text?.text= mCmObj.body_cm_obj


    }
}