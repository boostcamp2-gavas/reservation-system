## Gavas Reservation-System


#### MainController
- 1.1 Main Page
	- API[GET] : "/"
	- ModelAndView
		- View : mainpage.jsp
	- return ModelAndView

#### CategoryController
- 1.2 Category 목록 (REST API)
	- API[GET] : "/api/categories"
	- return Category(Domain)

- 1.3 Category 갯수
	- API[GET] : "/api/categories/{categoryId}/productscount"
	- SELECT count(*) FROM category;
	- return Integer(Category count)

- 1.4 Product 목록
	- API[GET] : "/api/categories/{categoryId}/product"
	- return DTO : product, 에 대한 DTO

- 특이사항
	- Category 갯수를 최초 ajax로 가져온 후 local cache에 저장(javascript)
	- Proudct 정보를 카테고리별로 10개(처음)를 최초 ajax로 가져온 후 local cache에 저장(javascript)

#### DetailController
- 2.1 Detail Page
		- API[GET] : "/details/{productId}"
		- ModelAndView
			- Model : productId(Server) -> data-productid="3"(Front)
			- View : detail.jsp
		- return ModelAndView

#### ProductController
- 2.2 Product 정보
	- API[GET] : "/api/products/{productId}/details"
	- return DTO : product, 에 대한 DTO

- 2.3 Comment 종합 건수 & 별점
	- API[GET] : "/api/products/{productId}/---"

- 2.4-1 Comment 정보 (review 페이지와 동일)
	- API[GET] : "/api/products/{productId}/usercomments?commentid=:commentid&limit=:limit"
		- usercommentid는 offset을 대신한다.
		- limit = 3
	- return DTO : review text + Image 1장 DTO

- 3.2 reserve product 정보
	- API[GET] : "/api/products/{id}/reservation"
	- return DTO = {
		productInfoDto,
		productPriceDto
		} 합친 하나의 DTO

- 특이사항
	- 지도 -> javascript naver map V3 사용

#### UserCommentController
- 2.4-2 Comment 이미지 정보
	- API[GET] : "/api/usercomments/{userCommentId}/images"

#### ReserveController
- 3.1 reserve Page
	- API[GET] : "/reserve/{productId}"
	- ModelAndView
		- Data
			- productId(Server) -> data-productid="3"(Front)
			- user 정보 from argumentResolver
		- View : reserve.jsp
	- return ModelAndView

#### ReservationController
- 4.1 myreservation Page
	- API[GET] : "/reservations"
	- ModelAndView
		- Data
			- userCommentId(Server) -> data-userCommentid="3"(Front)
		- view : myreservation.jsp
	- return ModelAndView


- 3.3 예매하기
	- API[POST] : "/api/reservations"
	- 예매한 후 myreservation 페이지로 이동

- 4.2 나의 예매 내역
	- API[GET] : "/api/reservations/{userid}"
	- return Domain : reservation_info

- 4.3 예매 내역 변경
	- API[PUT] : "/api/reservations"
	- RequestBody = {
		reservationId,
		reservationType
	}

#### ReviewController
- 5.1 reviewWrite Page
	- API[GET] : "/reviewWrite"
	- ModelAndView
		- Data : product Name
		- View : reviewWrite.jsp
	- return ModelAndView

- 6.1 review Page
	- API[GET] : "/review"
	- ModelAndView
		- Data : productId
		- view : review.jsp
	- return ModelAndView

- 5.2 Product Name
	- No API
	- Service내에서 Product Name을 ModelAndView로 넣어준다.

- 5.3 review write
	- API[POST] : "/api/review"
	- 리뷰작성 후 review 페이지로 이동

- 6.2 리뷰 받아오기
	- 2.4-1과 2.4-2와 동일한 로직 수행
		- 2.4-1의 limit값은 10이 된다.
