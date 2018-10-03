package datetime

object SystemTimeProvider {
    var millisecondTimer: () => Long = () => System.currentTimeMillis()

    def getCurrentTimeMilliseconds: Long = {
        millisecondTimer()
    }
}
