package train.android.kotlin.module.todo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.VISIBLE
import android.widget.*
import train.android.kotlin.R



class todoAcitivity  : AppCompatActivity(),Todointerface.TodoView  {

    lateinit var adapter:todoadapter
    var recyclerView: RecyclerView?=null
    lateinit var mProgress : RelativeLayout

    lateinit var todopresenter : Todointerface.TodoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        var aa = intent.getIntExtra("Id",0)

        recyclerView = this.findViewById(R.id.relLayout_check)
        mProgress = findViewById(R.id.progress_rel_todo)
        mProgress.visibility=VISIBLE


        //var aa = intent.getIntExtra("Id",0)
        todopresenter = TodoPresenter(this ,aa)
        todopresenter.tododata()
//        mProgress.visibility=GONE
    }

//    override fun showTitle(title: String) { getDataSuccess(title)
//    }

//    override fun showid(id:Int) { todoId.setText(id) }
//
//    override fun showtitle(title: String) { todotitle.setText(title) }
//
//    override fun showcompleted(compiler: Boolean) { todocomplete.setText(compiler.toString()) }

    override fun showMessage(message: String) {
        Toast.makeText(this@todoAcitivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showTitle(data: List<Usercheck>) {
        //recyclerView = findViewById(R.id.recyclertodo)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager=LinearLayoutManager(this)

        adapter = todoadapter(this@todoAcitivity, data)
        recyclerView!!.adapter = adapter

    }




}
