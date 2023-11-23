package com.devicethread.tools.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvUtils {


    public void generateCsv(String filePath, List<String> header, List<List<String>> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writeRow(writer, header);

            // Write data
            
            	for (List<String> rowData : data)
            	{
            		writeRow(writer, rowData);
            	}
                
            

            System.out.println("CSV file generated successfully at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    private static void writeRow(FileWriter writer, List<String> rowData) throws IOException {
        for (int i = 0; i < rowData.size(); i++) {
            writer.append(rowData.get(i));
            if (i < rowData.size() - 1) {
                writer.append(",");
            }
        }
        writer.append("\n");
    }
    
    private static void writeCSVHeader(FileWriter csvWriter, List<LinkedHashMap<String, Object>> data) throws IOException {
        // Write the header using the keys of the first row
        if (!data.isEmpty()) {
            Map<String, Object> firstRow = data.get(0);
            for (String key : firstRow.keySet()) {
            	System.out.println(key);
                csvWriter.append(key).append(",");
            }
            csvWriter.append("\n");
        }
    }

    private static void writeCSVData(FileWriter csvWriter, List<LinkedHashMap<String, Object>> data) throws IOException {
        // Write the data rows
        for (Map<String, Object> row : data) {
            for (Object value : row.values()) {
                csvWriter.append(value.toString()).append(",");
            }
            csvWriter.append("\n");
        }
    }
    
    public  void createCsv(String csvFilePath,List<LinkedHashMap<String, Object>> data )
    {
    	 
		/*
		 * // Add some sample data (you can dynamically add data based on your
		 * requirements) Map<String, Object> row1 = new HashMap<>(); row1.put("Name",
		 * "John"); row1.put("Age", 25); row1.put("City", "New York"); data.add(row1);
		 * 
		 * Map<String, Object> row2 = new HashMap<>(); row2.put("Name", "Alice");
		 * row2.put("Salary", 50000.0); row2.put("Department", "HR"); data.add(row2);
		 */

       

         try {
             // Create a FileWriter
             FileWriter csvWriter = new FileWriter(csvFilePath);

             // Write the header (based on the keys of the first row)
             writeCSVHeader(csvWriter, data);

             // Write the data
             writeCSVData(csvWriter, data);

             // Close the FileWriter
             csvWriter.close();

             System.out.println("CSV file has been generated successfully: " + csvFilePath);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
    }



