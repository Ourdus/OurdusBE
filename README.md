## OurdusBE_통합#5

- 통합 with front (API document를 중점으로)
- Deploy  
  : nginx, react, spring boot, docker
    - CI/CD tool : travis
- DB
  : RDS(MySQL)
  

📙 프로모션(Promotiom) &프로모션 작품 (PromotionProduct) Entity 생성 및 조회 구현 
### Table
---
![image](https://user-images.githubusercontent.com/55472510/108139955-7dd74580-7104-11eb-8e30-3b3c88886f25.png)  

Promotion & PromotionProduct table 간의 관계 => 양방향 
PromotionProduct & Product table 간의 관계 => 단방향 
 
<구현시 막혔던 부분> 
1. @JoinColumn(name="PRODUCT_ID") 부분에서 insertable=false로 설정해두면 값을 넣었을 때 insert가 막혀있으므로 promotion_id 값이 null로 받아와 insert 가 동작되지 않는다.
2. Data jpa의 update, delete issue: deleteById 함수를 이용해서 지우면 200이 나와도 db에서 지워지지 않는 경우발생 -> 예비로 query로 작성해서 동작하게 구현함

API 명세서
Ourdus 통합 페이지 [링크](https://github.com/Ourdus/Ourdus/wiki)  
프로모션, 프로모션 별 작품 부분 참조   

--- 

📙 리뷰 (Review) Entity 생성 및 조회 구현  
### Table
---
![image](https://user-images.githubusercontent.com/55472510/108140896-05718400-7106-11eb-913a-f5e2e4c831c1.png)

Review table 
 
<구현시 막혔던 부분> 


API 명세서
Ourdus 통합 페이지 [링크](https://github.com/Ourdus/Ourdus/wiki)  
리뷰 부분 참조 

--- 



📙 댓글 (Comment) Entity 생성 및 조회 구현  
### Table
---
![image](https://user-images.githubusercontent.com/55472510/108141231-a9f3c600-7106-11eb-9671-fa82362b3d45.png)

Comment table 
 
<구현시 막혔던 부분> 


API 명세서
Ourdus 통합 페이지 [링크](https://github.com/Ourdus/Ourdus/wiki)  
리뷰 부분 참조   

--- 
