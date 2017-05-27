# keyword-analysis
키워드 분석

## 주제 선정 배경
매일 생성되는 네이버 컨텐츠의 제목 또는 내용의 키워드를 분석하여 의미 있는 데이터를 생성합니다.

## 요구사항
- 단순 키워드들의 count 정보가 아닌 최소 2~3개 이상의 키워드 조합이 필요합니다.
(예를들어 “아이유 팔레트”, “k팝스타 보이프렌드” 같은 의미있는 키워드 추출)
- 추출된 키워드 데이터의 저장 구조에 대한 검토가 필요합니다.
(서비스별로 혹은 카테고리별로 많이 언급된 키워드 등 의미있는 데이터를 보기 위한 구조)
- 대량의 데이터 처리에 대한 고려가 필요합니다.
- 키워드 추출을 위한 오픈소스 사용이 가능합니다.

`문의사항은 이슈 항목에 등록해주시면 답변드리겠습니다.`

# Results

## Requirements
* Python 3.5
* java 1.8
* node.js 6.10.2

## Open Source
* [facebook-api](https://developers.facebook.com/)
* [Komoran Morphologica analyzer](http://shineware.tistory.com/entry/KOMORAN-ver-24)
* [Twitter Morphological analyzer](https://github.com/twitter/twitter-korean-text)

`But we used the Komoran morpheme analyzer.`
* [Word2Vec](https://github.com/taki0112/Word2VecJava) 

`But I've rewrite it in Java. See JunhoKim github`

## Algorithm
* [Cosine Similarity](https://ko.wikipedia.org/wiki/%EC%BD%94%EC%82%AC%EC%9D%B8_%EC%9C%A0%EC%82%AC%EB%8F%84)
* [TF-IDF](https://ko.wikipedia.org/wiki/TF-IDF)

## Screenshot (Kyunghee University Keyword & Words related to "연애")
![Screenshot](./screenshot.PNG)

## Optional
* Data Visualization in Web (Word cluod)
```bash
Keyword = DF * (1/log_3(Rank) * (1/5)
Associated word = Cosine similarity * 1000 * (1/log_3(Rank) 
```

## Author
Junho Kim, JungHyun Park, SeonYeong O
