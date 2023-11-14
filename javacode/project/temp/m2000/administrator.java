package temp.m2000;
import static java.lang.System.*;
import temp.foobar.*;

interface socket
{
    double getCircle();
    double pi = 3.14;
}

class line implements socket
{
    @Override 
    public double getCircle(){
        return 99.0*pi;
    }
}

public class administrator{
    public static void main(String[] args){
        father f = new father();
        son s = new son();
        f.show(1);
        s.show(1);
        s.show("HHH");
        var a = new line();
        out.println(a.getCircle());
    }
}

class animal{
    public animal(){
        System.out.println("Animal constructor is called");
    }
    public void showMsg(){
        System.out.println("This is a pet with any type");
    }
}

class person{
    private animal pet;
    public void show_msg(){
        pet.showMsg();
    }
    public person(){
        pet = new animal();
        System.out.println("Person constructor is called");
    }
    public static void main(String args[]){
        var test = "This is the person main method";
        System.out.println(test);
    }
    @Override
    public String toString(){
        return "this person is a fool";
    }
}

class father{
    public void show(int a){
        out.println("father is number " + a);
    }
    public void show(String s){
        out.println("father string is " + s);
    }
}

class son extends father{
    public void show(int a){
        out.println("son is number" + a);
    }
}
