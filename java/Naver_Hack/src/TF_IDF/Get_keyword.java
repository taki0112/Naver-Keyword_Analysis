package TF_IDF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Get_keyword {
	private static final String name = "KHU";
	public static void main(String[] args) {
		try {
			BufferedReader aver_br = new BufferedReader(new FileReader(name+"_tf_idf_aver.txt"));
			BufferedReader br = new BufferedReader(new FileReader(name+"_tf_idf.txt"));
			
			double avearage = Double.parseDouble(aver_br.readLine());
			System.out.println(avearage);
			avearage = Math.log(avearage) / Math.log(2);
			System.out.println(avearage);
			String line = "";
			
			FileWriter fWriter = new FileWriter(name+"_keyword.txt");
			while ( (line = br.readLine()) != null ) {
				for(String str : line.split(" ")) {
					String word = str.split(":")[0];
					double score = Double.parseDouble(str.split(":")[1]);
					if(score > avearage) {
						fWriter.write(word+":"+score+" ");
					}
				}
				fWriter.write("\n");
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
}
