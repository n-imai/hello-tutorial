package example.tutorial.domain

import scalikejdbc.NamedDB
import scalikejdbc.config.DBs
import scalikejdbc.interpolation.Implicits._

object SampleSetup {
  def main(args: Array[String]): Unit = {

    // 設定ファイルの読み込み & コネクションプール作成 etc
    DBs.setupAll()

    // トランザクション開始
    NamedDB('master).localTx { implicit session =>
      sql"""
        CREATE TABLE UserLocations(
          LocationId INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
          Address NVARCHAR(64) NOT NULL
        )
        """
        .update()
        .apply()

      sql"""
        INSERT INTO UserLocations (Address)
        VALUES (N'千代田区紀尾井町 A')
        """
        .update()
        .apply()

      sql"""
        INSERT INTO UserLocations (Address)
        VALUES (N'千代田区紀尾井町 B')
        """
        .update()
        .apply()

      sql"""
        INSERT INTO UserLocations (Address)
        VALUES (N'千代田区紀尾井町 C')
        """
        .update()
        .apply()
    }

  }
}