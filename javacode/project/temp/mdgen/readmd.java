import static java.lang.System.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;

public class readLines
{
    @Override
    public String toString()
    {
        String ans = new String();
        for(int i=0;i<data.size();++i)
        {
            ans += data.get(i) + '\n';
        }
        return ans;
    }

    public void read(String fileName) throws IOException
    {
        Scanner scanner = new Scanner(Path.of(fileName),StandardCharsets.UTF_8);
        data = new ArrayList<>();
        while(scanner.hasNext())
            data.add(scanner.nextLine());
    }
    private ArrayList<String> data;
}


