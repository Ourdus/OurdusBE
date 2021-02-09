API 명세서(카트, 주문)

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
바로 주문과 주문에서는 프론트단에서 json을 가진 채로 redirect 처리를 진행,
백엔드에서는 jwt 확인하고 interceptor 및 유저 정보 전달 역할


바로주문 (작품상세->구매하기, 데이터를 그대로 전달) (Header에서 jwt 확인)
POST /w/product/order
Request {{data:[
}
Response {data:[
}
SUCESS {}
FAIL {}


주문 (Header에서 jwt 확인)
POST /w/payment
Request {
    
}
Response {
    user_name: " ",
    user_tel: " ",
    user_Address: [ ]
}


주문결제(이게 진짜 주문임, Header에서 jwt 확인)
POST /w/payment/order
Request {
    {data:
    order_price: ,
    order_account: ,
    orderform: [
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
    order_id:    
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