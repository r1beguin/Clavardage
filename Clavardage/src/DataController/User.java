package DataController;

import com.sun.prism.impl.Disposer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class User{

    private String pseudo;

    private ArrayList<User> listeUserActifs;

    private String IpAddress ;

    int port;



    private ArrayList<Conversation> ConversationSauvegardées ;

    public User(String pseudo, ArrayList<User> listeUserActifs, String ipAddress, ArrayList<Conversation> conversationSauvegardées, int port) {
        this.pseudo = pseudo;
        this.listeUserActifs = listeUserActifs;
        IpAddress = ipAddress;
        port= port;
        ConversationSauvegardées = conversationSauvegardées;
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
