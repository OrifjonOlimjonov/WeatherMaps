package uz.orifjon.weathermaps.database

import androidx.room.*
import uz.orifjon.weathermaps.database.Coordinate

@Dao
interface CoordinateDao {


    @Insert
    fun add(coordinate: Coordinate)


    @Delete
    fun delete(coordinate: Coordinate)

    @Update
    fun update(coordinate: Coordinate)

    @Query("SELECT * FROM coordinate")
    fun list():List<Coordinate>


}