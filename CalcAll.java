import java.util.ArrayList;
import java.util.List;

public class CalcAll {
    public void calcAll(List<String[]> listNumArray,List<char[]> listOperatorArray ) throws Exception {
        Operator operator = new Operator();
        List<String> listOperator = new ArrayList<>();
        String str;
        List<String> listNum = new ArrayList<>();
        for (int i = 0; i < listOperatorArray.size(); i++) {
            str = new String(listOperatorArray.get(i));
            for (int j = 0; j < str.length(); j++) {
                if(str.charAt(j)!=' '){
                    listOperator.add(j,String.valueOf(str.charAt(j)));
                }else{
                    break;
                }
            }
            for (int n = 0; n < listNumArray.get(i).length; n++) {
                    if(listNumArray.get(i)[n]!=null){
                        listNum.add(listNumArray.get(i)[n]);
                    }else {
                        break;
                    }
                }
            str=operator.calc(listNum,listOperator);
            Test.AnswerArray.add(str);
            System.out.println(str);
            listNum.clear();
            listOperator.clear();
        }

    }
}
