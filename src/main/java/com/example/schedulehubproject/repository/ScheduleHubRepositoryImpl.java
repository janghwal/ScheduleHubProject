package com.example.schedulehubproject.repository;


import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.FilterNameUpdateAt;
import com.example.schedulehubproject.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class ScheduleHubRepositoryImpl implements ScheduleHubRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleHubRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ScheduleHubResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Schedule").usingGeneratedKeyColumns("schedule_id").usingColumns("title", "contents", "name", "password");;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("name", schedule.getName());
        parameters.put("password", schedule.getPassword());

        Number key = simpleJdbcInsert.executeAndReturnKey(parameters);

        List<List<Timestamp>> writtenAt = jdbcTemplate.query("SELECT create_at, update_at FROM Schedule WHERE schedule_id = ?", scheduleHubSaveRowMapper(), key);


        return new ScheduleHubResponseDto(
                key.longValue(),
                schedule.getName(),
                schedule.getTitle(),
                schedule.getContents(),
                writtenAt.get(0).get(0).toLocalDateTime(),
                writtenAt.get(0).get(1).toLocalDateTime()
        );
    }

    @Override
    public Optional<Schedule> findScheduleById(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select schedule_id, name, title, contents, create_at, update_at from schedule where schedule_id = ?", scheduleHubFindByIdRowMapper(), scheduleId);
        return result.stream().findAny();
    }

    @Override
    public List<ScheduleHubResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select schedule_id, name, title, contents, create_at, update_at from schedule order by update_at desc", scheduleHubFindAllRowMapper());
    }

    @Override
    public List<ScheduleHubResponseDto> findFilteredSchedule(FilterNameUpdateAt filterNameUpdateAt) {
        String sql;
        if(filterNameUpdateAt.getName() == null){
            sql= "select schedule_id, name, title, contents, create_at, update_at from schedule where update_at >= ?";
            return jdbcTemplate.query(sql, scheduleHubFindFilteredRowMapper(), filterNameUpdateAt.getFilteredTime());
        }else if(filterNameUpdateAt.getFilteredTime() == null){
            sql= "select schedule_id, name, title, contents, create_at, update_at from schedule where name = ?";
            return jdbcTemplate.query(sql, scheduleHubFindFilteredRowMapper(), filterNameUpdateAt.getName());
        }else{
            sql= "select schedule_id, name, title, contents, create_at, update_at from schedule where update_at > ? AND name = ?";
            return jdbcTemplate.query(sql, scheduleHubFindFilteredRowMapper(), filterNameUpdateAt.getFilteredTime(), filterNameUpdateAt.getName());
        }
    }

    private RowMapper<ScheduleHubResponseDto> scheduleHubFindFilteredRowMapper() {
        return new RowMapper<ScheduleHubResponseDto>() {
            @Override
            public ScheduleHubResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleHubResponseDto(
                        new Schedule(
                                rs.getLong("schedule_id"),
                                rs.getString("name"),
                                rs.getString("title"),
                                rs.getString("contents"),
                                rs.getTimestamp("create_at"),
                                rs.getTimestamp("update_at")
                        )
                );
            }
        };
    }

    private RowMapper<ScheduleHubResponseDto> scheduleHubFindAllRowMapper() {
        return new RowMapper<ScheduleHubResponseDto>() {
            @Override
            public ScheduleHubResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleHubResponseDto(
                        new Schedule(
                                rs.getLong("schedule_id"),
                                rs.getString("name"),
                                rs.getString("title"),
                                rs.getString("contents"),
                                rs.getTimestamp("create_at"),
                                rs.getTimestamp("update_at")
                        )
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleHubFindByIdRowMapper() {
        return new RowMapper(){

            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("schedule_id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("create_at"),
                        rs.getTimestamp("update_at")
                );
            }
        };
    }

    // 일정 생성 시간, 일정 수정 시간을 리스트에 담아 리턴한다. => entity에 담아서 리턴 받는것이 더 좋은것인가?
    private RowMapper<List<Timestamp>> scheduleHubSaveRowMapper() {

        return new RowMapper<List<Timestamp>>() {
            @Override
            public List<Timestamp> mapRow(ResultSet rs, int rowNum) throws SQLException {
                List<Timestamp> writtenAt = new ArrayList<>();
                writtenAt.add(rs.getTimestamp("create_at"));
                writtenAt.add(rs.getTimestamp("update_at"));
                return writtenAt;
            }
        };
    }
}
