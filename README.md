# ScheduleHubProject
## API
|기능|Method| URL                      | request                                                                                                 |response|상태 코드|
|:------|:---|:-------------------------|:--------------------------------------------------------------------------------------------------------|:---|:---|
|일정 등록|POST| /ScheduleHub             | 요청 body<br>{<br>“title” : “제목”<br>“conbtents” : “내용”<br>“name” : “이름”<br>“password” : “비밀번호”<br>}       |등록 정보|200: 정상등록|
|일정 단건 조회|GET| /ScheduleHub/{schedule_id}| 요청 param                                                                                                |단건 응답 정보<br>{<br>“title” : “제목”<br>“contents” : “내용”<br>“name” : “이름”<br>}|200: 정상조회|
|일정 전체 조회|GET| /ScheduleHub             | 요청 param                                                                                                |다건 응답 정보|200: 정상조회|
|조건에 맞는 일정 조회 |GET| /ScheduleHub/filter      | 요청 param<br> 요청 body <br>{"scheduleId": "schedule 식별자"<br> "timeFilter": **"EnumString"**}              |다건 응답 정보|200: 정상조회|
|일정 수정|PUT| /ScheduleHub/{schedule_id}| 요청 body<br>{<br>“title” : “수정 제목”<br>“conbtents” : “수정 내용”<br>“name” : “이름”<br>“password” : “비밀번호”<br>} |수정 정보|200: 정상수정|
|일정 삭제|DELETE| /ScheduleHub/{schedule_id}| 요청 param <br> 요청 body <br>{"password": "비밀번호"}                                                          |-|200: 정상삭제|

### EnumString
    * WITHIN_1_HOUR
        - 1시간 전
    * WITHIN_3_HOURS
        - 3시간 전
    * WITHIN_1_DAY
        - 1일 전
    * WITHIN_1_WEEK
        - 1주일 전
    * WITHIN_1_MONTH
        - 1달 전


## ERD
![화면 캡처 2025-03-21 111535](https://github.com/user-attachments/assets/1a1b1761-581b-4842-b40c-0343658e4e0c)

## controller
* api 참고

## service
* saveSchedule - 일정 생성 기능
* findScheduleById - 일정 단건 조회 기능
* findAllSchedule - 일정 전체 조회 기능
* findFilteredSchedule - 일정 필터링 기능
* updateSchedule - 일정 업데이트 기능
* deleteSchedule - 일정 삭제 기능

## repository
* saveSchedule - DB에 일정 저장
* findScheduleById - where schedule_id = scheduleId
* findAllSchedule
* findFilteredSchedule
* getPassword - password 검사
* updateSchedule - 일정 수정, 반환 값은 수정 row 수
* deleteSchedule - 일정 삭제, 반환 값은 삭제 row 수

## entity
* TimeFilter 
  * EnumString 저장 및 사용자의 선택 시간 반환
  * ex) 현재시간 3:00 , WITHIN_1_HOUR 선택 -> 2:00 반환
* FilterNameUpdateAt
  * 필터링에 사용될 시간과 이름을 저장하는 entity
* Schedule
  * schedule 정보를 임시 저장할 entity
  * service <-> repository 에서 사용

## dto
* controller <-> service 에서 사용

---
# troubleshooting


### findFilteredSchedule 구현
* 발단
  * 시간을 @requestbody로 입력 받아 연산을 통해 필터링된 schedule을 반환하려고 하였다.
* 원인
  * 사용자의 입력을 분, 시간, 날짜, 주 등 단위에 따라 구분을 하거나 사용자의 입력에 제한을 하는 것이 필요 했다.
* 해결 과정
  * 사용자의 입력이 제한되어 들어온다면 해결하기 쉬울 것이라고 판단하였다. 
  * Enum을 활용하여 사용자의 입력을 조절
  * 시간을 직접 입력하는 것이 아닌 보기에서 선택하는 형식이라고 가정하고, 1시간 안, 1주일 안 등 보기의 기간으로 필터링 하였다.
  * 해결 과정에서 이외의 값이 들어올 시간 필터링을 수행하지 않는 것으로 하였는데, 예외 처리를 통해 값을 다시 받는 쪽으로 수정하는 것이 조금 더 좋아보인다.