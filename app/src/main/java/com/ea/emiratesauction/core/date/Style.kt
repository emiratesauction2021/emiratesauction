package com.ea.emiratesauction.core.date

interface ASDateStyleProtocol{
    var value: String
}

enum class DayStyle (rawValue: String): ASDateStyleProtocol{

    /// The day of the month. A single d will use 1 for January 1st. example 1
    number ("d"),

    /// The day of the month. A double d will use 01 for January 1st. example 05
    zeroPaddedNumber("dd"),

    /// The day of week in the month. example  3rd Tuesday in December
    dayOfWeekInMonth ("F"),

    /// The abbreviation for the day of the week. example Tue
    abbreviationDayOfWeek ("E"),

    /// The wide name of the day of the week. example Tuesday
    fullDayOfWeek ("EEEE"),

    /// The narrow day of week. example T
    narrowDayOfWeek ("EEEEE"),

    /// The short day of week. example Tu
    shortDayOfWeek ("EEEEEE");

    override var value: String = rawValue

}

enum class HourStyle(rawValue: String): ASDateStyleProtocol{

    /// The 12-hour hour. example 4
    twelveHour("h"),

    /// The 12-hour hour padding with a zero if there is only 1 digit. example 05
    zeroPaddedTwelveHour("hh"),

    /// The 24-hour hour. example 23
    twentyFourHour("H"),

    /// The 24-hour hour padding with a zero if there is only 1 digit. example 03
    zeroPaddedTwentyFourHour("HH"),

    /// AM / PM for 12-hour time formats
    AMorPM("a");

    override var value: String = rawValue
}

enum class MinuteStyle(rawValue: String): ASDateStyleProtocol{

    /// The minute, with no padding for zeroes. example 5
    number("m"),

    /// The minute with zero padding. example 05
    zeroPaddedNumber("mm");

    override var value: String = rawValue
}

enum class MonthStyle(rawValue: String): ASDateStyleProtocol{

    /// The numeric month of the year. A single M will use '1' for January.
    number("M"),

    /// The numeric month of the year. A double M will use '01' for January.
    zeroPaddedNumber("MM"),

    /// The shorthand name of the month. example Dec
    shortName("MMM"),

    /// Full name of the month. example  December
    fullName("MMMM"),

    /// Narrow name of the month. example D
    narrowName("MMMMM");

    override var value: String = rawValue
}


enum class QuarterStyle(rawValue: String): ASDateStyleProtocol{

    /// The quarter of the year. example 4
    number("Q"),

    /// The quarter with  zero padding. example 04
    zeroPaddedNumber("QQ"),

    /// Quarter including "Q". example 4Q
    qAndNumber("QQQ"),

    /// Quarter spelled out. example 4th quarter
    spelledOut("QQQQ");

    override var value: String = rawValue
}



enum class SecondStyle(rawValue: String): ASDateStyleProtocol {

    /// The seconds, with no padding for zeroes. example 1
    number("s"),

    /// The seconds with zero padding. example 02
    zeroPaddedNumber("ss"),

    /// The milliseconds. example 123 
    milliseconds("SSS");

    override var value: String = rawValue

}

enum class Separator(rawValue: String): ASDateStyleProtocol {

    /// dash separator  `-`
    dash("-"),

    /// slash separator `/`
    slash("/"),

    /// colon separator  `:`
    colon(":"),

    /// space separator  
    space(" ");

    override var value: String = rawValue
}

enum class TimeZoneStyle(rawValue: String): ASDateStyleProtocol {

    /// The 3 letter name of the time zone. Falls back to GMT-08:00 (hour offset) if the name is not known. example CST
    threeLetterName("zzz"),

    /// The expanded time zone name, falls back to GMT-08:00 (hour offset) if name is not known. example Central Standard Time
    expandedName("zzzz"),

    /// Time zone with abbreviation and offset. example CST-06:00
    timeZone("ZZZZ"),

    /// RFC 822 GMT format. Can also match a literal Z for Zulu (UTC) time. example  -0600
    RFC822("Z"),

    /// ISO 8601 time zone format. example -06:00    
    ISO8601("ZZZZZ");

    override var value: String = rawValue
}


enum class YearStyle(rawValue: String): ASDateStyleProtocol {
    /// Year, no padding. example 2008
    noPadding("y"),

    /// Year, two digits (padding with a zero if necessary). example 08
    twoDigits("yy"),

    /// Year, minimum of four digits (padding with zeros if necessary). example 2008
    fourDigits("yyyy");

    override var value: String = rawValue
}