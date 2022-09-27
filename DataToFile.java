import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DataToFile {
    public static void output(List<String> strArray,String place) {
        FileOutputStream fileOutputStream=null;
        byte[] bytes;
        try {
            fileOutputStream = new FileOutputStream(place);
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
