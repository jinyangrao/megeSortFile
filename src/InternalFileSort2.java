import java.io.*;
import java.util.TreeSet;

public class InternalFileSort2 {
    TreeSet<String> fileSet;

    public InternalFileSort2() {this.fileSet = new TreeSet<String>();}

    public void addStringToTreeSet(String line) {fileSet.add(line);}

    public void readFileToTreeSet(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                addStringToTreeSet(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    void writeTreeSetToFile(String filePath) {
        File file = new File(filePath);
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            OutputStreamWriter outputSR = new OutputStreamWriter(fileOutput);
            BufferedWriter bufferedWriter = new BufferedWriter(outputSR);

            for(String line: fileSet) {
                bufferedWriter.write(line + "\n");
            }

            bufferedWriter.close();
            outputSR.close();
            fileOutput.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
