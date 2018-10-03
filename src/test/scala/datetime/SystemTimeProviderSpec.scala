package datetime

import org.scalatest.{FunSpec, Matchers}

class SystemTimeProviderSpec extends FunSpec with Matchers {
    describe("spec system time provider") {
        it("set system time provider timer and after reset it") {
            Given.setTimerToDesignatedValue(1000L)
            Then.currentMillisecondsShouldBe(1000L)
            Last.resetTimer()
        }
    }

    private def setTimerToDesignatedValue(value: Long): Unit = {
        SystemTimeProvider.millisecondTimer = () => value
    }

    private def currentMillisecondsShouldBe(spec: Long): Unit = {
        SystemTimeProvider.getCurrentTimeMilliseconds should be(spec)
    }

    private def resetTimer(): Unit = {
        SystemTimeProvider.millisecondTimer = () => System.currentTimeMillis()
    }

    def Given = this

    def Then = this

    def Last = this
}
