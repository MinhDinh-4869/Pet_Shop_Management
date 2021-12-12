package petshop.PetClasses;

import petshop.util.ModifyBalance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Pets {
    protected String pet_class; // assigned in child class
    protected String pet_species; // assigned in child class
    protected String name;
    protected String status;
    protected int age;
    protected double cost;
    public void setInfo()
    {
        JFrame input_frame = new JFrame("Set Info");
        input_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        input_frame.setSize(200, 200);

        JPanel info_session = new JPanel();
        JLabel pet_name = new JLabel("Pet Name: ");
        JTextField tf_pet_name = new JTextField();
        JLabel pet_age = new JLabel("Pet Age: ");
        JTextField tf_pet_age = new JTextField();
        JLabel pet_price = new JLabel("Cost: ");
        JTextField tf_pet_price = new JTextField();
        JLabel pet_species = new JLabel("Species: ");
        JTextField tf_pet_species = new JTextField();
        info_session.setLayout(new GridLayout(4,2));
        info_session.add(pet_name);
        info_session.add(tf_pet_name);
        info_session.add(pet_age);
        info_session.add(tf_pet_age);
        info_session.add(pet_price);
        info_session.add(tf_pet_price);
        info_session.add(pet_species);
        info_session.add(tf_pet_species);

        JPanel options = new JPanel();
        JButton add = new JButton("Add");
        JButton cancel = new JButton("Cancel");

        options.add(add);
        options.add(cancel);


        input_frame.getContentPane().add(BorderLayout.NORTH, info_session);
        input_frame.getContentPane().add(BorderLayout.SOUTH, options);

        ActionListener save_event = e-> {
            this.name = tf_pet_name.getText();

            String age_temp = tf_pet_age.getText();
            this.age = Integer.parseInt(age_temp);

            String price_temp = tf_pet_price.getText();
            this.cost = Double.parseDouble(price_temp);
            this.pet_species = tf_pet_species.getText();
            /*
            double doubleCost = Double.parseDouble(this.price);
            double doublePrice = doubleCost * 1.1;
            this.sell_price = String.format("%.2f", doublePrice);


            save_info();
            save_cost();
            save_pet();
            ModifyBalance.AddBuyToBalance(this.ID, this.price);
            */

            input_frame.dispose();
        };
        ActionListener cancel_event = e ->
                input_frame.dispose();

        add.addActionListener(save_event);
        cancel.addActionListener(cancel_event);
        input_frame.setVisible(true);
    }
    public void getInfo()
    {
        System.out.println("Pet info: ");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Species: " + this.pet_species);
        System.out.println("Price: " + this.cost);
        System.out.println("Class: " + this.pet_class);
    }
}
