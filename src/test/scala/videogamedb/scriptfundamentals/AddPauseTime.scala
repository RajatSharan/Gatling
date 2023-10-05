package videogamedb.scriptfundamentals;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt;


class AddPauseTime extends Simulation {

val httpProtocol=http.baseUrl("https://videogamedb.uk/api")
  .acceptHeader("application/json")

  val scn= scenario(name="Video Game DB -3 calls")
    .exec(http(requestName = "Get all video games -1st call")
    .get("/videogame"))
    .pause(duration = 5)

    .exec(http(requestName = "Get specific Name")
    .get("/videogame/1"))
    .pause(1,10)

    .exec(http(requestName = "Get video game all")
    .get("/videogaame"))
    .pause(3000.milliseconds)

  setUp(scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)
}
