package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 
 * Helper file to combine multiple files into one
 * 
 * @author Hemanga
 *
 */

public class MakeSingleFile {
	
	public static void main(String args[]){
		
		String directory = "d:/files_reviews";
		File f = new File (directory);
		System.out.println(f.exists());
		makeSingleFile(directory);
	}

	public static void makeSingleFile(String mainDir){
		
		File directory = new File(mainDir);
		
		File allFiles[] = directory.listFiles();
		
		for(File mainFile:allFiles){
		String dir = mainDir+File.separator+mainFile;
		String outputFile = mainDir+File.separator+mainFile+".txt";
		File singleFile = new File(outputFile);
		
		File direct = new File(dir);
		System.out.println(direct.exists());
		File files[] = direct.listFiles();
		int innerCounter = 0;
		int outerCounter = 0;
		
		try {
			if(singleFile.exists() != true) singleFile.createNewFile();
			Writer outputSingleWriter = new OutputStreamWriter(new FileOutputStream(singleFile));
			
			for(File f: files){
				BufferedReader reader = new BufferedReader(new FileReader(f));
				if(!singleFile.exists())singleFile.createNewFile();
				
				String c = "";
				
				while((c=reader.readLine())!=null){
					outputSingleWriter.write(c);
					innerCounter++;
				}
				outputSingleWriter.write("\n");
				outerCounter++;
				outputSingleWriter.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(innerCounter + " "+ outerCounter);
		}
	}
}
