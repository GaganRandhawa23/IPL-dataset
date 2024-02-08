import java.io.*;
import java.util.*;

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
        ArrayList<Delivery> deliveries = getDeliveriesData();

        findMatchesPerYear(matches);
        findTeamWinsPerAllYear(matches);
        findExtraRunsConcededPerTeam(matches, deliveries, 2016);
        findTopEconomicalBowler(matches, deliveries, 2015);
        findHighestWicketTakerPerTeam(matches, deliveries, 2015);

    }

    private static ArrayList<Match> getMatchesData() {
        String line;
        ArrayList<Match> matches = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("matches.csv"));
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
                match.setTossWinner(parts[MATCH_TOSS_WINNER]);
                match.setTossDecision(parts[MATCH_TOSS_DECISION]);
                match.setResult(parts[MATCH_RESULT]);
                match.setDl(parts[MATCH_DL]);
                match.setWinner(parts[MATCH_WINNER]);
                match.setWinsByRun(Integer.parseInt(parts[MATCH_WIN_BY_RUNS]));
                match.setWinsByWicket(Integer.parseInt(parts[MATCH_WIN_BY_WICKETS]));
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
            BufferedReader reader = new BufferedReader(new FileReader("deliveries.csv"));
            String fileHeading = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                Delivery delivery = new Delivery();
                delivery.setMatchId(Integer.parseInt(parts[MATCH_ID]));
                delivery.setInning(Integer.parseInt(parts[DELIVERY_INNING]));
                delivery.setBattingTeam(parts[DELIVERY_BATTING_TEAM]);
                delivery.setBowlingTeam(parts[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(parts[DELIVERY_OVER]));
                delivery.setBall(Integer.parseInt(parts[DELIVERY_BALL]));
                delivery.setBatsman(parts[DELIVERY_BATSMAN]);
                delivery.setNon_striker(parts[DELIVERY_NON_STRIKER]);
                delivery.setBowler(parts[DELIVERY_BOWLER]);
                delivery.setIsSuperOver(Integer.parseInt(parts[DELIVERY_IS_SUPER_OVER]));
                delivery.setWideRuns(Integer.parseInt(parts[DELIVERY_RUNS]));
                delivery.setByeRuns(Integer.parseInt(parts[DELIVERY_BYE_RUNS]));
                delivery.setLegbyeRuns(Integer.parseInt(parts[DELIVERY_LEGBYE_RUNS]));
                delivery.setNoballRuns(Integer.parseInt(parts[DELIVERY_NOBALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(parts[DELIVERY_PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(parts[DELIVERY_BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(parts[DELIVERY_EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(parts[DELIVERY_TOTAL_RUNS]));
                delivery.setPlayerDismissed(parts[DELIVERY_PLAYER_DISMISSED]);
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
        HashMap<Integer, Integer> matchesPerYear = new HashMap<>();

        for (Match match : matches) {
            matchesPerYear.put(match.getYear(), matchesPerYear.getOrDefault(match.getYear(), 0) + 1);
        }
        System.out.println("Q1.) Number of matches played per year in IPL:");
        System.out.println(matchesPerYear);
    }

    private static void findTeamWinsPerAllYear(ArrayList<Match> matches) {
        HashMap<String, Integer> teamWinsPerYear = new HashMap<>();

        for (Match match : matches) {
            String winningTeamName;
            if (match.getWinner().isEmpty()) {
                winningTeamName = "Result Not Declared";
            } else {
                winningTeamName = match.getWinner();
            }
            teamWinsPerYear.put(winningTeamName, teamWinsPerYear.getOrDefault(winningTeamName, 0) + 1);
        }
        System.out.println("Q2.) Number of matches won of all teams over all the years of IPL.");
        System.out.println(teamWinsPerYear);
    }

    public static void findExtraRunsConcededPerTeam(ArrayList<Match> matches, ArrayList<Delivery> deliveries, int year) {
        HashMap<String, Integer> extraRunConcededPerTeam = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (isMatchingYear(matches, year, delivery)) {
                extraRunConcededPerTeam.put(delivery.getBowlingTeam(), extraRunConcededPerTeam.getOrDefault(delivery.getBowlingTeam(), 0) + delivery.getExtraRuns());
            }
        }
        System.out.println("Q3.) Extra runs for the year " + year + ":");
        System.out.println(extraRunConcededPerTeam);
    }

    public static void findTopEconomicalBowler(ArrayList<Match> matches, ArrayList<Delivery> deliveries, int year) {
        HashMap<String, Integer> totalRuns = new HashMap<>();
        HashMap<String, Integer> validBallsCount = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (isMatchingYear(matches, year, delivery)) {
                totalRuns.put(delivery.getBowler(), totalRuns.getOrDefault(delivery.getBowler(), 0) + delivery.getTotalRuns());
            }
        }

        for (Delivery delivery : deliveries) {
            if (isMatchingYear(matches, year, delivery) && delivery.getNoballRuns() == 0 && delivery.getWideRuns() == 0) {
                validBallsCount.put(delivery.getBowler(), validBallsCount.getOrDefault(delivery.getBowler(), 0) + 1);
            }
        }

        HashMap<String, Double> economy = new HashMap<>();
        Set<String> keys = totalRuns.keySet();
        for (String key : keys) {
            int total_runs = totalRuns.get(key);
            float overs = (float) (validBallsCount.get(key)) / 6;

            economy.put(key, (double) (total_runs / overs));
        }

        List<Map.Entry<String, Double>> sortedEntries = economy.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue()).toList();

        System.out.println("Q.4) Top 10 Economical Bowlers: ");

        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Double> entry = sortedEntries.get(i);
            System.out.println(entry + ", ");
        }
    }

    public static void findHighestWicketTakerPerTeam(ArrayList<Match> matches, ArrayList<Delivery> deliveries, int year) {
        Map<String, Map<String, Integer>> bowlersWithMaxWicketsPerTeam = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (isMatchingYear(matches, year, delivery) && !delivery.getPlayerDismissed().isEmpty()) {
                Map<String, Integer> bowlerWicketCount = bowlersWithMaxWicketsPerTeam.get(delivery.getBowlingTeam());
                if (bowlerWicketCount == null) {
                    bowlerWicketCount = new HashMap<>();
                }
                bowlerWicketCount.put(delivery.getBowler(), bowlerWicketCount.getOrDefault(delivery.getBowler(), 0) + 1);
                bowlersWithMaxWicketsPerTeam.put(delivery.getBowlingTeam(), bowlerWicketCount);
            }
        }
        System.out.println("Q5.) Highest Wicket-Taker for Each Team in 2015:");

        for (Map.Entry<String, Map<String, Integer>> entry : bowlersWithMaxWicketsPerTeam.entrySet()) {
            String teamName = entry.getKey();
            Map<String, Integer> bowlerWicketCount = entry.getValue();
            Map.Entry<String, Integer> maxWicketEntry = Collections.max(bowlerWicketCount.entrySet(), Map.Entry.comparingByValue());

            System.out.println(teamName + " " + maxWicketEntry.getKey() + " " + maxWicketEntry.getValue());
        }
    }


    private static boolean isMatchingYear(ArrayList<Match> data, int year, Delivery delivery) {
        for (Match match : data) {
            if (match.getYear() == year && (match.getId()) == (delivery.getMatchId())) {
                return true;
            }
        }
        return false;
    }

}
