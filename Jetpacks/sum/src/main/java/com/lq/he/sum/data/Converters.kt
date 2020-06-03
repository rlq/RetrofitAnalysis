package com.lq.he.sum.data

import androidx.room.TypeConverter
import java.util.*

// 作用是什么 _将时间戳 与 日期互转
class Converters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar = Calendar.getInstance().apply {
        timeInMillis = value
    }
}