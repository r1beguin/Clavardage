package DataController;

import java.util.ArrayList;

public class Conversation {

    private Id emeteur;
    private Id Recepteur;

    private ArrayList<Message> historique;


    public Conversation(Id emeteur, Id recepteur, ArrayList<Message> historique) {
        this.emeteur = emeteur;
        Recepteur = recepteur;
        this.historique = historique;
    }
}
