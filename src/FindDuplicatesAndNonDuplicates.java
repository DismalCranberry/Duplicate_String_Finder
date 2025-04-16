import java.io.*;  
import java.util.*;  
  
public class FindDuplicatesAndNonDuplicates {  
    public static void main(String[] args) {  
  
        String inputFilePath = "String_Input.txt";  
        String duplicatesOutputFilePath = "Duplicates_Output.txt";  
        String nonDuplicatesOutputFilePath = "NonDuplicates_Output.txt";  
        String uniqueOutputFilePath = "Unique_Output.txt";  // New output file for unique strings
  
        try {  
            File inputFile = new File(inputFilePath);  
            Scanner scanner = new Scanner(inputFile);  
  
            // Use LinkedHashSet to preserve the insertion order
            Set<String> uniqueStrings = new LinkedHashSet<>();  
            Set<String> duplicates = new HashSet<>();  
  
            while (scanner.hasNextLine()) {  
                String line = scanner.nextLine().trim();  
                // If add returns false, line already existed, so add it to duplicates
                if (!uniqueStrings.add(line)) {  
                    duplicates.add(line);  
                }  
            }  
            scanner.close();  
  
            // For the non-duplicate file, create a set containing entries that occurred only once
            // Since uniqueStrings is a LinkedHashSet, we preserve the order here if needed.
            Set<String> nonDuplicates = new LinkedHashSet<>(uniqueStrings);  
            nonDuplicates.removeAll(duplicates);  
  
            // Write duplicates to file  
            FileWriter dupWriter = new FileWriter(duplicatesOutputFilePath);  
            if (duplicates.isEmpty()) {  
                dupWriter.write("No duplicate entries found.\n");  
            } else {  
                dupWriter.write("Duplicates:\n");  
                for (String duplicate : duplicates) {  
                    dupWriter.write(duplicate + "\n");  
                }  
            }  
            dupWriter.close();  
  
            // Write non-duplicates to file  
            FileWriter nonDupWriter = new FileWriter(nonDuplicatesOutputFilePath);  
            if (nonDuplicates.isEmpty()) {  
                nonDupWriter.write("No non duplicate entries found.\n");  
            } else {  
                nonDupWriter.write("Non-duplicate entries:\n");  
                for (String nonDuplicate : nonDuplicates) {  
                    nonDupWriter.write(nonDuplicate + "\n");  
                }  
            }  
            nonDupWriter.close();  
  
            // New: Write unique strings (all strings printed once without repeating) to file  
            FileWriter uniqueWriter = new FileWriter(uniqueOutputFilePath);  
            uniqueWriter.write("Unique entries:\n");  
            for (String unique : uniqueStrings) {  
                uniqueWriter.write(unique + "\n");  
            }  
            uniqueWriter.close();  
  
            System.out.println("Duplicates have been written to the file: " + duplicatesOutputFilePath);  
            System.out.println("Non-duplicate entries have been written to the file: " + nonDuplicatesOutputFilePath);  
            System.out.println("Unique entries have been written to the file: " + uniqueOutputFilePath);  
  
        } catch (FileNotFoundException e) {  
            System.out.println("The input file " + inputFilePath + " was not found.");  
        } catch (IOException e) {  
            System.out.println("An error occurred while writing to the output file: " + e.getMessage());  
        } catch (Exception e) {  
            System.out.println("An error occurred: " + e.getMessage());  
        }  
    }  
}