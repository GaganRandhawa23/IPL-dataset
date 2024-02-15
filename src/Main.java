import java.io.*;
import java.sql.*;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 1;
    public static final int MATCH_YEAR = 2;
    public static final int MATCH_CITY = 3;
    public static final int MATCH_DATE = 4;
    public static final int MATCH_TEAM1 = 5;
    public static final int MATCH_TEAM2 = 6;
    public static final int MATCH_TOSS_WINNER = 7;
    public static final int MATCH_TOSS_DECISION = 8;
    public static final int MATCH_RESULT = 9;
    public static final int MATCH_DL = 10;
    public static final int MATCH_WINNER = 11;
    public static final int MATCH_WIN_BY_RUNS = 12;
    public static final int MATCH_WIN_BY_WICKETS = 13;
    public static final int MATCH_PLAYER = 14;
    public static final int MATCH_VENUE = 15;
    public static final int MATCH_UMPIRE1 = 16;
    public static final int MATCH_UMPIRE2 = 17;
    public static final int DELIVERY_INNING = 2;
    public static final int DELIVERY_BATTING_TEAM = 3;
    public static final int DELIVERY_BOWLING_TEAM = 4;
    public static final int DELIVERY_OVER = 5;
    public static final int DELIVERY_BALL = 6;
    public static final int DELIVERY_BATSMAN = 7;
    public static final int DELIVERY_NON_STRIKER = 8;
    public static final int DELIVERY_BOWLER = 9;
    public static final int DELIVERY_IS_SUPER_OVER = 10;
    public static final int DELIVERY_RUNS = 11;
    public static final int DELIVERY_BYE_RUNS = 12;
    public static final int DELIVERY_LEGBYE_RUNS = 13;
    public static final int DELIVERY_NOBALL_RUNS = 14;
    public static final int DELIVERY_PENALTY_RUNS = 15;
    public static final int DELIVERY_BATSMAN_RUNS = 16;
    public static final int DELIVERY_EXTRA_RUNS = 17;
    public static final int DELIVERY_TOTAL_RUNS = 18;
    public static final int DELIVERY_PLAYER_DISMISSED = 19;
    public static final int DELIVERY_DISMISSAL_KIND = 20;
    public static final int DELIVERY_FIELDER = 21;


    public static void main(String[] args) {
        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveriesData();

        findMatchesPerYear(matches);
        //showdata(deliveries);

        findTeamWinsPerAllYear(matches);
        findExtraRunsConcededPerTeam(matches, deliveries, 2016);
        findTopEconomicalBowler(matches, deliveries, 2015);
        findHighestWicketTakerPerTeam(matches, deliveries, 2015);

    }

    private static List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();

        String sql = "SELECT * FROM matches";
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "Randhawa@2024";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Match match = new Match();

                match.setId(resultSet.getInt(MATCH_ID));
                match.setYear(resultSet.getInt(MATCH_YEAR));
                match.setCity(resultSet.getString(MATCH_CITY));
                match.setDate(resultSet.getString(MATCH_DATE));
                match.setTeam1(resultSet.getString(MATCH_TEAM1));
                match.setTeam2(resultSet.getString(MATCH_TEAM2));
                match.setTossWinner(resultSet.getString(MATCH_TOSS_WINNER));
                match.setTossDecision(resultSet.getString(MATCH_TOSS_DECISION));
                match.setResult(resultSet.getString(MATCH_RESULT));
                match.setDl(resultSet.getString(MATCH_DL));
                match.setWinner(resultSet.getString(MATCH_WINNER));
                match.setWinsByRun(resultSet.getInt(MATCH_WIN_BY_RUNS));
                match.setWinsByWicket(resultSet.getInt(MATCH_WIN_BY_WICKETS));
                match.setPlayer(resultSet.getString(MATCH_PLAYER));
                match.setVenue(resultSet.getString(MATCH_VENUE));
                match.setUmpire1(resultSet.getString(MATCH_UMPIRE1));
                match.setUmpire2(resultSet.getString(MATCH_UMPIRE2));

                matches.add(match);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static List<Delivery> getDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();

        String sql = "SELECT * FROM deliveries";
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "Randhawa@2024";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {

            while (resultSet.next()) {
                Delivery delivery = new Delivery();

                delivery.setMatchId(resultSet.getInt(MATCH_ID));
                delivery.setInning(resultSet.getInt(DELIVERY_INNING));
                delivery.setBattingTeam(resultSet.getString(DELIVERY_BATTING_TEAM));
                delivery.setBowlingTeam(resultSet.getString(DELIVERY_BOWLING_TEAM));
                delivery.setOver(resultSet.getInt(DELIVERY_OVER));
                delivery.setBall(resultSet.getInt(DELIVERY_BALL));
                delivery.setBatsman(resultSet.getString(DELIVERY_BATSMAN));
                delivery.setNonStriker(resultSet.getString(DELIVERY_NON_STRIKER));
                delivery.setBowler(resultSet.getString(DELIVERY_BOWLER));
                delivery.setIsSuperOver(resultSet.getInt(DELIVERY_IS_SUPER_OVER));
                delivery.setWideRuns(resultSet.getInt(DELIVERY_RUNS));
                delivery.setByeRuns(resultSet.getInt(DELIVERY_BYE_RUNS));
                delivery.setLegbyeRuns(resultSet.getInt(DELIVERY_LEGBYE_RUNS));
                delivery.setNoballRuns(resultSet.getInt(DELIVERY_NOBALL_RUNS));
                delivery.setPenaltyRuns(resultSet.getInt(DELIVERY_PENALTY_RUNS));
                delivery.setBatsmanRuns(resultSet.getInt(DELIVERY_BATSMAN_RUNS));
                delivery.setExtraRuns(resultSet.getInt(DELIVERY_EXTRA_RUNS));
                delivery.setTotalRuns(resultSet.getInt(DELIVERY_TOTAL_RUNS));
                delivery.setPlayerDismissed(resultSet.getString(DELIVERY_PLAYER_DISMISSED));
                delivery.setDismissalKind(resultSet.getString(DELIVERY_DISMISSAL_KIND));
                delivery.setFielder(resultSet.getString(DELIVERY_FIELDER));

                deliveries.add(delivery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveries;

    }

    private static void showdata(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            System.out.println(delivery.getMatchId());
        }

    }

    private static void findMatchesPerYear(List<Match> matches) {
        HashMap<Integer, Integer> matchesPerYear = new HashMap<>();

        for (Match match : matches) {
            matchesPerYear.put(match.getYear(), matchesPerYear.getOrDefault(match.getYear(), 0) + 1);
        }
        System.out.println("Q1.) Number of matches played per year in IPL:");
        System.out.println(matchesPerYear);
    }

    private static void findTeamWinsPerAllYear(List<Match> matches) {
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

    public static void findExtraRunsConcededPerTeam(List<Match> matches, List<Delivery> deliveries, int year) {
        HashMap<String, Integer> extraRunConcededPerTeam = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (isMatchingYear(matches, year, delivery)) {
                extraRunConcededPerTeam.put(delivery.getBowlingTeam(), extraRunConcededPerTeam.getOrDefault(delivery.getBowlingTeam(), 0) + delivery.getExtraRuns());
            }
        }
        System.out.println("Q3.) Extra runs for the year " + year + ":");
        System.out.println(extraRunConcededPerTeam);
    }

    public static void findTopEconomicalBowler(List<Match> matches, List<Delivery> deliveries, int year) {
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

    public static void findHighestWicketTakerPerTeam(List<Match> matches, List<Delivery> deliveries, int year) {
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

    private static boolean isMatchingYear(List<Match> data, int year, Delivery delivery) {
        for (Match match : data) {
            if (match.getYear() == year && (match.getId()) == (delivery.getMatchId())) {
                return true;
            }
        }
        return false;
    }
}
