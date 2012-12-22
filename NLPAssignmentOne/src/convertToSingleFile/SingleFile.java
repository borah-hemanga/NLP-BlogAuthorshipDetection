package convertToSingleFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class SingleFile {

	public static void main(String args[]){
		
		String dir = "d:/files_reviews/bodies_distiled_rwn";
		String outputFile = "d:/files_reviews/rwn_file.txt";
		File singleFile = new File(outputFile);
		
		File direct = new File(dir);
		System.out.println(direct.exists());
		File files[] = direct.listFiles();
		int innerCounter = 0;
		int outerCounter = 0;
		
		try {
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
