import java.util.TreeMap;

public class LRU {

  private Memory memory;
  private String[] pageRequests;
  private TreeMap<Integer, Integer> usedTime;
  private int timeCount;

  public LRU(Memory m, String[] pageRequests) {
    this.memory = m;
    this.pageRequests = pageRequests;
    this.usedTime = new TreeMap<Integer, Integer>();
    this.timeCount = 0;
  }

  public void run() {
    for (String page : this.pageRequests ) {
      this.alg(page);
      this.memory.printStat();
    }
  }

  private void alg(String page) {

    try {
      this.memory.getPage(page);
    } catch(Exception e) {
      int replacementIndex;

      if (!this.memory.isFull()) {
        replacementIndex = this.memory.indexOf(null);
      }
      else {
        replacementIndex = usedTime.firstKey();
        int lastTime = usedTime.get(replacementIndex);

        for (int key : usedTime.keySet() ) {
          if(lastTime > usedTime.get(key)) {
            lastTime = usedTime.get(key);
            replacementIndex = key;
          }
        }
      }

      this.memory.setPage(replacementIndex, page);
    }

    int index = this.memory.indexOf(page);
    usedTime.put(index, timeCount++);
  }
}
