import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        //matches

        String line;
        ArrayList <Matches> data = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/gagan/IdeaProjects/IPL/matches.csv"));
            System.out.println(reader.readLine()); //to ignore first line

            while ((line = reader.readLine()) != null)
            {
                // System.out.println(line);
                data.add(new Matches(line));
            }
            //System.out.println( reader.readLine());
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        // deliveries

        String line2;
        ArrayList <Deliveries> data2 = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/gagan/IdeaProjects/IPL/deliveries.csv"));
            System.out.println(reader.readLine()); //to ignore first line

            while ((line = reader.readLine()) != null)
            {
                // System.out.println(line);
                data2.add(new Deliveries(line));
            }
            //System.out.println( reader.readLine());
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        // questions

        DataProcessor.matchesPerYear(data);
            System.out.println("\n");
        DataProcessor.teamWins(data);
            System.out.println("\n");
        DataProcessor.extraRuns(data,data2,2016);
            System.out.println("\n");
        DataProcessor.topBowler(data,data2,2015);

    }
}