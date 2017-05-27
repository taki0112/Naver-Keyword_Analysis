package TF_IDF;

import TF_IDF.VectorSpaceModel.WriteOption;

public class main {

	public static void main(String[] args) {
		VectorSpaceModel vsm = new VectorSpaceModel("KHU_analysis.txt");
		vsm.CalculateTFIDF();
		
		vsm.Write("KHU_tf_idf.txt",WriteOption.TFIDF);
		//TF-IDF
	}

}
