package example.tutorial.domain

import scalikejdbc.{SQLSyntaxSupport, WrappedResultSet}

case class Location(
                     locationId: Int,
                     address: String,
                   )
object Location extends SQLSyntaxSupport[Location] {

  // snake_case への変換を無効にする指定
  override val useSnakeCaseColumnName = false

  // コネクションプールの名前 (*.conf で指定している名前)
  override val connectionPoolName = 'master

  // テーブル名
  override val tableName = "UserLocations"

  // テーブル名やカラム名の生成を担う
  val lo = syntax("lo")

  /*
    取得したレコードから Location 型への変換処理。
    頻繁に再利用されるのでコンパニオンオブジェクト内に定義しておく。
   */
  def *(set: WrappedResultSet): Location = {
    Location(
      // lo.resultName を通してカラム名を取得していることに注意
      locationId = set int lo.resultName.locationId,
      address = set string lo.resultName.address,
    )
  }
}