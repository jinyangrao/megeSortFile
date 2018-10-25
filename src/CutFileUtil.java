import java.io.*;
import java.util.ArrayList;

public class CutFileUtil {

    ArrayList<String> filesName = null;

    CutFileUtil() {
        filesName = new ArrayList<>();
    }

    void storeFilesName(String name) {
        filesName.add(name);
    }

    ArrayList<String> getFilesName() {
        return filesName;
    }

    void cutBigFile(String filePath, int fileNum) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        InputStreamReader inputSR = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputSR);

        String lineStr = null;

        String path = null;

        int linesNum = getFileLiensNum(filePath);
        System.out.println("file total lines: " + linesNum);

        path = new File(filePath).getParent();
        System.out.println("path: " + path);
        int cutFileSize = (int) Math.ceil(linesNum * 1.0 / fileNum);
        System.out.println("cut big file into " + fileNum + ", each small file is " + cutFileSize + " lines");
        for (int i = 0; i < fileNum; i++) {
            int count = 1;

            String cutFilePath = path + "/" + i + "_tmp.txt";

            storeFilesName(cutFilePath);

            File smallFile = new File(cutFilePath);


            FileOutputStream fileOutput = new FileOutputStream(smallFile);
            OutputStreamWriter outputSR = new OutputStreamWriter(fileOutput);
            BufferedWriter bufferedWriter = new BufferedWriter(outputSR);
            while (count <= cutFileSize) {
                if ((lineStr = bufferedReader.readLine()) != null) {

                    bufferedWriter.write(lineStr + "\n");
                }
                count++;

            }
            bufferedWriter.close();
            outputSR.close();
            fileOutput.close();
        }
    }

    int getFileLiensNum(String filePath) {
        int linesNum = 0;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException(filePath + " not found");
            }
            long fileLength = file.length();
            FileReader fileReader = new FileReader(file);
            LineNumberReader lineReader = new LineNumberReader(fileReader);
            lineReader.skip(fileLength);

            //lines num is int type
            linesNum = lineReader.getLineNumber();

            fileReader.close();
            lineReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linesNum;
    }
}

