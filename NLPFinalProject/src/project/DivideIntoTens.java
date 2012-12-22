package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

 /**
  * 
  * Helper class to convert into 10 individual files so that we can find POS (otherwise heap space error problem ':(' )
  * 
  * @author Hemanga
  *
  */

public class DivideIntoTens {

	public static void main(String args[]){
		File f = new File("MainFolder/my_file");
		Writer writerTest  = null;
		try{
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			int count = 0;
			int fileNo = 1;
			String s = null;
			String c;
			while((c = inputReader.readLine())!=null){
				File outPutFile =  new File("MainFolder/PosFolder/"+fileNo);
				if(!outPutFile.exists())outPutFile.createNewFile();
				writerTest = new OutputStreamWriter(new FileOutputStream(outPutFile));
				s+=c+"\n";
				count++;
				if(count%10 == 0){
					writerTest.write(s);
					writerTest.flush();
					fileNo++;
					s = "";
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
	}
		finally{
			try {
				writerTest.flush();
				writerTest.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}