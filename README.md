# java-chess

## Step1, 2 기능 요구사항

### 기물 공통

- [x] 움직이지 않을 경우 예외가 발생한다.

### Pawn

- [x] 앞에 다른 기물이 없을 때 한 칸 이동한다.
    - [x] **[예외]** 앞에 다른 기물이 있을 때
    - [x] **[예외]** 두 칸 이상 이동할 때
- [x] 처음 위치일 때 앞으로 두 칸 이동할 수 있다.
    - [x] **[예외]** 앞에 다른 기물이 있을 때 (아군, 적군)
    - [x] **[예외]** 처음 위치가 아닐 때
    - 블랙 : 7 rank
    - 화이트 : 2 rank
- [x] 대각선에 상대 기물이 있으면 대각선으로 이동한다.
    - [x] **[예외]** 대각선에 상대 기물이 없을 때
    - [x] **[예외]** 두 칸 이상 이동할 때
- [x] 뒤로 이동할 수 없다.

### Knight

- [x] L자로 이동한다.
    - [x] **[예외]** L자로 이동하지 않을 때
- [x] 이동 경로에 다른 기물이 있어도 뛰어 넘을 수 있다.
- [x] 도착지에 상대 기물이 있으면 잡을 수 있다.
    - [x] **[예외]** 도착지에 아군 기물이 있을 때

### Bishop

- [x] 대각선으로 이동할 수 있다.
    - [x] **[예외]** 대각선으로 이동하지 않을 때
- [x] 이동 경로에 다른 기물이 있으면 이동할 수 없다.
- [x] 도착지에 상대 기물이 있으면 잡을 수 있다.
    - [x] **[예외]** 도착지에 아군 기물이 있을 때

### Rook

- [x] 수직, 수평으로 이동할 수 있다.
    - [x] **[예외]** 수직, 수평으로 이동하지 않을 때
- [x] 이동 경로에 다른 기물이 있으면 이동할 수 없다.
- [x] 도착지에 상대 기물이 있으면 잡을 수 있다.
    - [x] **[예외]** 도착지에 아군 기물이 있을 때

### Queen

- [x] 수평, 수직, 대각선으로 이동할 수 있다.
    - [x] **[예외]** 수평, 수직, 대각선으로 이동하지 않을 때
- [x] 이동 경로에 다른 기물이 있으면 이동할 수 없다.
- [x] 도착지에 상대 기물이 있으면 잡을 수 있다.
    - [x] **[예외]** 도착지에 아군 기물이 있을 때

### King

- [x] 수평, 수직, 대각선으로 한 칸 이동할 수 있다.
    - [x] **[예외]** 수평, 수직, 대각선으로 이동하지 않을 때
    - [x] **[예외]** 두 칸 이상 이동할 때
- [x] 도착지에 상대 기물이 있으면 잡을 수 있다.
    - [x] **[예외]** 도착지에 아군 기물이 있을 때

### 체스판

- [x] 포지션이 체스판 범위 내인지 확인한다.
    - [x] **[예외]** 범위를 벗어났을 때
- [x] 기물의 이동 경로에 다른 기물이 있는지 확인한다.
    - [x] **[예외]** 경로에 다른 기물이 있을 때
- [x] 자신의 턴인지 확인한다.
    - [x] **[예외]** 상대의 턴일 때
- [x] 기물을 이동한다.

### 체스판 팩토리

- [x] 기물을 생성한다.
- [x] 체스판을 생성한다.

### 커맨드

- [x] start는 게임을 시작한다.
- [x] end는 게임을 종료한다.
- [x] move는 기물을 이동한다.

### 입력

- [x] 커맨드를 입력받는다.

### 출력

- [x] 체스판을 출력한다.
    - [x] 아래(백, 소문자), 위(흑, 대문자)

### 추가 룰

- [ ] 프로모션 : 폰이 상대 마지막 진영까지 도달하면 다른 기물로 변경할 수 있다.
- [ ] 앙파상
- [ ] 캐슬링

## Step3, 4 기능 요구사항

### 체스 게임

- 게임이 시작될 때 체스판을 만든다.
  - DB에 저장된 데이터가 존재하면 불러온다.
  - DB에 저장된 데이터가 없으면 체스판을 새로 만든다.
- 체스 게임을 진행한다. 
- 게임 점수를 계산한다.  
- 게임을 종료하면 데이터를 갱신한다. 
    - 킹이 죽지 않았으면 현재 체스판 데이터를 모두 저장한다.
    - 킹이 죽으면 모든 테이터를 삭제한다.


### 점수 계산

- [x] 현재까지 남아 있는 말에 따라 점수를 계산한다.
    - [x] 각 팀의 점수를 따로 계산한다.
- [x] queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
- [x] pawn의 기본 점수는 1점이다.
    - [x] 같은 세로줄에 같은 색의 폰이 있는 경우 0.5점을 준다.
- [x] 킹이 없다면 0점이다.

### 커맨드

- start
    - [x] 체스판을 출력한다.
- move
    - [x] 말을 움직인다.
    - [x] 체스판을 출력한다. 
- status
    - [x] 각 팀의 점수를 출력한다.
- end
    - [ ] 게임을 종료한다.
    - [ ] 체스판을 출력한다. 
    - [ ] 각 팀의 점수를 출력한다. 
- (추가) 킹이 죽으면 게임은 자동으로 종료된다.

### DB CRUD 기능

- [ ] 기존 게임 데이터가 존재하는지 여부 조회
- [ ] 기존 게임 데이터를 불러오기
- [ ] 게임 데이터 갱신
    - [ ] 이전 게임 데이터 삭제
    - [ ] 현재 게임 데이터 저장 (피스들, 턴)
