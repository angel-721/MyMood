package com.nima.mymood.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nima.mymood.model.Day
import com.nima.mymood.model.Effect
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDay(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEffect(effect: Effect)

    @Query("select * from effect where foreignKey = :fk")
    fun getEffectsByFK(fk: UUID): Flow<List<Effect>>

    @Query("select * from effect where rate in (:rate) order by rate asc")
    fun getEffectByRate(rate: List<Int>): Flow<List<Effect>>

    @Query("select * from day where year = :year and month = :month and day = :day")
    suspend fun getDayByDate(year: Int, month: Int, day: Int): Day

    @Query("select * from day where id = :id")
    fun getDayById(id: UUID): Flow<Day>

    @Query("select * from day order by year desc, month desc, day desc")
    fun getAllDays(): Flow<List<Day>>

    @Query("select * from effect")
    fun getAllEffects(): Flow<List<Effect>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDay(day: Day)

    @Delete
    suspend fun deleteDay(day: Day)

    @Delete
    suspend fun deleteEffect(effect: Effect)

    @Query("delete from effect where foreignKey = :fk")
    suspend fun deleteDayEffects(fk: UUID)

    @Query("delete from effect")
    suspend fun deleteAllEffects()

    @Query("delete from day")
    suspend fun deleteAllDays()
}