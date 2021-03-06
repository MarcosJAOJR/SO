public class MemoryManager {

  private String replaceAlg;
  private Memory memory;
  private String[] pageRequests;

  public MemoryManager(Memory m, String[] pageRequests, String replaceAlg) {
    this.memory = m;
    this.pageRequests = pageRequests;
    this.replaceAlg = replaceAlg;
    System.out.println("Iniciando algorítimo " + this.replaceAlg.toUpperCase());
  }

  public void init() throws Exception {
    switch (this.replaceAlg.toUpperCase()) {
      case "FIFO":
        FIFO fifo = new FIFO(this.memory, this.pageRequests);
        fifo.run();
        break;
      case "OPT":
        OPT opt = new OPT(this.memory, this.pageRequests);
        opt.run();
        break;
      case "LRU":
        LRU lru = new LRU(this.memory, this.pageRequests);
        lru.run();
        break;
      case "LFU":
        LFU lfu = new LFU(this.memory, this.pageRequests);
        lfu.run();
        break;
      case "MFU":
        MFU mfu = new MFU(this.memory, this.pageRequests);
        mfu.run();
        break;
      default:
        throw new Exception("Invalid replacement algorithm");
    }
  }
}
