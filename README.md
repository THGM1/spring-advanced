# 붉은 달의 성채 게임 저장 서버 구현

Spring Boot + MySQL 기반 게임 진행 저장 API

## 기술 스택

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL 8 (Docker)
- Gradle

## API 명세

| Method | URL | 설명 |
|---|---|---|
| POST | /games | 새 게임 생성 |
| GET | /games | 게임 목록 조회 |
| GET | /games/{gameId} | 게임 상세 조회 |
| PUT | /games/{gameId} | 플레이어 이름 변경 |
| PUT | /games/{gameId}/progress | 게임 진행 저장 |
| DELETE | /games/{gameId} | 게임 삭제 |

## 프로젝트 구조

```
com.gamebasic
├── common          # 공통 예외, 에러 응답
├── game            # 게임 도메인 (Controller, Service, Repository, Entity, DTO)
└── runcard         # 카드 도메인 (Repository, Entity, DTO)
```

## 트러블슈팅

### 1. `Unknown column 'g1_0.created_at' in 'field list'`
- **원인**: Auditing 필드(`createdAt`, `updatedAt`)를 엔티티에 추가했지만,
  `ddl-auto=update` 설정으로도 기존 테이블에 컬럼이 자동 반영되지 않음
- **해결**: 로컬 MySQL의 `games`, `run_cards` 테이블을 드롭한 뒤 서버를 재시작해
  Hibernate가 최신 엔티티 구조로 테이블을 새로 생성하도록 함

### 2. 게임 삭제 시 FK 제약 위반
- **원인**: `RunCard`가 `Game`을 단방향으로 참조하는 구조에서, 연결된 카드가
  남아있는 상태로 `Game`을 먼저 삭제하려다 외래키 제약조건 위반 발생
- **해결**: `deleteGame`에서 `RunCard`를 먼저 전부 삭제한 뒤 `Game`을 삭제하도록 순서 조정
