package videogamedb.scriptfundamentals

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CheckResponseBodyandExtract extends Simulation{

  val httpProtocol=http.baseUrl("https://www.videogamedb.uk/api/")
    .acceptHeader("application/Json")

  val scn = scenario(name="Check with JSON path")
    .exec(http(requestName = "Get specific game")
    .get("/videogame/1")
    .check(jsonPath("$.name").is("Resident Evil 4")))

    .exec(http(requestName = "Get All video game")
    .get("/videosgames")
    .check(jsonPath("$[1].id").saveAs(key = "gameId")))

    .exec(http(requestName = "Get Specific game")
      .get("/videosgames/#{gameId}")
      .check(jsonPath("$.name").is(expected = "Gran Turismo 3")))


  setUp(scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)


}
