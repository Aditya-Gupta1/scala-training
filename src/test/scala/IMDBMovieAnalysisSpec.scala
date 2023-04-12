import DataModels.Movie
import org.scalatest.flatspec.AnyFlatSpec

class IMDBMovieAnalysisSpec extends AnyFlatSpec {

  val movie1: Movie = Movie("The Godfather", 1972, Some(6), Some(1567), "Francis Ford Coppola", "USA", "Crime", Some("English"), 175, 10000)
  val movie2: Movie = Movie("The Dark Knight", 2008, Some(5), Some(3025), "Christopher Nolan", "USA", "Action", Some("English"), 152, 12000)
  val movie3: Movie = Movie("Léon: The Professional", 1994, Some(2), Some(900), "Luc Besson", "France", "Thriller", Some("French"), 110, 5000)
  val movie4: Movie = Movie("Pulp Fiction", 1994, Some(3), Some(2000), "Quentin Tarantino", "USA", "Crime", Some("English"), 154, 8000)
  val movie5: Movie = Movie("The Shawshank Redemption", 1994, Some(3), Some(2500), "Frank Darabont", "USA", "Drama", Some("English"), 142, 9000)
  val movie6: Movie = Movie("Amélie", 2001, Some(1), Some(1200), "Jean-Pierre Jeunet", "France", "Comedy", Some("French"), 122, 4000)
  val movies: List[Movie] = List(movie1, movie2, movie3, movie4, movie5, movie6)
  val movieAnalysis = new IMDBMovieAnalysis(movies)

  "IMDBMovieAnalysis.titlesByDirectorAndYearRange" should "return movies directed by a given director within a given year range" in {
    val result = movieAnalysis.titlesByDirectorAndYearRange("Quentin Tarantino", 1990, 2000)
    val expected = List(movie4)
    assert(result == expected)
  }

  "IMDBMovieAnalysis.englishTitlesByUserReview" should "return English movies with more than a given number of user reviews, sorted by review count in descending order" in {
    val result = movieAnalysis.englishTitlesByUserReview(2000)
    val expected = List(movie2, movie5)
    assert(result == expected)
  }

  "IMDBMovieAnalysis.highestBudgetByYearAndCountry" should "return the top N movies with the highest budget in a given year and country" in {
    val result = movieAnalysis.highestBudgetByYearAndCountry(1994, "USA", 2)
    val expected = List(movie4, movie5)
    assert(result == expected)
  }

  "IMDBMovieAnalysis.longestDurationByCountryAndMinVotes" should "return the top N movies with the longest duration in a given country, with at least a given number of votes" in {
    val result = movieAnalysis.longestDurationByCountryAndMinVotes("France", 1000, 1)
    val expected = List(movie6)
    assert(result == expected)
  }

  "IMDBMovieAnalysis.countTitlesByLanguageAndBudgetRange" should "return a list of tuples with the count of movies in a given language and budget range, sorted by count in descending order" in {
    val result = movieAnalysis.countTitlesByLanguageAndBudgetRange(2, 4, "USA")
    val expected = List(("English", 2))
    assert(result == expected)
  }
}
