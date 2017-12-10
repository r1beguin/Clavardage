package UI;
import DataController.*;

import javax.swing.*;

import java.awt.*;

import java.util.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class ConversationView extends JFrame {

    private JPanel contentPane;

    private JPanel panelParticipants;
    private JLabel labelParticipants;
    private JTextPane scrollparticipants;

    private JButton buttonQuitterConversation;
    private JPanel panelConversation;
    private JScrollPane scrollConversation;
    private JTextArea textArea;

    private JPanel panelMessage;
    private JTextField textMessage;
    private JButton buttonEnvoyer;

    public ConversationView() throws HeadlessException {

        this.setTitle("Conversation");

        contentPane = new JPanel(new GridLayout(1,2));
        setContentPane(contentPane);
        //contentPane.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
        contentPane.setPreferredSize(new Dimension(700,700));

        panelParticipants = new JPanel(new GridLayout(0,1));


        buttonQuitterConversation = new JButton("Quiter Conversation");


        panelParticipants.add(buttonQuitterConversation);

        panelConversation = new JPanel(new GridLayout(2,1));
        panelParticipants.setBorder(BorderFactory.createEmptyBorder(350,100,320,100));
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        scrollConversation = new JScrollPane(textArea);

        scrollConversation.setPreferredSize(new Dimension(50, 150));
        panelMessage = new JPanel(new GridLayout(1,2));
        textMessage = new JTextField();
        buttonEnvoyer = new JButton("Envoyer");

        panelMessage.add(textMessage);
        panelMessage.add(buttonEnvoyer);

        panelConversation.add(scrollConversation);
        panelConversation.add(panelMessage);



        contentPane.add(panelParticipants);
        contentPane.add(panelConversation);



        buttonEnvoyer.addActionListener(e-> envoiMessage(textMessage.getText()));
        buttonQuitterConversation.addActionListener(e-> quitterConversation());


    }


    public void envoiMessage(String message){

        this.textMessage.setText("");

        Date horodatage = new Date();
        Message mess = new Message(message,horodatage.toString());


        textArea.append("me : "+message+"\n");

        textArea.revalidate();
        textArea.repaint();

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
