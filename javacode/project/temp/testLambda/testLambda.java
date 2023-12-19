public class testLambda
{
    public static void main(String[] args){
        int[] val = {1,2,3,4,5,6,7};
        operate(val,e->System.out.print((e+3) + " "));
    }

    public static void operate(int[] val, increment it){
        for(int i=0;i<val.length;++i){
            it.test(val[i]);
        }
    }
}

interface increment
{
    abstract void test(int args);
}
