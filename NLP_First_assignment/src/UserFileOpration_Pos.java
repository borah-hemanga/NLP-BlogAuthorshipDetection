import java.io.*;
import java.util.*;
import java.lang.*;

import opennlp.tools.postag.POSTaggerME;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.tokenizers.*;

public class UserFileOpration_Pos {
    
	public static HashMap<String,Object> hm_line = new HashMap<String,Object>();
	public static HashMap<String,Object> hm = new HashMap<String,Object>();
	public static HashMap<String,Integer> DF = new HashMap<String,Integer>();
	
	public void clearHashtable()
	{
		hm.clear();
	}
	public Instances createinstance() throws IOException {
		// TODO Auto-generated method stub
		
		
		
		FastVector arrbset = new FastVector();
		FastVector attVals = new FastVector();
	    attVals.addElement("True");
	    attVals.addElement("Deceptive");
		int k = 0 ;
		//Attribute atrr = new Attribute("Attr");
		Map<String, Object> map = hm ;
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    String key = entry.getKey();
		   
		    arrbset.addElement(new Attribute(key));
		   
		}
		arrbset.addElement(new Attribute("att2",attVals));
		
		
		 Instances data = new Instances("Entires",arrbset,0);
		return data;
		
		
		
		
		
	}

	public Instances readFile(String Filename,boolean addfeaturevector,Instances data , POSTaggerME tagger ) {
		File dirName = new File("E:\\Java_NLP\\NLP_First_assignment");
		 int counter = 0 ; 
		try {
			Instances new_data = data;
			BufferedReader br;
			//Scanner sc = new Scanner(System.in);
		
			br = new BufferedReader(new FileReader(new File(dirName,
					Filename)));
            //hm.clear();
			String s = null; // br.readLine();
			char add = 'T';
			
			// s = br.readLine();
			while ((s = br.readLine()) != null) {
                counter ++ ;  
                String []split_data = s.split(" ");
                String [] tags = tagger.tag(split_data);
                int k = tags.length ;
                k = k-1;
				
                while ( k >= 0)
				{
                    
					
                    String nextgram = tags[k];
                   
                    	
                    k -- ;
					if(!addfeaturevector)
					{
						if (!(hm.containsKey((String) nextgram))) {
							
							hm.put((String) nextgram, 1);
											
								
						} else {
							hm.put((String) nextgram,
									(Integer) hm.get((String) nextgram) + 1);
	                      
						}
					}
					else
					{
						if (!(hm_line.containsKey((String) nextgram))) {
							hm_line.put((String) nextgram, 1);
														
    					} else {
							hm_line.put((String) nextgram,
							(Integer) hm_line.get((String) nextgram) + 1);         
						         }
						
					}
				}
				
				
				if(addfeaturevector)
				{
					
					new_data = addFeatureVector(new_data,add);
					hm_line.clear();
					/*code to flip the class of review*/
					if(((counter%4)) == 0)
					{
						if (add == 'T')
						{
							add = 'F';
						}
						else
						{
						  add = 'T';
						}
					}
					
						
					
				}else
				{
				//return null ;
				}
				
			}
			return new_data;

			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while reading File");
		}
		return null;

	
	
	}

	public Instances addFeatureVector(Instances data,char add) {
		// TODO Auto-generated method stub
		double[] vals;
		int count = 0 ; 
		int index = 0;
		Map<String, Object> map = hm_line ;
		 vals = new double[data.numAttributes()];
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			    String key = entry.getKey();
			    /* Normalization TD IDF*/
			    if((hm.containsKey(key))){
			    	Attribute atr = data.attribute(key);
			    	index = atr.index();
			        count = countofkeyInReview(key);
			    	vals[index] = ((Integer) map.get((Object)key)).doubleValue();
			    	if((Math.log(count))  != 0 )
			    	vals[index] = ((vals[index])/ (Math.log(count)));
			    	else
			    		vals[index] = 0;
			    }
		}
		Instance ins = new Instance(1.0,vals);
		ins.setDataset(data);
		
		if(add == 'T')
		{
			ins.setValue(data.attribute("att2"),"True");}
		else{
			ins.setValue(data.attribute("att2"),"Deceptive");}
		
		data.add(ins);
		return data;
		   
	}
	public int countofkeyInReview(String key) {
		// TODO Auto-generated method stub
		int  k = (Integer)DF.get((Object)key);
		
		return k;
	}
	public void createDFhash(String string1, String string2, String string3,
			String string4 ,POSTaggerME tagger) throws IOException {
		// TODO Auto-generated method stub
		File dirName = new File("E:\\Java_NLP\\NLP_First_assignment");
		BufferedReader br1;
		BufferedReader br2;
		BufferedReader br3;
		BufferedReader br4;
			
		br1 = new BufferedReader(new FileReader(new File(dirName,string1)));
		br2 = new BufferedReader(new FileReader(new File(dirName,string2)));
		br3 = new BufferedReader(new FileReader(new File(dirName,string3)));
		br4 = new BufferedReader(new FileReader(new File(dirName,string4)));
		
    	String s = null; 
    	
		
		initializeDF();
		setvalueDF(br1,tagger);
		setvalueDF(br2,tagger);
		setvalueDF(br3,tagger);
		setvalueDF(br4,tagger);
				
		
	  
	}
	private void setvalueDF(BufferedReader br1 ,POSTaggerME tagger) throws IOException {
		// TODO Auto-generated method stub
		String s = null ;
		//NGramTokenizer ngt = new NGramTokenizer();
		//ngt.setNGramMinSize(3);
		//ngt.setNGramMaxSize(3);
		//ngt.setDelimiters(" ");
		 String []split_data ;
		while ((s = br1.readLine()) != null) {
	          
			split_data = s.split(" ");
             String [] tags = tagger.tag(split_data);
             int k = tags.length ;
             k = k-1 ;
             HashSet<String> h = new HashSet<String>();
             while ( k >= 0)
				{           
				 String nextgram = tags[k];
            
			     h.add(nextgram);
			     k -- ;
			    
		    }
			 Iterator<String> it = h.iterator();
			    while (it.hasNext()) {
			        String pairs = (String)it.next();
			        DF.put(pairs, DF.get(pairs) + 1);
			        
			    }
		}
		
	}
	private void initializeDF() {
		Set<String> hmSet = hm.keySet();
		Iterator<String> iter = hmSet.iterator();
		while(iter.hasNext()){
			String item = (String)iter.next();
			DF.put(item, 0);
		}
	}
}