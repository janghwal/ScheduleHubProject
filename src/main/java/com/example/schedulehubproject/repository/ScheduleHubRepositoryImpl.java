package com.example.schedulehubproject.repository;


import com.example.schedulehubproject.dto.ScheduleHubResponseDto;
import com.example.schedulehubproject.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
