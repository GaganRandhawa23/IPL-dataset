import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int MATCH_YEAR = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE1 = 15;
    public static final int MATCH_UMPIRE2 = 16;
    public static final int DELIVERY_INNING = 1;
    public static final int DELIVERY_BATTING_TEAM = 2;
    public static final int DELIVERY_BOWLING_TEAM = 3;
    public static final int DELIVERY_OVER = 4;
    public static final int DELIVERY_BALL = 5;
    public static final int DELIVERY_BATSMAN = 6;
    public static final int DELIVERY_NON_STRIKER = 7;
    public static final int DELIVERY_BOWLER = 8;
    public static final int DELIVERY_IS_SUPER_OVER = 9;
    public static final int DELIVERY_RUNS = 10;
    public static final int DELIVERY_BYE_RUNS = 11;
    public static final int DELIVERY_LEGBYE_RUNS = 12;
    public static final int DELIVERY_NOBALL_RUNS = 13;
    public static final int DELIVERY_PENALTY_RUNS = 14;
    public static final int DELIVERY_BATSMAN_RUNS = 15;
    public static final int DELIVERY_EXTRA_RUNS = 16;
    public static final int DELIVERY_TOTAL_RUNS = 17;
    public static final int DELIVERY_PLAYER_DISMISSED = 18;
    public static final int DELIVERY_DISMISSAL_KIND = 19;
    public static final int DELIVERY_FIELDER = 20;

    public static void main(String[] args) {
        ArrayList<Match> matches = getMatchesData();
        ArrayList<Delivery> data2 = getDeliveriesData();

        findMatchesPerYear(matches);
        findTeamWinsPerAllYear(matches);
        extraRunsConcededPerTeam(matches, data2, 2016);
        findTopEconomicalBowler(matches, data2, 2015);

    }

    private static ArrayList<Match> getMatchesData() {
        String line;
        ArrayList<Match> matches = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/gagan/IdeaProjects/IPL/matches.csv"));
            String fileHeading = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                Match match = new Match();

                match.setId(Integer.parseInt(parts[MATCH_ID]));
                match.setYear(Integer.parseInt(parts[MATCH_YEAR]));
                match.setCity(parts[MATCH_CITY]);
                match.setDate(parts[MATCH_DATE]);
                match.setTeam1(parts[MATCH_TEAM1]);
                match.setTeam2(parts[MATCH_TEAM2]);
                match.setToss_winner(parts[MATCH_TOSS_WINNER]);
                match.setToss_decision(parts[MATCH_TOSS_DECISION]);
                match.setResult(parts[MATCH_RESULT]);
                match.setDl(parts[MATCH_DL]);
                match.setWinner(parts[MATCH_WINNER]);
                match.setWins_by_run(Integer.parseInt(parts[MATCH_WIN_BY_RUNS]));
                match.setWins_by_wicket(Integer.parseInt(parts[MATCH_WIN_BY_WICKETS]));
                match.setPlayer(parts[MATCH_PLAYER]);
                match.setVenue(parts[MATCH_VENUE]);
                match.setUmpire1(parts[MATCH_UMPIRE1]);
                match.setUmpire2(parts[MATCH_UMPIRE2]);
                matches.add(match);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static ArrayList<Delivery> getDeliveriesData() {
        String line;
        ArrayList<Delivery> deliveries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/gagan/IdeaProjects/IPL/deliveries.csv"));
            String fileHeading = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                Delivery delivery = new Delivery();
                delivery.setMatch_id(Integer.parseInt(parts[MATCH_ID]));
                delivery.setInning(Integer.parseInt(parts[DELIVERY_INNING]));
                delivery.setBatting_team(parts[DELIVERY_BATTING_TEAM]);
                delivery.setBowling_team(parts[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(parts[DELIVERY_OVER]));
                delivery.setBall(Integer.parseInt(parts[DELIVERY_BALL]));
                delivery.setBatsman(parts[DELIVERY_BATSMAN]);
                delivery.setNon_striker(parts[DELIVERY_NON_STRIKER]);
                delivery.setBowler(parts[DELIVERY_BOWLER]);
                delivery.setIs_super_over(Integer.parseInt(parts[DELIVERY_IS_SUPER_OVER]));
                delivery.setWide_runs(Integer.parseInt(parts[DELIVERY_RUNS]));
                delivery.setBye_runs(Integer.parseInt(parts[DELIVERY_BYE_RUNS]));
                delivery.setLegbye_runs(Integer.parseInt(parts[DELIVERY_LEGBYE_RUNS]));
                delivery.setNoball_runs(Integer.parseInt(parts[DELIVERY_NOBALL_RUNS]));
                delivery.setPenalty_runs(Integer.parseInt(parts[DELIVERY_PENALTY_RUNS]));
                delivery.setBatsman_runs(Integer.parseInt(parts[DELIVERY_BATSMAN_RUNS]));
                delivery.setExtra_runs(Integer.parseInt(parts[DELIVERY_EXTRA_RUNS]));
                delivery.setTotal_runs(Integer.parseInt(parts[DELIVERY_TOTAL_RUNS]));
                delivery.setPlayer_dismissed(parts[DELIVERY_PLAYER_DISMISSED]);
                delivery.setDismissal_kind(parts[DELIVERY_DISMISSAL_KIND]);
                delivery.setFielder(parts[DELIVERY_FIELDER]);

                deliveries.add(delivery);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;

    }

    private static void findMatchesPerYear(ArrayList<Match> matches) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Match match : matches) {
            map.put(match.getYear(), map.getOrDefault(match.getYear(), 0) + 1);
        }
        System.out.println("Q1.) Number of matches played per year in IPL:");
        System.out.println(map);
    }

    private static void findTeamWinsPerAllYear(ArrayList<Match> matches) {
        HashMap<String, Integer> map = new HashMap<>();

        for (Match match : matches) {
            String winner;
            if (match.getWinner().isEmpty()) {
                winner = "Result Not Declared";
            } else {
                winner = match.getWinner();
            }
            map.put(winner, map.getOrDefault(winner, 0) + 1);
        }
        System.out.println("Q2.) Number of matches won of all teams over all the years of IPL.");
        System.out.println(map);
    }

    public static void extraRunsConcededPerTeam(ArrayList<Match> matches, ArrayList<Delivery> deliveries, int year) {
        HashMap<String, Integer> map = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (isMatcingYear(matches, year, delivery)) {
                map.put(delivery.getBowling_team(), map.getOrDefault(delivery.getBowling_team(), 0) + delivery.getExtra_runs());
            }
        }
        System.out.println("Q3.) Extra runs for the year " + year + ":");
        System.out.println(map);
    }

    public static void findTopEconomicalBowler(ArrayList<Match> matches, ArrayList<Delivery> deliveries, int year) {
        HashMap<String, Integer> totalRuns = new HashMap<>();
        HashMap<String, Integer> validBalls = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (isMatcingYear(matches, year, delivery)) {
                totalRuns.put(delivery.getBowler(), totalRuns.getOrDefault(delivery.getBowler(), 0) + delivery.getTotal_runs());
            }
        }

        for (Delivery delivery : deliveries) {
            if (isMatcingYear(matches, year, delivery) && delivery.getNoball_runs() == 0 && delivery.getWide_runs() == 0) {
                validBalls.put(delivery.getBowler(), validBalls.getOrDefault(delivery.getBowler(), 0) + 1);
            }
        }

        HashMap<String, Double> economy = new HashMap<>();
        Set<String> keys = totalRuns.keySet();
        for (String key : keys) {
            int total_runs = totalRuns.get(key);
            float overs = (float) (validBalls.get(key)) / 6;

            economy.put(key, (double) (total_runs / overs));
        }

        List<Map.Entry<String, Double>> sortedEntries = economy.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue()).toList();

        System.out.println("Q.4) Top 10 Economical Bowlers: ");

        for (int i = 0; i  < 10; i++)
        {
            Map.Entry<String, Double> entry = sortedEntries.get(i);
            System.out.print(entry + ", ");
        }
    }

    private static boolean isMatcingYear(ArrayList<Match> data, int year, Delivery delivery) {
        for (Match match : data) {
            if (match.getYear() == year && (match.getId()) == (delivery.getMatch_id())) {
                return true;
            }
        }
        return false;
    }
}