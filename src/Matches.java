public class Matches {
    public int id;
    public int year;
    public String city;
    public String date;
    public String team1;
    public String team2;
    public String toss_winner;
    public String toss_decision;
    public String result;
    public String dl;
    public String winner;
    public int wins_by_run;
    public int wins_by_wicket;
    public String player;
    public String venue;
    public String umpire1;
    public String umpire2;

    public Matches(String s)
    {
        String[] parts = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)|(?<=\")(,)(?=\")");
        this.id = Integer.parseInt(parts[0]);
        this.year = Integer.parseInt(parts[1]);
        this.city =parts[2];
        this.date=parts[3];
        this.team1=parts[4];
        this.team2=parts[5];
        this.toss_winner=parts[6];
        this.toss_decision=parts[7];
        this.result=parts[8];
        this.dl=parts[9];
        this.winner=parts[10];
        this.wins_by_run= Integer.parseInt(parts[11]);
        this.wins_by_wicket= Integer.parseInt(parts[12]);
        this.player=parts[13];
        this.venue=parts[14];
        if (parts.length > 15) {
            this.umpire1 = parts[15];
            if (parts.length > 16) {
                this.umpire2 = parts[16];
            } else {
                this.umpire2 = null; // Set umpire2 to null if not present
            }
        } else {
            this.umpire1 = null; // Set umpire1 to null if not present
            this.umpire2 = null; // Set umpire2 to null if not present
        }

    }
}