# store24h
 Store24h java-backEnd

## Passos para executar

- No inteliJ configure o tipo Application para executar
- Set Java SDK 15+
- Configure a conexão com o banco de dados
- `docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=change-me --restart unless-stopped mysql:8`

docker inspect mysql:8

docker logs mysql | grep root

docker exec -it mysql mysql -p

docker logs mysql --follow



