package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 
 * 
 * Helper class to remove the header from the files -- the date and time parts
 * @author Hemanga
 *
 */

public class RemoveHeader {

	public static void main(String args[]){
		String dir = "ManipulationFolder";
		File files[] = new File(dir).listFiles();
		try{
			for(File f : files){
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				File outputFile = new File(f+"_Removed");
				Writer writerTest = new OutputStreamWriter(new FileOutputStream(outputFile));
			
				String c = "";
				
				while((c= inputReader.readLine())!=null){
					int i = c.indexOf(" | ");
					c = c.substring(i+3,c.length());
					System.out.println(c);
					writerTest.write(c + "\n");
				}
				
				writerTest.flush();
				writerTest.close();
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
