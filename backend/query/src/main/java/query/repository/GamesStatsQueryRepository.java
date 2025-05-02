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
            GROUP BY playerId;
            """.stripIndent();

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
            GROUP BY teamId;
            """.stripIndent();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GameStatsPlayerEntity> getGameStatsPerPlayer(String playerId) {

        return jdbcTemplate.query(PLAYER_QUERY, (rs, rowNum) ->
                GameStatsPlayerEntity.builder()
                        .teamId(rs.getString("teamId"))
                        .playerId(rs.getString("playerId"))
                        .points(rs.getDouble(("avg_points")))
                        .rebounds(rs.getDouble(("avg_rebounds")))
                        .assists(rs.getDouble(("avg_assists")))
                        .steals(rs.getDouble(("avg_steals")))
                        .blocks(rs.getDouble(("avg_blocks")))
                        .fouls(rs.getDouble(("avg_fouls")))
                        .turnovers(rs.getDouble(("avg_turnovers")))
                        .minutes_played(rs.getDouble(("avg_minutes")))
                        .build()

        );
    }

    public List<GameStatsTeamEntity> getGameStatsPerTeam(String teamId) {

        return jdbcTemplate.query(PLAYER_QUERY, (rs, rowNum) ->
                GameStatsTeamEntity.builder()
                        .teamId(rs.getString("teamId"))
                        .points(rs.getDouble(("avg_points")))
                        .rebounds(rs.getDouble(("avg_rebounds")))
                        .assists(rs.getDouble(("avg_assists")))
                        .steals(rs.getDouble(("avg_steals")))
                        .blocks(rs.getDouble(("avg_blocks")))
                        .fouls(rs.getDouble(("avg_fouls")))
                        .turnovers(rs.getDouble(("avg_turnovers")))
                        .minutes_played(rs.getDouble(("avg_minutes")))
                        .build()

        );

    }

}
