package train.android.kotlin.model

import android.arch.persistence.room.ColumnInfo

/**
 * Created by nlwd on 1/17/2018.
 */

class CompanyModel {
    @ColumnInfo(name = "name_company")
     var name_company: String? = null
    @ColumnInfo(name = "catchPhrase")
     var catchPhrase: String? = null
    @ColumnInfo(name = "bs")
     var bs: String? = null


}