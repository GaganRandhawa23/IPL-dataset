import java.util.ArrayList;
import java.util.HashMap;

public class DataProcessor {


    // question 1
    //Number of matches played per year of all the years in IPL
    public static void matchesPerYear(ArrayList<Matches> data) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Matches match : data) {
            map.put(match.year, map.getOrDefault(match.year, 0) + 1);
        }

        System.out.println("Q1.) Number of matches played per year in IPL:");

        for (int year : map.keySet()) {
            System.out.println("Year " + year + ": " + map.get(year) + " matches");
        }
    }



    // question 2
    //Number of matches won of all teams over all the years of IPL.

    public static void teamWins (ArrayList<Matches> data)
    {
        HashMap<String, Integer> map = new HashMap<>();

        for (Matches match : data) {
            String winner = match.winner.isEmpty() ? "No Result" : match.winner;
            map.put(winner, map.getOrDefault(winner, 0) + 1);
        }

        System.out.println("Q2.) Number of matches won of all teams over all the years of IPL.");

        for (String team : map.keySet()) {
            System.out.println("Team " + team + ": " + map.get(team) + " matches");
        }

    }
}
