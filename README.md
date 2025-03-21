# ScheduleHubProject
## API
|기능|Method|URL|request|response|상태 코드|
|:------|:---|:---|:---|:---|:---|
|일정 등록|POST|/ScheduleHub|요청 body<br>{<br>“title” : “제목”<br>“conbtents” : “내용”<br>“name” : “이름”<br>“password” : “비밀번호”<br>}|등록 정보|200: 정상등록|
|일정 단건 조회|GET|/ScheduleHub/{schedule_id}|요청 param|단건 응답 정보<br>{<br>“title” : “제목”<br>“contents” : “내용”<br>“name” : “이름”<br>}|200: 정상조회|
|일정 전체 조회|GET|/ScheduleHub|요청 param|다건 응답 정보|200: 정상조회|
|조건에 맞는 일정 조회 |GET|/ScheduleHub/filter/{schedule_id}&{time_filter}|요청 param|다건 응답 정보|200: 정상조회|
|일정 수정|PUT|/ScheduleHub/{schedule_id}|요청 body<br>{<br>“title” : “수정 제목”<br>“conbtents” : “수정 내용”<br>“name” : “이름”<br>“password” : “비밀번호”<br>}|수정 정보|200: 정상수정|
|일정 삭제|DELETE|/ScheduleHub/{schedule_id}|요청 param|-|200: 정상삭제|

## ERD
![화면 캡처 2025-03-21 111535](https://github.com/user-attachments/assets/1a1b1761-581b-4842-b40c-0343658e4e0c)
