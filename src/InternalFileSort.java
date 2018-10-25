import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class InternalFileSort {

    ArrayList<String> fileArray;

    public InternalFileSort() {
        this.fileArray = new ArrayList<String>();
    }

    public void addStringToArrayList(String line) {
        this.fileArray.add(line);
    }



    void readFileToArrayList(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                addStringToArrayList(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeArrayListToFile(String filePath) {
        File file = new File(filePath);
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            OutputStreamWriter outputSR = new OutputStreamWriter(fileOutput);
            BufferedWriter bufferedWriter = new BufferedWriter(outputSR);

            for(int i = 0; i < fileArray.size(); i++)
                bufferedWriter.write(fileArray.get(i)+"\n");

            bufferedWriter.close();
            outputSR.close();
            fileOutput.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sortArryList() {
        fileArray.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
