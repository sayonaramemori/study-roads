package temp.m2000;
import static java.lang.System.*;
import temp.foobar.*;

public class administrator{
    public static void main(String[] args){
        var a = new person();
        System.out.println(a);
        a.show_msg();
        out.println(Math.sqrt(2));
        test.show();
        test_access.show();
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


