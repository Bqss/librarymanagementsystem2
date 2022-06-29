package entity;

public class Member extends Manusia{
    String id, nomorTelepon  , email;

    public Member(String id, String nomorTelepon, String email) {
        this.id = id;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
    }

    public Member(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
