# 프로젝트 :  도서 판매 관리 시스템
#

# 맡은 부분 : 회원 관리 파트 & 관리자 파트 (회원, 상품, 주문 관리) & 결제 파트
#
## 사용 환경 
#### OS : window 11 pro
#### RAM : 16g
#### IDE : Intellij

#
## 프론트엔드 
##

### 프레임워크 : 리액트 18 ver.
### 라이브러리 : react-router-dom, react-daum-postcode, http-proxy-middleware, axios, react-modal, apexcharts, react-intersection-observer, html-react-parser, dompurify, react-bootstrap, react-quill, react-redux, @reduxjs/toolkit
#

## 백엔드

### 프레임워크 : 스프링부트 3.1.2
### 라이브러리(or 프레임워크) : spring-web, mySQL-driver, Spring-Security, JPA, mail, validation, modelMapper, jjwt, lombok

#

## 회원 상세 기능

#

### 1. 회원가입 
#### 1) 아이디, 닉네임, 이메일은 유일하게 설정하여 중복 검사를 진행 
#### 2) 아이디는 최소 6글자에서 12글자, 특수문자 허용하지 않는다. 비밀번호는 최소 8글자에서 15글자, 숫자 영문자 & 특수문자가 반드시 들어가야 한다. 닉네임은 특수문자 제외 4~20글자, 나머지 항목들(주소, 전화번호 등) 또한 양식에 맞게 넣을 수 있도록 한다. 
#### 3) 이메일 인증은 난수 8글자로 지정하여 사용자가 입력한 이메일로 전송하여 입력값과 일치하면 인증이 가능하도록 한다. 
#### 4) 인증 시간은 3분으로 주어지고 시간이 초과되면 인증코드가 자동으로 사라진다. 인증을 완료해도 사라진다. 
#### 5) 주소 API를 사용하여 주소를 직접 입력하는 것 보다 검색해서 입력할 수 있도록 한다. 
#### 6) 비밀번호는 BCrypt + salted 활용하여 DB에 암호화해서 저장할 수 있도록 한다. 


#
### 2. 회원탈퇴 
#### 1) 회원탈퇴시 유예기간을 지정하기 위해 DELETE_FLAG 컬럼과 DELETE_TIME을 지정한다. 회원탈퇴시 DELETE_FLAG가 Y로 설정된다 
#### 2) 이때 탈퇴한 시간을 기록한다. 
#### 3) 일정기간 이상 지날 시 자동으로 테이블에서 완전 삭제한다. 

#
### 3. 회원정보 수정 
#### 1) 회원정보 수정은 먼저 회원정보를 불러오는 뷰를 만들고 회원정보를 가져온다. 
#### 2) 뷰에 회원정보 수정 버튼을 만들어 사용자가 클릭한 후 정보를 수정할 수 있도록 한다. 
#### 3) 회원정보 수정은 앞서서 비밀번호 인증을 먼저 수행하고 진행한다. 
#### 4) 인증 완료 시 회원정보 수정, 암호 재설정 섹션을 선택할 수 있는 페이지로 이동한다.
#### 5) 회원정보 수정은 유저의 이름, 닉네임, 연락처, 주소, 성별을 바꿀 수 있다.
#### 6) 암호 재설정은 암호만 재설정 할 수 있도록 한다.


#
### 4. 회원권한 부여 및 인증 
#### 1) 회원가입을 통한 사용자와 관리자의 Role은 각각 USER, ADMIN으로 지정한다. 
#### 2) USER 권한을 가진 사용자는 본인이 작성한 게시글이나 상품 장바구니, 구매등의 일반적인 서비스를 이용할 수 있다. 
#### 3) ADMIN 권한을 가진 사용자는 플랫폼의 모든 권한을 위임받는다. 
#### 4) 인증 절차는 JWT 토큰을 활용한다. 인증된 access토큰과 refresh토큰 두 개가 있어야 서비스에 접근할 수 있도록 한다. 
#### 5) 만약 access토큰이 만료되었을때 인증된 refresh토큰을 가지고 있다면 access토큰을 재발급한다. 
#### 6) 만약 refresh토큰이 만료되었다면 로그인을 다시 해야한다. 


#
### 5. 로그인 
#### 1) 로그인 시 아이디와 비밀번호가 일치하면 해당 브라우저의 쿠키에 access토큰과 refresh토큰을 부여한다. 
#### 2) access토큰의 만료기간은 1시간, refresh토큰의 만료기간은 7일로 지정한다. 
#### 3) 성공적으로 로그인 시 해당 이메일로 로그인이 완료되었다는 이메일을 전송한다. 


#
### 6. 로그아웃 
#### 1) 로그아웃시 access토큰과 refresh토큰을 회수한다. 


#
### 7. 아이디 찾기  
#### 1) 사용자가 이메일값을 보내면 DB에 해당 이메일 값이 있을 때 그 이메일로 난수 8글자의 메일을 전송한다.
#### 2) 사용자는 본인이 입력한 이메일 사이트에 들어가서 해당 난수를 보고 인증을 진행하기 위해 입력한다.
#### 3) 일치하면 사용자 이메일과 같은 튜플에 있는 아이디를 사용자에게 제공해준다.



#
### 8. 비밀번호 찾기  
#### 1) 사용자가 아이디와 이메일값을 보내면 DB에 해당 이메일과 아이디 값이 존재하면서 같은 튜플에 있을 때 그 이메일로 난수 8글자의 메일을 전송한다.
#### 2) 사용자는 본인이 입력한 이메일 사이트에 들어가서 해당 난수를 보고 인증을 진행하기 위해 입력한다.
#### 3) 일치하면 사용자 이메일과 같은 튜플에 있는 사용자 고유 코드를 응답 본문에 실어서 전송하고 비밀번호 변경 페이지로 이동한다.
#### 4) 사용자가 새로운 암호를 입력하면 사용자 고유 코드와 함께 다시 서버로 전송하여 비밀번호를 업데이트한다.

#
### 9. 주문 내역 확인 
#### 1) 사용자는 본인이 구매한 주문 내역들을 열람할 수 있다.
#### 2) 주문 내역을 누르면 상세 주문 내역을 열람할 수 있다.
#### 3) 사용자는 주문을 취소할 수 있다.

#
## 관리자 페이지 기능


#
### 1. 로그인 
#### 1) 자동으로 생성된 아이디와 암호로 로그인을 진행한다. 
#### 2) 관리자 관련 페이지는 ADMIN 권한이 있어야만 접근할 수 있다. 
#### 3) 로그인은 JWT로 진행하고 토큰이 둘 다 있을 때 자동으로 관리자 메인페이지로 이동한다. 


#
### 2. 관리자 메인 페이지의 요약기능
#### 1) 회원수, 최근 주문건, 읽지 않은 문의글 수를 화면에 띄워 데이터가 한 눈에 볼 수 있게 한다. △ (회원수, 주문만 기능 구현)
#### 2) 각 요약 페이지 또는 왼쪽 메뉴 클릭시 해당하는 데이터 관리 페이지로 이동한다. o
#### 3) 신규 회원 동향을 한 눈에 알 수 있는 그래프를 만들어 전일, 전 14일을 기준으로 회원가입수 증감을 알 수 있다.
#### 4) 최근 주문건은 읽지 않은 주문 건, 생성시간을 기준으로 내림차순으로 상위 4개의 데이터를 보여준다. o
#### 5) 읽지 않은 주문 건은 따로 표기를 해두어 관리자가 잘 인지할 수 있도록 하고 읽은 주문건은 사라진다. (order 테이블의 isRead 플래그 활용)


#
### 3. 회원 관리 페이지 
#### 1) 접근시 100명을 기준으로 초기 화면에 띄운다. 
#### 2) 각 카테고리 별 클릭 시 오름차순, 내림차순 정렬이 가능하도록 한다. 
#### 3) 검색 기능을 통해 사용자가 본인의 아이디나 암호를 알고자 할 때 관리자는 해당 데이터를 쉽게 찾을 수 있도록 한다. 
#### 4) 검색 결과 개수를 표기해 데이터를 더욱 가독성 있게 한다. 
#### 5) 여러 조건(검색 키워드, 데이터 수, 카테고리 별 정렬, n페이지 접근)을 모두 가능하게 한다. ex) 주소 기준 오름차순, 데이터 50개씩으로 qwer 검색한 결과의 3페이지 접근 
#### 6) 페이지 기능(처음 / 이전 10페이지 / 다음 10페이지 / 마지막) 추가 
#### 7) 회원 블락 처리 추가 (관리자가 블락을 걸면 해당 사용자가 인증이 필요한 서비스에 접근이 안되도록 즉시 적용)

#
### 4. 주문 관리 페이지
#### 1) 접근 시 초기 화면에 최근 주문건 100개를 보여준다
#### 2) 관리자는 주문 상태를 변경할 수 있다. (주문 완료, 배송 준비중, 배송 중, 배송 완료, 환불 및 교환)
#### 3) 회원 관리 페이지와 마찬가지로 페이징 기능과 정렬기능이 가능하다.
#### 4) 상세 주문 관리에 들어가면 주문한 사용자의 정보(MemberEntity), 주문 정보(OrderEntity), 주문한 상품 리스트(OrderProductEntity) 정보를 볼 수 있다.


#
## 결제기능
#### 1) 결제하기 또는 장바구니에 담을 시 결제창으로 이동한다.
#### 2) 구매하기 페이지는 서버의 부담을 덜기위해 세션스토리지에 담은 데이터를 기반으로 한다.
#### 3) PG사는 PortOne을 활용했고 개발자 모드로 진행하기 때문에 결제는 되나 익일 자정에 환불된다.
#### 4) 결제 완료 시 주문한 내용을 확인할 수 있다. 해당 페이지는 한 번 확인하면 볼 수 없도록 한다.



#
## API
#
## /api/admin/(관리자 기능 api)
##
### 관리자 메인 페이지
###
#### autoLogin : 자동 로그인 기능
#### login/ : 로그인 기능
#### summary/ : 전체 유저 수를 반환
#### summaryNewUserPerDay/ : 현재로부터 2주전 까지의 신규유저수를 반환
#### showUnreadOrders/ : 관리자가 읽지 않은 주문들을 불러옵니다.

##
### 유저 관리 페이지(manage/user/)
###
#### /: 초기 페이지 접근 시 페이징 기반으로 유저 정보들을 리턴 (page=0, size=100, sort=id  DESC)
#### search/ :  유저의 데이터를 검색할 때 페이징 기능을 기반으로 리턴 (page=0, size, sort, 차순은 프론트에서 넘긴 값 기반)
#### update/ :  유저의 상태를 업데이트(N : 활성, Y : 휴면, B : 블랙리스트)

##
### 주문 관리 페이지(manage/order/)
###
#### / : 페이징 처리가 된 주문들을 조회하는 기능입니다.
#### search/ : 키워드를 입력하여 페이징 처리된 주문을 찾는 기능입니다.
#### update/ : 주문의 상태를 변경하는 기능입니다. 주문이 배송 중으로 변경 시 배송 시작시간을 추가하고 배송 완료로 변경 시 배송 완료시간이 추가됩니다.
#### detail/ : 주문을 클릭 시 주문의 상세 정보를 조회하는 기능입니다.

##
### 상품 관리 페이지
###
#### manage/product : 현재 등록되어 있는 상품을 조회하는 기능입니다. 
#### manage/product/search : 키워드를 입력하여 페이징 처리된 주문을 찾는 기능입니다. 
#### manage/product/duplicateValidation : isbn 번호를 입력했을 때 DB에 존재하면 isbn에 해당하는 데이터를 관리자에게 보여주고 없으면 등록하는 버튼과 함께 DB에 존재하지 않는다고 알려주는 기능입니다. 
#### manage/product/register : 중복되지 않는다고 나오면 등록하기 버튼을 통해 isbn이 서버로 전송되어 isbn에 해당하는 데이터들을 알라딘 api를 통해 불러와서 DB에 저장합니다.
#### manage/product/update : 상품의 절판 또는 구매 가능 여부를 설정할 수 있는 기능입니다. remain 컬럼을 활용해서 0 : 절판, 1 : 구매 가능 으로 플래그를 활용했습니다.


#
## /api/user/ (회원 기능 api)
##
### 회원가입 기능 (signUp/)
###
#### / : 회원가입
#### idValidation/ : 아이디 중복 검증
#### nicknameValidation/ : 닉네임 중복 검증
#### emailValidation/ : 이메일 중복 검증 후 인증번호 전송
#### email/auth/ : 전송한 인증번호 기반 이메일 인증
#### deleteExpiredVerificationCode/ DB에 임시 저장한 사용자가 입력한 이메일과 매핑되는 인증코드를 삭제합니다.
#
### 로그인 로그아웃 기능
###
#### logIn/ : 로그인 성공 시 JWT 토큰들을 받아옵니다.
#### logOut/ : 로그아웃 시 JWT 토큰들을 반환합니다.

#
### 본인 정보 수정 & 열람 기능 (myPage/)
###
#### / : 본인 정보 가져오기 기능
#### auth/ : 본인 정보 가져오기 전 암호 인증 기능
#### update/ : 본인 정보 업데이트 기능
#### orderList/ : 본인 주문 이력 열람 기능

# 
### 유저 탈퇴 & 복귀 기능 (이 시스템은 바로 삭제되지 않고 2주의 휴면기간이 주어집니다. 그 후에 완전 삭제됩니다.)
###
#### withdrawal/ : 활성 계정에서 휴면 계정으로 전환하는 기능
#### dormantAccount/ : 휴면 계정에서 활성 계정으로 전환하는 기능


#
### 아이디 & 암호 찾기 기능 (findMyInfo/)
###
#### byEmail/ : 아이디 찾기에서 가입된 이메일을 입력 시 인증코드 전송
#### byEmail/auth/ : 아이디 찾기에서 전송된 인증코드를 검사 후 인증 시 유저 아이디를 리턴하는 기능(사용자에게 보여주기 위함)
#### byEmailAndId/ : 비밀번호 찾기에서 가입된 아이디와 이메일을 입력 시 인증코드 전송
#### byEmailAndId/auth/ : 암호 찾기에서 전송된 인증코드를 검사 후 인증 시 유저 고유 코드를 리턴하는 기능(유저 고유코드는 이 다음 과정의 암호 재설정에서 유저 고유코드에 해당하는 암호를 바꾸기 위해 사용)
#### resetAndModifyPassword/ : 이 전에서 받은 유저 고유코드와 입력한 새로운 암호를 update하는 기능

#
### 결제 기능(purchase/)
#### validateProductIsExist/ : 관리자가 품절 처리를 해버려도 프론트단에서 새로고침 하지 않는 이상 품절 처리되었다는 뷰가 보이지 않기 때문에 유저가 상품을 담거나 결제할 가능성이 있기 때문에 먼저 상품의 존재 여부를 검증 절차를 거친다.
#### userInfo/ : 토큰 속 userNumber(로그인 되어있는 계정의 userNumber)를 DB와 비교해서 해당하는 주소, 연락처, 이름을 불러온다.
#### details/ : 세션스토리지로 넘긴 isbn 넘버를 DB와 비교해 해당하는 책 정보(책 커버, 제목, 가격 등)를 불러온다.
#### purchasedList/ : 사용자가 구매한 주문을 저장한다(주문자 정보(이름 연락처 등), 주문 관련 내용(주문 코드, 주문 명, 배송지 등), 상세 주문 내용(책 내용, 책 각각의 가격, 수량 등))
#### result/ : 구매 후 사용자가 구매한 상품을 한 번만 보여준다.
#### cancel/ : 배송과정이 배송 중 전까지 사용자가 주문을 취소할 수 있다.


#
## API
#
## /api/admin/(관리자 기능 api)
