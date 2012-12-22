package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;


/** 
 * 
 * Helper file used to test data by limiting word count on the blogs
 * 
 * @author Hemanga
 *
 */

public class PickShortBlogs {

	public static void main(String args[]){
		
		File f = new File("MainFolder/rwn_file");
		File newFile = new File("MainFolder/rwn_Short");
		
		try {
			BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			Writer w = new OutputStreamWriter(new FileOutputStream(newFile));
			String c;
			while((c = b.readLine())!= null){
				if(c.length()<4000)w.write(c+"\n");
				w.flush();
			}
			
			w.close();
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
