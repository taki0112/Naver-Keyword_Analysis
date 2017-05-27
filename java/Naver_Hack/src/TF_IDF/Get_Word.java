package TF_IDF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class Get_Word {
	private static final String name = "KHU";
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(name+"_analysis.txt"));
			HashSet<String> word_set = new HashSet<String>();
			
			String line = "";
			while( (line = br.readLine()) != null ) {
				for(String word : line.split(" "))
					word_set.add(word);
			}
			
			FileWriter fWriter = new FileWriter(name+"_word_set.txt");
			
			Iterator<String> it = word_set.iterator();
			
			while(it.hasNext()) {
				String word = it.next();
				fWriter.write(word+"\n");
			}
			fWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
