package project;

import java.io.File;
import java.io.FileOutputStream;
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
public class MachineLearning {

	public static void main(String args[]){
		
		Type type = Type.TypeGeneral;
		String path;
		
		if(type == Type.TypeGeneral){
			path = "MainFolder";
		}
		else
			path = "POS";
		
		String typeGeneral = path;
		
		int gram1 = 1;
		int gram2 = 2;
		ClassifersToUse c;
		c = ClassifersToUse.LibLinear;
		
		System.out.println("RESULTS FOR THE INDIVIDUAL FOLDS" + " \n\nFor GRAM ----------------- Min Gram " + gram1 + " and Max Gram " + gram2 + "\n\nClassifier " + c);
		
		for(int i = 1;i<6;i++)
			testForNGrams(typeGeneral+"/Train/"+1+"/train",typeGeneral+"/Train/"+1+"/test", gram1, gram2,c);
		
		/*
		 * Use this function for testing purpose
		 */
		int trainSetSelected = 5;
		System.out.println("\n\n\n\n\n\n\nResults for the test on unseen data ");
		testForNGrams(typeGeneral+"/Train/"+trainSetSelected+"/train",typeGeneral+"/Test",gram1,gram2,c);

	}
	
	/**
	 * 
	 * Test for testing data in same folder
	 * 
	 * @param type
	 * @param gram
	 */
	
	public static void testForNGrams(String path1,String path2,int gram1,int gram2,ClassifersToUse c){
			makeDataSet(path1,path2,gram1,gram2,c);
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
				filter.setInputFormat(dataRawTrain);
				filter.setIDFTransform(true);
				//filter.setOptions(Utils.splitOptions("-R first-last -W 100000 -prune-rate -1.0 -C -T -I -N 1 -L"));
	    		NGramTokenizer tokeniser = new NGramTokenizer();
	    		
	    		tokeniser.setNGramMinSize(gram1);
	    		tokeniser.setNGramMaxSize(gram2);
	    		
	    		filter.setTokenizer(tokeniser);
			    
			    Instances train = Filter.useFilter(dataRawTrain, filter);
	    	
				
			    Instances test = Filter.useFilter(dataRawTest, filter);
			    
			    
			    /** 
			     * 
			     * Code to verify the Arff files are valid or not...
			     * 
			     */
			    
			    /*File newFile = new File("D:/train");
				newFile.createNewFile();
				Writer writerTest = new OutputStreamWriter(new FileOutputStream(newFile));
				writerTest.write(train.toString());
				writerTest.flush();
				writerTest.close();
			    
			    
				newFile = new File("D:/test");
				newFile.createNewFile();
				writerTest = new OutputStreamWriter(new FileOutputStream(newFile));
				writerTest.write(test.toString());
				writerTest.flush();
				writerTest.close();*/
				
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
	    		
	    	    //Utils.joinOptions(ps.getBestClassifierOptions());
	    	    
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(ps, dataTest);
				
				printResults(eval);
				
				 
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
				
				printResults(eval);
				
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
	    	    //ps.addCVParameter("C 0.5 1.0 10");

	    	    // build and output best options
	    	    ps.buildClassifier(dataTrain);
	    		
	    	    
	    		Evaluation eval = new Evaluation(dataTrain);
				eval.evaluateModel(ps, dataTest);
				
				printResults(eval);
				
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
				
				printResults(eval);
								 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void printResults(Evaluation eval){
		System.out.println("Results of the set :::::::::::::::::::::: "+"\n");
		System.out.println("Percentage of correctly classified instances : " + eval.pctCorrect()+
				"\n"+ "Percentage of incorrectly classified instances : " + eval.pctIncorrect());
		System.out.println("No of correct predictions : "+eval.correct());
		
		for(int i = 0; i<5; i++){
			System.out.println("\nWriter No " + i + " --------");
			System.out.println("Precision : " +eval.precision(i)+ "\n"
					+"Recall : "+eval.recall(i)+ "\n"+"F measure/score  : "+eval.fMeasure(i)+"\n");
		}
		System.out.println("\n"+"----------------------------------------------------------------------------------------"+"\n\n\n\n");
	}

}
