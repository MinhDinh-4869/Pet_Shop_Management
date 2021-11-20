import petshop.util.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        try {
            JFrame frame = GUI.CreateMainGUI();
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong...");
        }
    }
}
