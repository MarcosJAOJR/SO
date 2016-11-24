public class MMU {

  private String replaceAlg;
  private Memory memory;
  private String[] pageRequests;

  public MMU(Memory m, String[] pageRequests, String replaceAlg) {
    this.memory = m;
    this.pageRequests = pageRequests;
    this.replaceAlg = replaceAlg;
  }

  public void init() throws Exception {
    switch (this.replaceAlg) {
      case "FIFO":
        FIFO fifo = new FIFO(this.memory, this.pageRequests);
        fifo.run();
        break;
      case "OPT":
        OPT opt = new OPT(this.memory, this.pageRequests);
        opt.run();
        break;
      default:
        throw new Exception("Invalid replacement algorithm");
    }
  }
}
