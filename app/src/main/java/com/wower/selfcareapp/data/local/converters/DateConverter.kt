package com.wower.selfcareapp.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

}