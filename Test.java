import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static int n;
    public static int  r;
    public static List<String> QuestionArray=new ArrayList<>();//储存输出数据
    public static List<String []> QuestionNumber=new ArrayList<>();//传输数值用于计算
    public static List<char []> QuestionSymbol=new ArrayList<>();//传输运算符
    public static void main(String[] args) {
        //-n 10 -r 10根据参数生成相应题目的数目和范围
        //随机生成运算式子
        //判断是否重复
        //输出运算式
        //计算
        //输出答案
        try {
            CheckArg.main(args);
        } catch(ArgException a){
           a.printStackTrace();
        }
/*使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围
将生成10以内（不包括10）的四则运算题目。该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息。
        生成的题目中计算过程不能产生负数，也就是说算术表达式中如果存在形如e1− e2的子表达式，那么e1≥ e2。!!!!!!!!!!!
        生成的题目中如果存在形如e1÷ e2的子表达式，那么其结果应是真分数。
        生成的题目存入执行程序的当前目录下的Exercises.txt文件*/
        for(int num=0;num<n;num++){
            String str= CreateQuestion.create(r,num);
            //利用Question查重，如果不重复，保存到QuestionArray
            QuestionArray.add(str);
        }
        //计算并返回计算过程中会出现负数的序列号
        //删除返回的元素在QuestionArray中
        //将QuestionArray输出到Exercises.txt
        QuestionToFile.output(QuestionArray);
        for (String[] strings :QuestionNumber){
            for(String str:strings){
                System.out.print(str+" ");
            }
            System.out.println();
        }
        for (char[] chars :QuestionSymbol){
            for(char ch:chars){
                System.out.print(ch+"  ");
            }
            System.out.println();
        }
        // 将结果输出到Answer.txt
        CalcAll calcAll=new CalcAll();
        calcAll.calcAll(QuestionNumber,QuestionSymbol);


//        单独测试CheckQuestion
//        QuestionArray.add("1+2+3");
//        String[] str1={"1","2","3"};
//        QuestionNumber.add(str1);
//        char[] chars={'+','(','+',')',' '};
//        QuestionSymbol.add(chars);
//        String[] str2={"3", "2", "1"};
//        char[] chars1={'+','+',' ',' ',' '};
//        if(CheckQuestion.check(str2,chars1)){
//            System.out.println("不重复");
//        }else{
//        System.out.println("重复");}
    }
}

