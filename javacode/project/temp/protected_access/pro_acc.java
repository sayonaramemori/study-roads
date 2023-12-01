public class pro_acc
{
    pro_acc(){
        pi = 3.14444;
    }
    protected double pi = 3.14;
    protected void show(){
        System.out.println("msg has been showed");
    }
}

class test
{
    test(){

    }
    public static void main(String[] argv)
    {
        var a = new pro_acc();
        a.show();
        System.out.println(a.pi);
    }
}
