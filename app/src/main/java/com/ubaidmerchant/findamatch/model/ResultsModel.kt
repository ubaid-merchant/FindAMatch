package com.ubaidmerchant.findamatch.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomMasterTable.TABLE_NAME

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = ResultsModel.TABLE_NAME)
data class ResultsModel(
    var gender: String?,
    @field:Embedded(prefix = "name")
    var name: NameModel?,
    @field:Embedded(prefix = "location")
    var location: LocationModel?,
    @PrimaryKey var email: String,
    @field:Embedded(prefix = "login")
    var login: LoginModel?,
    @field:Embedded(prefix = "dob")
    var dob: RegisteredModel?,
    @field:Embedded(prefix = "registered")
    var registered: RegisteredModel?,
    var phone: String?,
    var cell: String?,
    @field:Embedded(prefix = "id")
    var id: IdModel?,
    @field:Embedded(prefix = "picture")
    var picture: PictureModel?,
    var nat: String?
) {
    companion object {
        const val TABLE_NAME = "fam_results"
    }
}