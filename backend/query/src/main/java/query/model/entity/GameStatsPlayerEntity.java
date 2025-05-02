package query.model.entity;

import lombok.Builder;

@Builder
public record GameStatsPlayerEntity(String teamId,
                                    String playerId,
                                    double avgPoints,
                                    double avgRebounds,
                                    double avgAssists,
                                    double avgSteals,
                                    double avgBlocks,
                                    double avgFouls,
                                    double avgTurnovers,
                                    double avgMinutes_played) {
}
