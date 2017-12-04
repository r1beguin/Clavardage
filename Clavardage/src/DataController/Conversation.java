package DataController;

import java.util.ArrayList;

public class Conversation {

    private User emeteur;
    private User Recepteur;

    private ArrayList<Message> historique;


    public Conversation(User emeteur, User recepteur, ArrayList<Message> historique) {
        this.emeteur = emeteur;
        Recepteur = recepteur;
        this.historique = historique;
    }
}
