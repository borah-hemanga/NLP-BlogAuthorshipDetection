package test.pos;

import java.io.IOException;
import java.util.ArrayList;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ConvertToPOS {

	public static void main(String args[]){
		try {
			MaxentTagger tagger = new MaxentTagger("taggers/bidirectional-distsim-wsj-0-18.tagger");
			String sample = "I am a regular visitor at the Hard Rock Hotel in Chicago. The hotel is a comfortable stay and provides a homely atmosphere. They take care of each and every request you make. There was one instance when I had to get some medicine at the middle of the night. The manager personally handled the situation by taking me to the hospital nearby. I would also mention that the stay at the hotel is accompanied by a load of fun activities. These activities include singing, dancing and games. The hotel staff is very friendly and the room service is phenomenal. The food is custom available. The default culinary items are very fantastic as well. I therefore would like to recommend the hotel with the highest points.";
			
			String tagged = tagger.tagString(sample);
			
			
			ArrayList<String> sentence = parseSentenceForUnigrams(tagged);
			
			String pos = "";
			for(String word : sentence){
				if(word.length()>0)
				pos+=getType(word+" ");
			}
			
			System.out.println(pos);
			
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
