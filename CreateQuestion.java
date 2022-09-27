import static java.lang.String.*;

public class CreateQuestion {
    public static String create(int r,int num) {
        //[0,1)，用随机数来实现随机生成题目中的运算符类型和运算符的数量
        //运算符数量最多三个，类型+ - ×  ÷ ()
        int a = (int) (Math.random() * 3 + 1);//运算符的数目
        int c = (int) (Math.random() * r);//在范围中找出一个随机数作为第一个数字
        String[] number = new String[5];//储存数值
        int j = 0;//记录下一个数值储存的位置
        char[] symbol = {' ',' ',' ',' ',' ',' ',' '};//储存运算符
        int k = 0;//记录下一个运算符储存的位置
        int first;//记录分子，用于真分数的转换
        int place = 0;//记录括号位置
        if((int) (Math.random() * 2)==1&&a!=1){//判断有无括号,0为无，1为有//有时则随机在某个位置插入括号//只有一个运算符时括号没意义排除a==1
            place=(int) (Math.random()*a +1);//括号的位置取值范围是1到a
        }
        while(c==0){
            c=(int) (Math.random()*r);
        }
        number[j] = valueOf(c);
        String str;
        first = c;
        if (((int) (Math.random() * 2) == 0)) {//随机选择第一个是不是分数，0为分数
            while (first == c || c == 0||c==1) {
                c = (int) (Math.random() * r);
            }
            number[j] = number[j].concat("/");
            number[j] = number[j].concat(valueOf(c));
            if (first < c) {//小于就直接储存
                if(place==1){
                    str="(";
                    symbol[k++]='(';
                    str=str.concat(String.valueOf(first));
                }
                else{
                str = String.valueOf(first);
                }
                str = str.concat("/");
                str = str.concat(valueOf(c));
            } else {
                if(place==1){
                    symbol[k++]='(';
                    str="(";
                    str=str.concat(String.valueOf(first/c));
                }
                else{
                str = String.valueOf(first / c);
                }
                if(first%c!=0){
                    str=str.concat("'");
                    str=str.concat(String.valueOf(first%c));
                    str = str.concat("/");
                    str = str.concat(valueOf(c));
                }
            }

        } else {
            if(place==1){
                str="(";
                symbol[k++]='(';
                str=str.concat(String.valueOf(first));
            }else{
            str = String.valueOf(first);}
        }
        j++;
        for (int t=1; t<=a; t++) {
            int type;
            type = (int) (Math.random() * 4);
            switch (type) {
                case 0://0时链接+
                    str = str.concat(" + ");
                    symbol[k++] = '+';
                    break;
                case 1://1时链接-
                    str = str.concat(" - ");
                    symbol[k++] = '-';
                    break;
                case 2://2时链接*
                    str = str.concat(" × ");
                    symbol[k++] = '*';
                    break;
                case 3://3时链接÷
                    str = str.concat(" ÷ ");
                    symbol[k++] = '/';
                    break;
            }
            if(t==place-1){//如果当前运算符的位置是括号添加位置的前面一个则在其添加数值前加“（”
                str=str.concat("(");
                symbol[k++]='(';
            }
            if (3 == type) {
                c = (int) (Math.random() * r);
                while (c == 0) {
                    c = (int) (Math.random() * r);
                }
//                str = str.concat(valueOf(c));
                first=c;
            } else {
//                str = str.concat(valueOf(c = (int) (Math.random() * r)));
                while((first=(int)(Math.random()*r))==0){
                    first=(int)(Math.random()*r);//先不赋值，转换真分数
                }

            }
            number[j] = valueOf(first);
            if (((int) (Math.random() * 2) == 0)) {//随机选择是不是分数，0为分数
//                c = (int) (Math.random() * r);
//                while (c == 0) {
//                    c = (int) (Math.random() * r);
//                }
//                str = str.concat("/");
//                number[j] = number[j].concat("/");
//                str = str.concat(valueOf(c));
//                number[j] = number[j].concat(valueOf(c));
                while(first==c||c==0||c==1){
                    c=(int)(Math.random()*r);
                }
                number[j]=number[j].concat("/");
                number[j]=number[j].concat(valueOf(c));
                if(first<c){//小于就直接储存
                    str=str.concat(String.valueOf(first));
                    str=str.concat("/");
                    str=str.concat(valueOf(c));
                }else{
                    str=str.concat(String.valueOf(first/c));
                    if(first%c!=0){
                        str=str.concat("'");
                        str=str.concat(String.valueOf(first%c));
                        str=str.concat("/");
                        str=str.concat(valueOf(c));
                    }
                }

            }else{//不为分数时
                str=str.concat(String.valueOf(first));
            }
            if(t==place){//如果t为括号添加的运算符位置，则在添加完该位置的数值后加“）”
                str=str.concat(")");
                symbol[k++]=')';
            }
                j++;
            }
        //查重，重复返回false,不重复返回true
        if(!CheckQuestion.check(number,symbol)){
            return CreateQuestion.create(r,num);
        }
        if((CalculateOne.cal(number,symbol))==null){
            return CreateQuestion.create(r,num);
        }
        Test.QuestionNumber.add(number);
        Test.QuestionSymbol.add(symbol);
        str = str.concat(" = ");
        String str1=String.valueOf(num+1);
        str1=str1.concat(". ");
        str=str1.concat(str);
        return str;
    }
}
