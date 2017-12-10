package UI;
import DataController.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;


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

    private JPanel panelDynamique;
    private JScrollPane scrollActif;



    private HashMap<String,JLabel> tabLabelUser = new HashMap<String,JLabel>();

    public ClavardageView() throws HeadlessException {

        this.setTitle("Clavardage");

        contentPane = new JPanel(new GridLayout(0,2));
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

        panelDynamique = new JPanel(new GridLayout(0,2));

        scrollActif = new JScrollPane(panelDynamique,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        labelActif = new JLabel("Utilisateurs Actif :");


        panelUtilisateur.add(imageOffline);
        panelUtilisateur.add(labelChoixPseudo);
        panelUtilisateur.add(pseudoTextField);
        panelUtilisateur.add(pseudoButton);

        panelActif.add(labelActif);
        panelActif.add(panelDynamique);


        panelUser.add(panelActif,NORTH);


        contentPane.add(panelUtilisateur,WEST);
        contentPane.add(panelUser,EAST);

        pseudoButton.addActionListener(e-> modeConnecte());


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    User newUser;

    public void modeConnecte(){

        this.imageOffline.setIcon(this.iconeOnline);

        String pseudochoisi = pseudoTextField.getText();
        this.panelUtilisateur.remove(pseudoButton);
        this.panelUtilisateur.remove(pseudoTextField);
        this.panelUtilisateur.remove(labelChoixPseudo);
        panelUtilisateur.revalidate();
        panelUtilisateur.repaint();

        JLabel nouveauPseudo = new JLabel("Votre pseudo : "+ pseudochoisi);
        JLabel changePseudo = new JLabel("Changez de pseudo ici : ");
        JTextField nouveauChamps = new JTextField("");
        JButton nouveauPseudoBouton = new JButton("Changer de pseudo");

        panelUtilisateur.add(nouveauPseudo);
        panelUtilisateur.add(changePseudo);
        panelUtilisateur.add(nouveauChamps);
        panelUtilisateur.add(nouveauPseudoBouton);
        panelUtilisateur.revalidate();

        System.out.println(pseudochoisi);

        try{
            newUser = new User(pseudochoisi, InetAddress.getLocalHost().getHostAddress().toString());
            newUser.listenForUser.start();
            newUser.informuUser(true);

        }
        catch (Exception e){
            System.out.println("Error getLocalHost");
        }

    }



    public void addUserActif(DataController.Id id){



        String pseudo = id.getPseudo();

        JLabel newUserActif = new JLabel(pseudo);
        JButton newButtonActif = new JButton("Commencer conversation");

        tabLabelUser.put(pseudo,newUserActif);

        panelDynamique.add(newUserActif);
        panelDynamique.add(newButtonActif);

        panelDynamique.revalidate();
        panelDynamique.repaint();

        System.out.println("Ajout user actif");
    }

    public void deleteUserActif(DataController.Id id){

        String pseudo = id.getPseudo();

        if (tabLabelUser.containsKey(pseudo)){

            panelActif.remove(tabLabelUser.get(pseudo));

            tabLabelUser.remove(pseudo);

        }

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

