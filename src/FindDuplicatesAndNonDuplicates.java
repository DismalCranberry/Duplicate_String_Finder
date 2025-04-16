import java.io.*;  
import java.util.*;  
  
public class FindDuplicatesAndNonDuplicates {  
    public static void main(String[] args) {  
  
        String inputFilePath = "String_Input.txt";  
        String duplicatesOutputFilePath = "Duplicates_Output.txt"; //Prints only duplicate strings
        String nonDuplicatesOutputFilePath = "NonDuplicates_Output.txt"; //prints non duplicate strings
        String uniqueOutputFilePath = "Unique_Output.txt"; //Prints all strings but once
  
        try {  
            File inputFile = new File(inputFilePath);  
            Scanner scanner = new Scanner(inputFile);  
  
            Set<String> uniqueStrings = new LinkedHashSet<>();  
            Set<String> duplicates = new HashSet<>();  
  
            while (scanner.hasNextLine()) {  
                String line = scanner.nextLine().trim();  
                if (!uniqueStrings.add(line)) {  
                    duplicates.add(line);  
                }  
            }  
            scanner.close();  
  
            Set<String> nonDuplicates = new LinkedHashSet<>(uniqueStrings);  
            nonDuplicates.removeAll(duplicates);  

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
