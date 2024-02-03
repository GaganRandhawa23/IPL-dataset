public class Deliveries {
    int match_id, inning, over, ball, is_super_over, wide_runs, bye_runs, legbye_runs, noball_runs, penalty_runs, batsman_runs, extra_runs, total_runs;
    public String batting_team, bowling_team, batsman, bowler, non_striker, player_dismissed, dismissal_kind, fielder;


    public Deliveries(String s )
    {
      String[] parts = s.split(",");
      this.match_id = Integer.parseInt(parts[0]);
      this.inning= Integer.parseInt(parts[1]);
      this.batting_team =parts[2];
      this.bowling_team =parts[3];
      this.over= Integer.parseInt(parts[4]);
      this.ball=Integer.parseInt(parts[5]);
      this.batsman=parts[6];
      this.non_striker=parts[7];
      this.bowler=parts[8];
      this.is_super_over= Integer.parseInt(parts[9]);
      this.wide_runs= Integer.parseInt(parts[10]);
      this.bye_runs= Integer.parseInt(parts[11]);
      this.legbye_runs= Integer.parseInt(parts[12]);
      this.noball_runs= Integer.parseInt(parts[13]);
      this.penalty_runs= Integer.parseInt(parts[14]);
      this.batsman_runs= Integer.parseInt(parts[15]);
      this.extra_runs= Integer.parseInt(parts[16]);
      this.total_runs= Integer.parseInt(parts[17]);
        this.player_dismissed = (parts.length > 18 ) ? parts[8] : null;
        this.dismissal_kind = (parts.length > 19 ) ? parts[19] : null;
        this.fielder = (parts.length > 20 ) ? parts[20] : null;


    }



}
