package videogamedb.scriptfundamentals

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt



class CheckResponseCode extends Simulation {

  val httpProtocol=http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  val scn= scenario(name="Video Game DB -3 calls")
    .exec(http(requestName = "Get all video games -1st call")
      .get("/videogame")
    .check(status.is(expected = 400)))
    .pause(duration = 5)

    .exec(http(requestName = "Get specific Name")
      .get("/videogame/1")
    .check(status.in( 200 to 210)))
    .pause(1,10)

    .exec(http(requestName = "Get video game all")
      .get("/videogaame")
    .check(status.not(expected = 400),status.not(expected = 500)))
    .pause(3000.milliseconds)

  setUp(scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)

}
