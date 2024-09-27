package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	 private TextField hoursField;
	    private TextField minutesField;
	    private TextField secondsField;
	    private TextField millisecondsField;
	    private static File file ;
	    private static int hours;
	    private static int minutes;
	    private static int seconds;
	    private static int milliSeconds;
	    private static boolean time;
	    private static File dir;

   public void start(Stage primaryStage) {
        hoursField = new TextField();
        int x= 0;
        hoursField.setText(x+"00");
        hoursField.setPrefWidth(30);
        hoursField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                hoursField.setText(oldValue);
            } else if (!newValue.isEmpty() && Integer.parseInt(newValue) > 99) {
                hoursField.setText("99");
            }
        });

        minutesField = new TextField();
        minutesField.setText(x+"00");
        minutesField.setPrefWidth(30);
        minutesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                minutesField.setText(oldValue);
            } else if (!newValue.isEmpty() && Integer.parseInt(newValue) > 60) {
                minutesField.setText("60");
            }
        });

        secondsField = new TextField();
        secondsField.setText(x+"00");
        secondsField.setPrefWidth(30);
        secondsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                secondsField.setText(oldValue);
            } else if (!newValue.isEmpty() && Integer.parseInt(newValue) > 59) {
                secondsField.setText("59");
            }
        });

        millisecondsField = new TextField();
        millisecondsField.setText(x+"00");
        millisecondsField.setPrefWidth(40);
        millisecondsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                millisecondsField.setText(oldValue);
            } else if (!newValue.isEmpty() && Integer.parseInt(newValue) > 999) {
                millisecondsField.setText("999");
            }
        });

        Label colonLabel1 = new Label(":");
        Label colonLabel2 = new Label(":");
        Label colonLabel3 = new Label(":");

        HBox timeField = new HBox(5);
        timeField.getChildren().addAll(hoursField, colonLabel1, minutesField, colonLabel2, secondsField, colonLabel3, millisecondsField);
        timeField.setAlignment(Pos.CENTER);

        Label label = new Label("Time (h:m:s:ms):");
        label.setPadding(new Insets(5));

        // Adding radio buttons with full names
        RadioButton option1 = new RadioButton("Translate Delayed");
       
        RadioButton option2 = new RadioButton("Translate Hastened");
        ToggleGroup group = new ToggleGroup();
        option1.setToggleGroup(group);
        option2.setToggleGroup(group);

        HBox radioButtons = new HBox(10);
        radioButtons.getChildren().addAll(option1, option2);
        radioButtons.setAlignment(Pos.CENTER);

        // Adding two buttons with labels
        Label buttonLabel1 = new Label("Choose the subtitle file:");
        Button button1 = new Button("Choose");
        button1.setOnAction(event -> {
        	
            // Handle Button 1 action here
        	FileChooserExample choose1 = new FileChooserExample();
        	File choose1file = choose1.chooseFile(primaryStage);
            System.out.println(choose1file.getAbsolutePath());
            file = choose1file;
        });

        Label buttonLabel2 = new Label("Choose where to save the subtitle");
        Button button2 = new Button("Choose");
        button2.setOnAction(event -> {
            // Handle Button 2 action here
        	Dir choose1 = new Dir();
        	File choose1file = choose1.chooseDirectory(primaryStage);
        	dir = choose1file;
        	System.out.println(dir.getAbsolutePath());
        	
      
            
        });

        HBox buttonBox1 = new HBox(10);
        buttonBox1.getChildren().addAll(buttonLabel1, button1);
        buttonBox1.setAlignment(Pos.CENTER);

        HBox buttonBox2 = new HBox(10);
        buttonBox2.getChildren().addAll(buttonLabel2, button2);
        buttonBox2.setAlignment(Pos.CENTER);

        Button sendButton = new Button("Send Results");
        sendButton.setTranslateX(130);
        sendButton.setOnAction(event -> {
            try {
            	
                hours = Integer.parseInt(hoursField.getText());
                minutes = Integer.parseInt(minutesField.getText());
                seconds = Integer.parseInt(secondsField.getText());
                milliSeconds = Integer.parseInt(millisecondsField.getText());
                if (group.getSelectedToggle() == option1) {
                    time = true;
                } else if (group.getSelectedToggle() == option2) {
                    time = false;
                }
                System.out.println("Time: " + hours + ":" + minutes + ":" + seconds + ":" + milliSeconds);
                System.out.println("Hours: " + hours);
                System.out.println("Minutes: " + minutes);
                System.out.println("Seconds: " + seconds);
                System.out.println("Milliseconds: " + milliSeconds);
                System.out.println("Time boolean: " + time);
                my();
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers in all fields.");
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(label, timeField, radioButtons, buttonBox1, buttonBox2, sendButton);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 300); // Adjusted the scene size
        primaryStage.setTitle("Time Input Field");
        primaryStage.setScene(scene);
        primaryStage.show();
    } 
   public static void convertAnsiToUtf8(File inputFile, File outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "windows-1252"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
               String line;
               while ((line = reader.readLine()) != null) {
                   writer.write(line);
                   writer.newLine();
               }
           }
       }
  static  void my() throws IOException{
       int myHours2=0,myMinutes2=0;
		int mySeconds =0;
		int myMilliSeconds =0;
		int myHours =0;
		int myMinutes =0;
		int mySeconds2 =0;
		int myMilliSeconds2 =0;
		     Path source = Paths.get(file.getAbsolutePath());
		     Path target = Paths.get(dir.getAbsolutePath()+"\\"+file.getName());
		     
		     try {
		         // Copy the file
		         Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		         System.out.println("File copied successfully!");
		     } catch (IOException e) {
		         System.out.println("File copy failed.");
		         e.printStackTrace();
		     }
	
 
		Scanner scan ;
		  Pattern regex ;
	      Matcher matcher;
	      PrintWriter print ;
      File newfile = new File(dir+"\\"+file.getName());
      convertAnsiToUtf8(file,newfile);
		 scan = new Scanner(newfile);
		 print = new PrintWriter(newfile);
		 String line;
		 while(scan.hasNext())
		 {
	      line = scan.nextLine().trim(); 
	      regex = Pattern.compile("\\d{2}:\\d{2}:\\d{2},\\d{3}\\s*-->\\s*\\d{2}:\\d{2}:\\d{2},\\d{3}");
	      matcher = regex.matcher(line); 

	    	  
		 if(matcher.find())	
		 {

		      String line2=null;
		     myHours =Integer.parseInt(line.substring(0, 2));
		     myMinutes=Integer.parseInt(line.substring(3, 5));
			 mySeconds =Integer.parseInt(line.substring(6, 8));
			 myMilliSeconds=Integer.parseInt(line.substring(9, 12));
			 myHours2 =Integer.parseInt(line.substring(17, 19));
			 myMinutes2=Integer.parseInt(line.substring(20, 22));
             mySeconds2 =Integer.parseInt(line.substring(23, 25));
			 myMilliSeconds2=Integer.parseInt(line.substring(26, 29));
                                    // delay on Subtitle 
			 
			                     // if start
		      if(time==false) {
	      /*******************************#Left Side Start#******************************************/

             int fiHours = myHours+Main.hours;
             int fiMinutes = myMinutes+Main.minutes;
             //modyfiying Minutes
             if(fiMinutes>59)
             {
            	 fiHours++;
            	 fiMinutes-=60;
            	 
             }
             //modyfiying Seconds
             int fiSeconds = mySeconds+Main.seconds;
             if(fiSeconds>59)
             {
            	 fiMinutes++;
            	 fiSeconds-=60;
            	 
             }
             
           //modyfiying milliSeconds

             int fiMilliSeconds =  myMilliSeconds+Main.milliSeconds;
             if(fiMilliSeconds>999)
             {
            	 fiSeconds++;
            	 fiMilliSeconds-=1000;
            	 
             }
	                    // Hours Check         
                         String fiHou=String.valueOf(fiHours);
                      if(fiHou.length()==1)
                  {
                     	 fiHou = "0".concat(fiHou); 
              }
                           // Minutes Check  
                          String fiMinu=String.valueOf(fiMinutes);
                          if(fiMinu.length()==1)
             {
                      	 fiMinu = "0".concat(fiMinu); 
             }
                          // Seconds Check  
		              String fiSecon=String.valueOf(fiSeconds);
		              if(fiSecon.length()==1)
		              {
		            	  fiSecon = "0".concat(fiSecon); 
		              }
		              // milliSeconds Check  
		              String fiMilliSecn=String.valueOf(fiMilliSeconds);
		              if(fiMilliSecn.length()==1)
		              {
		            	  fiMilliSecn= "00".concat(fiMilliSecn); 
		              }
		              else if(fiMilliSecn.length()==2)
		              {
		            	  fiMilliSecn = "0".concat(fiMilliSecn);   
		            	  
		              }
		       /*******************************#Left Side End#******************************************/
		                                       /*******************/
              /*******************************#Right Side Start#******************************************/
		              int fiHours2 = myHours2+Main.hours;
		              int fiMinutes2 = myMinutes2+Main.minutes;
		              //modyfiying Minutes
		              if(fiMinutes2>59)
		              {
		             	 fiHours2++;
		             	 fiMinutes2-=60;
		             	 
		              }
		              //modyfiying Seconds
		              int fiSeconds2 = mySeconds2+Main.seconds;
		              if(fiSeconds2>59)
		              {
		             	 fiMinutes2++;
		             	 fiSeconds2-=60;
		             	 
		              }
		              
		            //modyfiying milliSeconds

		              int fiMilliSeconds2 =  myMilliSeconds2+Main.milliSeconds;
		              if(fiMilliSeconds2>999)
		              {
		             	 fiSeconds2++;
		             	 fiMilliSeconds2-=1000;
		             	 
		              }
		 	                    // Hours Check         
		                          String fiHou2=String.valueOf(fiHours2);
		                       if(fiHou2.length()==1)
		                   {
		                      	 fiHou2 = "0".concat(fiHou2); 
		               }
		                            // Minutes Check  
		                           String fiMinu2=String.valueOf(fiMinutes2);
		                           if(fiMinu2.length()==1)
		              {
		                       	 fiMinu2 = "0".concat(fiMinu2); 
		              }
		                           // Seconds Check  
		 		              String fiSecon2=String.valueOf(fiSeconds2);
		 		              if(fiSecon2.length()==1)
		 		              {
		 		            	  fiSecon2 = "0".concat(fiSecon2); 
		 		              }
		 		              // milliSeconds Check  
		 		              String fiMilliSecn2=String.valueOf(fiMilliSeconds2);
		 		              if(fiMilliSecn2.length()==1)
		 		              {
		 		            	  fiMilliSecn2= "00".concat(fiMilliSecn2); 
		 		              }
		 		              else if(fiMilliSecn2.length()==2)
		 		              {
		 		            	  fiMilliSecn2 = "0".concat(fiMilliSecn2);   
		 		            	  
		 		              }

         /*************************************************#Righ End#***********************************************************/
		              char [] linechar = line.toCharArray();
		              //Left Time
		                     //Hours convert to int
		              linechar[0]=fiHou.charAt(0);
		              linechar[1]=fiHou.charAt(1);
		                    //Minutes convert to int 
		              linechar[3]=fiMinu.charAt(0);
		              linechar[4]=fiMinu.charAt(1);
		                    //Seconds convert to int 
		              linechar[6]=fiSecon.charAt(0);
		              linechar[7]=fiSecon.charAt(1);
		                    //milliSeconds convert to int 
		              linechar[9]=fiMilliSecn.charAt(0);
		              linechar[10]=fiMilliSecn.charAt(1);
		              linechar[11]=fiMilliSecn.charAt(2);
		              
		              //Right Time
		              linechar[17]=fiHou2.charAt(0);
		              linechar[18]=fiHou2.charAt(1);
		                    //Minutes convert to int 
		              linechar[20]=fiMinu2.charAt(0);
		              linechar[21]=fiMinu2.charAt(1);
		                    //Seconds convert to int 
		              linechar[23]=fiSecon2.charAt(0);
		              linechar[24]=fiSecon2.charAt(1);
		                    //milliSeconds convert to int 
		              linechar[26]=fiMilliSecn2.charAt(0);
		              linechar[27]=fiMilliSecn2.charAt(1);
		              linechar[28]=fiMilliSecn2.charAt(2);
		             
		               line2 = new String(linechar);

	              
		    	  
		      }
		      
              // if end

		    //else start
		      else
		      { 
			      /*******************************#Left Side Start#******************************************/

		             int fiHours = myHours-Main.hours;
		             int fiMinutes = myMinutes-Main.minutes;
		             //modyfiying Minutes
		             if(fiMinutes<0)
		             {
		            	 fiHours--;
		            	 fiMinutes=60-(Main.minutes-myMinutes);
		            	 
		             }
		             //modyfiying Seconds
		             int fiSeconds = mySeconds-Main.seconds;
		             if(fiSeconds<0)
		             {
		            	 fiMinutes--;
		            	 fiSeconds=60-(Main.seconds-mySeconds);
		            	 
		             }
		             
		           //modyfiying milliSeconds

		             int fiMilliSeconds =  myMilliSeconds-Main.milliSeconds;
		             if(fiMilliSeconds<0)
		             {
		            	 fiSeconds--;
		            	 fiMilliSeconds=1000-(Main.milliSeconds-myMilliSeconds);
		            	 
		             }
			                    // Hours Check         
		                         String fiHou=String.valueOf(fiHours);
		                      if(fiHou.length()==1)
		                  {
		                     	 fiHou = "0".concat(fiHou); 
		              }
		                           // Minutes Check  
		                          String fiMinu=String.valueOf(fiMinutes);
		                          if(fiMinu.length()==1)
		             {
		                      	 fiMinu = "0".concat(fiMinu); 
		             }
		                          // Seconds Check  
				              String fiSecon=String.valueOf(fiSeconds);
				              if(fiSecon.length()==1)
				              {
				            	  fiSecon = "0".concat(fiSecon); 
				              }
				              // milliSeconds Check  
				              String fiMilliSecn=String.valueOf(fiMilliSeconds);
				              if(fiMilliSecn.length()==1)
				              {
				            	  fiMilliSecn= "00".concat(fiMilliSecn); 
				              }
				              else if(fiMilliSecn.length()==2)
				              {
				            	  fiMilliSecn = "0".concat(fiMilliSecn);   
				            	  
				              }
				       /*******************************#Left Side End#******************************************/
				                                       /*******************/
		              /*******************************#Right Side Start#******************************************/

				              //modyfiying Minutes
				              int fiHours2 = myHours-Main.hours;
					             int fiMinutes2 = myMinutes-Main.minutes;
					             //modyfiying Minutes
					             if(fiMinutes2<0)
					             {
					            	 fiHours2--;
					            	 fiMinutes2=60-(Main.minutes-myMinutes2);
					            	 
					             }
					             //modyfiying Seconds
					             int fiSeconds2 = mySeconds-Main.seconds;
					             if(fiSeconds2<0)
					             {
					            	 fiMinutes2--;
					            	 fiSeconds2=60-(Main.seconds-mySeconds2);
					            	 
					             }
					             
					           //modyfiying milliSeconds

					             int fiMilliSeconds2 =  myMilliSeconds2-Main.milliSeconds;
					             if(fiMilliSeconds2<0)
					             {
					            	 fiSeconds2--;
					            	 fiMilliSeconds2=1000-(Main.milliSeconds-myMilliSeconds2);
					            	 
					             }
						                    // Hours Check         
					                         String fiHou2=String.valueOf(fiHours2);
					                      if(fiHou2.length()==1)
					                  {
					                     	 fiHou2 = "0".concat(fiHou2); 
					              }
					                           // Minutes Check  
					                          String fiMinu2=String.valueOf(fiMinutes2);
					                          if(fiMinu2.length()==1)
					             {
					                      	 fiMinu2 = "0".concat(fiMinu2); 
					             }
					                          // Seconds Check  
							              String fiSecon2 =String.valueOf(fiSeconds2);
							              if(fiSecon2.length()==1)
							              {
							            	  fiSecon2 = "0".concat(fiSecon2); 
							              }
							              // milliSeconds Check  
							              String fiMilliSecn2=String.valueOf(fiMilliSeconds2);
							              if(fiMilliSecn2.length()==1)
							              {
							            	  fiMilliSecn2= "00".concat(fiMilliSecn2); 
							              }
							              else if(fiMilliSecn2.length()==2)
							              {
							            	  fiMilliSecn2 = "0".concat(fiMilliSecn2);   
							            	  
							              }
		         /*************************************************#Righ End#***********************************************************/
				              char [] linechar = line.toCharArray();
				              //Left Time
				                     //Hours convert to int
				              linechar[0]=fiHou.charAt(0);
				              linechar[1]=fiHou.charAt(1);
				                    //Minutes convert to int 
				              linechar[3]=fiMinu.charAt(0);
				              linechar[4]=fiMinu.charAt(1);
				                    //Seconds convert to int 
				              linechar[6]=fiSecon.charAt(0);
				              linechar[7]=fiSecon.charAt(1);
				                    //milliSeconds convert to int 
				              linechar[9]=fiMilliSecn.charAt(0);
				              linechar[10]=fiMilliSecn.charAt(1);
				              linechar[11]=fiMilliSecn.charAt(2);
				              
				              //Right Time
				              linechar[17]=fiHou2.charAt(0);
				              linechar[18]=fiHou2.charAt(1);
				                    //Minutes convert to int 
				              linechar[20]=fiMinu2.charAt(0);
				              linechar[21]=fiMinu2.charAt(1);
				                    //Seconds convert to int 
				              linechar[23]=fiSecon2.charAt(0);
				              linechar[24]=fiSecon2.charAt(1);
				                    //milliSeconds convert to int 
				              linechar[26]=fiMilliSecn2.charAt(0);
				              linechar[27]=fiMilliSecn2.charAt(1);
				              linechar[28]=fiMilliSecn2.charAt(2);
				             
				               line2 = new String(linechar);

			              
		    	  
		    	  
		    	  
		    	  
		    	  
		      }
          
              print.println(line2);
			
			 
			 
		 }
		 else 
		 {
			 print.println(line);	 
		
			 
		 }
			 
		 }
		   
		 scan.close();

		 print.close();    

	     
	    
    	
    	
    	
    	
    	
    }

    public static void main(String[] args) {
        launch(args);//
 
    }
}