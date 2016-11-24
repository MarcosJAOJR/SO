import java.util.Date;
import java.util.TreeMap;

public class PageReplacementExample {


  public static void main(String[] args) {
    // String pageRequests = "1,2,3,4,1,2,5,1,2,3,4,5";
    String pageRequests = "7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1";
    String[] arr = pageRequests.split(",");

    Memory m = new Memory(3);
    MMU mmu = new MMU(m, arr, "LFU");

    try {
      mmu.init();
    }
    catch(Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
