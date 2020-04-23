import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

    
    
    public static void main(String[] args) {
        
        System.out.println("Hello world!");
        
//        String str = "This is a test string.";
//        String[] strArr = str.split("[ \\p{Punct}]");
//        System.out.println(Arrays.toString(strArr));
        
//        int x = 0x01010001;
//        ExerciseB b = new ExerciseB();
//        System.out.println(b.getBit(x, 6));
        
//        ExerciseB b = new ExerciseB();
//        b.testStuff();

//        System.out.println(Byte.toString((byte) 'a'));
        
        String str = "tropical";
        int sum = 0;
        for (int i = 0; i < str.length(); i++)
            sum += (int) str.charAt(i);
        
        System.out.println(sum);
    }

}
