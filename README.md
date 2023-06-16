# lolsearcher-auto-patch

> League of Legends 의  업데이트되는 데이터를 감지하고 자동으로 lolsearcher로 업데이트하는 자동화 시스템
> 

## 작동 과정

> 1. 스케줄러를 사용해 하루에 한 번 게임 홈페이지를 크롤링한다.
> 2. 크롤링한 홈페이지에서 최신 패치 버전을 찾아 서버에 저장된 데이터의 패치 버전과 비교한다.   
> 2-1. ***일치할 경우 Job 종료***   
> 2-2. ***불일치할 경우 게임 서버로부터 JSON 데이터를 파싱***
> 3. 최신 JSON 데이터로부터 데이터를 추출해 Cache에 저장한다.


## Skill Set

> - Language   : Java   
> - Framework  : SpringBoot   
> - Scheduler  : Quartz   
> - Repository : Redis
> - Http-Client : WebClient
> 

## 업데이트할 LOL 데이터 항목

> **챔피언** : https://ddragon.leagueoflegends.com/cdn/13.10.1/data/ko_KR/champion.json   

> **소환사 주문** : https://ddragon.leagueoflegends.com/cdn/13.10.1/data/ko_KR/summoner.json

> **룬 특성** : https://ddragon.leagueoflegends.com/cdn/13.10.1/data/ko_KR/runesReforged.json

> **큐** : https://static.developer.riotgames.com/docs/lol/queues.json

> **아이템** : https://ddragon.leagueoflegends.com/cdn/13.10.1/data/ko_KR/item.json
