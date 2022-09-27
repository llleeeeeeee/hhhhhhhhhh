import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class QuestionToFile {
    public static void output(List<String> strArray) {
        FileOutputStream fileOutputStream=null;
        byte[] bytes;
        try {
            fileOutputStream = new FileOutputStream("Exercises.txt");
            for(String str:strArray){
            bytes=str.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            }
            fileOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
            if(fileOutputStream!=null){
                fileOutputStream.close();
            }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
