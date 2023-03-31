# java-chess

체스 미션 저장소

# 기능 목록

## DB

```sql
drop database chess;
create database chess;
use chess;

create table chess.game
(
    id          int auto_increment
        primary key,
    is_finished tinyint(1)   not null,
    turn        varchar(100) not null
);

create table chess.piece
(
    game_id int          not null,
    file    varchar(100) not null,
    `rank`  varchar(100) not null,
    type    varchar(100) not null,
    team    varchar(100) not null,
    constraint piece_game_id_fk
        foreign key (game_id) references chess.game (id)
);
```

- 게임을 저장한다
    - 기물을 저장한다
    - 게임 상태를 저장한다
- 게임을 업데이트한다.
    - 기물들을 업데이트한다.
    - 게임 상태를 업데이트한다.
- 게임을 불러온다
- 게임을 끝낸다.
- 최근에 안끝난 게임이 있는지 알 수 있다
- 최근에 안끝난 게임 Id를 가져온다

## 도메인

- 게임
    - 턴을 가진다
        - 백부터 시작한다.
        - 한 수마다 턴을 바꾼다
        - 자신의 턴에는 자신의 기물만 움직일 수 있다.
    - 체스판에서 기물을 움직인다
    - 킹이 잡히면 게임을 종료한다.
        - 왕이 없으면 게임을 진행할 수 없다
    - 각 팀의 점수를 가져올 수 있다.
    - 이긴 팀을 알 수 있다.
- 체스판
    - 체스판 시작 배열로 초기화한다
    - 위치 별 기물을 가진다
    - 기물을 움직인다
        - 목표 위치에 아군이 있으면 움직일 수 없다
        - 기물이 갈 수 없는 수로 움직일 수 없다
        - 장애물을 뛰어넘을 수 없다.
        - 목표 위치에 다른 색의 기물이 있으면 대체한다
    - 남은 기물들의 점수를 계산한다
        - 폰이 세로로 2개이상 있다면, 각각 기본점수의 반절로 친다.
    - 어떤 팀의 왕이 존재하는지 알 수 있다
    - 특수 룰
        - [ ] 폰이 상대진영 끝까지 도달하면 퀸으로 교체된다
- 기물
    - 폰, 비숍, 나이트, 룩, 퀸, 킹이 있다.
        - 하단 기물 클래스다이어그램 참조
    - 이동 가능한 수인지 판단한다
    - 공격 가능한 수인지 판단한다
    - 팀을 구분한다
    - 기본적으로 한 수 단위로 움직인다.
    - `Touched`(한 번 이상 움직인) 상태로 만든다.
    - 기물이 존재하는지 알 수 있다.
    - 기본 점수를 알 수 있다.
- 기물별 특성
    - 폰
        - 처음에는 1칸 혹은 2칸 앞으로 움직일 수 있다.
        - 첫 수 이후 1칸 앞으로 움직일 수 있다.
        - 공격은 앞대각선으로 할 수 있다.
            - 앞 방향으로는 공격할 수 없다
        - 흑은 아래방향, 백은 윗방향으로 움직인다.
    - 나이트
        - 한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다.
    - 킹
        - 가로/세로/대각선 한 칸 움직일 수 있다.
    - 비숍
        - 대각선 무한으로 움직일 수 있다.
    - 룩
        - 가로/세로 무한으로 움직일 수 있다.
    - 퀸
        - 가로/세로/대각선 무한으로 움직일 수 있다.
    - 빈 기물
        - 비어있는 기물이다.
        - 움직일 수 없다.
        - 어떤 팀도 아니다.
- 위치
    - 이동할 수 있다
    - 다른 위치까지의 방향들을 알 수 있다
- 수
    - 시작위치, 도착위치로 수를 만들 수 있다.
    - 양방향이 존재하면 예외를 던진다.
    - 수끼리 비교할 수 있다.
    - 단위 수를 가져온다.
    - 목적지를 찾을 수 있다.
- 방향들(방향 컬렉션)
    - 수직이나 수평으로 양방향이면 예외를 던진다.
    - 목적지를 찾을 수 있다.
    - 방향 컬렉션끼리 합칠 수 있다.
    - 최소 단위로 분할할 수 있다.
- 방향
    - 상하좌우
- File
    - A~H
    - 좌우로 이동 할 수 있다
        - 범위를 벗어난 이동은 예외를 던진다
- Rank
    - 1~8
    - 위 아래로 이동 할 수 있다
        - 범위를 벗어난 이동은 예외를 던진다

### 기물 클래스 다이어그램

```mermaid
classDiagram
    Piece <-- Pawn
    Piece <-- Knight
    Piece <-- King
    InfinitePiece <-- Queen
    InfinitePiece <-- Bishop
    InfinitePiece <-- Rook
    Piece <-- InfinitePiece

    <<abstract>> Piece
    <<abstract>> InfinitePiece
```

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
