package TF_IDF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class TF_IDF_write {
	public static void main(String[] args) {
		String name = "KHU";
		try {
			HashMap<String, Double> tf_idf_map = new HashMap<String, Double>();
			BufferedReader br = new BufferedReader(new FileReader(name+"_tf_idf.txt"));
			String line = "";
			while( (line = br.readLine()) != null ) {
				for(String str : line.split(" ")) { 
					String word = str.split(":")[0];
					double score = Double.parseDouble(str.split(":")[1]);
					tf_idf_map.put(word, score);
				}
			} // while
			
			Iterator<String> it = tf_idf_map.keySet().iterator();
			FileWriter fWriter = new FileWriter(name+"_tf_idf_aver.txt");
			double average = Average(tf_idf_map);
			fWriter.write(average + "\n");
			while(it.hasNext()) {
				String word = it.next();
				fWriter.write(word+":"+tf_idf_map.get(word)+"\n");
				
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
	
	public static double Average(HashMap<String, Double> tf_idf_map ) {
		double average_score = 0;
		Iterator<String> it = tf_idf_map.keySet().iterator();
		
		while(it.hasNext()) {
			String word = it.next();
			average_score += tf_idf_map.get(word);
		}
		
		average_score /= tf_idf_map.keySet().size();
		
		return average_score;
	}

}
