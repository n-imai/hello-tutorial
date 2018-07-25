package example.tutorial.domain

case class LocationItem(address: String) {
  import Location.column

  def toMap = Map(
    column.address -> address,
  )
}