package TestDifferentModels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class DivideDataForNGrams {
	
	public static void divideDataForNGrams(String inputFolder){
	try {
		
		File inputFiles[] = new File(inputFolder).listFiles();
		
		File testDataTruth = new File(inputFolder+File.separator+"test");
		
		Writer writerTestTruth = new OutputStreamWriter(new FileOutputStream(testDataTruth));
		
		File testDataDeceptive = new File(inputFolder+File.separator+"test");
		
		File trainDataDeceptive1 = new File(inputFolder+File.separator+"trainDeceptive1");
		File trainDataDeceptive2 = new File(inputFolder+File.separator+"trainDeceptive2");
		File trainDataDeceptive3 = new File(inputFolder+File.separator+"trainDeceptive3");
		File trainDataDeceptive4 = new File(inputFolder+File.separator+"trainDeceptive4");
		File trainDataDeceptive5 = new File(inputFolder+File.separator+"trainDeceptive5");
		
		Writer writerTestDeceptive = new OutputStreamWriter(new FileOutputStream(testDataDeceptive));
		
		Writer writerTrainDeceptive1 = new OutputStreamWriter(new FileOutputStream(trainDataDeceptive1));
		Writer writerTrainDeceptive2 = new OutputStreamWriter(new FileOutputStream(trainDataDeceptive2));
		Writer writerTrainDeceptive3 = new OutputStreamWriter(new FileOutputStream(trainDataDeceptive3));
		Writer writerTrainDeceptive4 = new OutputStreamWriter(new FileOutputStream(trainDataDeceptive4));
		Writer writerTrainDeceptive5 = new OutputStreamWriter(new FileOutputStream(trainDataDeceptive5));
		
		int first = 1;
		String type = "trainTruth";
		
			for(File file : inputFiles)
			{
				File trainDataTruth1 = new File(inputFolder+File.separator+type+1);
				File trainDataTruth2 = new File(inputFolder+File.separator+type+2);
				File trainDataTruth3 = new File(inputFolder+File.separator+type+3);
				File trainDataTruth4 = new File(inputFolder+File.separator+type+4);
				File trainDataTruth5 = new File(inputFolder+File.separator+type+5);
			
				Writer writerTrainTruth1 = new OutputStreamWriter(new FileOutputStream(trainDataTruth1));
				Writer writerTrainTruth2 = new OutputStreamWriter(new FileOutputStream(trainDataTruth2));
				Writer writerTrainTruth3 = new OutputStreamWriter(new FileOutputStream(trainDataTruth3));
				Writer writerTrainTruth4 = new OutputStreamWriter(new FileOutputStream(trainDataTruth4));
				Writer writerTrainTruth5 = new OutputStreamWriter(new FileOutputStream(trainDataTruth5));
				
				File testDataTruth1 = new File(inputFolder+File.separator+type+1);
				File testDataTruth2 = new File(inputFolder+File.separator+type+2);
				File testDataTruth3 = new File(inputFolder+File.separator+type+3);
				File testDataTruth4 = new File(inputFolder+File.separator+type+4);
				File testDataTruth5 = new File(inputFolder+File.separator+type+5);
			
				Writer writerTestTruth1 = new OutputStreamWriter(new FileOutputStream(testDataTruth1));
				Writer writerTestTruth2 = new OutputStreamWriter(new FileOutputStream(testDataTruth2));
				Writer writerTestTruth3 = new OutputStreamWriter(new FileOutputStream(testDataTruth3));
				Writer writerTestTruth4 = new OutputStreamWriter(new FileOutputStream(testDataTruth4));
				Writer writerTestTruth5 = new OutputStreamWriter(new FileOutputStream(testDataTruth5));
			
				
				int counter = 0;
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
				String c = "";
				
				while((c = inputReader.readLine())!=null)
					if(first == 1){	
						if( (counter - 0 % 5) == 0){
							writerTrainTruth1.write(c);
							writerTrainTruth2.write(c);
							writerTrainTruth3.write(c);
							writerTrainTruth4.write(c);
							writerTestTruth5.write(c);
						}
						if( (counter - 1 % 5) == 0){
							writerTrainTruth5.write(c);
							writerTrainTruth2.write(c);
							writerTrainTruth3.write(c);
							writerTrainTruth4.write(c);
							writerTestTruth1.write(c);
						}
						if( (counter - 2 % 5) == 0){
							writerTrainTruth5.write(c);
							writerTrainTruth1.write(c);
							writerTrainTruth3.write(c);
							writerTrainTruth4.write(c);
							writerTestTruth2.write(c);
						}
						if( (counter - 3 % 5) == 0){
							writerTrainTruth4.write(c);
							writerTrainTruth5.write(c);
							writerTrainTruth1.write(c);
							writerTrainTruth2.write(c);
							writerTestTruth3.write(c);
						}
						if( (counter - 4 % 5) == 0){
							writerTrainTruth3.write(c);
							writerTrainTruth4.write(c);
							writerTrainTruth5.write(c);
							writerTrainTruth1.write(c);
							writerTestTruth2.write(c);
							
							writerTestTruth.write(c);
							
						}
					}
				first = 2;
				type = "trainDeceptive";
									
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
