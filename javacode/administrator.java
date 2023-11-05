public class administrator{
    public static void main(String[] args){
        var a = new person();
        System.out.println(a);
        a.show_msg();
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
    /*
    public person(){
        System.out.println("Person constructor is called");
    }
    */
    public static void main(String args[]){
        var test = "This is the person main method";
        System.out.println(test);
    }
    @Override
    public String toString(){
        return "this person is a fool";
    }
}
