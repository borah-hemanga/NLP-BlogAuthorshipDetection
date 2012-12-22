package TestDifferentModels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibLINEAR;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.CVParameterSelection;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

enum ClassifersToUse {
	NaiveBayes, SMO,SVM,LibLinear
}

enum Type {
	TypeGeneral , TypePos
}

public class NLPAssignmentFinal {

	/**
	 * Main function to run
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		
		Type type = Type.TypePos;
		String path;
		
		if(type == Type.TypeGeneral){
			path = "testData/generalUniBiTri";
		}
		else
			path = "testData/POS";
		
		String typeGeneral = path;
		//String typePOS = "testData/POS";
		
		/*
		 * Run these only once
		 */
		//makeDataForGeneral(typeGeneral);
		//makeDataForPOS(typePOS);
		
		/*
		 * Run these only once
		 */
		//grainToTrain(typeGeneral+File.separator+"testFiles");
		//grainToTrain(typePOS+File.separator+"testFiles");
		
		/*
		 * The value of type is to be changed and the gram value is to be inserted each time
		 */
		int gram1 = 3;
		int gram2 = 3;
		int testSet = 1;
		ClassifersToUse c;
		c = ClassifersToUse.LibLinear;
		
		System.out.println("RESULTS FOR THE INDIVIDUAL FOLDS" + " \n\nFor GRAM ----------------- Min Gram " + gram1 + " and Max Gram " + gram2 + "\n\nClassifier " + c);
		
		testForNGrams(typeGeneral, gram1, gram2,c);
		
		/*
		 * Use this function for testing purpose
		 */
		System.out.println("\n\n\n\n\n\n\nResults for the test on unseen data ");
		testForNGramsSeparate(typeGeneral+File.separator+testSet+File.separator+"TESTDIRECTORY/TRAIN",typeGeneral+File.separator+"testFiles/TESTDIRECTORY/TEST",gram1,gram2,c);
	}
	
	/**
	 * For making data for general testing
	 * 
	 * @param type
	 */
	public static void makeDataForGeneral(String type){
		divideFilesInto80_20(type);
		divideIntoFiveSets(type+File.separator+"trainFiles",type);
		for(int i = 1;i<6;i++){
			grainToTrain(type+File.separator+i);
		}
	}
	
	/**
	 * For making data for testing pos
	 */
	public static void makeDataForPOS(String type){
		divideFilesInto80_20(type);
		divideIntoFiveSets(type+File.separator+"trainFiles",type);
		for(int i = 1;i<6;i++){
			grainToTrain(type+File.separator+i);
		}
	}
	
	/**
	 * Test for test and data in separate folders
	 * 
	 * @param trainDir
	 * @param testDir
	 * @param gram
	 */
	public static void testForNGramsSeparate(String trainDir,String testDir,int gram1,int gram2,ClassifersToUse c){
		
			makeDataSet(trainDir,testDir,gram1,gram2,c);
	}
	
	/**
	 * 
	 * Test for testing data in same folder
	 * 
	 * @param type
	 * @param gram
	 */
	
	public static void testForNGrams(String type,int gram1,int gram2,ClassifersToUse c){
		for(int i = 1;i<6;i++){
			makeDataSet(type+File.separator+i+File.separator+"TESTDIRECTORY/TRAIN",type+File.separator+i+File.separator+"TESTDIRECTORY/TEST",gram1,gram2,c);
		}
	}

	/**
	 * 
	 * Divides files into 80 % - 20 %
	 * 
	 * @param dir
	 */
	public static void divideFilesInto80_20(String dir){
		try {
			
			File files[] = new File(dir).listFiles();
			
			String outputDirTest = dir+File.separator+"testFiles";
			File outputDirTestDir = new File(outputDirTest);
			if(!(outputDirTestDir.exists()))outputDirTestDir.mkdir();
			
			String outputDirTrain = dir+File.separator+"trainFiles";
			File outputDirTrainDir = new File(outputDirTrain);
			if(!(outputDirTrainDir.exists()))outputDirTrainDir.mkdir();
			
			
			for(File f : files){
			
				String outputTestFileName= outputDirTest+File.separator+f.getName();
				String outputTrainFileName= outputDirTrain+File.separator+f.getName();
				
				File outputTestFile =  new File(outputTestFileName);
				File outputTrainFile =  new File(outputTrainFileName);
				
				if(!outputTestFile.exists())outputTestFile.createNewFile();
				if(!outputTrainFile.exists())outputTrainFile.createNewFile();
				
				Writer testData = new OutputStreamWriter(new FileOutputStream(outputTestFile));
				Writer trainData = new OutputStreamWriter(new FileOutputStream(outputTrainFile));
				System.out.println(f.toString());
				BufferedReader reader = new BufferedReader(new FileReader(f));
				
				int counter = 0;
				String c = "";
				
				while((c=reader.readLine())!=null){
					if(counter%5 == 0){
						testData.write(c+"\n");
					}
					if(counter%5 != 0)
					{
						trainData.write(c+"\n");
					}
					counter++;
				}
				
				testData.flush();
				trainData.flush();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Divide files into 5 sets
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
			
			File trainDataFile1 = new File(setDataDir1+File.separator+type+"_train"+1);
			File trainDataFile2 = new File(setDataDir2+File.separator+type+"_train"+2);
			File trainDataFile3 = new File(setDataDir3+File.separator+type+"_train"+3);
			File trainDataFile4 = new File(setDataDir4+File.separator+type+"_train"+4);
			File trainDataFile5 = new File(setDataDir5+File.separator+type+"_train"+5);

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
	
			File testDataFile1 = new File(setDataDir1+File.separator+type+"_test"+1);
			File testDataFile2 = new File(setDataDir2+File.separator+type+"_test"+2);
			File testDataFile3 = new File(setDataDir3+File.separator+type+"_test"+3);
			File testDataFile4 = new File(setDataDir4+File.separator+type+"_test"+4);
			File testDataFile5 = new File(setDataDir5+File.separator+type+"_test"+5);
	
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
	 * Divide reviews in files into individual files
	 * 
	 * @param inputDir
	 */
	public static void grainToTrain(String inputDir){
		
	try{
		File files[] = new File(inputDir).listFiles();
		System.out.println(files[0] + " "+ files[1]);
		
		for(File f : files){
			
			File testDir = new File(inputDir+File.separator+"TESTDIRECTORY");
			if(!testDir.exists())testDir.mkdir();
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			int i = 0;
			String c = "";
			File dirUpper = null;
			File dirLower = null;
			while((c = b.readLine())!=null){
					i++;
					
					if(f.toString().contains("test")){
						dirUpper = new File(testDir+File.separator+"TEST");
						if(!(dirUpper.exists()))dirUpper.mkdir();
						
						if(f.toString().contains("truth")){
							dirLower = new File(dirUpper+File.separator+"TRUTH");
							if(!(dirLower.exists()))dirLower.mkdir();
						}
						if(f.toString().contains("decep")){
							dirLower = new File(dirUpper+File.separator+"DECEP");
							if(!(dirLower.exists()))dirLower.mkdir();
						}
					}
					if(f.toString().contains("train")){
						dirUpper = new File(testDir+File.separator+"TRAIN");
						if(!(dirUpper.exists()))dirUpper.mkdir();
						
						if(f.toString().contains("truth")){
							dirLower = new File(dirUpper+File.separator+"TRUTH");
							if(!(dirLower.exists()))dirLower.mkdir();
						}
						if(f.toString().contains("decep")){
							dirLower = new File(dirUpper+File.separator+"DECEP");
							if(!(dirLower.exists()))dirLower.mkdir();
						}
					}
					
					File outPutFile = new File (dirLower+File.separator+i);
					System.out.println(dirUpper);
					System.out.println(dirLower);
					System.out.println(outPutFile);
					if(!(outPutFile.exists()))outPutFile.createNewFile();
					
					Writer writer = new OutputStreamWriter(new FileOutputStream(outPutFile));
					writer.write(c);
					writer.flush();

					//dir.delete();
				}
		}
	}catch(IOException e){
		e.printStackTrace();
		}
	
	}
	
	/**
	 * Make data sets and train and test model
	 * 
	 * @param filePathTrain
	 * @param filePathTest
	 * @param gram
	 */
	public static void makeDataSet(String filePathTrain,String filePathTest,int gram1, int gram2,ClassifersToUse c){
			
		TextDirectoryLoader loader = new TextDirectoryLoader();
	    try {
	    	
	    		loader.setDirectory(new File(filePathTrain));
				Instances dataRawTrain = loader.getDataSet();
				
				loader.setDirectory(new File(filePathTest));
				Instances dataRawTest = loader.getDataSet();
			    
				
				StringToWordVector filter = new StringToWordVector();
	    		NGramTokenizer tokeniser = new NGramTokenizer();
	    		
	    		tokeniser.setNGramMinSize(gram1);
	    		tokeniser.setNGramMaxSize(gram2);
	    		
	    		filter.setTokenizer(tokeniser);
	    		

			    filter.setInputFormat(dataRawTrain);
			    
			    Instances train = Filter.useFilter(dataRawTrain, filter);
	    	
				//filter.setInputFormat(dataRawTest);
				
			    Instances test = Filter.useFilter(dataRawTest, filter);
			    
			    
			    /***
			     * 
			     * 
			     * Replace this function each time to change models
			     * 
			     * 
			     */
			    //trainModelNaiveBayes(train,test);
			    //trainModelLibLinear(train,test);
			    if(c == ClassifersToUse.NaiveBayes)
			    	trainModelNaiveBayes(train, test);
			    if(c == ClassifersToUse.LibLinear)
			    	trainModelLibLinear(train, test);
			    if(c == ClassifersToUse.SMO)
			    	trainModelSMO(train, test);
			    if(c == ClassifersToUse.SVM)
			    	trainModelLibSVM(train, test);
			    
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void trainModel(Instances dataTrain,Instances dataTest){
	    try {
	    		LibLINEAR classifier = new LibLINEAR();
		    	classifier.setBias(10);
	    		
		    	classifier.buildClassifier(dataTrain);
		    	
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(classifier, dataTest);
				
				System.out.println(eval.toSummaryString("\nResults\n======\n", false));
				 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Model to train in SMO
	 * 
	 * @param dataTrain
	 * @param dataTest
	 */
	public static void trainModelSMO(Instances dataTrain,Instances dataTest){
	    try {
	    		SMO classifier = new SMO();
	    		
	    		CVParameterSelection ps = new CVParameterSelection();
	    	    ps.setClassifier(classifier);
	    	    ps.setNumFolds(5);  // using 5-fold CV
	    	    ps.addCVParameter("C 0.1 0.5 5");

	    	    // build and output best options
	    	    ps.buildClassifier(dataTrain);
	    		
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(ps, dataTest);
				System.out.println("Results of the set :::::::::::::::::::::: ");
				System.out.println("Percentage of correctly classified instances : " + eval.pctCorrect()+
						"\n"+ "Percentage of incorrectly classified instances : " + eval.pctIncorrect());
				System.out.println("No of correct predictions : "+eval.correct());
				System.out.println("TRUTHFUL");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(0)+ "\n"+"F measure/score  : "+eval.fMeasure(0));
				System.out.println("DECEPTIVE");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(1)+ "\n"+"F measure/score  : "+eval.fMeasure(1));
				 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * train Model in LibLinear
	 * 
	 * @param dataTrain
	 * @param dataTest
	 */
	public static void trainModelLibLinear(Instances dataTrain,Instances dataTest){
	    try {
	    		LibLINEAR classifier = new LibLINEAR();
	    		
	    		CVParameterSelection ps = new CVParameterSelection();
	    	    ps.setClassifier(classifier);
	    	    ps.setNumFolds(5);  // using 5-fold CV
	    	    ps.addCVParameter("C 0.1 0.5 5");

	    	    // build and output best options
	    	    ps.buildClassifier(dataTrain);
	    		
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(ps, dataTest);
				System.out.println("Results of the set :::::::::::::::::::::: ");
				System.out.println("Percentage of correctly classified instances : " + eval.pctCorrect()+
						"\n"+ "Percentage of incorrectly classified instances : " + eval.pctIncorrect());
				System.out.println("No of correct predictions : "+eval.correct());
				System.out.println("TRUTHFUL");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(0)+ "\n"+"F measure/score  : "+eval.fMeasure(0));
				System.out.println("DECEPTIVE");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(1)+ "\n"+"F measure/score  : "+eval.fMeasure(1));
				 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *
	 * Naive bayes trainer
	 * 
	 * @param dataTrain
	 * @param dataTest
	 */
	public static void trainModelNaiveBayes(Instances dataTrain,Instances dataTest){
	    try {
	    		NaiveBayes classifier = new NaiveBayes();

	    		CVParameterSelection ps = new CVParameterSelection();
	    	    ps.setClassifier(classifier);
	    	    ps.setNumFolds(5);  // using 5-fold CV
	    	    //ps.addCVParameter("C 0.1 0.5 5");

	    	    // build and output best options
	    	    ps.buildClassifier(dataTrain);
	    		
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(ps, dataTest);
				System.out.println("Results of the set :::::::::::::::::::::: ");
				System.out.println("Percentage of correctly classified instances : " + eval.pctCorrect()+
						"\n"+ "Percentage of incorrectly classified instances : " + eval.pctIncorrect());
				System.out.println("No of correct predictions : "+eval.correct());
				System.out.println("TRUTHFUL");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(0)+ "\n"+"F measure/score  : "+eval.fMeasure(0));
				System.out.println("DECEPTIVE");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(1)+ "\n"+"F measure/score  : "+eval.fMeasure(1));
				System.out.println("\n"+"----------------------------------------------------------------------------------------"+"\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * SVM trainer
	 * 
	 * @param dataTrain
	 * @param dataTest
	 */
	public static void trainModelLibSVM(Instances dataTrain,Instances dataTest){
	    try {
	    		LibSVM classifier = new LibSVM();
	    		
	    		CVParameterSelection ps = new CVParameterSelection();
	    	    ps.setClassifier(classifier);
	    	    ps.setNumFolds(5);  // using 5-fold CV
	    	    //ps.addCVParameter("C 0.1 0.5 5");

	    	    // build and output best options
	    	    ps.buildClassifier(dataTrain);
	    		
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(ps, dataTest);
				System.out.println("Results of the set :::::::::::::::::::::: ");
				System.out.println("Percentage of correctly classified instances : " + eval.pctCorrect()+
						"\n"+ "Percentage of incorrectly classified instances : " + eval.pctIncorrect());
				System.out.println("No of correct predictions : "+eval.correct());
				System.out.println("TRUTHFUL");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(0)+ "\n"+"F measure/score  : "+eval.fMeasure(0));
				System.out.println("DECEPTIVE");
				System.out.println("Precision : " +eval.precision(0)+ "\n"
						+"Recall : "+eval.recall(1)+ "\n"+"F measure/score  : "+eval.fMeasure(1));
				 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}