package KoreanAnalysis;

import java.util.List;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class Morph_test {

	public static void main(String[] args) {

		// 객체 생성
		MorphAnalyzer ma = new MorphAnalyzer("lib/models-full");
		// 사용자 정의 사전 추가
		//ma.setUserDic("dic/userdictionary.txt");
		
		String text = "대숲! 저 궁금한 게 있는데 많이들 군대갈 때 페이스북 계정을 애인한테 맡기고 가잖아요?";

		System.out.println(ma.analyze(text));
		
		Komoran komoran = new Komoran("lib/models-full");
		
		List<List<Pair<String, String>>> result = komoran.analyze(text);
		
		for(List<Pair<String, String>> eojeolResult : result) {
			for(Pair<String, String> wordMorph : eojeolResult) {
				System.out.println(wordMorph);
			}
			System.out.println();
		}  
		
		
		
	}


}
