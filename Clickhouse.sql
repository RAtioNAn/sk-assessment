CREATE TABLE game_stats
(
    eventId        UUID,
    timestamp      Int64,
    gameId         String,
    playerId       String,
    teamId         String,
    points         Int8,
    rebounds       Int8,
    assists        Int8,
    steals         Int8,
    blocks         Int8,
    fouls          Int8,
    turnovers      Float64,
    minutes_played Float64
) ENGINE = MergeTree
ORDER BY (gameId, playerId, teamId);

CREATE TABLE game_stats_stream
(
    eventId        UUID,
    timestamp      Int64,
    gameId         String,
    playerId       String,
    teamId         String,
    points         Int8,
    rebounds       Int8,
    assists        Int8,
    steals         Int8,
    blocks         Int8,
    fouls          Int8,
    turnovers      Float64,
    minutes_played Float64
) ENGINE = Kafka()
      SETTINGS
          kafka_broker_list = 'kafka:9094',
          kafka_topic_list = 'events.raw',
          kafka_group_name = 'clickhouse',
          kafka_format = 'JSONEachRow',
          kafka_num_consumers = 1,
          kafka_max_block_size = 1,
          kafka_skip_broken_messages = 1,
          kafka_commit_every_batch = 0,
          kafka_thread_per_consumer = 1;

CREATE MATERIALIZED VIEW game_stats_mv
    TO game_stats AS
SELECT * FROM game_stats_stream;