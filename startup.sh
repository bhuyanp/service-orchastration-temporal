echo "## Building and publishing Service Common..."
cd service-common || exit
./gradlew clean publishToMavenLocal || exit

printf '\n'
echo "## Building and Order Event Interface..."
cd ../order-event-interface || exit
./gradlew clean publishToMavenLocal || exit

printf '\n'
echo "## Building client sdk for Inventory Service..."
cd ../inventory-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for Inventory Service..."
./gradlew publishToMavenLocal || exit


printf '\n'
echo "## Building client sdk for Notification Service..."
cd ../notification-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for Notification Service..."
./gradlew publishToMavenLocal || exit


printf '\n'
echo "## Building client sdk for Order Service..."
cd ../order-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for Order Service..."
./gradlew publishToMavenLocal || exit


printf '\n'
echo "## Building client sdk for Payment Service..."
cd ../payment-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for Payment Service..."
./gradlew publishToMavenLocal || exit


printf '\n'
echo "## Building client sdk for Shipping Service..."
cd ../shipping-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for Shipping Service..."
./gradlew publishToMavenLocal || exit


printf '\n'
echo "## Starting docker containers..."
docker compose up -d
