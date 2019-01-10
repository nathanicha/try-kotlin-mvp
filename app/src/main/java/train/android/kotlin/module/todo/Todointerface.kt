package train.android.kotlin.module.todo



interface Todointerface{
    interface TodoView {

//        fun showid(id:Int)
//        fun showtitle(title:String)
//        fun showcompleted(compiler: Boolean)
        fun showMessage(message: String)
        fun showTitle(data: List<Usercheck>)
//        fun showTitle(title: String)
    }


    interface TodoPresenter{
        fun tododata()

    }

}