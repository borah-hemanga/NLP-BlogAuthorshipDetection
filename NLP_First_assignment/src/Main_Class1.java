import java.io.*;

import weka.core.Instances;

public class Main_Class1 {
static int unique = 0 ;

/*--------------------------- Code for reading source file in sequence so as to write on 5 files--------------------------------------- */
public static String[] splitFile(BufferedReader br) throws IOException
	
	{
		String S[]= new String[10];
		int counter = 0;
     	while (counter < 10 ) {
    		S[counter] = br.readLine();
    		//System.out.println(S[counter])
              counter++;
    		//[counter] = br_deceptive.readLine();
    		 //out1.write(s_truth);
     	}		
		return S;
		
	
	}
/*--------------------------- Code for writing on 5 files from 2 source file--------------------------------------- */
public static void  writeOnFile(BufferedWriter out,BufferedReader br1,BufferedReader br2,BufferedReader br3,BufferedReader br4,BufferedReader br5) throws IOException
  {
	
	  String S_author1[] =     splitFile(br1);
	  String S_author2[] =     splitFile(br2);
	  String S_author3[] =     splitFile(br3);
	  String S_author4[] =     splitFile(br4);
	  String S_author5[] =     splitFile(br5);
	  
	  //String S_deceptive[] = splitFile(br2);
	  int i = 0 ;
	  /* Write 4 string True*/
	  while(i<10)
	  {
		  /*for truth string start */
		 // S_author1[i] = S_truth[i].replace('.', ' ');
		 // S_author1[i] = S_truth[i].replace(',', ' ');
	 // S_truth[i] = S_truth[i].replace('!', ' ');
	  //S_truth[i] = S_truth[i].replace(':', ' ');
		  S_author1[i] = S_author1[i].replace('(', ' ');
		  S_author1[i] = S_author1[i].replace(')', ' ');
		  S_author1[i] = S_author1[i].replace('"', ' ');
		  S_author1[i] = S_author1[i].replaceAll("PM EST", "");
		  S_author1[i] = S_author1[i].replaceAll("AM EST", "");
		  S_author1[i] = S_author1[i].replace('/', ' ');
	  //S_truth[i] = S_truth[i].replaceAll("'", " ");
	  
	  /*for truth string end */
	  /*for deceptive string start */
		 // S_author2[i] = S_deceptive[i].replace('.', ' ');
		 // S_author2[i] = S_deceptive[i].replace(',', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace('!', ' ');
	  //S_deceptive[i] = S_deceptive[i].replace(':', ' ');
		  S_author2[i] = S_author2[i].replace('(', ' ');
		  S_author2[i] = S_author2[i].replace(')', ' ');
		  S_author2[i] = S_author2[i].replace('"', ' ');
		  S_author2[i] = S_author2[i].replaceAll("PM EST", "");
		  S_author2[i] = S_author2[i].replaceAll("AM EST", "");
		  S_author2[i] = S_author2[i].replace('/', ' ');
	 // S_deceptive[i] = S_deceptive[i].replaceAll("'", " ");
	  /*for deceptive string end */
		  S_author3[i] = S_author3[i].replace('(', ' ');
		  S_author3[i] = S_author3[i].replace(')', ' ');
		  S_author3[i] = S_author3[i].replace('"', ' ');
		  S_author3[i] = S_author3[i].replaceAll("PM EST", "");
		  S_author3[i] = S_author3[i].replaceAll("AM EST", "");
		  S_author3[i] = S_author3[i].replace('/', ' ');
		  
		  S_author4[i] = S_author4[i].replace('(', ' ');
		  S_author4[i] = S_author4[i].replace(')', ' ');
		  S_author4[i] = S_author4[i].replace('"', ' ');
		  S_author4[i] = S_author4[i].replaceAll("PM EST", "");
		  S_author4[i] = S_author4[i].replaceAll("AM EST", "");
		  S_author4[i] = S_author4[i].replace('/', ' ');
		  
		  S_author5[i] = S_author5[i].replace('(', ' ');
		  S_author5[i] = S_author5[i].replace(')', ' ');
		  S_author5[i] = S_author5[i].replace('"', ' ');
		  S_author5[i] = S_author5[i].replaceAll("PM EST", "");
		  S_author5[i] = S_author5[i].replaceAll("AM EST", "");
		  S_author5[i] = S_author5[i].replace('/', ' ');
		 
	  i++;
	  }
	  i=0;
	  while(i<10)
	  {
	  out.write(S_author1[i]);
	  out.newLine();
	  i++;
	  }
	  i=0;
	  while(i<10)
	  {
	  out.write(S_author2[i]);
	  out.newLine();
	  i++;
	  }
	  
	  
	  i=0;
	  while(i<10)
	  {
	  out.write(S_author3[i]);
	  out.newLine();
	  i++;
	  }
	  i=0;
	  while(i<10)
	  {
	  out.write(S_author4[i]);
	  out.newLine();
	  i++;
	  }
	  i=0;
	  while(i<10)
	  {
	  out.write(S_author5[i]);
	  out.newLine();
	  i++;
	  }	  
	 
	  
   }
/*--------------------------- Code for Spliting 2 files in 5 files--------------------------------------- */
	public static void splitfiles () throws IOException
	{
		 int counter =0 ;
		 
		  FileWriter fstream1 = new FileWriter("MainFolder/1.txt");
		  BufferedWriter out1 = new BufferedWriter(fstream1);
		  FileWriter fstream2 = new FileWriter("MainFolder/2.txt");
		  BufferedWriter out2 = new BufferedWriter(fstream2);
		  FileWriter fstream3 = new FileWriter("MainFolder/3.txt");
		  BufferedWriter out3 = new BufferedWriter(fstream3);
		  FileWriter fstream4 = new FileWriter("MainFolder/4.txt");
		  BufferedWriter out4 = new BufferedWriter(fstream4);
		  FileWriter fstream5 = new FileWriter("MainFolder/5.txt");
		  BufferedWriter out5 = new BufferedWriter(fstream5);
		  
		  
		  
		  File dirName = new File("MainFolder");
		  BufferedReader br_charlie;
		  br_charlie = new BufferedReader(new FileReader(new File(dirName,
				  "charlie")));
		  BufferedReader br_joe;
		  br_joe = new BufferedReader(new FileReader(new File(dirName,
				  "joe")));
		  BufferedReader br_josh;
		  BufferedReader br_kari;
		  BufferedReader br_mackenzie;
		  br_josh = new BufferedReader(new FileReader(new File(dirName,
		  "josh")));
		  br_kari = new BufferedReader(new FileReader(new File(dirName,
		  "kari")));
		  br_mackenzie = new BufferedReader(new FileReader(new File(dirName,
		  "mackenzie")));
		  
		  
		  //while(counter<20)
		  //{
		  writeOnFile(out1,br_charlie,br_joe,br_josh,br_kari,br_mackenzie);
		  writeOnFile(out2,br_charlie,br_joe,br_josh,br_kari,br_mackenzie);
		  writeOnFile(out3,br_charlie,br_joe,br_josh,br_kari,br_mackenzie);
		  writeOnFile(out4,br_charlie,br_joe,br_josh,br_kari,br_mackenzie);
		  writeOnFile(out5,br_charlie,br_joe,br_josh,br_kari,br_mackenzie);
		  counter++;
		  //}
		 		 		  
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
		
	    String [] S = new String [7]; // string S[5] S[6] is the name of output file
	    int counter = 0;
		splitfiles();
		while((S = setOfFiles(++counter)) != null )
		{
			 //S = setOfFiles(++counter);
		//{
		/*--------------------------------train data start--------------------------------------*/	  
		new UserFileOperation1().readFile(S[0],false,null);
		new UserFileOperation1().readFile(S[1],false,null);
		new UserFileOperation1().readFile(S[2],false,null);
		new UserFileOperation1().readFile(S[3],false,null);
		
		
		Instances data_train = new UserFileOperation1().createinstance();
		
		new UserFileOperation1().createDFhash(S[0],S[1],S[2],S[3]);
		//new UserFileOperation1().clearHashtable();
		data_train = new UserFileOperation1().readFile(S[0],true,data_train);// read the file and adds instance into instances 
		data_train = new UserFileOperation1().readFile(S[1],true,data_train);
		data_train = new UserFileOperation1().readFile(S[2],true,data_train);
		data_train = new UserFileOperation1().readFile(S[3],true,data_train);
		new CreateARFFFile().saveInstance(data_train,S[5]);
		//new UserFileOperation1().clearHashtable();
		/*----------------------train data end-----------------------------------------------------*/
		/*----------------------test data  start---------------------------------------------------------------------------*/
		//new UserFileOperation1().readFile(S[4],false,null);
		Instances data_test = new UserFileOperation1().createinstance();
		//new UserFileOperation1().clearHashtable();
	//	S[4] ="vaibhav.txt";
		data_test = new UserFileOperation1().readFile(S[4],true,data_test);
		
		new CreateARFFFile().saveInstance(data_test,S[6]);
		new UserFileOperation1().clearHashtable();
		/*--------------------------------------test data  end------------------------------------------------------*/
		}
		
		//System.out.println("Vaibhav IS good");

	}
	
	
}