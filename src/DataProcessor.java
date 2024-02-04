import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DataProcessor {

    // question 1
    //Number of matches played per year of all the years in IPL
    public static void matchesPerYear(ArrayList<Matches> data)
    {
        HashMap<Integer,Integer> map = new HashMap();

        for (Matches match : data)
        {
            map.put(match.year, map.getOrDefault(match.year,0) + 1);
        }
        System.out.println("Q1.) Number of matches played per year in IPL:");
        System.out.println(map);
    }

    // question 2
    //Number of matches won of all teams over all the years of IPL.

    public static void teamWins(ArrayList<Matches> data)
    {
        HashMap<String, Integer> map = new HashMap<>();

        for (Matches match : data)
        {
            String winner;
            if (match.winner.isEmpty())
            {
                winner = "No Result";
            } else
            {
                winner = match.winner;
            }
            map.put(winner, map.getOrDefault(winner, 0) + 1);
        }

        System.out.println("Q2.) Number of matches won of all teams over all the years of IPL.");
        System.out.println(map);
    }

    //question 3
    //For the year 2016 get the extra runs conceded per team.

    public static void extraRuns(ArrayList<Matches> data, ArrayList<Deliveries> data2, int year)
    {
        HashMap<String, Integer> map = new HashMap<>();

        for (Deliveries delivery : data2)
        {
            if (isMatcingYear(data, year, delivery))
            {
                map.put(delivery.bowling_team, map.getOrDefault(delivery.bowling_team, 0) + delivery.extra_runs);
            }
        }
        System.out.println("Q3.) Extra runs for the year " + year + ":");
        System.out.println(map);
    }


    //Question 4
    //For the year 2015 get the top economical bowlers.

    public static void topBowler(ArrayList<Matches> data, ArrayList<Deliveries> data2, int year)
    {
        HashMap<String, Integer> runs = new HashMap<>();
        HashMap<String,Integer> valid_balls = new HashMap<>();

        for (Deliveries delivery : data2)
        {
            if (isMatcingYear(data, year, delivery))
            {
                runs.put(delivery.bowler, runs.getOrDefault(delivery.bowler, 0) + delivery.total_runs);
            }
        }

        for (Deliveries delivery : data2)
        {
            if (isMatcingYear(data, year, delivery)  && delivery.extra_runs == 0   )
            {
                valid_balls.put(delivery.bowler, valid_balls.getOrDefault(delivery.bowler,0) + 1 );
            }
        }

//        System.out.println(runs);
//        System.out.println(valid_balls);

        HashMap<String, Double> economy = new HashMap<>();

        Set<String> keys = runs.keySet();

        for(String key : keys)
        {
            int total_runs = runs.get(key);
            float overs = valid_balls.get(key)/6;

            economy.put(key, (double) (total_runs/overs));
        }
        System.out.println(economy);
    }

    private static boolean isMatcingYear (ArrayList < Matches > data,int year, Deliveries delivery)
    {
        for (Matches match : data)
        {
            if (match.year == year && (match.id) == (delivery.match_id))
            {
                return true;
            }
        }
        return false;
    }
}







