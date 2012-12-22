import java.io.*;
import java.util.*;
import java.lang.*;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.tokenizers.*;

public class UserFileOperation1 {
    
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
	    attVals.addElement("Charlie");
	    attVals.addElement("Joe");
	    attVals.addElement("Josh");
	    attVals.addElement("Kari");
	    attVals.addElement("Mekenzie");
		int k = 0 ;
		//Attribute atrr = new Attribute("Attr");
		Map<String, Object> map = hm ;
		//vals = new double[hm.size()];
		/*for (String key : map.keySet()) {
			arrbset.addElement(UserFileOperation1.mapDistinctString.get((String)key));
			vals[k++] = 
			
		}*/
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    String key = entry.getKey();
		    /*get the original key here*/
		    //int length = key.length();
		    //String key_original = key.substring(0,(length-1));
		   // Object value = (Double)entry.getValue();
		   // String S = ""+UserFileOperation1.mapDistinctString.get((String)key);
		    arrbset.addElement(new Attribute(key));
		    //System.out.println(key);
		    //vals[k++] = ((Double) value).doubleValue();
		}
		arrbset.addElement(new Attribute("att2",attVals));
		
		//for (Map.Entry<String, String> entry : hm.entrySet())
			//	{
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		//}
		//Set s=hm.keySet(); 
	
		//Iterator i=s.iterator();
		//i.
		//while(i.hasNext)
		//	S.o.p(i.next());
		//S.o.p(i.next());  
		//System.out.println("Vaibhav" +
			//	"");
		 Instances data = new Instances("Entires",arrbset,0);
		return data;
		
		
		 //vals = new double[data.numAttributes()];
		 //for (Object value : map.values()) {
			    // ...
			//}
		 //data.add(new Instance(1.0,vals));
		 //System.out.println(data);
		 //new Instance()
		//addElement(new Attribute("att1"));
		
		// saveInstance(data);
		
		
	}

	public Instances readFile(String Filename,boolean addfeaturevector,Instances data ) {
		File dirName = new File("MainFolder");
		 int counter = 0 ; 
		try {
			Instances new_data = data;
			BufferedReader br;
			//Scanner sc = new Scanner(System.in);
		//	System.out.print("What is the minimum lenght of token");
			NGramTokenizer ngt = new NGramTokenizer();
			//ngt.setNGramMinSize(Integer.parseInt(sc.nextLine()));
			ngt.setNGramMinSize(1);
			//System.out.print("What is the maximum lenght of token");
			//Scanner sc2 = new Scanner(System.in);
			//ngt.setNGramMaxSize(Integer.parseInt(sc2.nextLine()));
			ngt.setNGramMaxSize(1);
			ngt.setDelimiters(" ");
			br = new BufferedReader(new FileReader(new File(dirName,
					Filename)));
            //hm.clear();
			String s = null; // br.readLine();
			char add = 'C';
			
			// s = br.readLine();
			while ((s = br.readLine()) != null) {
                counter ++ ;  
				ngt.tokenize(s);
				// System.out.println(ngt.getDelimiters());
				//Enumeration temp = ngt.listOptions();
				//HashMap hm = new HashMap();
				//hm.clear();
				// while(temp.hasMoreElements())
				while (ngt.hasMoreElements())
				{
					// (String)ngt.nextElement();
                    
					String nextgram = (String) ngt.nextElement();
					//nextgram = nextgram+ add ;
					
					//Console objConsole = System.console();
				//	if(nextgram == null)
					//objConsole.printf("Vaibhav Kumar");
					//cprintf("%s",nextgram);
					if(!addfeaturevector)
					{
						if (!(hm.containsKey((String) nextgram))) {
							hm.put((String) nextgram, 1);
							//System.out.println("Vaibhav");
							//System.out.println(nextgram);
							//cprintf("Vaibhav");
							
						/*	if(!(mapDistinctString.containsKey((Object)nextgram)))
							{
								mapDistinctString.put((Object)nextgram,counter++);
							}*/
							
							
	
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
					
					if(counter <10 )
					{
						add ='C';
					new_data = addFeatureVector(new_data,add);
					hm_line.clear();
					}
					else if(counter >= 10 && counter <20)
					{
						add ='1';
					new_data = addFeatureVector(new_data,add);
					hm_line.clear();
					}
					else if(counter >=20 && counter <30)
					{
						add ='2';
					new_data = addFeatureVector(new_data,add);
					hm_line.clear();
					}
					else if(counter >= 30 && counter <40)
					{
						add ='K';
					new_data = addFeatureVector(new_data,add);
					hm_line.clear();
					}
					else if(counter >= 40 && counter <50)
					{
						add ='M';
					new_data = addFeatureVector(new_data,add);
					hm_line.clear();
					}
					/*code to flip the class of review*/
					/*if(((counter%10)) == 0)
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
					*/
						
					
				}else
				{
				//return null ;
				}
				//new CreateARFFFile().createfile(hm);
			}
			return new_data;

			//new CreateARFFFile().createinstance(hm);
			// System.out.println(s);

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
			    	vals[index] = ((Integer) map.get((Object)key)).doubleValue(); // Add one smothing
			    	if((Math.log(count))  != 0 )
			    	   	vals[index] = ((vals[index])/(Math.log(count)));
			    	else
			    		vals[index] = 0;
			    }
		}
		Instance ins = new Instance(1.0,vals);
		ins.setDataset(data);
		
		if(add == 'C')
		{
			ins.setValue(data.attribute("att2"),"Charlie");}
		else if (add == '1'){
			ins.setValue(data.attribute("att2"),"Joe");}
		else if (add == '2'){
			ins.setValue(data.attribute("att2"),"Josh");}
		else if (add == 'K'){
			ins.setValue(data.attribute("att2"),"Kari");}
		else if (add == 'M'){
			ins.setValue(data.attribute("att2"),"Mekenzie");}
		data.add(ins);
		return data;
		   
	}
	public int countofkeyInReview(String key) {
		// TODO Auto-generated method stub
		int  k = (Integer)DF.get((Object)key);
		
		return k;
	}
	public void createDFhash(String string1, String string2, String string3,
			String string4) throws IOException {
		// TODO Auto-generated method stub
		File dirName = new File("MainFolder");
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
		setvalueDF(br1);
		setvalueDF(br2);
		setvalueDF(br3);
		setvalueDF(br4);
				
		
	  
	}
	private void setvalueDF(BufferedReader br1) throws IOException {
		// TODO Auto-generated method stub
		String s = null ;
		NGramTokenizer ngt = new NGramTokenizer();
		ngt.setNGramMinSize(1);
		ngt.setNGramMaxSize(1);
		ngt.setDelimiters(" ");
		while ((s = br1.readLine()) != null) {
	          
			ngt.tokenize(s);
			HashSet<String> h = new HashSet<String>();
			while (ngt.hasMoreElements())
			{
			  	String nextgram = (String) ngt.nextElement();
				
			    h.add(nextgram);
			    
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