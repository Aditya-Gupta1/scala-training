object DataModels {

  case class Movie(title: String,
                   year: Int,
                   budget: Option[Int],
                   reviews: Option[Int],
                   director: String,
                   country: String,
                   genre: String,
                   language: Option[String],
                   duration: Int,
                   votes: Int)

}
