**Run test-app**

gradle run --args="localhost 12345 jdbc:postgresql://localhost:5432/db_test_app newuser password"

curl -X POST -H "Content-Type: application/json" -d  '{"data":"value"}' http://localhost:12345/