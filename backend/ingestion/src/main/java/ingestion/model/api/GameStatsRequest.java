package ingestion.model.api;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record GameStatsRequest(
        @NotBlank String gameId,
        @NotBlank String playerId,
        @Min(0) @Max(6) int points,
        @Min(0) @Max(6) int rebounds,
        @Min(0) @Max(6) int assists,
        @Min(0) @Max(6) int steals,
        @Min(0) @Max(6) int blocks,
        @Min(0) @Max(6) int fouls,
        @DecimalMin("0.0") @DecimalMax("48.0") double turnovers,
        @DecimalMin("0.0") @DecimalMax("48.0") double minutes_played) {
}
