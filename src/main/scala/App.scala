import AppConfig.{inputFilePath, recordLimit}
import DataModels.Movie

import java.text.NumberFormat
import java.util.Locale
import scala.io.Source
import scala.util.Try

object App extends App {

  /**
   * Assumptions:
   * - If language is absent, it will be considered as "English".
   * - If user reviews are absent, they will be 0.
   * - If budget is absent, it will be 0.
   */


  val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)


  val movies = try {

    Source
      .fromFile(inputFilePath)
      .getLines()
      .slice(1, recordLimit + 1)
      .map(line => {
        val fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim)

        val title = fields(1)
        val year = fields(3).toInt
        val budget = Option(Try(currencyFormat.parse(fields(16).replaceAll("\"", "")).intValue()).getOrElse(0))
        val reviews = Option(Try(fields(20).toInt).getOrElse(0))
        val director = fields(9)
        val country = fields(7)
        val genre = fields(5)
        val language = Option(fields(8))
        val duration = fields(6).toInt
        val votes = fields(15).toInt

        Movie(title, year, budget, reviews, director, country, genre, language, duration, votes)
      })
      .take(recordLimit)
      .toList
  }
  catch {
    case e: java.io.FileNotFoundException =>
      println("File not found exception occurred: "+ e.getMessage)
      List.empty[Movie]
    case e: java.lang.NumberFormatException =>
      e.printStackTrace()
      println("Invalid data format exception occurred: "+ e.getMessage)
      List.empty[Movie]
    case e: Exception =>
      e.printStackTrace()
      println("Unexpected exception occurred: " + e.getMessage)
      List.empty[Movie]
  }

  val analyzer = new IMDBMovieAnalysis(movies)

  println("Task 1: Movies by director Louis Feuillade between 1900 and 1950")

  val task1 = analyzer.titlesByDirectorAndYearRange("Charles L. Gaskill", 1900, 1950)
  task1.foreach(println)

  println()
  println("Task 2: English movies with atleast 1000 user reviews")

  val task2 = analyzer.englishTitlesByUserReview(1000)
  task2.foreach(println)

  println()
  println("Task 3: Movie with the highest budget for USA for the year 1980")

  val task3 = analyzer.highestBudgetByYearAndCountry(1920, "USA")
  task3.foreach(println)

  println()
  println("Task 4: Movie with the longest duration in USA having min 10 votes")

  val task4 = analyzer.longestDurationByCountryAndMinVotes("USA", 10)
  task4.foreach(println)

  println()
  println("Task 5: Count of Movies in USA for different languages between the budget range of $100000 to $200000")

  val task5 = analyzer.countTitlesByLanguageAndBudgetRange(100000, 200000, "USA")
  task5.foreach(println)

}
