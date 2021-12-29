package petshop.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import petshop.Database.*;


public class NewGUI {
    public static void CreateNewGUI()
    {
        /* Connect to database */
        try{
        PostgreSQLJDBC.ConnectDatabase();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        JFrame main_frame = new JFrame("myShop");
        WindowAdapter exit = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    PostgreSQLJDBC.DisconnectDatabase();
                    System.exit(0);
                }
                catch (SQLException err)
                {
                    err.printStackTrace();
                }
            }
        };
        main_frame.addWindowListener(exit);
        main_frame.setSize(400,400);

        /* Copied from old file */
        JMenuBar mb = new JMenuBar();
        JMenu mn1 = new JMenu("Pets");
        JMenu mn2 = new JMenu("Customers");
        JMenu mn3 = new JMenu("Accessories");
        JMenu mn4 = new JMenu("Finance");

        mb.add(mn1);
        mb.add(mn2);
        mb.add(mn3);
        mb.add(mn4);
        // Pets items:
        JMenuItem pet_in = new JMenuItem("Add Pets");
        JMenuItem pet_out = new JMenuItem("Show Pets");
        //JMenuItem stock = new JMenuItem("Pet in Stock");
        //JMenuItem status = new JMenuItem("Pet Status");
        //JMenuItem money = new JMenuItem("Total");


        // Add items to menu
        mn1.add(pet_in);
        mn1.add(pet_out);
        //mn1.add(stock);
        //mn1.add(status);
        //mn2.add(money);
        // Event Listener
        ActionListener get_pet = e-> NewFrame.CreateFrameToChoosePetToAdd();
        pet_in.addActionListener(get_pet);

        ActionListener sell_pet = e->NewFrame.CreateFrameToFilterPets();
        pet_out.addActionListener(sell_pet);

        // Add components to frame
        main_frame.getContentPane().add(BorderLayout.NORTH, mb);
        main_frame.setVisible(true);
    }
}
