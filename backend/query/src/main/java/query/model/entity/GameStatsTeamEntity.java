package query.model.entity;

import lombok.Builder;

@Builder
public record GameStatsTeamEntity(String teamId,
                                  double points,
                                  double rebounds,
                                  double assists,
                                  double steals,
                                  double blocks,
                                  double fouls,
                                  double turnovers,
                                  double minutes_played) {
}
