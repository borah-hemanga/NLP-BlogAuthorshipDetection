package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CreateDataSet {

	/**
	 * 
	 * Divide data into 80% and 20% sets
	 * 
	 * @param folder
	 * @param percent
	 */
	public static void divideInto_80_20(String folder, int percent) {
		
		File mainFolder = new File(folder);

		File test = new File(folder + File.separator + "Test");
		File train = new File(folder + File.separator + "Train");

		File[] mainFiles = mainFolder.listFiles();
		int totalFiles = mainFiles.length;
		
		try {
				if(!test.exists())test.mkdir();
				if(!train.exists())train.mkdir();
				
				for(File f: mainFiles){
					File testWriter = new File(test+File.separator+f.getName());
					File trainWriter = new File(train+File.separator+f.getName());
					System.out.println(testWriter);
					if(!testWriter.exists() && testWriter.isDirectory() != true) testWriter.createNewFile();
					if(!trainWriter.exists() && trainWriter.isDirectory() != true) trainWriter.createNewFile();
				}
				
				int count;
				
				
				for(File f: mainFiles){
					count = 0;
					File testFile = new File(test+File.separator+f.getName());
					File trainFile = new File(train+File.separator+f.getName());
					BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
					Writer writerTest = new OutputStreamWriter(new FileOutputStream(testFile));
					Writer writerTrain = new OutputStreamWriter(new FileOutputStream(trainFile));
					String c;
					
					while((c = inputReader.readLine())!=null){
						if(count%percent != 0)writerTrain.append(c+"\n");
						else writerTest.append(c+"\n");
						count++;
				}
					writerTrain.flush();
					writerTest.flush();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	/**
	 * Divides the test data into five sets of 80% and 20% data and test sets. 
	 * 
	 * @param mainDir
	 */
	public static void divideIntoFiveFolds(String mainDir){
		
		File mainFiles[] = new File(mainDir).listFiles();
		int totalFiles = mainFiles.length;
		int count = 0;
		
		for(File f : mainFiles){
			count= 0;
			try{
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				String c;
				while((c = inputReader.readLine())!=null){
					//System.out.println(c);
				for(int i = 0;i < 5 ; i++){
						String fold = mainDir + File.separator + "Fold" + i;
						File foldDir = new File(fold);
						if(!foldDir.exists())foldDir.mkdir();
						File trainFolder = new File(fold+File.separator+"Train");
						File testFolder = new File(fold+File.separator+"Test");
						if(!testFolder.exists() )testFolder.mkdir();
						if(!trainFolder.exists() )trainFolder.mkdir();
						
						File testFile = new File(testFolder+ File.separator+ f.getName());
						File trainFile = new File(trainFolder+ File.separator+ f.getName());
						
						Writer writerTest = new OutputStreamWriter(new FileOutputStream(testFile));
						Writer writerTrain = new OutputStreamWriter(new FileOutputStream(trainFile));
						
						//System.out.println(testFile + " " + c);
						if(count%5 != 0){
							/*writerTrain.append(c+"\n");
							writerTrain.flush();
							writerTrain.close();*/
						}
						else 	{
							//System.out.println("Hi");
							/*writerTest.write(c);
							writerTest.flush();
							writerTest.close();*/
						}
						
					}
				count++;
				}
			}
			
				catch (IOException e){
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * 
	 * Divide the data into 5 sets
	 * 
	 * @param trainDataDir
	 * @param mainDataDir
	 */
	public static void divideIntoFiveSets(String trainDataDir,String mainDataDir){
		try{
		File trainDataFiles[] = new File(trainDataDir).listFiles();
		
		for(File f:trainDataFiles){
			
			BufferedReader reader = new BufferedReader(new FileReader(f));
			
			String type = f.getName();
			
			File setDataDir1 = new File(mainDataDir+File.separator+1);
			File setDataDir2 = new File(mainDataDir+File.separator+2);
			File setDataDir3 = new File(mainDataDir+File.separator+3);
			File setDataDir4 = new File(mainDataDir+File.separator+4);
			File setDataDir5 = new File(mainDataDir+File.separator+5);
	
			if(!setDataDir1.exists())setDataDir1.mkdir();
			if(!setDataDir2.exists())setDataDir2.mkdir();
			if(!setDataDir3.exists())setDataDir3.mkdir();
			if(!setDataDir4.exists())setDataDir4.mkdir();
			if(!setDataDir5.exists())setDataDir5.mkdir();
			
			File trainDataDir1 = new File(setDataDir1+File.separator+"train");
			File trainDataDir2 = new File(setDataDir2+File.separator+"train");
			File trainDataDir3 = new File(setDataDir3+File.separator+"train");
			File trainDataDir4 = new File(setDataDir4+File.separator+"train");
			File trainDataDir5 = new File(setDataDir5+File.separator+"train");
			
			if(!trainDataDir1.exists())trainDataDir1.mkdir();
			if(!trainDataDir2.exists())trainDataDir2.mkdir();
			if(!trainDataDir3.exists())trainDataDir3.mkdir();
			if(!trainDataDir4.exists())trainDataDir4.mkdir();
			if(!trainDataDir5.exists())trainDataDir5.mkdir();
			
			File trainDataFile1 = new File(trainDataDir1+File.separator+type);
			File trainDataFile2 = new File(trainDataDir2+File.separator+type);
			File trainDataFile3 = new File(trainDataDir3+File.separator+type);
			File trainDataFile4 = new File(trainDataDir4+File.separator+type);
			File trainDataFile5 = new File(trainDataDir5+File.separator+type);

			if(!trainDataFile1.exists())trainDataFile1.createNewFile();
			if(!trainDataFile2.exists())trainDataFile2.createNewFile();
			if(!trainDataFile3.exists())trainDataFile3.createNewFile();
			if(!trainDataFile4.exists())trainDataFile4.createNewFile();
			if(!trainDataFile5.exists())trainDataFile5.createNewFile();
			
			Writer writerTrain1 = new OutputStreamWriter(new FileOutputStream(trainDataFile1));
			Writer writerTrain2 = new OutputStreamWriter(new FileOutputStream(trainDataFile2));
			Writer writerTrain3 = new OutputStreamWriter(new FileOutputStream(trainDataFile3));
			Writer writerTrain4 = new OutputStreamWriter(new FileOutputStream(trainDataFile4));
			Writer writerTrain5 = new OutputStreamWriter(new FileOutputStream(trainDataFile5));
	
			File testDataDir1 = new File(setDataDir1+File.separator+"test");
			File testDataDir2 = new File(setDataDir2+File.separator+"test");
			File testDataDir3 = new File(setDataDir3+File.separator+"test");
			File testDataDir4 = new File(setDataDir4+File.separator+"test");
			File testDataDir5 = new File(setDataDir5+File.separator+"test");
			
			if(!testDataDir1.exists())testDataDir1.mkdir();
			if(!testDataDir2.exists())testDataDir2.mkdir();
			if(!testDataDir3.exists())testDataDir3.mkdir();
			if(!testDataDir4.exists())testDataDir4.mkdir();
			if(!testDataDir5.exists())testDataDir5.mkdir();
			
			File testDataFile1 = new File(testDataDir1+File.separator+type);
			File testDataFile2 = new File(testDataDir2+File.separator+type);
			File testDataFile3 = new File(testDataDir3+File.separator+type);
			File testDataFile4 = new File(testDataDir4+File.separator+type);
			File testDataFile5 = new File(testDataDir5+File.separator+type);

			if(!testDataFile1.exists())testDataFile1.createNewFile();
			if(!testDataFile2.exists())testDataFile2.createNewFile();
			if(!testDataFile3.exists())testDataFile3.createNewFile();
			if(!testDataFile4.exists())testDataFile4.createNewFile();
			if(!testDataFile5.exists())testDataFile5.createNewFile();
			
			Writer writerTest1 = new OutputStreamWriter(new FileOutputStream(testDataFile1));
			Writer writerTest2 = new OutputStreamWriter(new FileOutputStream(testDataFile2));
			Writer writerTest3 = new OutputStreamWriter(new FileOutputStream(testDataFile3));
			Writer writerTest4 = new OutputStreamWriter(new FileOutputStream(testDataFile4));
			Writer writerTest5 = new OutputStreamWriter(new FileOutputStream(testDataFile5));
			
			String c = "";
			int counter = 0;
			
			while((c = reader.readLine())!=null){
				if((counter - 0)%5 == 0){
					writerTrain1.write(c+"\n");
					writerTrain2.write(c+"\n");
					writerTrain3.write(c+"\n");
					writerTrain4.write(c+"\n");
					writerTest5.write(c+"\n");
				}
				if((counter - 1)%5 == 0){
					writerTrain5.write(c+"\n");
					writerTrain1.write(c+"\n");
					writerTrain2.write(c+"\n");
					writerTrain3.write(c+"\n");
					writerTest4.write(c+"\n");
				}
				if((counter - 2)%5 == 0){
					writerTrain4.write(c+"\n");
					writerTrain5.write(c+"\n");
					writerTrain1.write(c+"\n");
					writerTrain2.write(c+"\n");
					writerTest3.write(c+"\n");
				}
				if((counter - 3)%5 == 0){
					writerTrain3.write(c+"\n");
					writerTrain4.write(c+"\n");
					writerTrain5.write(c+"\n");
					writerTrain1.write(c+"\n");
					writerTest2.write(c+"\n");
				}
				if((counter - 4)%5 == 0){
					writerTrain2.write(c+"\n");
					writerTrain3.write(c+"\n");
					writerTrain4.write(c+"\n");
					writerTrain5.write(c+"\n");
					writerTest1.write(c+"\n");
				}
				
				writerTrain1.flush();
				writerTrain2.flush();
				writerTrain3.flush();
				writerTrain4.flush();
				writerTrain5.flush();
				
				writerTest1.flush();
				writerTest2.flush();
				writerTest3.flush();
				writerTest4.flush();
				writerTest5.flush();
				
				counter++;
				
			}
		}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * Divide the data into individual files by calling grainintoFiles
	 * 
	 * @param parentFolder
	 */
	
	public static void grainAllData(String parentFolder){
		
		String testFolder = parentFolder+"/Test";
		grainintoFiles(testFolder);
		
		for(int i = 1;i < 6; i++){
			String folderTest = parentFolder+ "/Train/"+i+"/train";
			String folderTrain = parentFolder+ "/Train/"+i+"/test";
			grainintoFiles(folderTest);
			grainintoFiles(folderTrain);
		}
	}
	
	public static void grainintoFiles(String dir){
		File directory = new File(dir);
		//System.out.println(dir);
		File files[] = directory.listFiles();
		int count = 0;
		
		for(File file : files){
			count = 0;
		try {
			
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			File grainedFolder = new File(directory+ File.separator+ file.getName()+"dir");
			System.out.println(grainedFolder);
			if(!grainedFolder.exists()){
				grainedFolder.mkdir();
			}
			String c;
			while((c = inputReader.readLine())!=null){
				File newFile = new File(grainedFolder + File.separator + count);
				newFile.createNewFile();
				Writer writerTest = new OutputStreamWriter(new FileOutputStream(newFile));
				writerTest.write(c);
				writerTest.flush();
				count++;
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

	
	public static void main(String args[]){
		String mainFolder = "MainFolder";
		int quarter = 5;
		divideInto_80_20(mainFolder,quarter);
		////divideIntoFiveFolds(mainFolder+"/Train");
		divideIntoFiveSets(mainFolder+"/Train",mainFolder+"/Train");
		grainAllData(mainFolder);
		
	}
}
