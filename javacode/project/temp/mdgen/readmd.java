import static java.lang.System.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.*;


public class readmd
{
    public static void main(String argv[]) throws IOException
    {
        if(argv.length!=1)
        {
            out.println("Please offer a filename");
            return;
        }
        Scanner scanner = new Scanner(Path.of(argv[0]),StandardCharsets.UTF_8);
        readLines test = new readLines(scanner);
        //out.print(test);
    }
}

class HeadLine
{
    public static boolean check(String line)
    {
        if(line.isBlank())return false;
        if(line.charAt(0)=='#')return true;
        else return false;
    }
    /**
     * @param the certain headline, no checking again
     * @return the content for anchor
     */
    public static String getContent(String line)
    {
        var ans = line.substring(line.indexOf(' ')).trim();
        return ans;
    }
}

class readLines
{
    public static void main(String argv[])
    {   

    }
    @Override
    public String toString(){
        String ans = new String();
        for(int i=0;i<data.size();++i)
        {
            ans += data.get(i);
        }
        return ans;
    }
    public readLines(Scanner scanner) throws IOException
    {
        data = new ArrayList<>();
        while(scanner.hasNext()){
            data.add(scanner.nextLine());
        }
    }
    private ArrayList<String> data;
    private ArrayList<Integer>  headLineIndex;
}


