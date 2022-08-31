package cen3024c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;



public class InputHandler {

	public static void inputHandler () {
		try {
	        
	        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
	         
	        // read text returned by server
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	         
	        String line, result;
	        while ((line = in.readLine()) != null) {
	        	if(line.contains("<h1>The Raven</h1>")) {
	        		result = line.replaceAll("<[^>]*>", "");
        			System.out.println(result);
	        		while ((line = in.readLine()) != null) {
	        			if(line.contains("<!--end chapter-->")) {
	        				break;
	        			}
	        			result = line.replaceAll("<[^>]*>", "");
	        			System.out.println(result);
	        			
	        		}
	        	}
	        }
	        in.close();
	         
	    }
	    catch (MalformedURLException e) {
	        System.out.println("Malformed URL: " + e.getMessage());
	    }
	    catch (IOException e) {
	        System.out.println("I/O Error: " + e.getMessage());
	    }
	     
	}
}
