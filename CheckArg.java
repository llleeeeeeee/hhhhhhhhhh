import java.nio.charset.StandardCharsets;

public class CheckArg {
    public static void main(String[] args) throws ArgException {
        if (args.length != 4) {
            throw new ArgException("参数数目有误");
        }
        int b = 0;
        for (int t = 0; t < 4; t++) {
            if (args[t].equals("-n")) {
                System.out.print("生成题目的数目是：" + " ");
                System.out.println(args[t + 1]);
                Integer i=Integer.valueOf(args[t+1]);
                if(i<1){
                    throw new ArgException("数目不能少于1");
                }
                Test.n=i;
                b++;
            }
            if (args[t].equals("-r")) {
                System.out.print("生成题目的数值范围是：" + " ");
                System.out.println(args[t + 1]);
                Integer i=Integer.valueOf(args[t+1]);
                if(i<1){
                    throw new ArgException("题目数值不能低于1");
                }
                Test.r=i;
                b++;
            }
        }
        if (b != 2) {
            throw new ArgException();
        }
    }
}
