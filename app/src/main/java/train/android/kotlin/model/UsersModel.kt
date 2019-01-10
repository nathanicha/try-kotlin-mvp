package train.android.kotlin.model

/**
 * Created by nack_ on 16/1/2561.
 */

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "UsersModel")
class UsersModel()  {

    @PrimaryKey(autoGenerate = true)
     var id: Int?   = null

    @ColumnInfo(name = "name")
     var name: String? = null

    @ColumnInfo(name = "username")
     var username: String? = null

    @ColumnInfo(name = "email")
     var email: String? = null

    @Embedded
    var address: AddressModel? = null

    @ColumnInfo(name = "phone")
     var phone:  String? = null

    @ColumnInfo(name = "website")
     var website:  String? = null

    @Embedded
    var company: CompanyModel? = null









}


