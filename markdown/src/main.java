import static java.lang.System.*;
import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.io.IOException;

 public class main{
    public static void main(String[] args)throws IOException{
        if(args.length==0){
            out.println("Please offer a file name");
        }
        else{
            reader in = new reader("hama.txt");
            in.print();
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







