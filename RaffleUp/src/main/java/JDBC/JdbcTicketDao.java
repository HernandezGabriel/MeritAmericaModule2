package JDBC;

import DBModels.Ticket;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTicketDao implements TicketDao{
    public JdbcTicketDao(DataSource dataSource) {

    }

    @Override
    public Ticket createTicketReturnNewTicket(Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> getAllTicketsByRaffleId(Long raffleId) {
        return null;
    }

    @Override
    public List<Ticket> getAllTicketsByUsersId(Long userId) {
        return null;
    }

    @Override
    public Ticket drawWinningTicketByRaffleId(Long raffleID) {
        return null;
    }
}
