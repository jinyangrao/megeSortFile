import java.util.ArrayList;

public class TestFunction {
    public static void main(String[] args) {
//        System.out.println("s".compareTo(""));
        ArrayList<String> testArrayList = new ArrayList<>();

        //                1      2      3       4       5     6       7
        String[] names = {"rao", "jin", "yang", "work", "in", "ebay", "company"};

        for(int i = 0; i < names.length; i++) {
            testArrayList.add(i, names[i]);
        }

        for(int i = 0; i < testArrayList.size(); i++) {
            System.out.println(testArrayList.get(i));
        }

        testArrayList.remove(4);

        for(int i = 0; i < testArrayList.size(); i++) {
            System.out.println(testArrayList.get(i));
        }

    }
}
