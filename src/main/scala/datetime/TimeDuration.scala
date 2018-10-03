package datetime

object TimeDuration {
    def apply(length: Double, unit: TimeUnit): TimeDuration = new TimeDuration(length, unit)

    def apply(length: Double, unit: String): Option[TimeDuration] = timeUnitLabels.get(unit).map(u => apply(length, u))

    private lazy val timeUnitLabels: Map[String, TimeUnit] = List(
        Milliseconds() -> List("ms", "milli", "millisecond"),
        Seconds() -> List("s", "sec", "second"),
        Minutes() -> List("min", "minute"),
        Hours() -> List("h", "hour"),
        Days() -> List("d", "day")
    ).flatMap(r => r._2.map(_ -> r._1)).toMap
}

class TimeDuration private(val length: Double, val unit: TimeUnit) {

    def toMilliseconds: Double = toUnit(Milliseconds())

    def toSeconds: Double = toUnit(Seconds())

    def toMinutes: Double = toUnit(Minutes())

    def toHours: Double = toUnit(Hours())

    def toDays: Double = toUnit(Days())

    def toUnit(unit: TimeUnit): Double = unit.convert(length, this.unit)

    def ==(duration: TimeDuration): Boolean = math.abs(length - duration.toUnit(unit)) <= Double.MinPositiveValue

    def !=(duration: TimeDuration): Boolean = ! ==(duration)

    def >(duration: TimeDuration): Boolean = length > duration.toUnit(unit)

    def <(duration: TimeDuration): Boolean = length < duration.toUnit(unit)

    def >=(duration: TimeDuration): Boolean = ! <(duration)

    def <=(duration: TimeDuration): Boolean = ! >(duration)

    def +(duration: TimeDuration): TimeDuration = TimeDuration(length + duration.toUnit(unit), unit)

    def -(duration: TimeDuration): TimeDuration = TimeDuration(length - duration.toUnit(unit), unit)

    def unary_- : TimeDuration = TimeDuration(-length, unit)

    def *(factor: Double): TimeDuration = TimeDuration(length * factor, unit)

    def /(factor: Double): TimeDuration = TimeDuration(length / factor, unit)

    def /(duration: TimeDuration): Double = length / duration.toUnit(unit)
}