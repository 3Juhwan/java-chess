package repository;

import db.JdbcTemplate;
import db.RowMapper;
import dto.RoomDto;

import java.util.List;
import java.util.Optional;

public class RoomDao {
    private static final String TABLE_NAME = "rooms";
    private static final String GAME_STATUES_TABLE_NAME = "game_states";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<RoomDto> rowMapper = (resultSet) -> new RoomDto(
            resultSet.getInt("room_id")
    );

    RoomDao() {
        this(new JdbcTemplate());
    }

    RoomDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(final RoomDto roomDto) {
        final String insertQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?)";
        jdbcTemplate.execute(insertQuery, "" + roomDto.room_id());
    }

    public Optional<RoomDto> addNewRoom() {
        final String selectQuery = "SELECT MAX(room_id) AS room_id FROM " + TABLE_NAME;
        List<RoomDto> roomDtos = jdbcTemplate.executeAndGet(selectQuery, rowMapper);
        final String insertQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?)";
        final String roodId = String.valueOf(roomDtos.get(0).room_id() + 1);
        jdbcTemplate.execute(insertQuery, roodId);
        return Optional.ofNullable(roomDtos.get(0));
    }

    public Optional<RoomDto> find(final String roomId) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE room_id = ?";
        List<RoomDto> roomDtos = jdbcTemplate.executeAndGet(query, rowMapper, roomId);
        return Optional.of(roomDtos.get(0));
    }

    public List<RoomDto> findActiveRoomAll() {
        final String query = "SELECT * FROM " + TABLE_NAME + " AS r JOIN "
                + GAME_STATUES_TABLE_NAME + " AS s ON r.room_id = s.game_id WHERE s.state != 'GAMEOVER'";
        return jdbcTemplate.executeAndGet(query, rowMapper);
    }
}
