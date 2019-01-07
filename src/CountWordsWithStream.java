import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountWordsWithStream {

    public static void main(String[] args){

        String fileName = "C:\\Users\\kthapa2\\Desktop\\CountWords.txt"; //provide the full path for the text file
        List<String> wordsList = new ArrayList<>();

        try {
            Stream<String> fileLines = Files.lines(Paths.get(fileName), Charset.forName("Cp1252"));  //Java encoding for Windows ANSI is Cp1252
            wordsList = fileLines.flatMap(line -> Arrays.stream(line.toLowerCase()
                    .replaceAll("[^a-z\\s]*", "")   //delete all non-alphanumeric characters
                    .split(" ")))  //split the string line with space
                    .filter(word -> !word.isEmpty())  //remove all the empty strings
                    .collect(Collectors.toList());  //store the array of strings in list

            if(wordsList.isEmpty()){
                throw new IOException ("Input text file is empty");
            }
            //System.out.println(Arrays.toString(WordsList.toArray()));
            calculateTotalWords(wordsList);
        } catch (NoSuchFileException e) {
            System.out.println("No such file exist in source path!: "+e.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }

    /**
     * Use Java Stream to operate stream operations and print output in console.
     * @param wordsList
     */
    private static void calculateTotalWords(List<String> wordsList) {
        long totalCount = wordsList.size();
        System.out.println("Total Count: "+ totalCount);
        long wordCount = wordsList.stream().filter(word -> word.startsWith("s")).count();
        System.out.println("Total no of words that begins with letter s are: "+ wordCount);
        float percentage = (float) ((wordCount*100) / totalCount);
        System.out.println("Percentage of all the words in the file begin with the letter s is: "+percentage + "%.");
    }

}
