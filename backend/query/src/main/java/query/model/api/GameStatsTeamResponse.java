package query.model.api;

import lombok.Builder;
import query.model.entity.GameStatsTeamEntity;

import java.util.List;

@Builder
public record GameStatsTeamResponse(List<GameStatsTeamEntity> teamsStats) {
}
