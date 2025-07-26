#!/bin/bash

WALLET_ID="123e4567-e89b-12d3-a456-426614174000"
ROUNDS=50

for i in $(seq 1 $ROUNDS); do
  curl -s -X POST http://localhost:8080/api/v1/wallet \
    -H "Content-Type: application/json" \
    -d "{\"walletId\":\"$WALLET_ID\", \"operationType\":\"DEPOSIT\", \"amount\":1}" &
done

wait
echo "Done. Now check the balance:"
curl -s http://localhost:8080/api/v1/wallets/$WALLET_ID

