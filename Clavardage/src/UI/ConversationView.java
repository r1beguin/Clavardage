package UI;

import javax.swing.*;

import java.awt.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class ConversationView extends JFrame {

    private JPanel contentPane;

    private JPanel panelParticipants;
    private JLabel labelParticipants;
    private JScrollPane scrollparticipants;

    private JButton buttonQuitterConversation;
    private JPanel panelConversation;
    private JScrollPane scrollConversation;

    private JPanel panelMessage;
    private JTextField textMessage;
    private JButton buttonEnvoyer;

    public ConversationView() throws HeadlessException {

        this.setTitle("Conversation");

        contentPane = new JPanel(new GridLayout(1,2));
        setContentPane(contentPane);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));

        panelParticipants = new JPanel(new GridLayout(0,1));
        labelParticipants = new JLabel("Participants à la conversation :");
        scrollparticipants = new JScrollPane();

        buttonQuitterConversation = new JButton("Quiter Conversation");
        panelParticipants.add(labelParticipants);
        panelParticipants.add(scrollparticipants);

        panelParticipants.add(buttonQuitterConversation);

        panelConversation = new JPanel(new GridLayout(2,1));
        scrollConversation = new JScrollPane();
        panelMessage = new JPanel(new GridLayout(1,2));
        textMessage = new JTextField();
        buttonEnvoyer = new JButton("Envoyer");
        panelMessage.add(textMessage);
        panelMessage.add(buttonEnvoyer);
        panelConversation.add(scrollConversation);
        panelConversation.add(panelMessage);


        contentPane.add(panelParticipants);
        contentPane.add(panelConversation);



        buttonEnvoyer.addActionListener(e-> envoiMessage());
        buttonQuitterConversation.addActionListener(e-> quitterConversation());


    }


    public void envoiMessage(){

        this.textMessage.setText("");

    }

    public void quitterConversation(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }

    public void afficherErreur(String message) {
        showMessageDialog(this, message, "Erreur", ERROR_MESSAGE);
    }


    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
