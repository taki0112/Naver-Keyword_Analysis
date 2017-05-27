package KoreanAnalysis;
/*
 * Twitter Korean Text - Scala library to process Korean text
 *
 * Copyright 2014 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.LinkedList;
import java.util.List;

import com.twitter.penguin.korean.KoreanTokenJava;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import scala.collection.Seq;



public class twit_test {
  public static void main(String[] args) {
    String text = "대숲 어쩌죠...? 짝사랑을 시작하게 된 거 같아요ㅜㅜㅜㅠㅠㅠㅠㅠ";

    // Normalize
    CharSequence normalized = TwitterKoreanProcessorJava.normalize(text);
    System.out.println(normalized);
    // 한국어를 처리하는 예시입니다ㅋㅋ #한국어
 

    // Tokenize
    Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
    //System.out.println(TwitterKoreanProcessorJava.tokensToJavaStringList(tokens));
    // [한국어, 를, 처리, 하는, 예시, 입니, 다, ㅋㅋ, #한국어]
   // System.out.println(TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(tokens));
    // [한국어(Noun: 0, 3), 를(Josa: 3, 1),  (Space: 4, 1), 처리(Noun: 5, 2), 하는(Verb: 7, 2),  (Space: 9, 1), 예시(Noun: 10, 2), 입니(Adjective: 12, 2), 다(Eomi: 14, 1), ㅋㅋ(KoreanParticle: 15, 2),  (Space: 17, 1), #한국어(Hashtag: 18, 4)]


    // Stemming
    Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
    System.out.println(TwitterKoreanProcessorJava.tokensToJavaStringList(stemmed));
    // [한국어, 를, 처리, 하다, 예시, 이다, ㅋㅋ, #한국어]
    List<KoreanTokenJava> change = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
    LinkedList<String> change_list = new LinkedList<String>();
    for(int i=0; i<change.size(); i++) {
    	 KoreanTokenJava str = change.get(i);
    	 String str2 = str.toString();
    	 str2 = str2.replaceAll(":", "");
    	 str2 = str2.replaceAll("[0-9]", "");
    	 str2 = str2.replaceAll(" , ", "");
    	 change_list.add(str2);
    }
    
    System.out.println(change_list);
    // [한국어(Noun: 0, 3), 를(Josa: 3, 1),  (Space: 4, 1), 처리(Noun: 5, 2), 하다(Verb: 7, 2),  (Space: 9, 1), 예시(Noun: 10, 2), 이다(Adjective: 12, 3), ㅋㅋ(KoreanParticle: 15, 2),  (Space: 17, 1), #한국어(Hashtag: 18, 4)]


    /*
    // Phrase extraction
    List<KoreanPhraseExtractor.KoreanPhrase> phrases = TwitterKoreanProcessorJava.extractPhrases(tokens, true, true);
    System.out.println(phrases);
    // [한국어(Noun: 0, 3), 처리(Noun: 5, 2), 처리하는 예시(Noun: 5, 7), 예시(Noun: 10, 2), #한국어(Hashtag: 18, 4)]
     */

  }
}
