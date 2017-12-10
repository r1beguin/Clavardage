package DataController;

public class Id {

    private String pseudo;

    private String IpAddress;


    public Id(String pseudo, String ipAddress) {
        this.pseudo = pseudo;
        IpAddress = ipAddress;

    }

    public String getPseudo() {
        return pseudo;
    }

    public String getIpAddress() {
        return IpAddress;
    }

}


