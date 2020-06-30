package com.lec.sts13_jdbc.board.beans;

import com.lec.sts13_jdbc.board.C;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BWriteDAO {

    JdbcTemplate template;

    public BWriteDAO(){
        this.template = C.template;
    }

    public ArrayList<BWriteDTO> select(){
        return (ArrayList<BWriteDTO>) template.query(C.SQL_WRITE_SELECT, new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));
    }

    public int insert(final BWriteDTO dto){
        return this.template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString(1, dto.getSubject());
                ps.setString(2, dto.getContent());
                ps.setString(3, dto.getName());

            }
        });
    }

    public ArrayList<BWriteDTO> view(int uid){

        int result = template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(C.SQL_WRITE_INC_VIEWCNT);
                ps.setInt(1, uid);
                return ps;
            }
        });
        if(result >= 1){
            return (ArrayList<BWriteDTO>) template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, uid);
                }
            }, new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));
        }else{
            return null;
        }
    }

    public ArrayList<BWriteDTO> sview(int uid){
        return (ArrayList<BWriteDTO>) template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, uid);
            }
        }, new BeanPropertyRowMapper<BWriteDTO>(BWriteDTO.class));
    }



    public int update(final BWriteDTO dto){
        return template.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, dto.getSubject());
                ps.setString(2, dto.getContent());
                ps.setInt(3, dto.getUid());
            }
        });
    }

    public int delete(final BWriteDTO dto){
        return this.template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(C.SQL_WRITE_DELETE_BY_UID);
                ps.setInt(1, dto.getUid());
                return ps;
            }
        });
    }

}
