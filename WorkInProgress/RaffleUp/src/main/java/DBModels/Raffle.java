package DBModels;

import java.sql.Date;
import java.sql.Timestamp;

public class Raffle {

    long raffle_id ;
    String raffle_name;
    String raffle_item_name ;
    long raffle_owner_id;
    String raffle_description;
    String raffle_picture_location;
    String raffle_link ;
    int raffle_end_size_limit ;
    Timestamp raffle_end_date_and_time ;

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    boolean expired;

    @Override
    public String toString() {
        return "Raffle{" +
                "raffle_id=" + raffle_id +
                ", raffle_name='" + raffle_name + '\'' +
                ", raffle_item_name='" + raffle_item_name + '\'' +
                ", raffle_owner_id=" + raffle_owner_id +
                ", raffle_description='" + raffle_description + '\'' +
                ", raffle_picture_location='" + raffle_picture_location + '\'' +
                ", raffle_link='" + raffle_link + '\'' +
                ", raffle_end_size_limit=" + raffle_end_size_limit +
                ", raffle_end_date_and_time=" + raffle_end_date_and_time +
                ", expired=" + expired +
                '}';
    }

    public long getRaffle_id() {
        return raffle_id;
    }

    public void setRaffle_id(long raffle_id) {
        this.raffle_id = raffle_id;
    }

    public String getRaffle_name() {
        return raffle_name;
    }

    public void setRaffle_name(String raffle_name) {
        this.raffle_name = raffle_name;
    }

    public String getRaffle_item_name() {
        return raffle_item_name;
    }

    public void setRaffle_item_name(String raffle_item_name) {
        this.raffle_item_name = raffle_item_name;
    }

    public long getRaffle_owner_id() {
        return raffle_owner_id;
    }

    public void setRaffle_owner_id(long raffle_owner_id) {
        this.raffle_owner_id = raffle_owner_id;
    }

    public String getRaffle_description() {
        return raffle_description;
    }

    public void setRaffle_description(String raffle_description) {
        this.raffle_description = raffle_description;
    }

    public String getRaffle_picture_location() {
        return raffle_picture_location;
    }

    public void setRaffle_picture_location(String raffle_picture_location) {
        this.raffle_picture_location = raffle_picture_location;
    }

    public String getRaffle_link() {
        return raffle_link;
    }

    public void setRaffle_link(String raffle_link) {
        this.raffle_link = raffle_link;
    }

    public int getRaffle_end_size_limit() {
        return raffle_end_size_limit;
    }

    public void setRaffle_end_size_limit(int raffle_end_size_limit) {
        this.raffle_end_size_limit = raffle_end_size_limit;
    }

    public Timestamp getRaffle_end_date_and_time() {
        return raffle_end_date_and_time;
    }

    public void setRaffle_end_date_and_time(Timestamp raffle_end_date_and_time) {
        this.raffle_end_date_and_time = raffle_end_date_and_time;
    }
}
