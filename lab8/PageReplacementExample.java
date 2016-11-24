import java.util.Date;
import java.util.TreeMap;

public class PageReplacementExample {


  public static void main(String[] args) {
    String pageRequests = "1,2,3,4,1,2,5,1,2,3,4,5";
    String[] arr = pageRequests.split(",");

    String replaceAlg = (args.length > 0)?args[0]:"FIFO";

    Memory m = new Memory(3);
    MemoryManager mm = new MemoryManager(m, arr, replaceAlg);

    try {
      mm.init();
    }
    catch(Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
