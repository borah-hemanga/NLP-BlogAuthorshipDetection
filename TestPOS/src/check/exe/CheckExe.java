package check.exe;

import java.io.IOException;

public class CheckExe {

	public static void main(String args[])
	{
			Runtime rt = Runtime.getRuntime() ;
			
			Process p;
			try {
				 p = rt.exec("java - slkdkl");
				 p.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
}
