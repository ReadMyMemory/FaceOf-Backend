# FaceOf_test
상상기업 페이스오브 프로토타입





# 요구사항 정리 (매우매우매우 간소화 하기)

1. 유저가 웹서비스에 접속함 (완료)


2. 유저가 이미 가지고 있는 사진을 업로드 함 (완료)


3. 업로드된 사진에서 유의미한 데이터를 추출함 (OpenCV에서 막힘, 이미지를 OpenCv에 던져주는것 까지는 가능한데 OpenCV를 깔아서 import 해와야함)


4. 관상 분석 함수를 만들어서 돌림


5. 함수 만들 자신이 없으면 그냥 챗gpt openapi에다가 룰과 관상 규칙을 알려줘서 좀 학습시키고 추출한 데이터를 전송하면 관상을 분석한 문장까지 완성 시켜서 돌려주는 방식


6. 유저에게 관상분석 결과를 알려줄 페이지를 보여줌


# 데이터 베이스?

DB없어도 가능할듯

DB가 없으니 entity, repository도 필요 없을듯




# 환경설정 기록
스프링 부트 설정(Spring initializr)

스프링부트 2.7.17 (3.xx 넘어가면 따로 설정해줄게 많다고 함)
자바 11 (이상하게 8이 안먹음)


OpenCV 4.8.0 



### Dependencies

__Lombok__ 

__spring web__ 

__thymeleaf__



### 인텔리제이 설정

Setting => Gradle 검색 => bulid run, test 모두 Gradle에서 인텔리제이로 변경



### resources

index.html => 초기 페이지, 여기서 이미지 업로드, static

result.html => 관상 결과 분석할 페이지, templates
