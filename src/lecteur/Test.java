package lecteur;

import javax.sound.sampled.AudioInputStream;

public class Test {
	
	static int choix =3;
	static int pause=1; //1 lecture, 0 pause
    public static int changement=0;

	public static void main(String [ ] arg) throws InterruptedException {
		
		//rajout
		AudioInputStream audioInputStream = null;
		byte bytes[]=null;
		int bytesRead=0;

		
		int ChangementDeMusique=-1;
		int choix2=3;
		int pause2=1;
		String liste="-1";
		String liste2="-1";
		Connection dweet=new Connection();
		Audio musiqueajouer= new Audio();
		Thread musiqueajouerThread=new Thread(musiqueajouer);
		
		while (true){
			dweet.run();
			liste2=null;
			liste2=dweet.GetListe();
			String parts2[]=liste2.split(",");
			if(parts2.length<7){
				System.out.println("Erreur de Dweet");
			}
			else{
				if(parts2[5].contains("musique")){
					if(parts2[6].contains("mae")){
						choix2=1; /* on joue du christophe Mae*/
					}
					if(parts2[6].contains("bakermat")){
						choix2=0; /*bakermat */
					}
					if(parts2[6].contains("berceuse")){
						choix2=2; /* on joue de la berceuse*/
	
					}
					if(parts2[6].contains("pasdemusique")){
						
					}
					if(parts2[7].contains("play")){
						pause2=1; /* on met play*/
					}
					if(parts2[7].contains("pause")){
						pause2=0; /* on met pause */
					}
					
				}
				if(choix!=choix2){
					ChangementDeMusique=-1;
				}
				if(ChangementDeMusique==-1 && parts2[7].contains("play") ||ChangementDeMusique==0 && parts2[7].contains("pause") || ChangementDeMusique==0 && parts2[7].contains("play") ){
					if (ChangementDeMusique==-1){
					
					liste=liste2;
						String parts[]=liste.split(",");
						if(parts[5].contains("musique")){
							if(parts[6].contains("mae")){
								choix=1; /* on joue du Christophe Mae*/
							}
							if(parts[6].contains("bakermat")){
								choix=0; /*bakermat */
							}
							if(parts[6].contains("berceuse")){
								choix=2; /* on joue de la berceuse*/
							}
							int PasCommencerAvecPause;	
						if(musiqueajouerThread.isAlive() || musiqueajouer.isAlive()){
							musiqueajouer.interrupt();
							musiqueajouerThread=null;
							musiqueajouerThread=new Thread(musiqueajouer);
							musiqueajouerThread.start();
							ChangementDeMusique=0;
							
						}
						
						
						
						else{
							if (choix==3){
								/*on a pas lancé de musique*/
							}
							else{
								musiqueajouerThread.start();
								ChangementDeMusique=0;	
							}
					
						}
						
						}
					}
					if(pause!=pause2){
						 
						pause=pause2;
						if(pause==0){
							audioInputStream = musiqueajouer.audioInputStream;
							bytes=musiqueajouer.bytes;
							bytesRead=musiqueajouer.bytesRead;
							//musiqueajouer.interrupt();
							musiqueajouer.wait1();
							//musiqueajouerThread=null;
							changement=1;
						}
						
						if(pause==1){
							changement=0;
							musiqueajouerThread=new Thread(musiqueajouer);
							musiqueajouerThread.start();
							musiqueajouer.audioInputStream=audioInputStream ;
							musiqueajouer.bytes=bytes;
							musiqueajouer.bytesRead=bytesRead;
						//	musiqueajouer.run();
							
	
						}
						
					}
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
 }
}

