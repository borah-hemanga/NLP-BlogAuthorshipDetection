import java.io.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import weka.core.Instances;

public class Pos {
static int unique = 0 ;

/*--------------------------- Code for reading source file in sequence so as to write on 5 files--------------------------------------- */
public static String[] splitFile(BufferedReader br) throws IOException
	
	{
		String S[]= new String[4];
		int counter = 0;
     	while (counter < 4 ) {
    		S[counter] = br.readLine();
              counter++;
    		//[counter] = br_deceptive.readLine();
    		 //out1.write(s_truth);
     	}		
		return S;
		
	
	}
/*--------------------------- Code for writing on 5 files from 2 source file--------------------------------------- */
public static void  writeOnFile(BufferedWriter out,BufferedReader br_truth,BufferedReader br_deceptive) throws IOException
  {
	
	  String S_truth[] =     splitFile(br_truth);
	  String S_deceptive[] = splitFile(br_deceptive);
	  //int i = 0 ;
	  /* Write 4 string True*/
	 // while(i<4)
	  //{
		  /*for truth string start */
	  //S_truth[i] = S_truth[i].replace('.', ' ');
	  //S_truth[i] = S_truth[i].replace(',', ' ');
	  //S_truth[i] = S_truth[i].replace('!', ' ');
	  //S_truth[i] = S_truth[i].replace(':', ' ');
	  //S_truth[i] = S_truth[i].replace('(', ' ');
	  //S_truth[i] = S_truth[i].replace(')', ' ');
	  //S_truth[i] = S_truth[i].replace('"', ' ');
	  //S_truth[i] = S_truth[i].replace('$', ' ');
	  //S_truth[i] = S_truth[i].replace('`', ' ');
	  
	 // S_truth[i] = S_truth[i].replace('~', ' ');
	  //S_truth[i] = S_truth[i].replace('`', ' ');
	  
	  //S_truth[i] = S_truth[i].replaceAll("'", " ");
	  
	  /*for truth string end */
	  /*for deceptive string start */
	  //S_deceptive[i] = S_deceptive[i].replace('.', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace(',', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace('!', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace(':', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace('(', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace(')', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace('"', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace('$', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace('`', ' ');
	  //S_deceptive[i] = S_deceptive[i].replaceAll("'", " ");
	  /*for deceptive string end */
	  //i++;
	  //}
	  out.write(S_truth[0]);
	  out.newLine();
	  out.write(S_truth[1]);
	  out.newLine();
	  out.write(S_truth[2]);
	  out.newLine();
	  out.write(S_truth[3]);
	  out.newLine();
	  
	  /* Write 4 string Deceptive*/
	  out.write(S_deceptive[0]);
	  out.newLine();
	  out.write(S_deceptive[1]);
	  out.newLine();
	  out.write(S_deceptive[2]);
	  out.newLine();
	  out.write(S_deceptive[3]);
	  out.newLine();
	  
   }
/*--------------------------- Code for Spliting 2 files in 5 files--------------------------------------- */
	public static void splitfiles () throws IOException
	{
		 int counter =0 ;
		 
		  FileWriter fstream1 = new FileWriter("1.txt");
		  BufferedWriter out1 = new BufferedWriter(fstream1);
		  FileWriter fstream2 = new FileWriter("2.txt");
		  BufferedWriter out2 = new BufferedWriter(fstream2);
		  FileWriter fstream3 = new FileWriter("3.txt");
		  BufferedWriter out3 = new BufferedWriter(fstream3);
		  FileWriter fstream4 = new FileWriter("4.txt");
		  BufferedWriter out4 = new BufferedWriter(fstream4);
		  FileWriter fstream5 = new FileWriter("5.txt");
		  BufferedWriter out5 = new BufferedWriter(fstream5);
		  
		  
		  
		  File dirName = new File("E:\\Java_NLP\\NLP_First_assignment\\src");
		  BufferedReader br_truth;
		  br_truth = new BufferedReader(new FileReader(new File(dirName,
				  "hotel_truthful")));
		  BufferedReader br_deceptive;
		  br_deceptive = new BufferedReader(new FileReader(new File(dirName,
				  "hotel_deceptive")));
		  
		  
		  
		  while(counter<20)
		  {
		  writeOnFile(out1,br_truth,br_deceptive);
		  writeOnFile(out2,br_truth,br_deceptive);
		  writeOnFile(out3,br_truth,br_deceptive);
		  writeOnFile(out4,br_truth,br_deceptive);
		  writeOnFile(out5,br_truth,br_deceptive);
		  counter++;
		  }
		 		 		  
		  //Close the output stream
		  out1.close();
		  out2.close();
		  out3.close();
		  out4.close();
		  out5.close();
		
	}
	/*--------------------------- Code for providing 2 distinct set  start--------------------------------------- */
	public static String [] setOfFiles (int counter)
	{
		String S [] = new String [7];
		 int n = counter ;
		 int k  = 0 ;
		 int next = 0 ;
		 
		if (counter <=5)
		{
		   	while (n<=5)
		   	{
		   	  	S[next++] = n+".txt" ;
		   	  	n ++ ; 
		   	}
		   	if(counter > 1){
		   	   n = counter-1 ;
		   	
		   	while(k < n)
		   	{
		   		k++;
		   		S[next++] = k+".txt" ;
		   		//n--;
		   		
		   	}
		   	//k = 0 ;
		   	}
		   	S [next++] =  (unique++)+"";
		   	S[next++] =(unique ++)+"" ;
		 return S ;	
		}
		else
		{
		
		return null;
		}
		
	}
	/*--------------------------- Code for providing 2 distinct set  End--------------------------------------- */
	
	/*--------------------------- main function--------------------------------------- */
	public static void main(String[] args) throws IOException {
		/* Exterior hua code */
		InputStream modelIn = null;
		POSModel model = null ;

		try {
		  modelIn = new FileInputStream("en-pos-maxent.bin");
		   model = new POSModel(modelIn);
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
		  e.printStackTrace();
		}
		
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		POSTaggerME tagger = new POSTaggerME(model);
		//String sent[] = new String[]{"Most", "large", "cities", "in", "the", "US", "had",
          //      "morning", "and", "afternoon", "newspapers", "."};		  

        /* Exterior code ends*/
		
	    String [] S = new String [7]; // string S[5] S[6] is the name of output file
	    int counter = 0;
		splitfiles();
		while((S = setOfFiles(++counter)) != null )
		{
		/*--------------------------------train data start--------------------------------------*/	  
		new UserFileOpration_Pos().readFile(S[0],false,null,tagger);
		new UserFileOpration_Pos().readFile(S[1],false,null,tagger);
		new UserFileOpration_Pos().readFile(S[2],false,null,tagger);
		new UserFileOpration_Pos().readFile(S[3],false,null,tagger);
		
		
		Instances data_train = new UserFileOpration_Pos().createinstance();
		
		new UserFileOpration_Pos().createDFhash(S[0],S[1],S[2],S[3],tagger);
		//new UserFileOperation1().clearHashtable();
		data_train = new UserFileOpration_Pos().readFile(S[0],true,data_train,tagger);// read the file and adds instance into instances 
		data_train = new UserFileOpration_Pos().readFile(S[1],true,data_train,tagger);
		data_train = new UserFileOpration_Pos().readFile(S[2],true,data_train,tagger);
		data_train = new UserFileOpration_Pos().readFile(S[3],true,data_train,tagger);
		new CreateARFFFile().saveInstance(data_train,S[5]);
		//new UserFileOperation1().clearHashtable();
		/*----------------------train data end-----------------------------------------------------*/
		/*----------------------test data  start---------------------------------------------------------------------------*/
		//new UserFileOperation1().readFile(S[4],false,null);
		Instances data_test = new UserFileOpration_Pos().createinstance();
		//new UserFileOperation1().clearHashtable();
		
		data_test = new UserFileOpration_Pos().readFile(S[4],true,data_test,tagger);
		
		new CreateARFFFile().saveInstance(data_test,S[6]);
		new UserFileOpration_Pos().clearHashtable();
		/*--------------------------------------test data  end------------------------------------------------------*/
		}
		
		//System.out.println("Vaibhav IS good");

	}
	
	
}