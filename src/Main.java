import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
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

        matchesPerYear(data);
    }


    public static void matchesPerYear(ArrayList<Matches> dataList) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Matches match : dataList) {
            map.put(match.year, map.getOrDefault(match.year, 0) + 1);
        }

        System.out.println("Q1.) Number of matches played per year in IPL:");

        for (int year : map.keySet()) {
            System.out.println("Year " + year + ": " + map.get(year) + " matches");
        }
    }
}