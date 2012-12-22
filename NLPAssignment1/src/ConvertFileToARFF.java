import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class ConvertFileToARFF {

  public Instances createDataset(String filePath) throws Exception {

    FastVector atts = new FastVector(2);
    atts.addElement(new Attribute("filename", (FastVector) null));
    atts.addElement(new Attribute("contents", (FastVector) null));
    Instances data = new Instances("text_files_in : " + filePath+" ", atts, 0);

	try {
	  InputStreamReader is;
	  is = new InputStreamReader(new FileInputStream(filePath));
	  BufferedReader b = new BufferedReader (is);
	  StringBuffer txtStr = new StringBuffer();
	  String c;
	  while ((c = b.readLine()) != null) {
	    txtStr.append(c);
	    double[] newInst = new double[2];
		newInst[0] = (double)data.attribute(0).addStringValue(filePath);
	    newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
		data.add(new Instance(1.0, newInst));
	    txtStr = new StringBuffer();
	  }
	} catch (Exception e) {
	  System.err.println("failed to convert file: " + filePath);
	}

    return data;
  }
  
  public static void main(String[] args) {
	String directoryPath = "C:/testData";
    File dir = new File(directoryPath);
    String[] files = dir.list();
    
	ConvertFileToARFF[] tdta = new ConvertFileToARFF[files.length];
    
	for (int i = 0; i < files.length; i++)
	{
	try {
			File filePath = new File(directoryPath + File.separator + files[i]);
			tdta[0] = new ConvertFileToARFF();
			Instances dataset = tdta[0].createDataset(filePath.toString());
			System.out.println(dataset);
     } catch (Exception e) {
    	  System.err.println(e.getMessage());
    	  e.printStackTrace();
      }
   }
}
}