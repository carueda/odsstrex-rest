import com.typesafe.config.ConfigFactory
import java.io.File
import java.util.ServiceConfigurationError
import org.mbari.odss.services.planning._
import org.mbari.odss.services.planning.trex.TrexClient
import org.scalatra._
import javax.servlet.ServletContext
import com.typesafe.scalalogging.slf4j.Logging


class ScalatraBootstrap extends LifeCycle with Logging {
  override def init(context: ServletContext) {
    logger.info("init")

    val configFilename = context.getInitParameter("configFile")
    if (configFilename == null) {
      throw new ServiceConfigurationError("Could not retrieve configuration parameter: configFile.  Check web.xml")
    }
    logger.info(s"Loading configuration from $configFilename")
    val configFile = new File(configFilename)
    if (!configFile.canRead) {
      throw new ServiceConfigurationError("Could not read configuration file " + configFile)
    }

    val config = ConfigFactory.parseFile(configFile)
    val trexEndpoint = config.getString("trex.endpoint")
    val trexClient = new TrexClient(trexEndpoint)
    context.mount(new PlanningServlet(trexClient), "/*")
  }

  override def destroy(context: ServletContext) {
    logger.info("destroy")
  }
}
