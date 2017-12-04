package DataController;

public class Id {

    private String pseudo;

    private String IpAddress ;

    private int port;

    public Id(String pseudo, String ipAddress, int port) {
        this.pseudo = pseudo;
        IpAddress = ipAddress;
        this.port = port;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public int getPort() {
        return port;
    }
}
