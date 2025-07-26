# Wallet App – Spring Boot REST API

Этот проект реализует простую систему кошелька с использованием Java 17, Spring Boot, PostgreSQL, Liquibase и Docker. Приложение поддерживает базовые операции пополнения и снятия средств, обработку ошибок, конкурентный доступ, миграции базы данных и контейнеризацию.

## Основные возможности

- `POST /api/v1/wallet` — пополнение (`DEPOSIT`) или снятие (`WITHDRAW`) с кошелька по UUID
- `GET /api/v1/wallets/{walletId}` — получение текущего баланса кошелька
- Обработка ошибок:
  - Неверный тип операции (например, `"FLY"`)
  - Нулевые или отрицательные суммы
  - Недостаточно средств
- Безопасность при одновременных запросах (pessimistic lock)
- Миграции базы данных с помощью Liquibase
- Контейнеризация через Docker и Docker Compose

## Технологии

- Java 17
- Spring Boot 3.1.x
- Maven 3.9.x
- PostgreSQL
- Liquibase
- Docker / Docker Compose

## Сборка и запуск

1. Собрать приложение:

   ```bash
   mvn clean package spring-boot:repackage -DskipTests
   ```

2. Запустить контейнеры:

   ```bash
   docker compose up --build
   ```

3. Тестирование через curl:

   Пополнение:

   ```bash
   curl -X POST http://localhost:8080/api/v1/wallet      -H "Content-Type: application/json"      -d '{"walletId":"<uuid>", "operationType":"DEPOSIT", "amount":1000}'
   ```

   Снятие:

   ```bash
   curl -X POST http://localhost:8080/api/v1/wallet      -H "Content-Type: application/json"      -d '{"walletId":"<uuid>", "operationType":"WITHDRAW", "amount":500}'
   ```

   Проверка баланса:

   ```bash
   curl http://localhost:8080/api/v1/wallets/<uuid>
   ```

4. Остановка приложения:

   ```bash
   docker compose down
   ```

## Тестирование

Юнит-тесты:

```bash
mvn clean test
```

Интеграционный тест `WalletControllerTest` проверяет корректную работу REST-слоя.

Для проверки устойчивости к конкуренции доступен скрипт `test-concurrency.sh`, отправляющий параллельные запросы:

```bash
chmod +x test-concurrency.sh
./test-concurrency.sh
```

## Очистка

```bash
docker compose down
rm -rf target
```

## Примеры ответов

Успешно:

```json
{"walletId":"...","balance":1000}
```

Ошибка операции:

```json
{"error":"Invalid operation type: FLY"}
```

Недостаточно средств:

```json
{"error":"Insufficient funds"}
```
