package train.android.kotlin.module.main.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import train.android.kotlin.R
import train.android.kotlin.room.SosRoom
import train.android.kotlin.module.main.adapter.CostompagerAdapter
import train.android.kotlin.module.main.fragment.FragmentOne
import train.android.kotlin.module.main.fragment.FragmentTwo
import train.android.kotlin.model.UsersModel
import train.android.kotlin.module.main.gallery.activity.GalleryActivity
import train.android.kotlin.module.main.contractor.MainContractor
import train.android.kotlin.module.main.presenter.MainPresenter


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener , MainContractor.MainView {


    private var mAdapter = CostompagerAdapter(supportFragmentManager)
    private var mPresenter: MainContractor.MainPresenter= MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActionBar()
        mPresenter.getData()

    }

    private fun setActionBar(){
        setSupportActionBar(main_toolbar)

        var toggle = ActionBarDrawerToggle(this, main_drawer_layout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        main_nav_view.setNavigationItemSelectedListener(this)
    }

    private fun setAdapter(){
        mAdapter.addFragments(FragmentOne(),"Map")
        mAdapter.addFragments(FragmentTwo(),"Lists")
        main_custom_viewpager.adapter = mAdapter
        main_custom_tab_layout.setupWithViewPager(main_custom_viewpager)
    }

    override fun getDataSuccess(list: List<UsersModel>) {
        SosRoom.getAppDatabase(applicationContext).callAppDao().insertUsersAll(list)
        setAdapter()
    }

    override fun getDataFailure(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_gallery -> {
                val intent =  Intent(this, GalleryActivity::class.java)
                Toast.makeText(this,"Click", Toast.LENGTH_SHORT).show()
                this.startActivity(intent)
            }
        }

        main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}














