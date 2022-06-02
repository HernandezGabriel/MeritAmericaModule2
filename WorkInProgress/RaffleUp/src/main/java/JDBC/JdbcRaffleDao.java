package JDBC;

import DBModels.Raffle;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcRaffleDao implements RaffleDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRaffleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public long createRaffleReturnNewRaffleIdOrZero(Raffle raffle) {
        String sql = "Insert Into raffle" +
                "(raffle_name,raffle_item_name,raffle_owner_id) " +
                "Values(?,?,?) returning raffle_id ;";
        SqlRowSet sqlRowSet;

       try{
           sqlRowSet= jdbcTemplate.queryForRowSet(sql,raffle.getRaffle_name(),raffle.getRaffle_item_name(),raffle.getRaffle_owner_id());
       }catch(DataAccessException e){
           System.err.println(e.getLocalizedMessage());
           return 0;
       }

        if (sqlRowSet.next()){
            return sqlRowSet.getLong("raffle_id");
        }
        return 0;
        //return jdbcTemplate.queryForObject(sql,raffle.getRaffle_name(),raffle.getRaffle_item_name(),raffle.getRaffle_owner_id(), Raffle.class);
    }

    @Override
    public List<Raffle> listAllRafflesNotExpired() {
        String sql="Select * from raffle where expired is not true;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        List<Raffle> raffles = new ArrayList<>();
        while (sqlRowSet.next()){
//
//            raffle_id serial unique not Null,
//                    raffle_name varchar(20) not Null,
//            raffle_item_name varchar(20) not Null,
//            raffle_owner_id int not null,
//                    raffle_description varchar(200) null,
//                    raffle_picture_location varchar(200) null,
//                    raffle_link varchar(200) unique null,
//                    raffle_end_size_limit int null,
//                    raffle_end_date_and_time timestamp null,
//                    expired boolean null,

            Raffle tempRaffle= new Raffle();
            tempRaffle.setRaffle_id(sqlRowSet.getLong("raffle_id"));
            tempRaffle.setRaffle_name(sqlRowSet.getString("raffle_name"));
            tempRaffle.setRaffle_item_name(sqlRowSet.getString("raffle_item_name"));
            tempRaffle.setRaffle_owner_id(sqlRowSet.getLong("raffle_owner_id"));
            tempRaffle.setRaffle_description(sqlRowSet.getString("raffle_description"));
            tempRaffle.setRaffle_picture_location(sqlRowSet.getString("raffle_picture_location"));
            tempRaffle.setRaffle_link(sqlRowSet.getString("raffle_link"));
            tempRaffle.setRaffle_end_size_limit(sqlRowSet.getInt("raffle_end_size_limit"));
            tempRaffle.setRaffle_end_date_and_time(sqlRowSet.getTimestamp("raffle_end_date_and_time"));
            tempRaffle.setExpired(sqlRowSet.getBoolean("expired"));

            raffles.add(tempRaffle);
        }
        return raffles;
    }

    @Override
    public void expireRaffle() {

    }

    @Override
    public void generateRaffleLink() {

    }

    @Override
    public void handleWinner() {

    }
}
