public class Matches {
    public int id, year, wins_by_run, wins_by_wicket;
    public String city, date, team1, team2, toss_winner, toss_decision, result, dl, winner, player, venue, umpire1, umpire2;

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