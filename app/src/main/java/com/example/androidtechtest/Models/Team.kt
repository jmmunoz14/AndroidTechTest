package com.example.androidtechtest.Models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "team")
data class Team(
    @PrimaryKey
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("idLeague")
    @ColumnInfo(name = "idLeague")
    val idLeague: String,
    @SerializedName("strAlternate")
    @ColumnInfo(name = "strAlternate")
    val strAlternate: String,
    @SerializedName("intFormedYear")
    @ColumnInfo(name = "intFormedYear")
    val intFormedYear: String,
    @SerializedName("strDescriptionEN")
    @ColumnInfo(name = "strDescriptionEN")
    val strDescriptionEN: String,
    @SerializedName("strTeamBadge")
    @ColumnInfo(name = "strTeamBadge")
    val strTeamBadge: String,
    @SerializedName("strTeamJersey")
    @ColumnInfo(name = "strTeamJersey")
    val strTeamJersey: String,
    @SerializedName("strWebsite")
    @ColumnInfo(name = "strWebsite")
    val strWebsite: String,
    @SerializedName("strFacebook")
    @ColumnInfo(name = "strFacebook")
    val strFacebook: String,
    @SerializedName("strTwitter")
    @ColumnInfo(name = "strTwitter")
    val strTwitter: String,
    @SerializedName("strInstagram")
    @ColumnInfo(name = "strInstagram")
    val strInstagram: String,
    @SerializedName("strStadium")
    @ColumnInfo(name = "strStadium")
    val strStadium: String
) : Parcelable
