package ingestion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ingestion.model.api.GameStatsRequest;
import ingestion.model.event.GameStatsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

@Service
public class GameStatsService {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${spring.kafka.topics.events}")
    private String eventTopic;

    ObjectMapper objectMapper = new ObjectMapper();

    public SendResult<String, String> sendEvent(GameStatsRequest stats) throws JsonProcessingException {

        var event = GameStatsEvent.builder()
                .eventId(UUID.randomUUID())
                .timestamp(Instant.now().toEpochMilli())
                .gameId(stats.gameId())
                .teamId(stats.teamId())
                .playerId(stats.playerId())
                .assists(stats.assists())
                .blocks(stats.blocks())
                .fouls(stats.fouls())
                .points(stats.points())
                .rebounds(stats.rebounds())
                .steals(stats.steals())
                .turnovers(stats.turnovers())
                .minutes_played(stats.minutes_played())
                .build();

        var stringEvent = objectMapper.writeValueAsString(event);
        var result = template.send(eventTopic, event.eventId().toString(), stringEvent);

        return result.join();
    }

}
