import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("matches.csv"));
            System.out.println(reader.readLine());
            System.out.println("here it begins");

            while((line = reader.readLine()) != null)
            {
                System.out.println(line);

            }
            System.out.println( reader.readLine());
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}
