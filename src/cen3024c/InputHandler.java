package cen3024c;

import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.*;
import java.util.Map.Entry;
import java.lang.*;

/**
 * InputHandler analyzes text selected in GUI.java to determine the top 20 words used in the file.
 */

public class InputHandler {

	public static Object[][] wordList = new Object[20][2];
	
	public static void inputHandler () {
		try {
			
			// Input file is gathered from the GUI class 
			// and uses the directory of the chosen file to write the word count file
			File story = GUI.file;
	        File file = new File(story.getAbsoluteFile().getParent()+"\\"+"text.txt");
	        PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
	        
	        // read text returned by server
	        BufferedReader in = new BufferedReader(new FileReader(story));
	        String line, result;
	        while ((line = in.readLine()) != null) {

	        	result = line.replaceAll("[^A-Za-z ]", " "); // remove all non-word characters excluding space
	        	result = result.replaceAll("&mdash", " ");
	        	result = result.replaceAll("\\s{2,}", " "); 

	        	out.println(result);
	        }
	        in.close();
	        out.close();
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();		
		}
		catch (IOException e) {
	        System.out.println("I/O Error: " + e.getMessage());
	    }
	}

	// reads and sorts text from file and returns frequency of words
	public static void wordCount() {
		
		File file = new File(GUI.file.getAbsoluteFile().getParent()+"\\"+"text.txt");
		
		try {
			
			File count = new File(GUI.file.getAbsoluteFile().getParent()+"\\" + "count.txt");
			BufferedReader in = new BufferedReader(new FileReader(file));
			PrintWriter out = new PrintWriter(new FileOutputStream(count, false));
			String line;
			HashMap<String,Integer> wordCount = new LinkedHashMap<String,Integer>();
			String[] lineSplit;
			
			// converts text to lower case, splits words by space
			while ((line = in.readLine()) != null) {
				line = line.toLowerCase();
				lineSplit = line.split(" ");

				// creates new key and/or adds to count each instance of a word
				for(int i = 0; i < lineSplit.length; ++i) {
					if(wordCount.containsKey(lineSplit[i])) {
						wordCount.put(lineSplit[i],wordCount.get(lineSplit[i])+1);
					}
					else {
						wordCount.put(lineSplit[i],1);
					}
				}
			}
			
			Iterator<Entry<String, Integer>> it = wordCount.entrySet().iterator();
			while (it.hasNext()) {
			    Entry<String, Integer> e = it.next();
			    //String key = e.getKey();
			    Integer value = e.getValue();
			    if (value == 29) {
			        it.remove();
			    }
			}
			
			// sort by most frequent words
			wordCount = sortByValue(wordCount);

			// connect to sql database
			Connection connection;
			try {
				ResultSet results = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordoccurences", "root", "cop2805");
				Statement stmt = connection.createStatement();
			
			// write to count.txt and database
			for(String i : wordCount.keySet()) {
				out.println(i + " " + wordCount.get(i));
				String stmtInsert = "INSERT INTO words (word, frequency) VALUES ('" + i + "','" +  wordCount.get(i) + "')";
				stmt.execute(stmtInsert);
				//System.out.println(i + " " + wordCount.get(i));
			}
				String strSelect = "SELECT * FROM words";
				ResultSet result = stmt.executeQuery(strSelect);
				int counts = 0;
				
				// read from database to generate top 20 words for GUI
				while (result.next()) {
					System.out.println(result.getInt("id") + ": " + result.getInt("frequency") + " " + result.getString("word"));
					if (counts < 20) {
						wordList[counts][0] = result.getString("word");
						wordList[counts][1] = result.getInt("frequency");
						counts++;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();		
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * sortByValue sorts the word count read from wordCount().
	 *
	 * @param hm Takes hashmap generated from file that is read in wordCount.   
	 * @return Returns the sorted word and count data in file 
	 */
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Collections.reverse(list);
        
        // put data from sorted list to new HashMap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> word : list) {
            temp.put(word.getKey(), word.getValue());
        }
        return temp;
    }
	
	
	
}
