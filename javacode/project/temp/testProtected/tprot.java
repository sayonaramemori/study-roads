public class tprot
{
    protected static void showmsg(){
        System.out.println("OUT");
    }
}

class testp{
    public static void main(String[] args){
        tprot.showmsg();
    }
}
