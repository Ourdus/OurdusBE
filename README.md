## OurdusBE
2021-01-26~2021-02-02  
Product CRUD 구현해보기  

### Table
---
![image](https://user-images.githubusercontent.com/59992230/105850581-43a6e680-6025-11eb-86c6-52445c3fe795.png)
* 작품, 작품 카테고리, 작품 옵션은 Table 생성 必
* 작품 이미지는 선택사항

### API 명세
---

4. 작품 조회
```
GET /w/product
Request
Response
SUCCESS { "code": 200, "message": "작품전체조회", "data": [
{ "product_id": 1,
  "user_id": 123,
  "author_id": "1",
  "author_name" : "aaa", #name추가
  "category_name": 3,  #id대신 name
  "product_name": "작품명",
  "product_price": 5000,
  "product_option_num": 2,
  "product_hit" : 3 #hit 추가
  "product_purchase" : 15 #구매량 추가
  "product_review" : 133 #리뷰수 추가
  "product_rate" : 5 #별점 추가
 }]
}
FAIL {"code": 500, "message": "작품전체조회실패"}
```

```
GET /w/category/{category_id}
Request
Response
SUCCESS { "code": 200, "message": "카테고리별 작품 조회 성공", "data":
{ "product_id": 1,
  "author_id": 123,
  "author_name": "작가이름",
  "category_name": "음료(커피,차)",  #카테고리 아이디 대신 이름
  "product_name": "작품명",
  "product_price": 5000,
  "product_option_num": 2,
  "product_rate" : 5 , #추가
  "product_review" : 123, #추가
  "product_purchase" : 111, #추가
  "product_option_num" : 3, #추가
 }
}
FAIL {"code": 500, "message": "카테고리별 작품 조회 실패"}
```

```
GET /w/product/{product_id}
Request
Response
SUCCESS { "code": 200, "message": "작품조회 성공", "data":
{ "product_id": 1,
  "author_id": 123,
  "author_name": "작가이름",
  "category_id": 3,
  "product_name": "작품명",
  "product_price": 5000,
  "product_option_num": 2,
 "product_rate" : 5 , #추가
  "product_review" : 123, #추가
  "product_purchase" : 111, #추가
 "product_hit" : 3 , #추가
 }
}
FAIL {"code": 500, "message": "작품 조회 실패"}
```
- 이미지 추가시 이미지도 함께 반환
- 실조회시에는 할인율 등등도 같이 반환될 수 있어야함 (이후에 추가)

- 원하는 방식으로 API method 생성 가능(PUT, PATCH, DELETE ...)
 단, **수정시에 유의할 점** 작업하고 있는 로컬 폴더 내의 README.md 파일을 작업한 형식대로 수정하고 commit&push
 
- **author_id** 를 보내는 방법 (어느 방법을 사용하셔도 OK)
	- 1) user 정보를 직접 parameter로 주입
	- 2) 로그인한 정보를 전달 (Session을 이용 or Jwt Token을 이용)

- 작품 Crawling

```
git pull origin
git checkout proto/product#2
git checkout -b proto/product#2_이름
git push origin HEAD
```