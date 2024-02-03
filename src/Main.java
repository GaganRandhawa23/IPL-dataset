import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {

        //matches

        String line;
        ArrayList <Matches> data = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("matches.csv"));
            System.out.println(reader.readLine()); //to ignore first line
            System.out.println("here it begins");

            while ((line = reader.readLine()) != null) {
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
            BufferedReader reader = new BufferedReader(new FileReader("deliveries.csv"));
            System.out.println(reader.readLine()); //to ignore first line
            System.out.println("here it begins");

            while ((line = reader.readLine()) != null) {
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



        // quetsions

        //DataProcessor.matchesPerYear(data);
        //DataProcessor.teamWins(data);

        DataProcessor.extraRuns(data,data2,2016);

    }
}