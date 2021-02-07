## OurdusBE_통합#3_혜진

주문(Order) Entity 생성 및 조회 구현
관련 내용 [이슈사항](https://github.com/Ourdus/OurdusBE/issues/20)
### Table
---
![image](https://user-images.githubusercontent.com/59992230/107112429-f20b2100-689a-11eb-9ca7-6b3fbb612953.png)

![image](https://user-images.githubusercontent.com/59992230/107118071-fa2a8700-68c1-11eb-8c88-7619c969747e.png)


![image](https://user-images.githubusercontent.com/59992230/107118063-e67f2080-68c1-11eb-9db5-03e564d55845.png)


복합키를 사용에 참고한 [링크](https://woowabros.github.io/experience/2019/01/04/composit-key-jpa.html)  
@IdClass 이용시엔 *(자바 ORM 표준 JPA 프로그래밍 책 참고)*
1. 식별자 클래스의 속성명과 엔터티에서 사용하는 식별자의 속성명이 같아야한다.
2. Serialiable 인터페이스를 구현해야한다.
3. equals, hashCode를 구현해야한다.
4. 기본생성자가 있어야한다.
5. 식별자 클래스는 public이어야한다.

이를 만족하는 OOOId Class들을 각각 Image, Option, Detail에 구현해주었다.

API 명세서

카트
```
카트리스트 조회(Header에서 jwt 확인)
GET /w/cart
Request
Response
SUCCESS { "success": true,
"response": [
    {"cart_id": ,
    "author_id": , 
    "author_name": ,
    "product_id": ,
    "product_name": ,
    "option_info": ,
    "product_num":, 
    "product_detail_price": 
    }
],
"apiError": null
}
FAIL {"success": false,
"response": "errormessage",
"apiError": code
}

카트 삭제
POST /w/cart/delete
Request { "cart_id": }
Response
SUCESS {}
FAIL {}

카트 담기(Header에서 jwt 확인)
POST /w/cart-in
Request {data:[
    {
    "author_id": , 
    "author_name": ,
    "product_id": ,
    "product_name": ,
    "option_info": ,
    "product_num":, 
    "product_detail_price":
}, ...]    
}
Response
SUCESS {}
FAIL {}
```

주문
```
바로주문 (작품상세->구매하기, 데이터를 그대로 전달) (Header에서 jwt 확인)
POST /w/product/order
Request {{data:[
    {
    "author_id": , 
    "author_name": ,
    "product_id": ,
    "product_name": ,
    "option_info": ,
    "product_num":, 
    "product_detail_price":
}, ...]    
}
Response {data:[
    {
    "author_id": , 
    "author_name": ,
    "product_id": ,
    "product_name": ,
    "option_info": ,
    "product_num":, 
    "product_detail_price":
}, ...]    
}
SUCESS {}
FAIL {}


주문 (Header에서 jwt 확인)
POST /w/payment
Request {
    {data:[
    {
    "author_id": , 
    "author_name": ,
    "product_id": ,
    "product_name": ,
    "option_info": ,
    "product_num":, 
    "product_detail_price":
}, ...] 
}
Response {
    
    response:
    user_name: " ",
    user_tel: " ",
    user_Address: [ ],
    product: [
    {
    "author_id": , 
    "author_name": ,
    "product_id": ,
    "product_name": ,
    "option_info": ,
    "product_num":, 
    "product_detail_price":
}, ...]    
}


주문확인(Header에서 jwt 확인)
GET /w/me/order/payment
Response {
    
    response:
    orders:[
        {
            "order_id": ,
            "order_date": ,
            "order_price": ,
            "orderDetails": [
               {"order_detail_id": ,
                "author_id": ,
                "author_name": ,
                "product_id": ,
                "product_name": ,
                "option_info": ,
                "product_num": }, ...
            ]
        }

    ]    
}

주문상세확인
GET /w/me/order/payment/detail/{order_id}

```