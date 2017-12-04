package UI;
import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class ClavardageView extends JFrame{

    private JPanel contentPane;
    private JPanel panelUtilisateur;
    private JPanel offline;
    private JPanel online;


    private JLabel labelChoixPseudo;
    private JTextField pseudoTextField;

    private ImageIcon iconeOffline;
    private JLabel imageOffline;
    private ImageIcon iconeOnline;
    private JLabel  imageOnline;

    private JButton pseudoButton;
    private JPanel panelUser;

    private JPanel panelActif;
    private JLabel labelActif;

    private JScrollPane scrollActif;

    public ClavardageView() throws HeadlessException {

        this.setTitle("Clavardage");

        contentPane = new JPanel();
        setContentPane(contentPane);

        panelUtilisateur = new JPanel(new GridLayout(0,1));
        panelUtilisateur.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

        labelChoixPseudo = new JLabel("Choissisez un pseudo");
        pseudoTextField = new JTextField(10);
        labelChoixPseudo.setLabelFor(pseudoTextField);
        pseudoTextField.setToolTipText("Entrez votre pseudo ici");


        iconeOffline = new ImageIcon("images/No-Avatar.png");
        imageOffline = new JLabel(iconeOffline);
        iconeOnline =  new ImageIcon("images/check.png");
        imageOnline = new JLabel(iconeOnline);

        pseudoButton = new JButton("OK");

        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 200));

        panelUser = new JPanel(new GridLayout(2,1));
        panelUser.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        panelActif = new JPanel(new GridLayout(0,1));
        panelActif.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));


        labelActif = new JLabel("Utilisateurs Actif :");


        scrollActif = new JScrollPane();


        panelUtilisateur.add(imageOffline);
        panelUtilisateur.add(labelChoixPseudo);
        panelUtilisateur.add(pseudoTextField);
        panelUtilisateur.add(pseudoButton);

        panelActif.add(labelActif);
        panelActif.add(scrollActif);


        panelUser.add(panelActif,NORTH);


        contentPane.add(panelUtilisateur,WEST);
        contentPane.add(panelUser,EAST);

        pseudoButton.addActionListener(e-> modeConnecte());


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void modeConnecte(){

        this.imageOffline.setIcon(this.iconeOnline);
        this.labelChoixPseudo.setText("Pseudo choisi : ...");
        this.pseudoTextField.setText("...");
        this.pseudoButton.setText("Changer pseudo");

        ConversationView conversation = new ConversationView();
        conversation.display();



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

