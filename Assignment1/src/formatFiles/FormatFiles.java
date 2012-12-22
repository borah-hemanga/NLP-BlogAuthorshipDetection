package formatFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FormatFiles {
	
	 public static void main(String[] args) {
		 
		String directoryPath = "C:/testData";
		int totaNoOfDocs = 0; 

		
		divideIntoFiveDataSets("c:/testData","hotel_deceptive");
		divideIntoFiveDataSets("c:/testData","hotel_truthful");
		
		for (int i = 0; i < 5; i++)
		{
		try {
				File filePathDeceptive = new File(directoryPath + File.separator + "train_hotel_deceptive"+i);
				File filePathTruthful = new File(directoryPath + File.separator + "train_hotel_truthful"+i);
				
				HashMap<Integer,WordStoringObject> masterHashMap = new HashMap<Integer,WordStoringObject>();
				totaNoOfDocs = addToFeatureVector(filePathTruthful,masterHashMap);
				
				System.out.println(masterHashMap.get("my".hashCode()).word+ " "+masterHashMap.get("my".hashCode()).count+ " "+masterHashMap.get("my".hashCode()).appearedIn);
				
				totaNoOfDocs += addToFeatureVector(filePathDeceptive,masterHashMap);
				
				System.out.println(masterHashMap.get("my".hashCode()).word+ " "+masterHashMap.get("my".hashCode()).count+" "+masterHashMap.get("my".hashCode()).appearedIn);
				
				calculateIdf(masterHashMap,totaNoOfDocs);
				
				//System.out.println(masterHashMap.get("my".hashCode()).word+ " "+masterHashMap.get("my".hashCode()).count+" "+masterHashMap.get("my".hashCode()).appearedIn+" "+masterHashMap.get("my".hashCode()).idf);
				
				
				addUnknownTag(masterHashMap);
				
				System.out.println(masterHashMap.get("<UNK>".hashCode()).word+ " "+masterHashMap.get("my".hashCode()).count+" "+masterHashMap.get("my".hashCode()).appearedIn+" "+masterHashMap.get("<UNK>".hashCode()).idf);

				File filePath = new File(directoryPath+File.separator);
				createTrainDataForSVM(filePath,masterHashMap,"feature_vector"+i +"train",i,"train");
				createTrainDataForSVM(filePath,masterHashMap,"feature_vector"+i +"test",i,"test");
				System.out.println(directoryPath+File.separator+"feature_vector"+i +"train");
				
	     } catch (Exception e) {
	    	  System.err.println(e.getMessage());
	    	  e.printStackTrace();
	      }
	   }
		
	 }
	
	 public static void addUnknownTag(HashMap<Integer,WordStoringObject> masterHashMap){
		 
		 int minVal = findMinimum(masterHashMap);
		 
		 if(masterHashMap.containsKey(minVal)){
			 WordStoringObject word = masterHashMap.get(minVal);
			 masterHashMap.remove(minVal);
			 masterHashMap.put("<UNK>".hashCode(), word);
		 }
		 
	 }
	 
	 public static int findMinimum(HashMap<Integer,WordStoringObject> masterHashMap){
		 Set <Integer> listOfIds = masterHashMap.keySet();
		 int minVal = 400;
		 int id = 0;
		 for(int val : listOfIds){
			 int currentCount = masterHashMap.get(val).getCount();
			 if(currentCount<=minVal){
				 minVal = currentCount;
				 id = val;
			 }
		 }
		 return id;
	 }
	 
	public static void calculateIdf ( HashMap<Integer,WordStoringObject> masterHashMap  , int totalNoOfDocs){
		Set <Integer> listOfIds = masterHashMap.keySet();
		for(int val : listOfIds)
		{
			WordStoringObject word = masterHashMap.get(val);
			word.idf =  (float)((float)totalNoOfDocs/(float)word.appearedIn);//(masterHashMap.get(word.hashCode()).appearedIn)
			//if(word.word.equals("my"))System.out.println(word.word+ " "+word.idf+" "+totalNoOfDocs+" "+word.appearedIn);
			masterHashMap.put(val, word);
		}
	}
	 
	 public static void divideIntoFiveDataSets(String filePath,String type){
		 
		 String train = "c:/testData"+File.separator+"train_"+ type;
		 String test = "c:/testData"+File.separator+"test_" + type;
		 OutputStream outputStreamTrain = null;
		 OutputStream outputStreamTest = null;
		 
		 InputStreamReader is = null;
		
		int iterationNumber = 0;

			  try {
				
				for(;iterationNumber< 5 ; iterationNumber++){
				String iteratorVal = "" + iterationNumber;

				File outPutFileTrain = new File(train+iteratorVal);
				File outPutFileTest = new File(test+iteratorVal);  
				
				outputStreamTrain = new FileOutputStream(outPutFileTrain);
				if(!outPutFileTrain.exists())
					outPutFileTrain.createNewFile();
				Writer writerTrain = new OutputStreamWriter(outputStreamTrain);
				
				outputStreamTest = new FileOutputStream(outPutFileTest);
				if(!outPutFileTest.exists())
					outPutFileTest.createNewFile();
				Writer writerTest = new OutputStreamWriter(outputStreamTest);
				
				is = new InputStreamReader(new FileInputStream(filePath+File.separator+type));
				
				BufferedReader b = new BufferedReader (is);
				String c;
				String reviewWithoutPunctuations = "";
				int checkIteration = iterationNumber;
				while ((c = b.readLine()) != null) {
					checkIteration++;
					  //reviewWithoutPunctuations = removePunctuations(c) + "\n";
					reviewWithoutPunctuations = c+"\n";
					 if(checkIteration%5 == 0){
						 writerTest.write(reviewWithoutPunctuations);
					 }
					 else
					 {
						 writerTrain.write(reviewWithoutPunctuations);
					 }
					  
					  writerTest.flush();
					  writerTrain.flush();
			  }
			}
			  } catch (FileNotFoundException e) {
					e.printStackTrace();
			  } catch (IOException e) {
				e.printStackTrace();
			}
			  finally{
				  try {
					outputStreamTrain.close();
					outputStreamTest.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
}
	 
	 public static void createTrainDataForSVM(File filePath,HashMap<Integer,WordStoringObject> masterHashMap,String fileOutputName,int iteratorNum,String reviewType){
		 String truth = filePath +File.separator+ reviewType+"_hotel_truthful" + iteratorNum;
		 String decep = filePath +File.separator+ reviewType+"_hotel_deceptive" + iteratorNum;
		 
		 File outPutFile = new File(filePath + File.separator+fileOutputName);
		 File filePathTruth = new File(truth);
		 File filePathDecep = new File(decep);
		 
		 OutputStream outputStream = null;
		 InputStreamReader isTruth = null;
		 InputStreamReader isDecep = null;
		 int counter = 0;
		  try {
			if(!outPutFile.exists())
				outPutFile.createNewFile();
			
			outputStream = new FileOutputStream(outPutFile);
			Writer writerTrain = new OutputStreamWriter(outputStream);
			
			isTruth = new InputStreamReader(new FileInputStream(filePathTruth));
			isDecep = new InputStreamReader(new FileInputStream(filePathDecep));
			
			BufferedReader bTruth = new BufferedReader (isTruth);
			BufferedReader bDecep = new BufferedReader (isDecep);
			String c;
		  
			while ((c = bTruth.readLine()) != null) {
				  String reviewWithoutPunctuations = c; //removePunctuations(c);
				  ArrayList<String> a= parseSentenceForUnigrams(reviewWithoutPunctuations);
				  HashMap<Integer,WordStoringObject> lineHashMap = new HashMap<Integer, WordStoringObject>();
				  for(String word: a)
					  addValueToHashMap(lineHashMap, word, 0);
			  
				  String outputFeatureVector = "+1";
			  
				  for(String word: a){
					  float tf_idf = 0;
					  if(masterHashMap.get(word.hashCode())!=null){
						  //tf_idf = ((float)(lineHashMap.get(word.hashCode()).count)/(float)a.size());(masterHashMap.get(word.hashCode()).getIdf());
						  tf_idf = masterHashMap.get(word.hashCode()).getAppearedIn()*((float)(lineHashMap.get(word.hashCode()).count)/(float)a.size());
						  outputFeatureVector+= " " + word.hashCode() + ":"+ tf_idf;
					  }
					  else {
						  if(masterHashMap.get("<UNK>".hashCode())!=null){
							 // tf_idf = ((float)(lineHashMap.get(word.hashCode()).count)/(float)a.size());(masterHashMap.get("<UNK>".hashCode()).getIdf());
						  outputFeatureVector+= " " + "<UNK>".hashCode() + ":"+ tf_idf;
						  }
					  }
					  //outputFeatureVector += " " + word +" ";
				  }
				  
				  	 outputFeatureVector += "\n";
					 writerTrain.write(outputFeatureVector);
					 
					 counter++;
		  }
			
			while ((c = bDecep.readLine()) != null) {
				  String reviewWithoutPunctuations = c;//removePunctuations(c);
				  ArrayList<String> a= parseSentenceForUnigrams(reviewWithoutPunctuations);
				  HashMap<Integer,WordStoringObject> lineHashMap = new HashMap<Integer, WordStoringObject>();
				  for(String word: a)
					  addValueToHashMap(lineHashMap, word, 0);
			  
				  String outputFeatureVector = "-1";
			  
				  for(String word: a){
					  float tf_idf = 0;
					  if(masterHashMap.get(word.hashCode())!=null){
						  //tf_idf = ((float)(lineHashMap.get(word.hashCode()).count)/(float)a.size());*/(masterHashMap.get(word.hashCode()).getIdf());
						  outputFeatureVector+= " " + word.hashCode() + ":"+ tf_idf;
					  }
					  else {
						  if(masterHashMap.get("<UNK>".hashCode())!=null){
							 // tf_idf =((float)(lineHashMap.get(word.hashCode()).count)/(float)a.size());(masterHashMap.get("<UNK>".hashCode()).getIdf());
						  outputFeatureVector+= " " + "<UNK>".hashCode() + ":"+ tf_idf;
						  }
					  }
					  outputFeatureVector += " " + word +" ";
				  }
				  
				  	 outputFeatureVector += "\n";
					 writerTrain.write(outputFeatureVector);
					 writerTrain.flush();
					 counter++;
		  }
		  } catch (FileNotFoundException e) {
				e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		}
		  finally{
			  try {
				outputStream.flush();
				outputStream.close();
				isTruth.close();
				isDecep.close();
				System.out.println(counter);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	 }
	 
	  /**
	   * Makes feature vectors
	   * 
	   * @param filePath
	   * @param masterHashMap
	   */
	  public static int addToFeatureVector(File filePath,HashMap<Integer,WordStoringObject> masterHashMap){
		  int docNo = 0;
		  InputStreamReader is = null;
		  try {
			is = new InputStreamReader(new FileInputStream(filePath));
			BufferedReader b = new BufferedReader (is);
			String c;
		  while ((c = b.readLine()) != null) {
			  String reviewWithoutPunctuations = c;//removePunctuations(c);
			  ArrayList<String> a= parseSentenceForUnigrams(reviewWithoutPunctuations);
			  for(String word : a){
					  addValueToHashMap(masterHashMap,word,docNo);
			  }
			  docNo++;
		  }
		  } catch (FileNotFoundException e) {
				e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		}
		  finally{
			  try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  return docNo;
	  }
	  
	  /**
	   * Adds to HashMap
	   * 
	   * @param hashMap
	   * @param word
	   */
	  public static void addValueToHashMap(HashMap<Integer,WordStoringObject> hashMap,String word,int docNo){
		  Integer key = word.hashCode();

		  if(hashMap.containsKey(key)){
			  WordStoringObject wordVal = hashMap.get(key);
			  int newCount = wordVal.count;
			  int oldDocNo = wordVal.docNo;
			  int appearedInTillNow = wordVal.appearedIn;
			  if(oldDocNo != docNo){
				  appearedInTillNow++;
			  }
			  newCount++;
			  wordVal = new WordStoringObject(word,newCount,docNo,appearedInTillNow);
			  hashMap.put(key, wordVal);
			  
			  
			  return;
		  }
		  else{
			  WordStoringObject wordVal = new WordStoringObject(word,1,docNo,1);
			  hashMap.put(key, wordVal);  
			  
			  return;
		  }
	  }
	  
	  /**
	   * Removes punctuations from sentences
	   * 
	   * @param sentence
	   * @return
	   */
	  public static String removePunctuations(String sentence){
		  int start = 0;
		  int end = sentence.length();
		  int current = start;
		  String newSentence = "";
		  boolean changed = false;
		  while(start<end){
			  if(sentence.charAt(current) == '.' || sentence.charAt(current)==','||sentence.charAt(current)==':'
					  ||sentence.charAt(current)=='!'||sentence.charAt(current)=='/'||sentence.charAt(current)=='('||sentence.charAt(current)==')'){
				  newSentence += sentence.substring(start, current);
				  current+=1;
				  if(current<end && sentence.charAt(current)!=' ') newSentence+=" ";
				  start = current;
				  changed = true;
			  }
			  current++;
			  if(current>=end) break;
		  }
		  
		  if(changed)
		  return newSentence.toLowerCase();
		  else return sentence.toLowerCase();
	  }
	  
	  /**
	   * Generates unigrams for sentences
	   * 
	   * @param sentence
	   * @return
	   */
	  public static ArrayList<String> parseSentenceForUnigrams(String sentence){
		  ArrayList<String> words = new ArrayList<String>();

		  int start = 0;
		  int end = sentence.length();
		  int currentChar = 0;
		  while(currentChar<end)
		  {
			  if(sentence.charAt(currentChar) == ' '){
				  String word = sentence.substring(start, currentChar).toLowerCase();
				  words.add(word);
				  start = currentChar + 1;
			  }
			  currentChar++;
		  }
		  String word = sentence.substring(start).toLowerCase();

		  words.add(word);
		  
		  return words;
	  }
	  
	  /**
	   * Generates bigrams for sentences
	   * 
	   * @param sentence
	   * @return
	   */
	  public static ArrayList<String> parseSentenceForBigrams(String sentence){
		  ArrayList<String> words = new ArrayList<String>();

		  int start = 0;
		  int end = sentence.length();
		  int currentPosition = start;
		  int prevPosition = start;
		  int prevPositionOld = start;
		  boolean keepTrackOfFirstValue = true; 
		  while(currentPosition<end)
		  {
			  if(sentence.charAt(currentPosition) == ' '){
				  String word = sentence.substring(prevPositionOld, currentPosition).toLowerCase();
				  if(keepTrackOfFirstValue == false) words.add(word);//sentence.substring(start, currentChar).toLowerCase()
				  prevPositionOld = prevPosition;
				  prevPosition = currentPosition + 1;
				  keepTrackOfFirstValue = false;
			  }
			  currentPosition++;
		  }
		  String word = sentence.substring(prevPositionOld).toLowerCase();
		  words.add(word);//sentence.substring(start)
		  return words;
	  }
	  
	  
	  /**
	   * Generates trigrams for sentences
	   * 
	   * @param sentence
	   * @return
	   */
	  public static ArrayList<String> parseSentenceForTrigrams(String sentence){
		  ArrayList<String> words = new ArrayList<String>();

		  int start = 0;
		  int end = sentence.length();
		  int currentPosition = start;
		  int prevPosition = start;
		  int prevPositionOld1 = start;
		  int prevPositionOld2 = start;
		  
		  int keepTrackOfFirstValue = 0;
		  
		  while(currentPosition<end)
		  {
			  if(sentence.charAt(currentPosition) == ' '){
				  String word = sentence.substring(prevPositionOld2, currentPosition).toLowerCase();
				  if(keepTrackOfFirstValue > 1) words.add(word);//sentence.substring(start, currentChar).toLowerCase()
				  prevPositionOld2 = prevPositionOld1;
				  prevPositionOld1 = prevPosition;
				  prevPosition = currentPosition + 1;
				  keepTrackOfFirstValue++;
			  }
			  currentPosition++;
		  }
		  String word = sentence.substring(prevPositionOld2).toLowerCase();
		  words.add(word);//sentence.substring(start)
		  return words;
	  }
	  
}