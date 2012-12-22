package convertToPOS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ConvertToPOS {

	public static void main(String args[]){
		try {
			MaxentTagger tagger = new MaxentTagger("taggers/bidirectional-distsim-wsj-0-18.tagger");
			String inputDirName = "testData/inputFiles";
			File inputDir[] = new File(inputDirName).listFiles();
			String outputDirName = "testData"+File.separator+"POS";
			File outputDir = new File(outputDirName);
			if(!outputDir.exists())outputDir.mkdir();
			
			
		for(File f : inputDir){
			String outputFileName = "";
			if(f.toString().contains("decep"))
				outputFileName = outputDirName+File.separator+"decep";
			if(f.toString().contains("truth"))
				outputFileName = outputDirName+File.separator+"truth";
			
			File outputFile = new File(outputFileName);
			System.out.println(outputFile);
			
			if(!outputFile.exists())outputFile.createNewFile();
			Writer outputData = new OutputStreamWriter(new FileOutputStream(outputFile));
			
			BufferedReader input = new BufferedReader(new FileReader(f));
			String c  ="";
			
			while((c=input.readLine())!=null){
				String tagged = tagger.tagString(c);
				ArrayList<String> sentence = parseSentenceForUnigrams(tagged);
			
				String pos = "";
				for(String word : sentence){
				//if((!word.contains(".")) || (!word.contains("/")) || (!word.contains("(")) || (!word.contains(")")) || (!word.contains("-")) || (!word.contains("/")) || (!word.contains("'")) || (!word.contains(",")))
					if(word.length()>0)
						pos+=getType(word+" ");
				}
			//System.out.println(pos);
			outputData.write(pos+"\n");
			outputData.flush();
			
		}
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> parseSentenceForUnigrams(String sentence){
		  ArrayList<String> words = new ArrayList<String>();

		  int start = 0;
		  int end = sentence.length();
		  int currentChar = 0;
		  while(currentChar<end)
		  {
			  if(sentence.charAt(currentChar) == ' '){
				  String word = sentence.substring(start, currentChar);
				  words.add(word);
				  start = currentChar + 1;
			  }
			  currentChar++;
		  }
		  String word = sentence.substring(start);

		  words.add(word);
		  
		  return words;
	  }
	
	public static String getType(String s){
		if(s.length()>0){
		int index = s.indexOf('/');
		
		s = s.substring(index+1);
		}
		
		return s;
	}
}
