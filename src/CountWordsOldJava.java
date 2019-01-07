import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CountWordsOldJava {

        public static void main(String args[]) {
            File file = new File("C:\\Users\\kthapa2\\Desktop\\CountWords.txt");
            //String fileName = "C:\\Users\\kthapa2\\Desktop\\CountWords.txt";


            if (!file.isFile()) {
                System.out.println(file + " very bad not good file.");
            }

            String[] words;// this it used to capture words to count by jayden
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {

                StringBuilder buffer = new StringBuilder();// build string for jayden
                String jayden;

                while ((jayden = in.readLine()) != null)
                    buffer.append(jayden).append("\n");//new line for jayden
                words = buffer.toString().replaceAll("[0-9]+", "").split("\\W+");// buffer split

            } catch (IOException ex) {
                words = new String[1];
                System.out.println("jayden has Error with file...");
                System.exit(0);
            }
            calculateTotalWords(words);
        }

        static void calculateTotalWords(String[] words){
            System.out.println(Arrays.toString(words));
            Map<String, Integer> map = new TreeMap<>(); // TreeMap to hold words as key and count as value
            for (String word1 : words) {
                String key = word1.toLowerCase();//change to lower case
                if ((key.length() > 1) && (key.charAt(0) == 's')) {
                    if (!map.containsKey(key)) {
                        map.put(key, 1);
                    } else {
                        int value = map.get(key);
                        value++;
                        map.put(key, value);
                    }
                }
            }
            // print key and value count of each entry from jayden
            System.out.println(words.length);
            System.out.println("Total Count: "+ words.length);
            System.out.println("No. of unique words begin with letter s are: "+map.size());
            int sum = 0;
            float percentage;
            for (int f : map.values()) {
                sum += f;
            }
            System.out.println("Total no of words that begins with letter s are: "+sum);
            percentage = (float) ((sum*100) / words.length);
            System.out.println("Percentage of all the words in the file begin with the letter s is: "+percentage + "%.");


            map.forEach((k, v) -> System.out.println(k + "\t" + v));
        }
}

