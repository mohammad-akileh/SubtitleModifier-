package application;
import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserExample {

    public static File chooseFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        // Set the initial directory
        // fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Set the file extension filter
        ExtensionFilter txtFilter = new ExtensionFilter("Text files (*.txt)", "*.txt");
        ExtensionFilter extFilter = new ExtensionFilter("Srt files (*.srt)", "*.srt");
        fileChooser.getExtensionFilters().addAll(extFilter,txtFilter);
        

        // Show the file chooser
        return fileChooser.showOpenDialog(primaryStage);
    }
    
}
//import java.io.*;
//import java.util.Scanner;
//import java.util.regex.*;
//
//
//public class FileChooserExample {
//
//	static void edit ()throws FileNotFoundException{
//		int seconds =0;
//		int milliseconds =0;
//	     File file1 = new File("C:\\subtitles");
//	     File file2 = new File("C:\\NewSubtitels");
//	     file2.mkdir();
//	   File listfiles[]= file1.listFiles();
//		Scanner scan ;
//		  Pattern regex ;
//	      Matcher matcher;
//	      File[] listfiles2 = new File[listfiles.length];
//	      int count=0;
//	   for (File file : listfiles) {
//		 scan = new Scanner(file);
//		 listfiles2[count]= new File("C:\\NewSubtitels\\"+file.getName());
//		 PrintWriter print1 = new PrintWriter(listfiles2[count]);
//		 String line;
//		 while(scan.hasNext())
//		 {
//	      line = scan.nextLine().trim(); 
//	      regex = Pattern.compile("\\\\d{2}:\\\\d{2}:\\\\d{2},\\\\d{3}\\\\s*-->\\\\s*\\\\d{2}:\\\\d{2}:\\\\d{2},\\\\d{3}");
//	      matcher = regex.matcher(line); 
//		 if(matcher.find())	
//		 {
//			 seconds =Integer.parseInt(line.substring(6, 8));
//			 milliseconds=Integer.parseInt(line.substring(9, 12));
//              int fisec = seconds+ Main.seconds;
//              int fimillisec = milliseconds+ Main.milliseconds;
//              if(fimillisec>999)
//              {
//            	  fisec++;
//            	  fimillisec=fimillisec-1000;  
//            	  
//            	  
//              }
//              String fisecn=String.valueOf(fisec);
//              String fimillisecn=String.valueOf(fimillisec);
//              char [] linechar = line.toCharArray();
//              linechar[6]=fisecn.charAt(0);
//              linechar[7]=fisecn.charAt(1);
//              linechar[9]=fisecn.charAt(0);
//              linechar[10]=fisecn.charAt(1);
//              linechar[11]=fisecn.charAt(2);
//              String line2 = new String(linechar);
//			 
//			 print1.println(line2);	 
//			 print1.close(); 
//			 
//			 
//		 }
//		 else 
//		 {
//			 print1.println(line);	 
//			 print1.close();
//			 
//		 }
//			 
//		 }
//		   
//		   
//		   
//		   
//	}
//	   System.out.println(listfiles.length);
//	     
//	     
//
//			
//		
//		
//		
//		
//	}
//	
//	
//	public static void main(String[] args) throws FileNotFoundException {
//}
//
//}/*
// * 
//int secondsdel=Main.seconds;
//int millisecondsdel=Main.milliseconds;
//int fisecdel= (secondsdel*1000)+millisecondsdel;
//seconds=Integer.parseInt(line.substring(6, 8));
//milliseconds=Integer.parseInt(line.substring(9, 12));
//int fisec= (seconds*1000)+milliseconds;
//int endsec=fisec+fisecdel;
// * */
