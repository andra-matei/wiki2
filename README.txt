# Project Title
Wiki Indexer

## Authors

* **Adrian Zburatura ** - Junior Developer
* **Andra Matei ** - Junior AM Engineer

##Demo
You can view a demo of the application here: 
http://sendvid.com/zar71vb6

## Getting Started
What things you need to install the software and how to install them
- Java IDE (eg: IntelliJ)
- >= JDK 1.7
- Maven 3
- >= Tomcat 7.0


### Installing
Step 1: Run Tab -> Edit Configurations -> "+" -> Tomcat Server -> Local -> Configure (choose file path) ->
		Deployment -> "+" -> Artifacts -> Choose "wikiweb:war exploded" -> Press "Ok" button

Step 2: File Tab -> Project Structure -> Modules -> "+" -> Choose "Web" -> Press "Ok" button


## Running the tests
Explain how to run the automated tests for this system
Step 1 : Run Tab -> Edit Configurations -> "+" -> JUnit -> Press "Ok" button
Step 2 : Run -> Press the name for the JUnit

### Break down into end to end tests
Every test tests the output of a method using assertEquals which compares
the expected ouput with the actual output.

@Autowired will automatically load the cached Web Application Context. In setup(), 
MockMvc is injected with the resulting WebApplicationContext. MockMvc is used to 
perform the requests and define the expectations in the tests.

## Built With
Maven

## Acknowledgments

**WriteFileFromXML.java

The writeFileFromXML() method receives a string representing a title and it looks for this specific title
in the database. If it can find it, then the method will return the list of the top ten words.
Otherwise, it opens a new URL connection composed by the wiki root URL and the added title. The input of this 
connection is being stored in a file and line by line parsed. 

The auxiliar method allTitles() gets the title from URL and splits it by the url specific separator : "%0A".
It returns the concatenation of all the separate titles.

The line "titleForUrl = titleForUrl.replaceAll(" ", "_")" permits the application to calculate the top ten words
of a title when it has more than one word, such as "New Mexico".


**CountWords.java

The countWords() method returns a list of 10 Words (the words with the biggest number of occurrences
found in the article).
It analyses each line from the .xml file, it splits it by the separator " " and it puts it into the list of words
"words".
Then, it analyses each word from "words". If it contains only letters, has a length bigger than 2 and is not a 
preposition, then it can be put in the "wordCount" Map. 
In "wordCount", each word is stored together with its number of occurences.
After that, the new entry (the processed article) is being persisted in the database.

**ParseFile.java

The method readFromFile() receives a file path in order to process the file.
It calcules the article's final title. If the method can find the results of the given article,
then it sends this information to the method isFromDatabase() and puts the top Ten Words in the "wordList".
Otherwise, it calculates the top ten words of the article by calling the countWords() method. 





