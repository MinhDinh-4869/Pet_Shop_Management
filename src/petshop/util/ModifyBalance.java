package petshop.util;
import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ModifyBalance {
    public static void InitBalance()
    {
        try{
            FileWriter fw = new FileWriter("./dat/balance.txt");
            fw.write("0,0,0\n");
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void AddSoldToBalance(String data_line)
    {
        try {
            FileWriter fw = new FileWriter("./dat/balance.txt", true);
            List<String> elements = Arrays.asList(data_line.split(",")); //elements = {ID, class}

            String cost_path = "./dat/cost/" + elements.get(0) + ".txt";
            String data = DataReader.ReadLines(cost_path)[0];

            List<String> costs = Arrays.asList(data.split(","));
            String cost_out = costs.get(1);

            // Read all the transactions in month
            String[] balances = DataReader.ReadLines("./dat/balance.txt");
            String last_trans = balances[balances.length - 1];

            String last_balance = Arrays.asList(last_trans.split(",")).get(2);
            double new_balance = Double.parseDouble(last_balance) + Double.parseDouble(cost_out);
            fw.write(elements.get(0) + "," + cost_out + "," + String.format("%.2f", new_balance) + "\n");
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void AddBuyToBalance(String ID, String price)
    {
        try{
            FileWriter fw = new FileWriter("./dat/balance.txt", true);
            String[] balances = DataReader.ReadLines("./dat/balance.txt");

            String last_transaction = balances[balances.length - 1];
            String last_balance = Arrays.asList(last_transaction.split(",")).get(2);

            double new_balance = Double.parseDouble(last_balance) - Double.parseDouble(price);
            fw.write(ID + "," + ("-" + price) + "," + String.format("%.2f", new_balance) + "\n");
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
