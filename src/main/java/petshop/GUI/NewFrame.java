package petshop.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import petshop.Classes.Cat;
import petshop.Classes.Dog;
import petshop.Classes.Pets;
import petshop.Database.PostgreSQLJDBC;

class FilterPanel extends JPanel{

    public void UpdatePetList(String[] ids)
    {
        this.removeAll();
        this.setLayout(new GridLayout(ids.length, 2));

        for(String id : ids)
        {
            System.out.println(id);
            this.add(new JLabel(id));
            this.add(new ViewButton(id));
        }

        this.revalidate();
        this.repaint();
    }
}

class ViewButton extends JButton
{
    ViewButton(String id)
    {
        super();
        final Pets p = new Pets(id);
        this.setText("View");
        ActionListener click_event = e->
                p.getInfo();
        this.addActionListener(click_event);
    }
}
public class NewFrame {
    public static void CreateFrameToChoosePetToAdd()
    {
        JFrame choose_pet = new JFrame("Add Pet");
        //ActionListener exit = e-> choose_pet.dispose();
        choose_pet.setSize(200, 200);

        JPanel pet_list = new JPanel();
        pet_list.setLayout(new GridLayout(2,2));
        //************ CODE BLOCK FOR DOG BUTTON ************
        JButton dog = new JButton("Dog");
        ActionListener dog_chose = e -> {
            choose_pet.dispose();
            Dog new_dog = new Dog();
            new_dog.setInfo();
            //return new_dog;
        };
        dog.addActionListener(dog_chose);
        //************ CODE BLOCK FOR CAT BUTTON ************
        JButton cat = new JButton("Cat");
        ActionListener cat_choose = e->{
            choose_pet.dispose();
            Cat c = new Cat();
            c.setInfo();
        };
        cat.addActionListener(cat_choose);
        //***************************************************

        pet_list.add(dog);
        pet_list.add(cat);
        choose_pet.getContentPane().add(BorderLayout.CENTER, pet_list);
        choose_pet.setVisible(true);
    }

    public static void CreateFrameToFilterPets()
    {
        JFrame filter_frame = new JFrame("Search");
        filter_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        filter_frame.setSize(400, 400);

        JPanel filter_session = new JPanel();
        FilterPanel result_session = new FilterPanel();
        filter_session.setLayout(new GridLayout(3, 2));

        int no_of_specs = PostgreSQLJDBC.CountResult("SELECT count(distinct species) FROM species");
        String[] list_of_specs = new String[no_of_specs];
        try{
            ResultSet rs = PostgreSQLJDBC.Read("SELECT distinct species FROM species");
            int idx = 0;
            while(rs.next())
            {
                list_of_specs[idx] = rs.getString("species");
                idx++;
            }
            PostgreSQLJDBC.CloseStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        JComboBox<String> specs = new JComboBox<>(list_of_specs);
        JTextField age_tf = new JTextField(10);
        JButton search = new JButton("Search");
        filter_session.add(new JLabel("Age: "));
        filter_session.add(age_tf);
        filter_session.add(new JLabel("Species: "));
        filter_session.add(specs);
        filter_session.add(search);

        //Datatype for lambda expression
        AtomicInteger age = new AtomicInteger(-1);
        AtomicReference<String> species = new AtomicReference<>("");

        ActionListener filter = e ->{
            age.set(Integer.parseInt(age_tf.getText()));
            species.set(specs.getItemAt(specs.getSelectedIndex()));

            int no_of_pets = PostgreSQLJDBC.CountResult("SELECT count(*) FROM pet p join species s on " +
                    "(p.breed = s.breed) WHERE p.age = " + age.get() + " and s.species = " +
                    PostgreSQLJDBC.S2S(species.get()) + ";");
            String[] ids = new String[no_of_pets];
            try
            {
                ResultSet rs = PostgreSQLJDBC.Read("SELECT pet_id FROM pet p join species s on " +
                        "(p.breed = s.breed) WHERE p.age = " + age.get() + " and s.species = " +
                        PostgreSQLJDBC.S2S(species.get()) + ";");
                int idx = 0;
                while(rs.next())
                {
                    ids[idx] = rs.getString("pet_id");
                    idx++;
                }
                PostgreSQLJDBC.CloseStatement();
            }
            catch (SQLException exp)
            {
                exp.printStackTrace();
            }
            result_session.UpdatePetList(ids);
        };
        search.addActionListener(filter);

        filter_frame.getContentPane().add(BorderLayout.NORTH, filter_session);
        //filter_frame.getContentPane().add(BorderLayout.CENTER, search);
        filter_frame.getContentPane().add(BorderLayout.SOUTH,result_session);
        filter_frame.setVisible(true);
    }
}

