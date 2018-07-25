package example.tutorial.domain

import scalikejdbc.NamedDB
import scalikejdbc.config.DBs
import scalikejdbc.interpolation.Implicits._

object SampleSelect {

  // 重要！
  import Location.column

  def main(args: Array[String]): Unit = {
    DBs.setupAll()

    val locations = select()
    locations foreach { location =>
      println(location)
    }
  }

  def select(): Vector[Location] = {
    NamedDB('master).readOnly { implicit session =>
      sql"""
         SELECT * FROM UserLocations WHERE locationId <= 5
         """
        .map { set =>
          Location(
            locationId = set int column.locationId,
            address = set string column.address,
          )
        }
        .collection
        .apply[Vector]()
    }
  }
}
