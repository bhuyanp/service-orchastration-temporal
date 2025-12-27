echo "## Building and publishing service common..."
cd service-common || exit
./gradlew clean publishToMavenLocal

printf '\n'
echo "## Building and order event interface..."
cd ../order-event-interface || exit
./gradlew clean publishToMavenLocal

printf '\n'
echo "## Building client sdk for inventory service..."
cd ../inventory-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for inventory service..."
./gradlew publishToMavenLocal


printf '\n'
echo "## Building client sdk for notification service..."
cd ../notification-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for notification service..."
./gradlew publishToMavenLocal


printf '\n'
echo "## Building client sdk for order service..."
cd ../order-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for order service..."
./gradlew publishToMavenLocal


printf '\n'
echo "## Building client sdk for payment service..."
cd ../payment-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for payment service..."
./gradlew publishToMavenLocal


printf '\n'
echo "## Building client sdk for shipping service..."
cd ../shipping-service || exit
./gradlew clean openApiGenerate


printf '\n'
echo "## Publishing client sdk for shipping service..."
./gradlew publishToMavenLocal


printf '\n'
echo "## Starting docker containers..."
docker compose up -d
