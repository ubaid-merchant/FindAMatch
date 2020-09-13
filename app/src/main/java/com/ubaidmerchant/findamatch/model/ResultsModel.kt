package com.ubaidmerchant.findamatch.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = ResultsModel.TABLE_NAME)
data class ResultsModel(
    var gender: String? = null,
    @field:Embedded(prefix = "name")
    var name: NameModel? = null,
    @field:Embedded(prefix = "location")
    var location: LocationModel? = null,
    @PrimaryKey var email: String,
    @field:Embedded(prefix = "login")
    var login: LoginModel? = null,
    @field:Embedded(prefix = "dob")
    var dob: RegisteredModel? = null,
    @field:Embedded(prefix = "registered")
    var registered: RegisteredModel? = null,
    var phone: String? = null,
    var cell: String? = null,
    @field:Embedded(prefix = "id")
    var id: IdModel? = null,
    @field:Embedded(prefix = "picture")
    var picture: PictureModel? = null,
    var nat: String? = null,
    var status: String? = null,
    var isSelected: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "fam_results"
    }
}