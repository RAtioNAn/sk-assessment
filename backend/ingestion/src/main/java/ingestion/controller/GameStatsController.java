package ingestion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ingestion.model.api.GameStatsRequest;
import ingestion.service.GameStatsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

@Slf4j
@RestController
@RequestMapping("/api")
public class GameStatsController {

    @Autowired
    private GameStatsService gameStatsService;

    @PostMapping(value = "/ingest", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerDevice(@Valid @RequestBody GameStatsRequest sendRequest) {

        try {
            gameStatsService.sendEvent(sendRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().build();
    }

}
