public class hello{
    public static void main(String[] args){
        boolean state=true;
        System.out.println("Hello world");
        String str_list[] = new String[5];
        for(int i=0;i<str_list.length;++i){
            double r = Math.random();
            System.out.println(r);
            System.out.println(str_list[i]);
        }
        person temp = new person();
        System.out.println(temp);
    }
}


