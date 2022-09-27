import java.util.ArrayList;
import java.util.List;

public class CheckQuestion {
    public static List<DataNode> list1=new ArrayList<>();//储存待测数据
    public static List<DataNode> list2=new ArrayList<>();
    public static boolean check(String[] number,char[] symbol){
        if(Test.QuestionSymbol.size()==0){//当题库为空证明肯定不重复，直接返回true
        }else{//若不为空则遍历题库，根据题目要求可知，如果运算符的运算顺序相同且操作数也相同则重复，否则不重复
            //先按实际运算符的执行顺序排序，排序结果放在List<dataNode>,dataNode中储存执行的运算符，和前后操作符的位置
            //构建生成List<dataNode>的方法
            GetList(list1,symbol);//得到list1
            for(int i=0;i<Test.QuestionSymbol.size();i++){//遍历所有题目运算符并生成对应list
                GetList(list2, Test.QuestionSymbol.get(i));
                int n=0;
                if(list1.size()== list2.size()){
                    for(int j=0;j< list2.size();j++){
                        if(list1.get(j).symbol!=list2.get(j).symbol){
                            n++;
                            break;
                        }
                    }
                    if(n==0){//n==0证明运算符全部相等，然后比较操作数，如果操作数也重复，则重复
                        if(CompareNumber(number,list1,Test.QuestionNumber.get(i),list2)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static void GetList(List<DataNode> list1, char[] symbol){
        char[] symbol1=symbol.clone();
        DataNode dataNode;
        for(int i = 0;!Character.isSpaceChar(symbol1[i]); i++){//先找出括号，因为括号优先级最高，找出后为了后面方便也要把它删除
            if(symbol1[i]=='('){
                dataNode=new DataNode(symbol1[i+1],i,i+1);
                symbol1[i+1]=' ';
                symbol1[i+2]=' ';//把括号检测出来后删除
                symbol1[i]=' ';//删除括号中的运算符，避免重复检测
                int t=i;
                for(;!Character.isSpaceChar(symbol1[t+3]);t++){
                    symbol1[t]=symbol1[t+3];
                    symbol1[t+3]=' ';
                }
                list1.add(dataNode);
            }
        }
        for(int i=0;!Character.isSpaceChar(symbol1[i]);i++){//找乘除
            if(symbol1[i]=='*'||symbol1[i]=='/'){
                dataNode=new DataNode(symbol1[i],i,i+1);
                list1.add(dataNode);
            }
        }
        for(int i=0;!Character.isSpaceChar(symbol1[i]);i++){
            if(symbol1[i]=='+'||symbol1[i]=='-'){
                dataNode=new DataNode(symbol1[i],i,i+1);
                list1.add(dataNode);
            }
        }
    }
    public static boolean CompareNumber(String[] a,List<DataNode> list1,String[] b,List<DataNode> list2){//如果重复返回true
        //campare 0
        //judge 1second 0first /1first 0second
        //true,campare 1frist/1second,ture to compare latest
        //false ture to ture
        int first1,first2;//左操作数所在的位置
        int second1,second2;//右操作数的位置
        for(int i=0;i<list1.size();i++){
            first1=list1.get(i).first;//找出两组数据的第一个运算符的左右操作数
            second1=list1.get(i).second;
            first2=list2.get(i).first;
            second2=list2.get(i).second;
            //第一个比较两边
            if(i==0){
                if(a[first1].equals(b[first2])&&a[second1].equals(b[second2])||a[first1].equals(b[second2])&&a[second1].equals(b[first2])){//
                    continue;
                }else {//其他情况直接break,后返回false
                    break;
                }
            }
            if(i==1){//第二个运算符，先判断是不是衔接的
                if(list1.get(i).first!=list1.get(i-1).second&&list1.get(i).second!=list1.get(i-1).first){//如果不衔接
                    if(list2.get(i).first!=list2.get(i-1).second&&list2.get(i).second!=list2.get(i-1).first){//检测数据也不衔接
                        if(a[first1].equals(b[first2])&&a[second1].equals(b[second2])||a[first1].equals(b[second2])&&a[second1].equals(b[first2])){//且重复，返回true,不用看最后一个
                            return true;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
                }else {//衔接，看下一个操作数（非衔接端）
                    if(list2.get(i).first==list2.get(i-1).second||list2.get(i).second==list2.get(i-1).first){//检测数据也要衔接
                        //找出要比较的操作数
                        int toCompare1,toCompare2;
                        if(list1.get(i).first==list1.get(i-1).second){
                            toCompare1=list1.get(i).second;
                        }else {//进来这里肯定是衔接的，else直接赋值另一个
                            toCompare1=list1.get(i).first;
                        }
                        if(list2.get(i).first==list2.get(i-1).second){
                            toCompare2=list2.get(i).second;
                        }else {
                            toCompare2=list2.get(i).first;
                        }
                        if(a[toCompare1].equals(b[toCompare2])){//比较重复则继续,如果只有两个字符则返回true
                            if(list1.size()==2){
                                return true;
                            }else{
                                continue;
                            }
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
                }
            }
            if(i==2){//第三个字符，前面解决的第二个运算符且不衔接的情况，其实是三个运算符的情况，所以执行这段的情况只会是共有三个运算符且前面都衔接，只需要比较最后一个的second
                if(a[second1].equals(b[second2])){//最后一个操作数是否重复
                    return true;
                }else{
                    break;
                }
            }
            if(list1.size()==1){//上面已经解决了2、3个运算符的算式重复的情况，现在补充只有一个字符且重复的情况，能执行到这怎么重复
                return true;
            }
        }
        return false;
    }
}
