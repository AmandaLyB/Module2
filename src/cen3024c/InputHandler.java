package cen3024c;

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



public class InputHandler {

	public static void inputHandler () {
		try {
	        
	        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
	        
	        File file = new File("D:\\Valencia\\CEN 3024C SoftDev 1\\text.txt");
	        PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
	        
	        // read text returned by server
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        
	        String line, result;
	        while ((line = in.readLine()) != null) {
	        	if(line.contains("<h1>The Raven</h1>")) {
	        		result = line.replaceAll("<[^>]*>", "");
        			System.out.println(result); // prints title
        			out.println(result);
	        		while ((line = in.readLine()) != null) {
	        			if(line.contains("<!--end chapter-->")) {
	        				break;
	        			}
	        			result = line.replaceAll("<[^>]*>", "");
	        			result = result.replaceAll("’", "'");
	        			result = result.replaceAll("&mdash", " ");
	        			result = result.replaceAll("[^A-Za-z0-9 ']", "");
	        			System.out.println(result);
	        			out.println(result);
	        			
	        		}
	        	}
	        }
	        in.close();
	        out.close();
	        
	    }
	    catch (MalformedURLException e) {
	        System.out.println("Malformed URL: " + e.getMessage());
	    }
	    catch (IOException e) {
	        System.out.println("I/O Error: " + e.getMessage());
	    }
	     
	}

	public static void wordCount() {
		
		File file = new File("D:\\Valencia\\CEN 3024C SoftDev 1\\text.txt");
		
		try {
			
			File count = new File("D:\\Valencia\\CEN 3024C SoftDev 1\\text.txt");
			BufferedReader in = new BufferedReader(new FileReader(file));
			PrintWriter out = new PrintWriter(new FileOutputStream(count, false));
			
			String line;
			while ((line = in.readLine()) != null) {
				line.toLowerCase();
				
			
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
