package com.flow.assignment.util

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class Converters {
    companion object{
        @JvmStatic
        @TypeConverter
        fun longToDate(value: Long): LocalDateTime {
            return LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC)
        }

        @JvmStatic
        @TypeConverter
        fun dateToLong(date: LocalDateTime): Long {
            return date.toEpochSecond(ZoneOffset.UTC)
        }
    }
}