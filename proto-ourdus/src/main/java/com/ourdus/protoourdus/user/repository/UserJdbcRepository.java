package com.ourdus.protoourdus.user.repository;

import com.ourdus.protoourdus.user.model.UserVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
        //기본적으로 jdbcTemplate의 update로는 preparedstatement 템플릿 호출이 가능하나 여기서는 원하는 userid를 keyholder로 받아오기위해 update(PreparedStatementCreator psc, KeyHolder generatedKeyHolder) 형태 사용
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
    public void update(UserVo userVo) {
        jdbcTemplate.update(
            "UPDATE user SET user_pw=?, user_name=?, user_tel=?, user_point=?, writer_flag=? WHERE user_email=?",
            userVo.getUserPw(),
            userVo.getUserName(),
            userVo.getUserTel(),
            userVo.getUserPoint(),
            userVo.getWriterFlag(),
            userVo.getUserEmail()
            );
    }

    @Override
    public Optional<UserVo> findByEmail(String userEmail) {
        List<UserVo> results = jdbcTemplate.query(
                "SELECT * FROM user WHERE user_email=?",
                userVoRowMapper,
                userEmail
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }


    static RowMapper<UserVo> userVoRowMapper = (rs, rowNum) -> new UserVo.Builder()
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
