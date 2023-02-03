package com.nima.mymood.repository

import com.nima.mymood.database.MoodDao
import com.nima.mymood.model.Day
import com.nima.mymood.model.Effect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class MoodRepository @Inject constructor(private val dao: MoodDao) {

    suspend fun addDay(day: Day) =
        dao.addDay(day)

    suspend fun addEffect(effect: Effect) =
        dao.addEffect(effect)

    fun getEffectsByFK(fk: UUID): Flow<List<Effect>> =
        dao.getEffectsByFK(fk).flowOn(Dispatchers.IO).conflate()

    fun getEffectByRate(rate: List<Int>): Flow<List<Effect>> =
        dao.getEffectByRate(rate).flowOn(Dispatchers.IO).conflate()

    suspend fun getDayByDate(year: Int, month: Int, day: Int): Day =
        dao.getDayByDate(year, month, day)

    fun getDayById(id: UUID): Flow<Day> =
        dao.getDayById(id)

    fun getDayByYear(year: Int): Flow<List<Day>> =
        dao.getDayByYear(year).flowOn(Dispatchers.IO).conflate()

    fun getDayByMonth(year: Int, month: Int): Flow<List<Day>> =
        dao.getDayByMonth(year, month).flowOn(Dispatchers.IO).conflate()

    suspend fun updateDay(day: Day) =
        dao.updateDay(day)

    suspend fun deleteDay(day: Day) =
        dao.deleteDay(day)

    suspend fun deleteDayEffects(fk: UUID) =
        dao.deleteDayEffects(fk)
}