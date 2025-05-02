package query.model.api;

import lombok.Builder;
import query.model.entity.GameStatsPlayerEntity;

import java.util.List;

@Builder
public record GameStatsPlayerResponse(List<GameStatsPlayerEntity> playersStats) {
}
