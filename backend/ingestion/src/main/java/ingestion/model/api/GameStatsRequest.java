package ingestion.model.api;

import lombok.Builder;

@Builder
public record GameStatsRequest(
        String gameId,
        String playerId,
        int points,
        int rebounds,
        int assists,
        int steals,
        int blocks,
        int fouls,
        int turnovers,
        double minutes_played) {
}
