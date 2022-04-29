import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class hackerrank {

    public static long doubleSize(List<Long> arr, long b){
        List<Long> list2= new ArrayList<>(arr);
        list2.sort(Comparator.naturalOrder());
        for (int i = 0; i < list2.size(); i++) {
            if (b== list2.get(i)){
                b=b*2;
            }
        }


        return b;


    }
}
