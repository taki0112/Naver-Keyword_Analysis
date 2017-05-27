package TF_IDF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Get_DF {
	private static String name = "CAU";
	public static void main(String[] args) {
		try {
			name = args[0];
			System.out.println(System.getProperty("user.dir"));
			BufferedReader br = new BufferedReader(new FileReader(name+ "_word_set.txt"));
			ConcurrentHashMap<String, Integer> df_map = new ConcurrentHashMap<String, Integer>();
			
			String line = "";
			
			while ( (line = br.readLine()) != null ) {
				df_map.put(line, 0);
			}
			
			br = new BufferedReader(new FileReader(name+"_keyword.txt"));
			while( (line = br.readLine()) != null) {
				HashSet<String> set = new HashSet<String>(Arrays.asList(line.split(" ")));
				Iterator<String> it = set.iterator();
				while(it.hasNext()) {
					String word = it.next().split(":")[0];
					try {
						df_map.put(word, df_map.get(word) + 1 );
					} catch(NullPointerException e) {
						System.out.println(word);
					}
					
				}
			}
			

			//BufferedWriter fWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tf_idf.txt"), "MS949"));
			FileWriter fWriter = new FileWriter("tf_idf.txt");

			Iterator<String> it = sortByValue(df_map).iterator();
			
			int flag_count = 0;
			while(it.hasNext()) {
				String word = it.next();
				fWriter.write(word + ":" + df_map.get(word) + "\n");
				if(flag_count++ > 19) break;
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
