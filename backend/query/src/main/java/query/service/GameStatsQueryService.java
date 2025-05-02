package query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import query.model.api.GameStatsPlayerResponse;
import query.model.api.GameStatsTeamResponse;
import query.repository.GamesStatsQueryRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

@Service
public class GameStatsQueryService {

    @Autowired
    private GamesStatsQueryRepository repository;

    public GameStatsPlayerResponse getPlayerStats(String playerId) {

        var playersStats = repository.getGameStatsPerPlayer(playerId);

        return GameStatsPlayerResponse
                .builder()
                .playersStats(playersStats)
                .build();
    }

    public GameStatsTeamResponse getTeamStats(String teamId) {

        var teamStats = repository.getGameStatsPerTeam(teamId);

        return GameStatsTeamResponse
                .builder()
                .teamsStats(teamStats)
                .build();
    }

}
