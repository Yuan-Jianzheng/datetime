package datetime

sealed trait TimeUnit {
    protected val weightRelateToMs: Double
    protected val msWeightRelateToMs = 1.0
    protected val secondsWeightRelateToMs = 1000.0
    protected val minutesWeightRelateToMs = 60 * 1000.0
    protected val hoursWeightRelateToMs = 60 * 60 * 1000.0
    protected val daysWeightRelateToMs = 24 * 60 * 60 * 1000.0

    def toMilliseconds(d: Double): Double = d * weightRelateToMs

    def toSeconds(d: Double): Double = d * weightRelateToMs / secondsWeightRelateToMs

    def toMinutes(d: Double): Double = d * weightRelateToMs / minutesWeightRelateToMs

    def toHours(d: Double): Double = d * weightRelateToMs / hoursWeightRelateToMs

    def toDays(d: Double): Double = d * weightRelateToMs / daysWeightRelateToMs

    def convert(d: Double, unit: TimeUnit): Double = unit.toMilliseconds(d) / weightRelateToMs
}

case class Milliseconds() extends TimeUnit {
    override protected val weightRelateToMs: Double = msWeightRelateToMs
}

case class Seconds() extends TimeUnit {
    override protected val weightRelateToMs: Double = secondsWeightRelateToMs
}

case class Minutes() extends TimeUnit {
    override protected val weightRelateToMs: Double = minutesWeightRelateToMs
}

case class Hours() extends TimeUnit {
    override protected val weightRelateToMs: Double = hoursWeightRelateToMs
}

case class Days() extends TimeUnit {
    override protected val weightRelateToMs: Double = daysWeightRelateToMs
}
