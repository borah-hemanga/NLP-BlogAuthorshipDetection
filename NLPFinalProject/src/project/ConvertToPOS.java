package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * 
 * @author Hemanga
 *
 * This file convets the files into the respective Parts of Speech
 */

public class ConvertToPOS {

	public static void main(String args[]){
		
		File files[] = new File("ManipulationFolder").listFiles();
		for(File f: files)
			makePos(f.toString());
		
		
		//File f = new File("MainFolder/");
			
		//makePos(f.toString());
		
	}
	
	/**
	 * Convert into POS for individual Files
	 * 
	 * @param inputFile
	 */
	
	public static void makePos(String inputFile){
		BufferedReader input = null;
		Writer outputData = null;
		MaxentTagger tagger = null;
		try {
			tagger = new MaxentTagger("taggers/bidirectional-distsim-wsj-0-18.tagger");			
			File f = new File(inputFile);
			String outputFileName = inputFile+"_pos";
			File outputFile = new File(outputFileName);
			System.out.println(outputFile);
			
			if(!outputFile.exists())outputFile.createNewFile();
			outputData = new OutputStreamWriter(new FileOutputStream(outputFile));
			
			input = new BufferedReader(new FileReader(f));
			String c  ="";
			
			while((c=input.readLine())!=null){
				String tagged = tagger.tagString(c);
				System.out.println(tagged);
				ArrayList<String> sentence = parseSentenceForUnigrams(tagged);
			
				String pos = "";
				for(String word : sentence){
					if(word.length()>0)
						pos+=getType(word+" ");
				}
			outputData.write(pos+"\n");
			outputData.flush();
			outputData.flush();
			
		}
			tagger = null;
			System.gc();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				outputData.close();
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tagger = null;
			System.gc();
			System.gc();
		}
	}
	
	/**
	 * 
	 * Removes the words and '/' marks from the POS
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
