import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordGuess {

  public static void main(String[] args)
    throws IOException, ClassNotFoundException {
    Scanner console = new Scanner(System.in);
    Random rand = new Random();
    List<String> words;
    try (Stream<String> word = Files.lines(Paths.get("/Users/scott/projects/MarksTyProject/words_alpha.txt"))) {
      words = word.collect(Collectors.toList());
    }


    while (!words.isEmpty()) {
      int x = rand.nextInt((words.size()));

      boolean correct = false;
      char[] word = words.remove(x).toCharArray();

      String temp = "";
      for (int j = 0; j < word.length; j = j + 1) {
        temp += "*";
      }
      char[] cover = temp.toCharArray();

      int i = rand.nextInt(word.length);

      while (cover[i] == '*') {
        cover[i] = word[i];
      }
      System.out.println();
      System.out.println(cover);
      int wrongAnswers = 0;
      while (correct == false) {

        System.out.println("Your attempts: " + wrongAnswers);
        System.out.println("Enter word");


        String answer = console.next();

        if (answer.equals(String.copyValueOf(word))) {
          correct = true;
        } else if (!(answer.equals(String.copyValueOf(word)))) {
          i = rand.nextInt(word.length);
          while (cover[i] != '*') {
            i = rand.nextInt(word.length);
          }
          cover[i] = word[i];
          System.out.println(cover);
          wrongAnswers = wrongAnswers+1;
        }
      }
    }
  }
}
