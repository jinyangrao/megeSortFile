import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGen {
     void genFile(String filePath, int numCount) {
         try {
             File file = new File(filePath);
             file.mkdirs();
             file.createNewFile();

             //1000,0000=109M
//             int numCount = 1000;
             String s = null;
             Random random = new Random();
             if (file.exists()) {
                 file.delete();
             }

             FileWriter fw = new FileWriter(file);

             for (int i = 0; i < numCount; i++) {
                 s = Integer.toString(random.nextInt());
                 fw.write(s + "\n");
             }
             fw.close();
         }catch (IOException e) {
             e.printStackTrace();
         }
     }
}
