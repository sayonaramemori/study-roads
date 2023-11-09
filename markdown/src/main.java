import static java.lang.System.*;
import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.io.IOException;

 public class main{
    public static void main(String[] args)throws IOException{
        if(args.length!=1){
            out.println("Please offer a file name");
        }
        else{
            reader in = new reader(args[0]);
        }
    }
}

class reader{
    private String content[] = new String[1024];
    public void print(){
        for(int i=0;;++i){
            if(content[i]==null)break;
            out.println(content[i]);
        }
    }
    String index(int lineNumber){
        return new String(content[lineNumber]);
    }
    reader(String name) throws IOException{
        Scanner read = new Scanner(Path.of(name),StandardCharsets.UTF_8);
        int i=0;
        while(read.hasNext()){
            var line = read.nextLine();
            out.println(line);
            content[i++] = new String(line);
        }
    }
}

class checkSyntax{
    public static boolean isHeadline(String line){
        if(line.length()==0)return false;
        var temp = line.trim();
        if(temp[0]=='#')return true;
        else return false;
    }
    public static boolean wellNumber(String headline){

    }
    public static 
}







