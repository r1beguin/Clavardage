import UI.*;
import DataController.*;
import Network.*;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        ClavardageView frame = new ClavardageView();
        frame.display();

        Scanner sc = new Scanner(System.in);


           ConversationView conv = new ConversationView();
           conv.display();




    }



}
