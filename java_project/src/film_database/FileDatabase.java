package film_database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDatabase {
	

	public FileDatabase() {}
	
	
	/*
	 * public void saveDatabase(String name) throws IOException { File soubor = new
	 * File(name+".txt"); FileWriter fw = null; BufferedWriter out = null; try { fw
	 * = new FileWriter(soubor); out = new BufferedWriter(fw); for (int
	 * i=0;i<prvkyDatabaze.length;i++) { try {
	 * 
	 * 
	 * out.write(new String("Jmeno je: " + prvkyDatabaze[i].getJmeno() +
	 * ", rok narozeni: "+ prvkyDatabaze[i].getRocnik() + ", studijni prumer: "+
	 * prvkyDatabaze[i].getStudijniPrumer())); out.newLine(); }
	 * 
	 * catch (Exception e) { out.write(new String("Jmeno je: " +
	 * prvkyDatabaze[i].getJmeno() + ", rok narozeni: "+
	 * prvkyDatabaze[i].getRocnik() + ", studijni prumer: nezadan")); out.newLine();
	 * 
	 * } } } catch (IOException e) { System.out.println("Soubor  nelze otevřít");
	 * } finally { System.out.println("Ulozeno do: "+ System.getProperty("user.dir")
	 * + File.separator + name+".txt" ); System.out.println(); out.close();
	 * fw.close(); // nutno doimplementovat null check atd. }
	 * 
	 * }
	 * 
	 * public void listDirectory() {
	 * System.out.println("Aktuální adresář: "+System.getProperty("user.dir") +
	 * File.separator); File adresar = new File(System.getProperty("user.dir") +
	 * File.separator); if (adresar.exists()) { //System.out.println("adresář " +
	 * adresar.getName() + " existuje"); File[] soubory = adresar.listFiles(); for
	 * (File s: soubory) { System.out.println("* " + s.getName()); }
	 * System.out.println("Vyberte textovy soubor s vasi databazi:"); } else {
	 * System.out.println("adresář " + adresar.getName() + " neexistuje");
	 * //adresar.mkdir(); } }
	 * 
	 * public void loadDatabase(String name) throws IOException
	 
	{
		posledniStudent=0;
		String celyText = "";
		FileReader fr = null; 
		BufferedReader in = null;
		int counter=0;
		 try {
				fr = new FileReader(name);
				in = new BufferedReader(fr);
				String radek;
				int pocetRadku=0;
				while ((radek = in.readLine()) != null) 
				{
					pocetRadku++;
				}
				in.close(); 
				fr.close(); 
				fr = new FileReader(name);
				in = new BufferedReader(fr);
				prvkyDatabaze=new Student[pocetRadku];
				while ((radek = in.readLine()) != null) 
				{
				celyText = new String(celyText + radek + "\n");
				String[] arrOfStr = radek.split(" ");
				String[] arrOfStrJmeno = arrOfStr[2].split(",");
				String[] arrOfStrNarozeni = arrOfStr[5].split(",");
				String[] arrOfStrPrumer = arrOfStr[8].split("\n");
				prvkyDatabaze[posledniStudent++]=new Student(arrOfStrJmeno[0],Integer.parseInt(arrOfStrNarozeni[0]));
				try
				{
					prvkyDatabaze[counter++].setStudijniPrumer(Float.parseFloat(arrOfStrPrumer[0]));
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					System.out.println("picovina");
				}
					
				}
				System.out.print("\n*** Výpis cele databaze ***\n"+celyText);
	        }
		 	catch (IOException e)
		 	{
				System.out.println("Soubor  nelze otevřít");
		 	} 
		 	finally 
		 	{ 
				in.close(); 
				fr.close(); // nutno doimplementovat null check atd.
			} 
			System.out.println();
	}
	*/
}
