package train.android.kotlin.module.main.contractor

import android.view.Menu
import android.view.MenuItem
import train.android.kotlin.model.UsersModel

/**
 * Created by Nut on 2/7/2018.
 */
interface MainContractor {
    interface MainView {
        fun onBackPressed()
        fun onCreateOptionsMenu(menu: Menu) : Boolean
        fun onOptionsItemSelected(item: MenuItem) : Boolean
        fun onNavigationItemSelected(item: MenuItem) : Boolean
        fun getDataSuccess(list: List<UsersModel>)
        fun getDataFailure(message: String)
    }

    interface MainPresenter{
        fun getData()

    }
}