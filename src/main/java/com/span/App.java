package com.span;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Read file from the command line then calculate points per team and finally write the results to a file.
 *
 */
public class App {

    final static String OUTPUT_FILE_PATH = "out-file.txt";
    final static String MSG_TOO_FEW_ARGUMENTS  = "ERROR: Please enter the file name as the first command line argument!";

    public static void main( String[] args ) throws Exception {
        System.out.println("Passed file path : " + args[0]);

        Match match = new Match();
        File file = null;

         if (0 < args.length) {
            file = new File(args[0]);
         } else {
            System.err.println(MSG_TOO_FEW_ARGUMENTS);
            System.exit(1);
         }

         BufferedReader br = null;

        try {

            String currentLine;

            br = new BufferedReader(new FileReader(file));

            // list that holds strings of a file
            List<String> listOfLinesOfStrings = new ArrayList<String>();
        
            while ((currentLine = br.readLine()) != null) {
                listOfLinesOfStrings.add(currentLine);
            }

            // storing the data in arraylist to array
            String[] array = listOfLinesOfStrings.toArray(new String[0]);

            Map<String, Integer> results = match.leagueTable(array);
            
            LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
            results.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
            
            // write results to a file
            writeToFile(reverseSortedMap);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null){
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
   

    public static void writeToFile(LinkedHashMap<String, Integer> data) throws IOException {

        // new file object
        File file = new File(OUTPUT_FILE_PATH);
        
        BufferedWriter bw = null;

        try {

            bw = new BufferedWriter(new FileWriter(file));
            int count = 0;

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                count++;
                String str = entry.getValue() == 1 ? " pt" : " pts";
                bw.write( count + ". " + entry.getKey() + ", " + entry.getValue() + str);
                bw.newLine();
            }

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                bw.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
  
    }
}
