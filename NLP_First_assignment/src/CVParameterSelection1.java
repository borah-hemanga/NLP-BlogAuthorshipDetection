
import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.*;
import weka.classifiers.trees.*;

import java.io.*;

public class CVParameterSelection1 {
	 public static void CVSelect(String S , String parameter) throws Exception
	   {
			 File dirName = new File(S);
			 
			  BufferedReader br = new BufferedReader(new FileReader(new File(dirName,0+".arff")));
			  
		      //BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		      Instances data = new Instances(br);
		      br.close();
		      data.setClassIndex(data.numAttributes() - 1);

		      // setup classifier
		      CVParameterSelection ps = new CVParameterSelection();
		      ps.setClassifier(new SMO());
		      ps.setNumFolds(5);  // using 5-fold CV
		      ps.addCVParameter(parameter);

		      // build and output best options
		      ps.buildClassifier(data);
		      System.out.println(Utils.joinOptions(ps.getBestClassifierOptions()));
		   
			 
	   }
	 
   public static void main(String[] args) throws Exception {
      // load data
	 CVSelect("E:\\Java_NLP\\NLP_First_assignment\\UnigramFold1","C 0.01 0.05 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\UnigramFold2","C 0.01 0.05 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\UnigramFold3","C 0.06 0.07 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\UnigramFold4","C 0.01 0.05 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\UnigramFold5","C 0.06 0.07 8");
	   
	   /*CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Bigram+fold1","C 0.05 0.15 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Bigram+fold2","C 0.05 0.15 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Bigram+fold3","C 0.05 0.15 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Bigram+fold4","C 0.05 0.15 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Bigram+fold5","C 0.05 0.15 8");
	   */
	  /* CVSelect("E:\\Java_NLP\\NLP_First_assignment\\TRIGRAM+fold1","C 0.1 0.5 2");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\TRIGRAM+fold2","C 0.1 0.5 2");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\TRIGRAM+fold3","C 0.1 0.5 2");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\TRIGRAM+fold4","C 0.1 0.5 2");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\TRIGRAM+fold5","C 0.1 0.5 2");
	   */
	   
	  /* CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Pos_Second_fold1","C 0.38 0.43 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Pos_Second_fold2","C 0.15 0.3 5");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Pos_Second_fold3","C 0.25 0.28 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Pos_Second_fold4","C 0.38 0.42 8");
	   CVSelect("E:\\Java_NLP\\NLP_First_assignment\\Pos_Second_fold5","C 0.31 0.33 8");
	   */
	   
   }
  
}
