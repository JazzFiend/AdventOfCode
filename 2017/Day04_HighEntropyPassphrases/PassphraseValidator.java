import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.io.*;

public class PassphraseValidator {
  final static private String passphraseFile = "input.txt";

  private static Collection<String> extractPassphrases() {
    ArrayList<String> passphrases = new ArrayList<String>();
    try {
      String line;
      FileReader fileReader = new FileReader(passphraseFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        passphrases.add(line);
      }
    } catch(IOException ex) {
      System.out.println(ex);
    }
    return passphrases;
  }

  private static Collection<String> calculateWordPermutations(String wordInput) {
    ArrayList<String> stringsToReturn = new ArrayList<String>();
    if(wordInput.length() == 1) {
      stringsToReturn.add(wordInput);
    } else {
      for(int i = 0; i < wordInput.length(); i++) {
        StringBuilder word = new StringBuilder(wordInput);
        char oneCharacter = word.charAt(i);
        String recursionInput = word.deleteCharAt(i).toString();
        Collection<String> remainingPermutations = calculateWordPermutations(recursionInput);
        for(String remainingPermutation : remainingPermutations) {
          stringsToReturn.add(oneCharacter + remainingPermutation);
        }
      }
    }
    return stringsToReturn;
  }

  private static boolean isValidPassphrase(String passphrase) {
    HashSet<String> wordSet = new HashSet<String>();
    String[] words = passphrase.split(" ");
    Collection<String> wordPermutations;
    for(String word : words) {
      if(wordSet.contains(word)) {
        return false; 
      }
      wordPermutations = calculateWordPermutations(word);
      for(String wordPermutation : wordPermutations) {
        wordSet.add(wordPermutation);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int validPassphrases = 0;
    Collection<String> passphrases = extractPassphrases();
    for(String passphrase : passphrases) {
      if(isValidPassphrase(passphrase)) {
        validPassphrases++;
      }
    }
    System.out.println("Valid Passphrases: " + validPassphrases);
  }
}
