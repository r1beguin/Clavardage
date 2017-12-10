package DataController;

import UI.*;

import com.sun.prism.impl.Disposer;

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class User{

    private static String pseudo;

    private ArrayList<Id> listeUserActifs;

    private static String IpAddress ;

    Exception PseudoPasDispo = new Exception();

    ClavardageView UI= new ClavardageView();

    private ArrayList<Conversation> ConversationSauvegardées ;


    public User(String pseudo, String ipAddress) throws Exception {

        this.listeUserActifs= new ArrayList<Id>();

        if (this.listeUserActifs.contains(pseudo)) {
            System.out.println("pseudo pas dispo");
            throw PseudoPasDispo;
        } else {
            this.pseudo = pseudo;
        }

        IpAddress = ipAddress;


    }

    public void changerPseudo(String pseudo) throws Exception {
        if (this.listeUserActifs.contains(pseudo)) {
            throw PseudoPasDispo;
        } else {
            this.pseudo = pseudo;
        }
    }

    public Thread listenForUser = new Thread(new Runnable() {
        @Override
        public void run() {
            byte[] buffer = new byte[65536];

            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);


            try{
                DatagramSocket socket = new DatagramSocket(5000);

                socket.receive(paquet);
                System.out.println("je reçois UDP");
            }
            catch (Exception e){
                System.out.println("Error UDP");
            }



            String message = new String(paquet.getData(),0,paquet.getLength());
            String ip = paquet.getAddress().getHostAddress().toString();

            if (message == "reset"){

                for (Id i : listeUserActifs){
                    if (i.getIpAddress()==ip){
                        listeUserActifs.remove(i);
                        UI.deleteUserActif(i);
                    }

                }

            }else{
            Id newUser = new Id(message,ip);

            listeUserActifs.add(newUser);
            UI.addUserActif(newUser);
            }


            try {

                DatagramPacket paquetReponse = new DatagramPacket(pseudo.getBytes(), pseudo.length(), InetAddress.getByName(ip), 5000);

                DatagramSocket socketReponse = new DatagramSocket();

                socketReponse.send(paquetReponse);

            }catch (Exception e){
                System.out.println("Error UDP reponse");

            }


        }

    });


    public void informuUser(boolean connection){

        if (connection){

            try{
                DatagramPacket paquetConnection = new DatagramPacket(pseudo.getBytes(), pseudo.length(), InetAddress.getByName("255.255.255.255"), 5000);

                DatagramSocket socketConnection = new DatagramSocket();

                socketConnection.setBroadcast(true);

                socketConnection.send(paquetConnection);

                System.out.println("je me connecte");
            }catch (Exception e){
                System.out.println("Error UDP connection");

            }

        }else{

            try{
                String message = "reset";
                DatagramPacket paquetDeconnection = new DatagramPacket(message.getBytes(), pseudo.length(), InetAddress.getByName("255.255.255.255"), 5000);

                DatagramSocket socketDeconnection = new DatagramSocket();

                socketDeconnection.setBroadcast(true);

                socketDeconnection.send(paquetDeconnection);
            }catch (Exception e){
                System.out.println("Error UDP connection");

            }


        }


    }








}
