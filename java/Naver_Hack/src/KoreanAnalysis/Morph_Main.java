package KoreanAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Morph_Main {
	public static void main(String[] args) {

		// 객체 생성
		MorphAnalyzer ma = new MorphAnalyzer("lib/models-full");
		// 사용자 정의 사전 추가
		//ma.setUserDic("dic/userdictionary.txt");
		
		//String text = "우리나라에서 지난 리우올림픽에서 태권도 선수로 몇명이나갔는지,모두다 메달을 땄는지좀 알려주세요";
		//System.out.println(ma.analyze(text));
		
		try {
			FileWriter fWriter = new FileWriter("KHU_analysis.txt");
			BufferedReader br = new BufferedReader(new FileReader("KHU_10000.txt"));
			String line = "";
			
			int count = 1;
			while( (line = br.readLine()) != null ) {
				String morph = ma.analyze(line);
				Pattern p = Pattern.compile("([가-힣]+)");
				Matcher matcher = p.matcher(morph);
				String result = "";
				while(matcher.find()) {
					result += matcher.group() + " ";
				}
//				morph = morph.replaceAll("[0-9]", "");
//				morph = morph.replaceAll("[a-zA-Z]", "");
				fWriter.write(result);
				fWriter.write("\n");
				System.out.println(count++);
			}
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	  public static String Get_Hangul(String str) {
		  StringBuffer hangul = new StringBuffer();
		  for(int i=0; i<str.length(); i++) {
			  if(checkHan(str.charAt(i))) 
				  hangul.append(str.charAt(i));
			  else
				  hangul.append(" ");
		  }
		  return hangul.toString();
	  }
	  
	  private static boolean checkHan(char cValue) {
		  if (cValue >= 0xAC00 && cValue <= 0xD743)
			  return true;
		  else
			  return false;
	  }

}

