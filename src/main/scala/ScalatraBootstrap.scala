import org.mbari.odss.services.planning._
import org.scalatra._
import javax.servlet.ServletContext
import com.typesafe.scalalogging.slf4j.Logging


class ScalatraBootstrap extends LifeCycle with Logging {
  override def init(context: ServletContext) {
    logger.info("init")
    context.mount(new PlanningServlet, "/*")
  }

  override def destroy(context: ServletContext) {
    logger.info("destroy")
  }
}
