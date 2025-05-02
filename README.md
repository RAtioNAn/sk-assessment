### README

### Architecture overview
<br />

![architecture](Assessment.png)

<br />

##### Key components:
* __DNS service__ capable of balancing requests (Round-Robin, Geo, etc.).
* Multiple __load balancers__ reverse-proxying requests to backend API servers.
* __Ingestion__ service to process POST requests with game statistics.
* __Query__ service to process query requests.
* __Kafka__ to store events.
* __Clickhouse__ to store events projection and run analytics queries on data.

<br />

#### Design advantages:
* DNS service and load balancers provide HA.
* Kafka provides HA, fast writes of data and serves as a buffer allowing async data processing.
* Clickhouse provides data sharding, HA with replicas inside shards and fast aggregation queries on data.
* Each component may be scaled in/out independently on demand.
* Each component has clear boundaries and may be replaced with another solution.
* System is highly extensible. More functionality may be added just by adding new services reading data from kafka topic, etc.

<br />

#### Design disadvantages
* Increased operational complexity.
* Increased deployment costs.
* Some delay between posting data to server and seeing actualised data on query side (but delay is tolerable and depends on computation resources available).
In other word system is eventually consistent because of its async design.

### Running locally
<br />

#### Prerequisites
* Linux/MacOS
* Git
* Docker (Podman should do the job as well)
* JDK 21

<br />

#### Running
* Clone repo.
  ```shell
  git clone  https://github.com/RAtioNAn/sk-assessment.git
  ```
* Change working dir.
  ```shell
  cd sk-assessment/backend
  ```
* Build docker images for backend.
  ```shell
  ./gradlew ingestion:dockerBuildImage
  ./gradlew query:dockerBuildImage 
  ```
* Spin-up infra.
  ```shell
  cd ..
  docker-compose up -d kafka-init kafka-ui clickhouse-ui
  ```
* Goto localhost:8082 and login with the following credentials
  * Host - http://localhost:8123
  * Username - default
  * Password - default
  * Copy and paste queries from [Clickhouse.sql](Clickhouse.sql)
  * Run queries one by one in the same order they appear in the file.
  * ![Img1](img1.png)
  * ![Img2](img2.png)

* Spin-up backend.
  ```shell
  docker-compose up -d query ingestion
  ```
* Spin-up load-balancer.
  ```shell
  docker-compose up -d load-balancer
  ```
__PS__ If you have errors building images with gradle on Mac go to ./docker/config.json and remove __"credsStore" : "desktop"__ line.

<br />

#### Endpoints
* http://localhost:8080/api/ingest
* http://localhost:8080/api/query/stats/team/{teamId}
* http://localhost:8080/api/query/stats/player/{playerId}

#### Requests examples
* ```shell
  curl --location 'http://localhost:8080/api/ingest' \
  --header 'Content-Type: application/json' \
  --data '{
  "gameId": "game124",
  "playerId": "playerId145",
  "teamId" : "team123",
  "points": 2,
  "rebounds": 2,
  "assists": 5,
  "steals": 1,
  "blocks": 2,
  "fouls": 3,
  "turnovers": 2.0,
  "minutes_played": 36.5
  }'
  ```
* ```shell
  curl --location 'http://localhost:8080/api/query/stats/team/team123'
  ```
* ```shell
  curl --location 'http://localhost:8080/api/query/stats/player/player123'
  ```
  
<br />

### Deployment
* AWS Route53 for DNS
* AWS ALB for load balancing
* AWS EKS for workload
  * Dedicated node group for servers (t3, t4 family machines)
  * Dedicated node group for Clickhouse nodes with rb5 machines family and fast EBS (like io2 storage family).
  * Dedicated node group for Kafka nodes with rb5 machines family and fast EBS (like io2 storage family).
