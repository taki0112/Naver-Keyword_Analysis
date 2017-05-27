package Web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class web_tf_idf {
	private static final int TOP = 10000;
	public static void main(String[] args) {
		
		try {
			String university = "CAU";
			BufferedReader br = new BufferedReader(new FileReader(university+"_tf_idf_aver.txt"));
			ConcurrentHashMap<String, Double> tf_idf_map = new ConcurrentHashMap<String, Double>();
			String line = "";
			double average = Double.parseDouble(br.readLine());
			
			while ( (line = br.readLine()) != null ) {
				String word = line.split(":")[0];
				double score = Double.parseDouble(line.split(":")[1]);
				
				tf_idf_map.put(word, score);
			}
			
			Iterator<String> it = sortByValue(tf_idf_map).iterator();
			
			
			FileWriter fWriter = new FileWriter("tf_idf.txt");
			int count = 0;
			while(it.hasNext()) {
				String word = it.next();
				double score = tf_idf_map.get(word);
				
				if(count < TOP && score > average) {
					fWriter.write(word+":"+score+"\n");
					count++;
				}
				
			}
			fWriter.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List sortByValue(Map map) {
	    List<String> list = new ArrayList<String>();
	    list.addAll(map.keySet());
	    Collections.sort(list, new Comparator() {
	        public int compare(Object o1, Object o2) {
	            Object v1 = map.get(o1);
	            Object v2 = map.get(o2);
	            
	            return ((Comparable) v1).compareTo(v2);
	        }
	    });
	    Collections.reverse(list); // 주석이 오름차순
	    return list;
	}
	

	

}
