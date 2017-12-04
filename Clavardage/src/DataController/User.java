package DataController;

import com.sun.prism.impl.Disposer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class User{

    private String pseudo;

    private ArrayList<Id> listeUserActifs;

    private String IpAddress ;

    private int port_ecoute;
    private int port_envoi;

    Exception PseudoPasDispo = new Exception();


    private ArrayList<Conversation> ConversationSauvegardées ;


    public User(String pseudo, ArrayList<Id> listeUserActifs, String ipAddress, int port_ecoute, int port_envoi, ArrayList<Conversation> conversationSauvegardées) throws Exception {
        this.listeUserActifs = listeUserActifs;
        if (this.listeUserActifs.contains(pseudo)) {
            throw PseudoPasDispo;
        } else {
            this.pseudo = pseudo;
        }

        IpAddress = ipAddress;
        this.port_ecoute = port_ecoute;
        this.port_envoi = port_envoi;
        ConversationSauvegardées = conversationSauvegardées;
    }

    public void changerPseudo(String pseudo) throws Exception {
        if (this.listeUserActifs.contains(pseudo)) {
            throw PseudoPasDispo;
        } else {
            this.pseudo = pseudo;
        }
    }

    Thread listenForUser = new Thread(new Runnable() {
        @Override
        public void run() {
            byte[] buffer = new byte[65536];

            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);

            try{
            DatagramSocket socket = new DatagramSocket(5000);

            socket.receive(paquet);}
            catch (Exception e){
                System.out.println("Error UDP");
            }

            String message = new String(paquet.getData(),0,paquet.getLength());

        }
    });




}
