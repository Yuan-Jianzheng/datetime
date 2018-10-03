package datetime

import java.util.TimeZone

import org.scalatest.{FunSpec, Matchers}

class TimeZoneSpec extends FunSpec with Matchers {
    describe("spec time zone set and get") {
        it("set local time zone to UTC") {
            Given.setLocalTimeZone(utcTimeZone)
            Then.currentLocalTimeZoneShouldBe(utcTimeZone)
            Last.resetLocalTimeZone()
        }

        it("set local time zone to America/New_York") {
            Given.setLocalTimeZone(americalTimeZone)
            Then.currentLocalTimeZoneShouldBe(americalTimeZone)
            Last.resetLocalTimeZone()
        }

        it("set local time zone to Asia/Shanghai") {
            Given.setLocalTimeZone(shangHaiTimeZone)
            Then.currentLocalTimeZoneShouldBe(shangHaiTimeZone)
            Last.resetLocalTimeZone()
        }

        it("set local time zone to a time zone which id is not exist, the time zone should be gmt") {
            Given.setLocalTimeZone(TimeZoneService.getTimeZone("time zone id not exist"))
            Then.currentLocalTimeZoneShouldBe(gmtTimeZone)
            Last.resetLocalTimeZone()
        }
    }

    private val utcTimeZone: TimeZone = TimeZoneService.getTimeZone("UTC")

    private val americalTimeZone: TimeZone = TimeZoneService.getTimeZone("America/New_York")

    private val shangHaiTimeZone: TimeZone = TimeZoneService.getTimeZone("Asia/Shanghai")

    private val gmtTimeZone: TimeZone = TimeZoneService.getTimeZone("GMT")

    private def setLocalTimeZone(zone: TimeZone): Unit = {
        TimeZoneService.setLocalTimeZone(zone)
    }

    private def currentLocalTimeZoneShouldBe(spec: TimeZone): Unit = {
        TimeZoneService.getLocalTimeZone should be(spec)
    }

    private def resetLocalTimeZone(): Unit = {
        TimeZoneService.resetLocalTimeZone()
    }

    def Given = this

    def Then = this

    def Last = this
}
