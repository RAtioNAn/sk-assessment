package query.model.entity;

import lombok.Builder;

@Builder
public record GameStatsPlayerEntity(String teamId,
                                    String playerId,
                                    double points,
                                    double rebounds,
                                    double assists,
                                    double steals,
                                    double blocks,
                                    double fouls,
                                    double turnovers,
                                    double minutes_played) {
}
