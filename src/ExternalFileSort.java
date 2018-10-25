import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ExternalFileSort {


    public static void main(String[] args) {
        String path = "/home/pumbaa/tmpDir/test2/";
        String fileName = "sourcedata_test1.txt";

        int fileNum = 3;

        //1000,0000=109M
        int numCoun = 300000000;

       FileGen fileGen = new FileGen();
       fileGen.genFile(path + fileName, numCoun);

        CutFileUtil cutFileUtil = new CutFileUtil();
        try {
            cutFileUtil.cutBigFile(path + fileName, fileNum);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> filesName = cutFileUtil.getFilesName();

        for (int i = 0; i < filesName.size(); i++) {
            InternalFileSort inFile = new InternalFileSort();
            String filePath = filesName.get(i);

            System.out.println("sorted filePath: " + filePath);

            inFile.readFileToArrayList(filePath);
            inFile.sortArryList();
            inFile.writeArrayListToFile(filePath);
        }

        System.out.println("filesName's size: " + filesName);

        //k merge sort~!
        try {
            k_mergeSort(filesName, fileNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //get minimum value's index
    static int getMinimumValue(HashMap<Integer, String> values) {
        if (values.size() == 0) {
            return 0;
        }

        Set keys = values.keySet();
        Object[] keysList = keys.toArray();

        int index = (int)keysList[0];
        String minLine = values.get(index);

        for(Integer key: values.keySet()) {
            if(values.get(key).compareTo(minLine) < 0) {
                minLine = values.get(key);
                index = key;
            }
        }
        return index;
    }


    static void k_mergeSort(ArrayList<String> filesName, int k_runs) throws IOException {

        HashMap<Integer, String> k_values = new HashMap();

        int filesNumToRead = filesName.size();

        ArrayList<File> k_files = new ArrayList<>();
        ArrayList<BufferedReader> k_readers = new ArrayList<>();


        //get files tag in collection and store BufferedReader
        for (int k = 0; k < k_runs; k++) {
            File file = new File(filesName.get(k));
            k_files.add(file);

            FileInputStream fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader br = new BufferedReader(inputStreamReader);

            k_readers.add(br);
        }

        System.out.println(filesNumToRead + " files to read~!");


        int everyTurnFound = 0;
        boolean ISFIRST = true;

        File result = new File("/home/pumbaa/tmpDir/test2/sorted_result.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(result);
        OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStream);

        while (filesNumToRead != 0) {
            if (ISFIRST) {
                for (int k = 0; k < k_runs; k++) {
                    BufferedReader br = k_readers.get(k);
                    String line = null;
                    System.out.println("load value~!: " + k);



                    if ((line = br.readLine()) != null) {
                        k_values.put(k, line);
                    } else {
                        filesNumToRead--;
                        k_values.remove(everyTurnFound);
                    }

                }
                ISFIRST = false;
            } else {



                BufferedReader br = k_readers.get(everyTurnFound);

                String line = k_values.get(everyTurnFound);

//                System.out.println("result line: " + line);


                //result~!
                bufferedWriter.write(line + "\n");

                String nextLine = null;
                if ((nextLine = br.readLine()) != null) {
                    k_values.replace(everyTurnFound, nextLine);
                } else {
                    filesNumToRead--;
                    k_values.remove(everyTurnFound);
                }
            }

            everyTurnFound = getMinimumValue(k_values);

        }
        for (BufferedReader item : k_readers) {
            item.close();
        }

        bufferedWriter.close();
        outputStream.close();
        fileOutputStream.close();
        System.out.println("~~~done~~~");
    }
}
