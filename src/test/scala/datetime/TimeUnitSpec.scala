package datetime

import org.scalatest.{FunSpec, Matchers}

class TimeUnitSpec extends FunSpec with Matchers {
    describe("spec time unit convert") {
        it("milliseconds convert to milliseconds, seconds, minutes, hours and days") {
            Given.setTime(24 * 60 * 60 * 1000, Milliseconds())
            When.convertToMilliseconds()
            Then.timeShouldBe(24 * 60 * 60 * 1000, Milliseconds())

            When.convertToSeconds()
            Then.timeShouldBe(24 * 60 * 60, Seconds())

            When.convertToMinutes()
            Then.timeShouldBe(24 * 60, Minutes())

            When.convertToHours()
            Then.timeShouldBe(24, Hours())

            When.convertToDays()
            Then.timeShouldBe(1, Days())
        }

        it("days convert to milliseconds, seconds, minutes, hours and days") {
            Given.setTime(1, Days())
            When.convertTo(Days())
            Then.timeShouldBe(1, Days())

            When.convertTo(Hours())
            Then.timeShouldBe(24, Hours())

            When.convertTo(Minutes())
            Then.timeShouldBe(24 * 60, Minutes())

            When.convertTo(Seconds())
            Then.timeShouldBe(24 * 60 * 60, Seconds())

            When.convertTo(Milliseconds())
            Then.timeShouldBe(24 * 60 * 60 * 1000, Milliseconds())
        }
    }

    private var testedValue: Double = Double.NaN
    private var testedUnit: TimeUnit = null

    private def setTime(value: Double, unit: TimeUnit): Unit = {
        this.testedValue = value
        this.testedUnit = unit
    }

    private var transformedValue: Double = Double.NaN
    private var transformedUnit: TimeUnit = null

    private def convertToMilliseconds(): Unit = {
        transformedUnit = Milliseconds()
        transformedValue = testedUnit.toMilliseconds(testedValue)
    }

    private def convertToSeconds(): Unit = {
        transformedUnit = Seconds()
        transformedValue = testedUnit.toSeconds(testedValue)
    }

    private def convertToMinutes(): Unit = {
        transformedUnit = Minutes()
        transformedValue = testedUnit.toMinutes(testedValue)
    }

    private def convertToHours(): Unit = {
        transformedUnit = Hours()
        transformedValue = testedUnit.toHours(testedValue)
    }

    private def convertToDays(): Unit = {
        transformedUnit = Days()
        transformedValue = testedUnit.toDays(testedValue)
    }

    private def convertTo(unit: TimeUnit): Unit = {
        transformedUnit = unit
        transformedValue = unit.convert(testedValue, testedUnit)
    }

    private def timeShouldBe(specValue: Double, specUnit: TimeUnit): Unit = {
        transformedValue should be(specValue)
        transformedUnit should be(specUnit)
    }

    def Given = this

    def When = this

    def Then = this
}
