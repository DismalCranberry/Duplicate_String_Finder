import java.io.*;
import java.util.*;

public class FindDuplicates {
    public static void main(String[] args) {

        String inputFilePath = "String_Input.txt";
        String outputFilePath = "Duplicates_Output.txt";

        try {
            File inputFile = new File(inputFilePath);
            FileWriter writer = getFileWriter(inputFile, outputFilePath);

            writer.close();
            System.out.println("Duplicates have been written to the file: " + outputFilePath);

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

        FileWriter writer = new FileWriter(outputFilePath);
        if (duplicates.isEmpty()) {
            System.out.println("No duplicate entries found.");
        } else {
            writer.write("Duplicates:\n");
            for (String duplicate : duplicates) {
                writer.write(duplicate + "\n");
            }
        }
        return writer;
    }
}