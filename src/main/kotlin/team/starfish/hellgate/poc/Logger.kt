package team.starfish.hellgate.poc

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory
import java.lang.management.ManagementFactory

fun enableDatabaseLogIfDevMode() {
    setLogLevel(if (isDevelopmentMode()) "DEBUG" else "INFO", "com.zaxxer")
    setLogLevel(if (isDevelopmentMode()) "DEBUG" else "INFO", "Exposed")
}

private fun setLogLevel(logLevel: String, packageName: String) {
    val logger = (LoggerFactory.getILoggerFactory() as LoggerContext).getLogger(packageName)
    logger.info(packageName + " current logger level: " + logger.level)
    logger.info("Changing to loglevel: $logLevel")
    logger.level = Level.toLevel(logLevel)
}

private fun isDevelopmentMode(): Boolean {
    val runtimeMxBean = ManagementFactory.getRuntimeMXBean()
    val listOfArguments = runtimeMxBean.inputArguments
    for (arg in listOfArguments) {
        if (arg.contains("io.ktor.development=true")) return true
    }
    return false
}
