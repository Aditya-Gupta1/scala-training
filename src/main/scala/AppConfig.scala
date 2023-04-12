import com.typesafe.config.{Config, ConfigFactory}

object AppConfig {

  lazy val config: Config = ConfigFactory.load("application.conf")
  lazy val inputFilePath: String = config.getConfig("app").getString("input_file")
  lazy val recordLimit: Int = config.getConfig("app").getInt("records_limit")

}
