import java.util.ArrayList;
import java.util.Collection;
import java.io.*;

public class ChecksumCalculator {
  public static void main(String[] args) {
    String filename = "input.txt";
    ArrayList<ArrayList<Integer>> spreadsheet = importSpreadsheetValues(filename);
    System.out.format("Largest/Smallest Checksum: %d\n", calculateLargestSmallestChecksum(spreadsheet));
    System.out.format("Divisible Checksum: %d\n", calculateDivisibleChecksum(spreadsheet));
  }

  private static int calculateLargestSmallestChecksum(ArrayList<ArrayList<Integer>> spreadsheet) {
    int checksumTotal = 0;

    for(ArrayList<Integer> row : spreadsheet) {
      int smallestValue = Integer.MAX_VALUE;
      int largestValue = -1;
      for(int number : row) {
        if(number < smallestValue) {
          smallestValue = number;
        }
        if(number > largestValue) {
          largestValue = number;
        }
      }
      checksumTotal += (largestValue - smallestValue);
    }
    return checksumTotal;
  }

  private static int calculateDivisibleChecksum(ArrayList<ArrayList<Integer>> spreadsheet) {
    int checksumTotal = 0;
    for(ArrayList<Integer> row : spreadsheet) {
      int p1 = -1;
      int p2;
      int rowResult = -1;

      while(++p1 < row.size()) {
        p2 = p1;
        while(++p2 < row.size()) {
          if(row.get(p1) > row.get(p2)) {
            if(row.get(p1) % row.get(p2) == 0) {
              rowResult = (row.get(p1) / row.get(p2));
              break;
            }
          } else {
            if(row.get(p2) % row.get(p1) == 0) {
              rowResult = (row.get(p2) / row.get(p1));
              break;
            }
          }
        }
        if(rowResult != -1) {
          break;
        }
      }
      checksumTotal += rowResult;
    }
    return checksumTotal;
  }

  private static ArrayList<ArrayList<Integer>> importSpreadsheetValues(String filename) {
    ArrayList<ArrayList<Integer>> spreadsheetValues = new ArrayList<ArrayList<Integer>>();
    try {
      String line;
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        ArrayList<Integer> spreadsheetRow = new ArrayList<Integer>();
        String[] splitString = line.split(" ");
        for(String number : splitString) {
          spreadsheetRow.add(Integer.parseInt(number));
        }
        spreadsheetValues.add(spreadsheetRow);
      }
    } catch(IOException ex) {
      System.out.println(ex);
    }
    return spreadsheetValues;
  }
}
