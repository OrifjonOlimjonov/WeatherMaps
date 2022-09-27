package uz.orifjon.weathermaps.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Coordinate(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val latitude:Double,
    val longitude:Double
):Serializable
