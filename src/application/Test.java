package application;

import java.io.File;
import java.io.IOException;

public class Test {

	
	
	
	public static void main(String[] args) throws IOException {
		// C:\Users\moham\Desktop
		File file1= new File("C:\\Users\\moham\\Desktop\\Subtitles\\dd.srt");
		File ff = new File("C:\\Users\\moham\\Desktop\\newff.srt");
		 Main.convertAnsiToUtf8(file1,ff);
       
 

	}

}
