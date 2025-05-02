package query.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import query.model.entity.GameStatsPlayerEntity;
import query.model.entity.GameStatsTeamEntity;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

@Repository
public class GamesStatsQueryRepository {

    private static final String PLAYER_QUERY = """
            SELECT
              playerId,
              teamId,
              avg(points) AS avg_points,
              avg(rebounds) AS avg_rebounds,
              avg(assists) AS avg_assists,
              avg(steals) AS avg_steals,
              avg(blocks) AS avg_blocks,
              avg(fouls) AS avg_fouls,
              avg(turnovers) AS avg_turnovers,
              avg(minutes_played) AS avg_minutes
            FROM game_stats
            GROUP BY playerId, teamId
            HAVING playerId == '%s';
            """.replace("\n", " ");

    private static final String TEAM_QUERY = """
            SELECT
              teamId,
              avg(points) AS avg_points,
              avg(rebounds) AS avg_rebounds,
              avg(assists) AS avg_assists,
              avg(steals) AS avg_steals,
              avg(blocks) AS avg_blocks,
              avg(fouls) AS avg_fouls,
              avg(turnovers) AS avg_turnovers,
              avg(minutes_played) AS avg_minutes
            FROM game_stats
            GROUP BY teamId
            HAVING teamId == '%s';
            """.replace("\n", " ");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GameStatsPlayerEntity> getGameStatsPerPlayer(String playerId) {
        var query = String.format(PLAYER_QUERY, playerId);


        return jdbcTemplate.query(query, (rs, rowNum) ->
                GameStatsPlayerEntity.builder()
                        .teamId(rs.getString("teamId"))
                        .playerId(rs.getString("playerId"))
                        .avgPoints(rs.getDouble(("avg_points")))
                        .avgRebounds(rs.getDouble(("avg_rebounds")))
                        .avgAssists(rs.getDouble(("avg_assists")))
                        .avgSteals(rs.getDouble(("avg_steals")))
                        .avgBlocks(rs.getDouble(("avg_blocks")))
                        .avgFouls(rs.getDouble(("avg_fouls")))
                        .avgTurnovers(rs.getDouble(("avg_turnovers")))
                        .avgMinutes_played(rs.getDouble(("avg_minutes")))
                        .build()

        );
    }

    public List<GameStatsTeamEntity> getGameStatsPerTeam(String teamId) {
        var query = String.format(TEAM_QUERY, teamId);

        return jdbcTemplate.query(query, (rs, rowNum) ->
                GameStatsTeamEntity.builder()
                        .teamId(rs.getString("teamId"))
                        .avgPoints(rs.getDouble(("avg_points")))
                        .avgRebounds(rs.getDouble(("avg_rebounds")))
                        .avgAssists(rs.getDouble(("avg_assists")))
                        .avgSteals(rs.getDouble(("avg_steals")))
                        .avgBlocks(rs.getDouble(("avg_blocks")))
                        .avgFouls(rs.getDouble(("avg_fouls")))
                        .avgTurnovers(rs.getDouble(("avg_turnovers")))
                        .avgMinutes_played(rs.getDouble(("avg_minutes")))
                        .build()

        );

    }

}
