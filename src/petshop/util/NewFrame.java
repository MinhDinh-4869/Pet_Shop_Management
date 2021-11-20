package petshop.util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import petshop.pets.classes.*;

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
                  new_dog.CreateFrameToGetInfo();
                  //return new_dog;
           };
           dog.addActionListener(dog_chose);
           //************ CODE BLOCK FOR CAT BUTTON ************
           JButton cat = new JButton("Cat");
           ActionListener cat_choose = e->{
                  choose_pet.dispose();
                  Cat new_cat = new Cat();
                  new_cat.CreateFrameToGetInfo();
           };
           cat.addActionListener(cat_choose);
           //***************************************************

           pet_list.add(dog);
           pet_list.add(cat);
           choose_pet.getContentPane().add(BorderLayout.CENTER, pet_list);
           choose_pet.setVisible(true);
    }
/*
    public static void CreateFrameToChoosePetToSell()
    {
           JFrame sell_pet = new JFrame("Sell Pet");
           sell_pet.setSize(200, 200);

           JPanel pet_list = new JPanel();
           pet_list.setLayout(new GridLayout(2,2));
           //************ CODE BLOCK FOR DOG BUTTON ************
           JButton dog = new JButton("Dog");
           ActionListener dog_chose = e -> {
                  CreateFrameToShowListOfPetInStock();
                  sell_pet.dispose();
           };
           dog.addActionListener(dog_chose);

           pet_list.add(dog);
           sell_pet.getContentPane().add(BorderLayout.CENTER, pet_list);
           sell_pet.setVisible(true);
    }
*/
    public static void CreateFrameToShowListOfPetInStock()
    {
           JFrame list_frame = new JFrame("List of Pets");
           list_frame.setSize(400,400);
           list_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           String[] list_of_pets = DataReader.ReadLines("./dat/pet_in_stock.txt");
           JPanel pet_list = new JPanel();
           pet_list.setLayout(new GridLayout(list_of_pets.length, 3));
           //for each

           class ViewButton extends JButton
           {
                  ViewButton(String path){
                         this.setText("View");
                         ActionListener action = e -> {
                                List<String> elements = Arrays.asList(path.split(","));
                                String data_path = "./dat/" + elements.get(1) + "/" + elements.get(0) + ".txt";
                                if(DataReader.CheckFileExist(data_path))
                                {
                                       String[] data = DataReader.ReadLines(data_path);
                                       System.out.println("Pet data: " + data[0]);
                                }
                                else
                                {
                                       this.setEnabled(false);
                                }
                         };
                         this.addActionListener(action);
                  }
           }

           class SellButton extends JButton
           {
                  SellButton(String data_line)
                  {
                         this.setText("Sell");
                         ActionListener action = e->{
                                if(DeleteFile.DeleteDataAndCost(data_line))
                                {
                                       this.setText("Sold");
                                       this.setEnabled(false);
                                }
                                else
                                {
                                       System.out.println("Can not delete data");
                                }
                         };
                         this.addActionListener(action);
                  }
           }
           for (String list_of_pet : list_of_pets) {
                  pet_list.add(new JLabel(list_of_pet));
                  pet_list.add(new ViewButton(list_of_pet));
                  pet_list.add(new SellButton(list_of_pet));
           }

           list_frame.getContentPane().add(BorderLayout.NORTH, pet_list);
           list_frame.setVisible(true);
    }
}
