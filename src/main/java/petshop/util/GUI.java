package petshop.util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {
    public static JFrame CreateMainGUI()
    {
        JFrame main_frame = new JFrame("Pet Shop");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(800, 600);

        JMenuBar mb = new JMenuBar();
        JMenu mn1 = new JMenu("Pets");
        JMenu mn2 = new JMenu("Finance");
        JMenu mn3 = new JMenu("Accessories Shop");
        JMenu mn4 = new JMenu("Customer Service");

        mb.add(mn1);
        mb.add(mn2);
        mb.add(mn3);
        mb.add(mn4);
        // Pets items:
        JMenuItem pet_in = new JMenuItem("Pet IN");
        JMenuItem pet_out = new JMenuItem("Pet OUT");
        JMenuItem stock = new JMenuItem("Pet in Stock");
        JMenuItem status = new JMenuItem("Pet Status");
        JMenuItem money = new JMenuItem("Total");


        // Add items to menu
        mn1.add(pet_in);
        mn1.add(pet_out);
        mn1.add(stock);
        mn1.add(status);
        mn2.add(money);
        // Event Listener
        ActionListener get_pet = e-> NewFrame.CreateFrameToChoosePetToAdd();
        pet_in.addActionListener(get_pet);

        //ActionListener sell_pet = e->NewFrame.CreateFrameToShowListOfPetInStock();
        //pet_out.addActionListener(sell_pet);

        // Add components to frame
        main_frame.getContentPane().add(BorderLayout.NORTH, mb);
        main_frame.setVisible(true);
        return main_frame;
    }
}
