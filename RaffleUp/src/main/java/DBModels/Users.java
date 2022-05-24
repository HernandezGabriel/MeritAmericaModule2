package DBModels;

public class Users {
    Long users_id;
    String username;
    String first_name;
    String last_name;
    String email;
    Long referredByUsersId;

    public Long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Long users_id) {
        this.users_id = users_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getReferredByUsersId() {
        return referredByUsersId;
    }

    public void setReferredByUsersId(Long referredByUsersId) {
        this.referredByUsersId = referredByUsersId;
    }

    @Override
    public String toString() {
        return "User{" +
                "users_id=" + users_id +
                ", username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", referredByUsersId=" + referredByUsersId +
                '}';
    }
}
