public class main{
    public static void main(String[] args){
        var a = new person();
        System.out.println(a);
    }
}

class person{
    public person(){}
    @Override
    public String toString(){
        return "this person is a fool";
    }
}
