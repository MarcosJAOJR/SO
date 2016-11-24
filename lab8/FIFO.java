public class FIFO {

  private Memory memory;
  private String[] pageRequests;
  private int currentIndex;

  public FIFO(Memory m, String[] pageRequests) {
    this.memory = m;
    this.pageRequests = pageRequests;
    this.currentIndex = 0;
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
      if(currentIndex >= this.memory.length) {
        currentIndex = 0;
      }

      this.memory.setPage(currentIndex, page);
      this.currentIndex++;
    }
  }
}
