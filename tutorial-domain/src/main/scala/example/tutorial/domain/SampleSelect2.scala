package example.tutorial.domain

import scalikejdbc.NamedDB
import scalikejdbc.config.DBs
import scalikejdbc.interpolation.Implicits._

object SampleSelect2 {

  // 重要！
  import Location.lo

  def main(args: Array[String]): Unit = {
    DBs.setupAll()

    select() foreach { location =>
      println(location)
    }
  }

  def select(): Vector[Location] = {
    NamedDB('master).readOnly { implicit session =>
      sql"""
        SELECT ${lo.result.*} FROM ${Location as lo} WHERE ${lo.locationId} <= 1000
        """
        .map(Location.*)// 重要！
        .collection
        .apply[Vector]()
    }
  }
}