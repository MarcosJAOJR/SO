public class OPT {

  private Memory memory;
  private String[] pageRequests;

  public OPT(Memory m, String[] pageRequests) {
    this.memory = m;
    this.pageRequests = pageRequests;
  }

  public void run() {
    for(int i = 0; i < this.pageRequests.length ; i++) {
      this.alg(this.pageRequests[i], i);
      this.memory.printStat();
    }
  }

  private void alg(String page, int currentIndex) {
    int replacementIndex = 0;
    int lastOcurrency = 0;

    try {
      this.memory.getPage(page);
    } catch(Exception e) {
      if (!this.memory.isFull()) {
        replacementIndex = this.memory.indexOf(null);
      }
      else {
        int memoryIndex = 0;
        // For each page in memory verify the lastest ocurrency
        while(memoryIndex < this.memory.length) {
          int ocurrency = nextOcurrency(currentIndex+1, this.memory.page(memoryIndex));
          if(ocurrency > lastOcurrency) {
            lastOcurrency = ocurrency;
            replacementIndex = memoryIndex;
          }
          memoryIndex++;
        }
      }

      this.memory.setPage(replacementIndex, page);
    }
  }

  private int nextOcurrency(int since, String page) {
    int i;
    for (i = since ; i < this.pageRequests.length ; i++ )
      if(page.equals(this.pageRequests[i]))
        return i;
    return i;
  }
}
