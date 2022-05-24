package JDBC;

import DBModels.Raffle;
import DBModels.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket createTicketReturnNewTicket(Ticket ticket);
    List<Ticket> getAllTicketsByRaffleId(Long raffleId);
    List<Ticket> getAllTicketsByUsersId(Long userId);
    Ticket drawWinningTicketByRaffleId(Long raffleID);


}
