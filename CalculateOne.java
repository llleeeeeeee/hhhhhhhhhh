import java.util.ArrayList;
import java.util.List;

public class CalculateOne {
    public static String cal(String [] number,char[] symbol){
        String str1;
        str1 = new String(symbol);
        Operator operator = new Operator();
        List<String> listOperator = new ArrayList<>();
        List<String> listNum = new ArrayList<>();
        for (int m = 0; m < str1.length(); m++) {
            if(str1.charAt(m)!=' '){
                listOperator.add(m,String.valueOf(str1.charAt(m)));
            }else{
                break;
            }
        }
        for (int n = 0; n < number.length; n++) {
            if(number[n]!=null){
                listNum.add(number[n]);
            }else {
                break;
            }
        }
        return operator.calc(listNum,listOperator);
    }
}
