package petshop.util;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class DeleteFile {
    public static boolean DeleteDataAndCost(String data_line)
    {
        List<String> elements = Arrays.asList(data_line.split(","));
        File data = new File("./dat/" + elements.get(1) + "/" + elements.get(0) + ".txt");
        File cost = new File("./dat/cost/" + elements.get(0) + ".txt");
        String[] stock_data = DataReader.ReadLines("./dat/pet_in_stock.txt");
        if (data.delete() && cost.delete())
        {
            try{
                FileWriter fw = new FileWriter("./dat/pet_in_stock.txt");
                for(String item : stock_data)
                {
                    if(!item.equals(data_line))
                    {
                        fw.write(item + "\n");
                    }
                }
                fw.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return true;
        }
        else
        {
            System.out.println("Some error occurred");
            return false;
        }
    }
}
