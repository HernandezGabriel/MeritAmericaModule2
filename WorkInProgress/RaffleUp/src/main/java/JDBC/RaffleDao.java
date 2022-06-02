package JDBC;

import DBModels.Raffle;

import java.util.List;

public interface RaffleDao {

    long createRaffleReturnNewRaffleIdOrZero(Raffle raffle);
    List<Raffle> listAllRafflesNotExpired();
    void expireRaffle();
    void generateRaffleLink();
    void handleWinner();








}
