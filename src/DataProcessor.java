import java.util.ArrayList;
import java.util.HashMap;

public class DataProcessor {


    // question 1
    //Number of matches played per year of all the years in IPL
    public static void matchesPerYear(ArrayList<Matches> data) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Matches match : data)
        {
            map.put(match.year, map.getOrDefault(match.year, 0) + 1);
        }

        System.out.println("Q1.) Number of matches played per year in IPL:");

        for (int year : map.keySet())
        {
            System.out.println("Year " + year + ": " + map.get(year) + " matches");
        }
    }



    // question 2
    //Number of matches won of all teams over all the years of IPL.

    public static void teamWins (ArrayList<Matches> data)
    {
        HashMap<String, Integer> map = new HashMap<>();

        for (Matches match : data)
        {
            String winner = match.winner.isEmpty() ? "No Result" : match.winner;
            map.put(winner, map.getOrDefault(winner, 0) + 1);
        }

        System.out.println("Q2.) Number of matches won of all teams over all the years of IPL.");

        for (String team : map.keySet())
        {
            System.out.println("Team " + team + ": " + map.get(team) + " matches");
        }

    }

    //question 3
    //For the year 2016 get the extra runs conceded per team.

    public static void extraRuns(ArrayList<Matches> data, ArrayList<Deliveries> data2, int year) {
        HashMap<String, Integer> map = new HashMap<>();

        for (Deliveries delivery : data2)
        {
            if (isMatchingYear(data, year, delivery))
            {
                map.put(delivery.bowling_team, map.getOrDefault(delivery.bowling_team, 0) + delivery.extra_runs);
            }
        }
        System.out.println("Q3.) Extra runs for the year " + year + ":");
        for (String team : map.keySet())
        {
            System.out.println("Team -->" + team + ": " + map.get(team) + " Runs");
        }
    }
    private static boolean isMatchingYear(ArrayList<Matches> data, int year, Deliveries delivery) {
        for (Matches match : data)
        {
            if (match.year == year && (match.id)==(delivery.match_id))
            {
                return true;
            }
        }
        return false;
    }




}



    //Question 4
    //For the year 2015 get the top economical bowlers.


