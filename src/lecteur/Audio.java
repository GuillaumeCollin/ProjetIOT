package lecteur;

import  java.io.*;

import javax.sound.sampled.*;
 
 
public class Audio extends Thread{
	 
    File fichier;
    public AudioInputStream audioInputStream = null;
    public byte bytes[];
    public int bytesRead=0;
    SourceDataLine line=null;
  
    public boolean t=true;    
    public void run(){
    	
    	int choix=Test.choix;
    	
    	if(Test.changement==1 && line.isOpen()){
    		line.start();
    	}
    	else{
	    	
	    	if (choix==0){
	    		this.fichier = new File("0.wav");/* On joue du bakermat */
	    	}
	    	else if (choix==1){
	    		this.fichier = new File("1.wav");/* On joue du Christophe mae */
	    	}
	    	else if (choix==2){
	    		this.fichier = new File("2.wav");/* On joue de la berceuse */
	    	}
	    	else{
	    		this.fichier = new File("error.wav");/* On joue du finkk  */
	    	}
	        try {
	        @SuppressWarnings("unused")
	        AudioFileFormat format = AudioSystem.getAudioFileFormat(this.fichier);
	        } catch (UnsupportedAudioFileException e1) {
	            e1.printStackTrace();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	         
	        try {
	            audioInputStream = AudioSystem.getAudioInputStream(this.fichier);
	        } catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	         AudioFormat audioFormat = audioInputStream.getFormat();
	         DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
	          
	         try {
	             line = (SourceDataLine) AudioSystem.getLine(info);
	                        
	             } catch (LineUnavailableException e) {
	               e.printStackTrace();
	               return;
	             }
	          
	        try {
	                line.open(audioFormat);
	        } catch (LineUnavailableException e) {
	                    e.printStackTrace();
	                    return;
	        }
	        line.start();
	        
    	}
	       try {
	    	  
	    	    bytes = new byte[1024]; //rajout
		        bytesRead=0;//rajout
	    	   
	            while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1 ) {
	                 line.write(bytes, 0, bytesRead);
	                 if(Test.changement==1){
	                	 line.stop();
	                 }
	                }
	            t=true;
	        } catch (IOException io) {
	            io.printStackTrace();
	            return;
	         
	        }
	    
    }
    
    public void interrupt() {
        super.interrupt();
        try {
        	audioInputStream.close(); // Fermeture du flux si l'interruption n'a pas fonctionné.
        	line.close();
        } catch (IOException e) {}
    }
    
    public void wait1()  {
 
    	line.stop();
    }
    
    
   // public void restart(){
    //		line.start();	
   //}
}
