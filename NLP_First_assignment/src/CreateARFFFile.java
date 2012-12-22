 import weka.core.Attribute;
 import weka.core.FastVector;
 import weka.core.Instance;
 import weka.core.Instances;
 import java.io.*;
 import java.util.*;
 import java.lang.*;
 import weka.core.tokenizers.*;
import java.util.Map.Entry;
import weka.core.converters.ArffSaver;

public class CreateARFFFile  extends UserFileOperation1 {
 
	/* Function to create ARFF file */

  public void saveInstance(Instances data, String name ) throws IOException {
	// TODO Auto-generated method stub
	//Instance dataRel = new Instance("att5", atrr, 0);
	 ArffSaver saver = new ArffSaver();
	 saver.setInstances(data);
	 saver.setFile(new File(name +".arff"));
	 //saver.setDestination(new File("./test.arff"));   // **not** necessary in 3.5.4 and later
	 saver.writeBatch();
	
}
	
		
}
