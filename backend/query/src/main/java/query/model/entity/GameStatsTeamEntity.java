package query.model.entity;

import lombok.Builder;

@Builder
public record GameStatsTeamEntity(String teamId,
                                  double avgPoints,
                                  double avgRebounds,
                                  double avgAssists,
                                  double avgSteals,
                                  double avgBlocks,
                                  double avgFouls,
                                  double avgTurnovers,
                                  double avgMinutes_played) {
}
