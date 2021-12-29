package petshop.Classes;

import petshop.Database.PostgreSQLJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pets implements PetIF{
    protected String id;
    protected String pet_class; // assigned in child class
    protected String pet_species; // assigned in child class
    protected String name;
    //protected String status;
    protected int age;
    protected double cost;
    Pets(){}
    public Pets(String id) {
        try {
            ResultSet rs = PostgreSQLJDBC.Read("SELECT * FROM pet WHERE pet_id = " +
                    PostgreSQLJDBC.S2S(id) + ";");
            rs.next();
            this.id = id;
            this.name = rs.getString("pet_name");
            this.age = rs.getInt("age");
            this.pet_species = rs.getString("breed");
            this.cost = rs.getDouble("price_in");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
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

        int no_of_breed = PostgreSQLJDBC.CountResult("SELECT count(*) FROM species WHERE species = "
                + PostgreSQLJDBC.S2S(this.pet_class) + ";");
        String[] list_of_breed = new String[no_of_breed];
        try{
            ResultSet rs = PostgreSQLJDBC.Read("SELECT breed FROM species WHERE species = "
                    + PostgreSQLJDBC.S2S(this.pet_class) + ";");
            int idx = 0;
            while(rs.next())
            {
                list_of_breed[idx] = rs.getString("breed");
                idx++;
            }
            PostgreSQLJDBC.CloseStatement();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        JComboBox<String> options_specs = new JComboBox<>(list_of_breed);

        info_session.setLayout(new GridLayout(4,2));
        info_session.add(pet_name);
        info_session.add(tf_pet_name);
        info_session.add(pet_age);
        info_session.add(tf_pet_age);
        info_session.add(pet_price);
        info_session.add(tf_pet_price);
        info_session.add(pet_species);

        info_session.add(options_specs);
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

            this.pet_species = options_specs.getItemAt(options_specs.getSelectedIndex());


            try {
                String sql = "INSERT INTO pet(pet_name, age, breed, price_in)";
                sql += "VALUES(" + ("'" + this.name + "'") + "," + (this.age)
                        +"," + ("'" + this.pet_species + "'") + "," + this.cost +  ");";
                PostgreSQLJDBC.Insert(sql);
                System.out.println("Inserted");

            }
            catch (SQLException err)
            {
                err.printStackTrace();
            }
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
        JFrame info_frame = new JFrame("Pet's Info");
        info_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info_frame.setSize(400,400);

        JPanel info_session = new JPanel();
        info_session.setLayout(new GridLayout(5,2));

        try
        {
            ResultSet rs = PostgreSQLJDBC.Read("SELECT * FROM pet p join species s on " +
                    "(p.breed = s.breed) WHERE pet_id = '"+id+"';");
            rs.next();
            String pet_name = rs.getString("pet_name");
            int age = rs.getInt("age");
            String breed = rs.getString("breed");
            String species = rs.getString("species");
            double price = rs.getDouble("price_in") * 1.1;
            info_session.add(new JLabel("Pet's Name: "));
            info_session.add(new JLabel(pet_name));
            info_session.add(new JLabel("Pet's Age: "));
            info_session.add(new JLabel("" + age));
            info_session.add(new JLabel("Pet's Species: "));
            info_session.add(new JLabel(species));
            info_session.add(new JLabel("Pet's Breed: "));
            info_session.add(new JLabel(breed));
            info_session.add(new JLabel("Pet's base price: "));
            info_session.add(new JLabel("" + price));
            PostgreSQLJDBC.CloseStatement();
        }
        catch (SQLException ect)
        {
            ect.printStackTrace();
        }
        info_frame.getContentPane().add(BorderLayout.CENTER, info_session);
        info_frame.setVisible(true);
    }
}
