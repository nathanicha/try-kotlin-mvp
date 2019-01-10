package train.android.kotlin.module.topost

import com.bignerdranch.expandablerecyclerview.Model.ParentObject

/**
 * Created by Nut on 1/29/2018.
 */
class PostObj(var title_n:String,var mCommentList :MutableList<CmObj>) : ParentObject {
    override fun setChildObjectList(p0: MutableList<Any>?) {

    }
    var userId:Int?=null
    var id:Int?=null
    var title:String?=title_n
    var body:String?=null


    override fun getChildObjectList(): MutableList<CmObj> {
        return mCommentList
    }
}