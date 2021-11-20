package petshop.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataReader {
    public DataReader(){}
    public static boolean CheckFileExist(String file_path)
    {
        File f = new File(file_path);
        return f.exists() && !f.isDirectory();
    }
    private static int CountLines(String path)
    {
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            //String line;
            while(br.readLine() != null)
            {
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return counter;
    }
    public static String[] ReadLines(String path)
    {
        String[] data_lines = new String[CountLines(path)];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            int idx = 0;
            while((line = reader.readLine()) != null)
            {
                data_lines[idx] = line;
                idx++;
            }
            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return data_lines;
    }
}
