package KoreanAnalysis;

import java.util.List;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class MorphAnalyzer {
	// static String outputdir = "";
	
	private Komoran komoran;

	// initialization
	// input : models-light or models-full directory path
	MorphAnalyzer(String modelsPath) {
		
		komoran = new Komoran(modelsPath);
	}
	
	// If you want to use user dictionary
	// input : user dictionary file path 
	public void setUserDic(String dicPath) {
		komoran.setUserDic(dicPath);
	}
	
	/*
	// input : dictionary file path
	public void setFWD(String dicPath) {
		komoran.setFWD(dicPath);
	} */
	
	
	// normal sentence analysis
	// input : sentence
	// result : only noun
	public String analyze(String sentence) {
		String morResult = "";
		
		List<List<Pair<String, String>>> result = komoran.analyze(sentence);
		
		for(List<Pair<String, String>> eojeolResult : result ) {
			for(Pair<String, String> wordMorph : eojeolResult) {
				if(wordMorph.getSecond().contains("N") && !wordMorph.getSecond().contains("NA")) {
					if(wordMorph.getFirst().length() != 1) {
						String temp = wordMorph.getFirst();
						temp.replaceAll("\\s", "");
						morResult+=temp+" ";
					}
				}
			}
		}
		morResult = morResult.trim();
		return morResult;
	}
	
	// Sentence analysis using user desired tags
	// input : desired tags, sentence
	// result : analysis result contained words which have user desired tags
	public String analyzeTAG(String[] TAG, String sentence) {
		String morResult = "";
		List<List<Pair<String, String>>> result = komoran.analyze(sentence);
		
		for(List<Pair<String, String>> eojeolResult : result ) {
			for(Pair<String, String> wordMorph : eojeolResult) {
				for(int i=0; i<TAG.length; i++) {
					if(wordMorph.getSecond().contains(TAG[i])) {
						
						// if(wordMorph.getFirst().length() != 1) {
							String temp = wordMorph.getFirst();
							temp.replaceAll("\\s", "");
							morResult+=temp+" ";
						// } 
						
						// for checking tag
						// morResult+=wordMorph + " ";
					}
				}
				
			}
		}
		morResult = morResult.trim();
		return morResult;
	}
	
	/**/

}
