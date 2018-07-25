package example.tutorial.domain

import scalikejdbc.config.DBs
import scalikejdbc.{NamedDB, insert, withSQL}

object SampleInsert2 {

  def main(args: Array[String]): Unit = {
    DBs.setupAll()

    insertLocation(
      LocationItem(address = s"町田")
    )
    // INSERT したレコードを確認
    SampleSelect2.select().foreach(println)
  }

  def insertLocation(item: LocationItem): Unit = {
    NamedDB('master).localTx { implicit session =>
      withSQL {
        insert
          .into(Location)
          .namedValues(item.toMap)
      }.update().apply()
    }
  }
}