import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        String line;
        Matches[] data =new Matches[700];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("matches.csv"));
            System.out.println(reader.readLine());
            System.out.println("here it begins");

            int i=1;
            while((line = reader.readLine()) != null)
            {
                //System.out.println(line);
                data[i]= new Matches(line);
                i++;
            }
            System.out.println( reader.readLine());
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
//        for(int i =1; i<data.length; i++)
//        {
//            System.out.println(data[i].id);
//        }


        matches_per_year(data,2017);
    }
    public static void matches_per_year(Matches[] data,int year)
    {
        HashMap <Integer,Integer>map =  new HashMap<>();
        int i =1;
        while(i<637)
        {
            map.put(data[i].id, data[i].year);
            i++;
        }

        int count = 0;

        for (int y : map.values()) {
            if (y == year) {
                count++;
            }
        }

        System.out.println(count);
    }
}