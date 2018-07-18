package example.tutorial.domain

import scalikejdbc.NamedDB
import scalikejdbc.config.DBs
import scalikejdbc.interpolation.Implicits._

object SampleInsert {
  def main(args: Array[String]): Unit = {

    // 設定ファイルの読み込み & コネクションプール作成 etc
    DBs.setupAll()

    NamedDB('master).localTx { implicit session =>
      (1 to 10) foreach { number =>
        val address = s"千代田区紀尾井町 $number"
        sql"""
          INSERT INTO UserLocations (Address)
          VALUES ($address)
          """
          .update()
          .apply()
      }
    }
  }
}