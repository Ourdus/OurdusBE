package com.ourdus.protoourdus.user.repository;

import com.ourdus.protoourdus.user.model.UserVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class UserJdbcRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserVo insert(UserVo userVo) {
        //생성된 user_id값을 알아내기 위해 keyholder 이용 참고: https://preamtree.tistory.com/91
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(user_id, user_email, user_pw, user_name, user_tel) VALUES (null, ?, ?, ?, ?)",  new String[]{"user_id"});
            ps.setString(1, userVo.getUserEmail());
            ps.setString(2, userVo.getUserPw());
            ps.setString(3, userVo.getUserName());
            ps.setString(4, userVo.getUserTel());
            return ps;
        }, keyHolder);
        Long key = keyHolder.getKey().longValue();
        return new UserVo.Builder(userVo)
                .userId(key)
                .build();
    }

    @Override
    public Optional<UserVo> findByEmail(String userEmail) {
        List<UserVo> results = jdbcTemplate.query(
                "SELECT * FROM user WHERE user_email=?",
                new RowMapper<UserVo>() {
                    @Override
                    public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new UserVo.Builder()
                                .userId(rs.getLong("user_id"))
                                .userEmail(rs.getString("user_email"))
                                .userPw(rs.getString("user_pw"))
                                .userName(rs.getString("user_name"))
                                .userTel(rs.getString("user_tel"))
                                .regDate(rs.getTimestamp("reg_date").toLocalDateTime())
                                .userPoint(rs.getLong("user_point"))
                                .writerFlag(rs.getBoolean("writer_flag"))
                                .build();
                    }
                },
                userEmail
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }
}
