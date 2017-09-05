import javax.swing.text.html.HTMLDocument;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by WangDi on 2017/7/28.
 */
public class TestHashTable{

    private static final Hashtable<String,Integer> ht = new Hashtable<String,Integer>();
    public static void main(String[] args) throws InterruptedException{

        for (int i = 0; i < 10; i++) {

            new Thread(()->{

                if(ht.get("a") == null){
                    ht.put("a",1);
                }
                else {
                    ht.put("a",ht.get("a") + 1);
                }
            }).start();
        }

        Thread.sleep(7000);

        Iterator<Map.Entry<String,Integer>> iterator = ht.entrySet().iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next().getValue());
        }
    }
}
