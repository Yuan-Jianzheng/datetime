package datetime

import java.util.TimeZone

object TimeZoneService {
    def getLocalTimeZone: TimeZone = {
        TimeZone.getDefault
    }

    def setLocalTimeZone(zone: TimeZone): Unit = {
        TimeZone.setDefault(zone)
    }

    def resetLocalTimeZone(): Unit = {
        //when time zone is null, reset the default time zone to the value
        //that is the originally time zone when vm is first started
        setLocalTimeZone(null)
    }

    //id can be full name such as "America/Los_Angeles", "Asia/Shanghai"
    //abbreviation name such as "PST", "UTC"
    //custom name such as "GMT-8:00"
    //if the given ID can not be understood the return is GMT zone
    def getTimeZone(id: String) = {
        TimeZone.getTimeZone(id)
    }
}
