import DataModels.Movie

class IMDBMovieAnalysis(movies: List[Movie]) {

  def titlesByDirectorAndYearRange(director: String, startYear: Int, endYear: Int): List[Movie] = {
    movies.filter(movie => movie.year >= startYear && movie.year <= endYear && movie.director == director)
  }

  def englishTitlesByUserReview(reviewFilter: Int): List[Movie] = {
    movies.filter(movie => movie.reviews.getOrElse(0) > reviewFilter && movie.language.getOrElse("English") == "English")
      .sortBy(_.reviews.getOrElse(0))(Ordering[Int].reverse)
  }

  def highestBudgetByYearAndCountry(yearFilter: Int, countryFilter: String, nRows: Int = 10): List[Movie] = {
    movies.filter(movie => movie.year == yearFilter && movie.country == countryFilter)
      .sortBy(_.budget.getOrElse(0))(Ordering[Int].reverse)
      .take(nRows)
  }

  def longestDurationByCountryAndMinVotes(countryFilter: String, minVotes: Int, nRows: Int = 10): List[Movie] = {
    movies.filter(movie => movie.country == countryFilter && movie.votes >= minVotes)
      .sortBy(_.duration)(Ordering[Int].reverse)
      .take(nRows)
  }

  def countTitlesByLanguageAndBudgetRange(budgetStart: Int, budgetEnd: Int, countryFilter: String): List[(String, Int)] = {
    val filteredMovies = movies
      .filter(_.country == countryFilter)
      .filter(movie => budgetStart <= movie.budget.getOrElse(0) && movie.budget.getOrElse(0) <= budgetEnd)

    val groupedMovies = filteredMovies.groupBy(_.language.getOrElse("English"))
    val titleCounts = groupedMovies.map(item => (item._1, item._2.size)).toList
    titleCounts.sortBy(-_._2)
  }
}
