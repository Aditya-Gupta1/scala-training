# Scala Assignment

**Input File:** `src/main/resources/imdb_movies.csv`

### Problem statement:

Read the given CSV (which contains the IMDB movie data) with using scala file reader, process the data for the given queries and print the result in the console.

**Input:** Should provide the file path in the config file <br>
**Output:** Print the movie details as per the checkpoints.

### Checkpoints:

1.  Titles directed by given director in the given year range e.g.:
    generate titles report for director D.W. Griffith and year range 2010 to 2020
2. Generate report of English titles which have user reviews more than given user review filter and sort the report with user reviews by descending
3. Generate highest budget titles for the given year and country filters
4. Generate report of longest duration title for the given country filter, no of minimum votes
   filter and sort by duration in descending order
5. Generate language wise report to count the titles for the given budget range and country filter and sort with count descending
   Output: language : count
6. Write test cases for the implemented functions for the unit tests



**Note:**  
1. Load only top 10000 records for better performance.
2. Use java version which is compatible with scala plugin (java 8/11 is prefered)
3. No hardcoded values in the code. Define such values in config files.



Scala app should cover these parts
1.	File reader should handle the exception if the path of the file is invalid/ file not found exception
2.	Other exceptions like number format exception when processing the data for the given queries
3.	It should have standard exception handler and should print the error message in the console
4.	Data processing should work with scala collections and control loops and even include collection methods like map, filter, foreach etc
