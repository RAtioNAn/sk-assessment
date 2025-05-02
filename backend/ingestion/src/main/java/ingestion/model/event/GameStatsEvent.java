package ingestion.model.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GameStatsEvent(
        UUID eventId,
        Long timestamp,
        String gameId,
        String teamId,
        String playerId,
        int points,
        int rebounds,
        int assists,
        int steals,
        int blocks,
        int fouls,
        double turnovers,
        double minutes_played) {
}
