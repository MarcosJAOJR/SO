import java.util.TreeMap;

public class LFU {

  private Memory memory;
  private String[] pageRequests;
  private TreeMap<Integer, Integer> frequencyMap;

  public LFU(Memory m, String[] pageRequests) {
    this.memory = m;
    this.pageRequests = pageRequests;
    this.frequencyMap = new TreeMap<Integer, Integer>();
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

      int index = this.memory.indexOf(page);
      // Increment count
      this.frequencyMap.put(index, this.frequencyMap.get(index)+1);
    } catch(Exception e) {
      int replacementIndex;

      if (!this.memory.isFull()) {
        replacementIndex = this.memory.indexOf(null);
      }
      else {
        replacementIndex = frequencyMap.firstKey();
        int lessFrequency = frequencyMap.get(replacementIndex);

        for (int key : frequencyMap.keySet() ) {
          if(lessFrequency > frequencyMap.get(key)) {
            lessFrequency = frequencyMap.get(key);
            replacementIndex = key;
          }
        }
      }

      this.memory.setPage(replacementIndex, page);
      // Reset count
      this.frequencyMap.put(replacementIndex, 1);
    }
  }
}
