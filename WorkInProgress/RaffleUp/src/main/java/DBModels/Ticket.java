package DBModels;

public class Ticket {

    long ticket_id;
    long users_id;
    long raffle_id;

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(long users_id) {
        this.users_id = users_id;
    }

    public long getRaffle_id() {
        return raffle_id;
    }

    public void setRaffle_id(long raffle_id) {
        this.raffle_id = raffle_id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", users_id=" + users_id +
                ", raffle_id=" + raffle_id +
                '}';
    }
}
