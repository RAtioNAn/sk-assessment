package query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import query.model.api.GameStatsPlayerResponse;
import query.model.api.GameStatsTeamResponse;
import query.service.GameStatsQueryService;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

@RestController
@RequestMapping("/api/query")
public class GameStatsQueryController {

    @Autowired
    private GameStatsQueryService gameStatsQueryService;

    @GetMapping("/stats/player/{id}")
    public GameStatsPlayerResponse getGameStatsPlayer(@PathVariable("id") String playerId) {

        return gameStatsQueryService.getPlayerStats(playerId);
    }

    @GetMapping("/stats/team/{id}")
    public GameStatsTeamResponse getGameStatsTeam(@PathVariable("id") String teamId) {

        return gameStatsQueryService.getTeamStats(teamId);
    }

}
