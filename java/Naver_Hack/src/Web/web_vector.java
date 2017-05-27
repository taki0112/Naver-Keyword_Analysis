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
public class web_vector {
	private static int dimension;
	private static ConcurrentHashMap<String, double[]> vector_map = new ConcurrentHashMap<String, double[]>(); // term vector map
	public static void main(String[] args) {
		try {
			Make_Vector_Map(args[0]);
			String target_word = args[1];
			
			ConcurrentHashMap<String, Double> vector_sim = new ConcurrentHashMap<String, Double>();
			
			Iterator<String> it = vector_map.keySet().iterator();
			
			while(it.hasNext()) {
				String compare_word = it.next();
				double ts_ss = 0;
				if(!target_word.equals(compare_word)) {
					ts_ss = Cosine(vector_map.get(target_word), vector_map.get(compare_word));
					vector_sim.put(compare_word, ts_ss);
				}
			}
			Iterator<String> sort_it = sortByValue(vector_sim).iterator();
			FileWriter fWriter = new FileWriter("vector.txt");
			
			int flag_count =0;
			while(sort_it.hasNext()) {
				String word = sort_it.next();
				fWriter.write(word + ":" + vector_sim.get(word) + "\n");
				if(flag_count ++ > 19 ) break;
			}
			
			fWriter.close();
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
	
	public static void Make_Vector_Map(String university) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(university + "_vector.txt"));
			String line = br.readLine();
			String [] num = line.split(" ");
			
			int term_num = Integer.parseInt( num[0] ); // 단어 수
			dimension = Integer.parseInt( num[1] ); // 차원
			
			while( (line = br.readLine()) != null ){
				double[] vectors = new double[dimension];
				String term = line.split(" ")[0];
		//		System.out.println(term);
				if(!term.isEmpty()) {
					for (int i = 1; i < dimension + 1; i++) {
						vectors[i - 1] = Double.parseDouble(line.split(" ")[i]);
					}

					vector_map.put(term, vectors);
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double Cosine(double[] v1, double[] v2) {
		double result = 0;

		result = InnerProduct(v1, v2) / (VectorSize(v1) * VectorSize(v2));

		return result;
	}

	public static double VectorSize(double[] vector) {
		double vector_size = 0;

		for (int i = 0; i < vector.length; i++) {
			vector_size += Math.pow(vector[i], 2);
		}
		vector_size = Math.sqrt(vector_size);

		return vector_size;
	}

	public static double InnerProduct(double[] v1, double[] v2) {
		double Inner = 0;
		for (int i = 0; i < v1.length; i++) {
			Inner += v1[i] * v2[i];
		}
		return Inner;
	}

	public static double Euclidean(double[] v1, double[] v2) {
		double ED = 0;
		for (int i = 0; i < v1.length; i++) {
			double sec = v1[i] - v2[i];
			ED += Math.pow(sec, 2);
		}

		ED = Math.sqrt(ED);

		return ED;
	}

	public static double Theta(double[] v1, double[] v2) {
		double V = Cosine(v1, v2);
		double theta = Math.acos(V) + 10;

		return theta;
	}

	public static double Triangle(double[] v1, double[] v2) {
		double theta = Theta(v1, v2);
		theta = Math.toRadians(theta);
		double TS = 0;
		TS = (VectorSize(v1) * VectorSize(v2) * Math.sin(theta)) / 2;


		return TS;

	}

	public static double Magnitude_Difference(double[] v1, double[] v2) {
		double MD = 0;
		MD = Math.abs(VectorSize(v1) - VectorSize(v2));

		return MD;
	}

	public static double Sector(double[] v1, double[] v2) {
		double SS = 0;
		SS = Math.PI * (Math.pow((Euclidean(v1, v2) + Magnitude_Difference(v1, v2)), 2)) * (Theta(v1, v2) / 360);

		return SS;
	}

	public static double TS_SS(double[] v1, double[] v2) {
		double TS_SS = 0;
		TS_SS = Triangle(v1, v2) * Sector(v1, v2);

		return TS_SS;
	}

}
