import java.io.*;
import java.util.*;

public class FindNonDuplicates {
    public static void main(String[] args) {

        String inputFilePath = "String_Input.txt";
        String outputFilePath = "NonDuplicates_Output.txt";

        try {
            File inputFile = new File(inputFilePath);
            FileWriter writer = getFileWriter(inputFile, outputFilePath);

            writer.close();
            System.out.println("Non duplicate entries have been written to the file: " + outputFilePath);

        } catch (FileNotFoundException e) {
            System.out.println("The input file " + inputFilePath + " was not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the output file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static FileWriter getFileWriter(File inputFile, String outputFilePath) throws IOException {

        Scanner scanner = new Scanner(inputFile);

        Set<String> uniqueStrings = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
           
            if (!uniqueStrings.add(line)) {
                duplicates.add(line);
            }
        }
        scanner.close();

        // Calculate non duplicate entries by subtracting duplicates from uniqueStrings.
        Set<String> nonDuplicates = new HashSet<>(uniqueStrings);
        nonDuplicates.removeAll(duplicates);

        FileWriter writer = new FileWriter(outputFilePath);
        if (nonDuplicates.isEmpty()) {
            System.out.println("No non duplicate entries found.");
        } else {
            writer.write("Non-duplicate entries:\n");
            for (String nonDuplicate : nonDuplicates) {
                writer.write(nonDuplicate + "\n");
            }
        }
        return writer;
    }
}
