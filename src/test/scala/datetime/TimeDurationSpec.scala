package datetime

import org.scalatest.{FunSpec, Matchers}

class TimeDurationSpec extends FunSpec with Matchers {
    describe("spec time duration convert") {
        it("milliseconds convert to milliseconds, seconds, minutes, hours and days") {
            Given.setTimeDurations(TimeDuration(24 * 60 * 60 * 1000, Milliseconds()))
            When.transformToMilliseconds()
            Then.timeDurationLengthShouldBe(24 * 60 * 60 * 1000)

            When.transformToSeconds()
            Then.timeDurationLengthShouldBe(24 * 60 * 60)

            When.transformToMinutes()
            Then.timeDurationLengthShouldBe(24 * 60)

            When.transformToHours()
            Then.timeDurationLengthShouldBe(24)

            When.transformToDays()
            Then.timeDurationLengthShouldBe(1)
        }
    }

    describe("spec time duration logical operation") {
        it("== logical operation") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(300, "sec").get)
            Then.firstEqualSecondTimeDuration()
        }

        it("!= logical operation") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(6, Minutes()))
            Then.firstNotEqualSecondTimeDuration()
        }

        it("> logical operation") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(299, Seconds()))
            Then.firstGreaterSecondTimeDuration()
        }

        it(">= logical operation") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(300, Seconds()))
            Then.firstGreaterEqualSecondTimeDuration()
        }

        it("< logical operation") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(1, Hours()))
            Then.firstLessSecondTimeDuration()
        }

        it("<= logical operation") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(300, Seconds()))
            Then.firstLessEqualSecondTimeDuration()
        }
    }

    describe("spec time duration arithmetic operation") {
        it("time duration add time duration") {
            Given.setTimeDurations(TimeDuration(5, Minutes()), TimeDuration(1, Hours()))
            When.firstAddSecondTimeDuration()
            Then.timeDurationShouldBe(TimeDuration(65, Minutes()))
        }

        it("time duration subtract time duration") {
            Given.setTimeDurations(TimeDuration(1, Hours()), TimeDuration(5, Minutes()))
            When.firstSubSecondTimeDuration()
            Then.timeDurationShouldBe(TimeDuration(55, Minutes()))
        }

        it("time duration negative") {
            Given.setTimeDurations(TimeDuration(5, Minutes()))
            When.negativeTimeDuration()
            Then.timeDurationShouldBe(TimeDuration(-300, Seconds()))
        }

        it("time duration multiply factor") {
            Given.setTimeDurations(TimeDuration(5, Minutes()))
            When.scaleWith(12)
            Then.timeDurationShouldBe(TimeDuration(1, Hours()))
        }

        it("time duration divide factor") {
            Given.setTimeDurations(TimeDuration(5, Minutes()))
            When.divideWith(2.5)
            Then.timeDurationShouldBe(TimeDuration(2, Minutes()))
        }

        it("time duration divide time duration") {
            Given.setTimeDurations(TimeDuration(5, Minutes()))
            When.divideWith(TimeDuration(12, Seconds()))
            Then.dividedResultShouldBe(25)
        }
    }

    private var timeDurations: List[TimeDuration] = null

    private def setTimeDurations(durations: TimeDuration*): Unit = {
        timeDurations = durations.toList
    }

    private var testedValue: Double = Double.NaN

    private def transformToMilliseconds(): Unit = {
        testedValue = timeDurations.head.toMilliseconds
    }

    private def transformToSeconds(): Unit = {
        testedValue = timeDurations.head.toSeconds
    }

    private def transformToMinutes(): Unit = {
        testedValue = timeDurations.head.toMinutes
    }

    private def transformToHours(): Unit = {
        testedValue = timeDurations.head.toHours
    }

    private def transformToDays(): Unit = {
        testedValue = timeDurations.head.toDays
    }

    private def timeDurationLengthShouldBe(specValue: Double): Unit = {
        testedValue should be(specValue)
    }

    private def firstEqualSecondTimeDuration(): Unit = {
        timeDurations.head == timeDurations.tail.head should be(true)
    }

    private def firstNotEqualSecondTimeDuration(): Unit = {
        timeDurations.head != timeDurations.tail.head should be(true)
    }

    private def firstGreaterSecondTimeDuration(): Unit = {
        timeDurations.head > timeDurations.tail.head should be(true)
    }

    private def firstGreaterEqualSecondTimeDuration(): Unit = {
        timeDurations.head >= timeDurations.tail.head should be(true)
    }

    private def firstLessSecondTimeDuration(): Unit = {
        timeDurations.head < timeDurations.tail.head should be(true)
    }

    private def firstLessEqualSecondTimeDuration(): Unit = {
        timeDurations.head <= timeDurations.tail.head should be(true)
    }

    private var timeDuration: TimeDuration = null

    private def firstAddSecondTimeDuration(): Unit = {
        timeDuration = timeDurations.head + timeDurations.tail.head
    }

    private def firstSubSecondTimeDuration(): Unit = {
        timeDuration = timeDurations.head - timeDurations.tail.head
    }

    private def negativeTimeDuration(): Unit = {
        timeDuration = -timeDurations.head
    }

    private def scaleWith(factor: Double): Unit = {
        timeDuration = timeDurations.head * factor
    }

    private def divideWith(factor: Double): Unit = {
        timeDuration = timeDurations.head / factor
    }

    private def divideWith(duration: TimeDuration): Unit = {
        testedValue = timeDurations.head / duration
    }

    private def dividedResultShouldBe(spec: Double): Unit = {
        testedValue should be(spec)
    }

    private def timeDurationShouldBe(specDuration: TimeDuration): Unit = {
        timeDuration == specDuration should be(true)
    }

    def Given = this

    def When = this

    def Then = this
}
