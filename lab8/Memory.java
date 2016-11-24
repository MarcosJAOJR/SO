public class Memory {

  public int length;

  private String[] memory;
  private int nPageFault;
  private int tPageReplace;
  private int tExec;


  public Memory(int length) {
    this.memory = new String[length];
    this.length = this.memory.length;
    this.nPageFault = 0;
    this.tPageReplace = 0;
    this.tExec = 0;
  }

  public String getPage(String page) throws Exception {
    for (int i = 0; i < this.memory.length ; i++ ) {
      if (this.memory[i] != null && this.memory[i].equals(page))
        return this.memory[i];
    }
    this.nPageFault++;
    throw new Exception("Page Fault");
  }

  public void setPage(int index, String page) {
    if (index >= 0 && index < memory.length) {
      if (this.memory[index] != null) {
        // Espaço de memória não estava vazio
        // TODO: adicionar tempo de troca de página
      }
      this.memory[index] = page;
    }
  }

  public boolean isFull() {
    for (String slot : this.memory) {
      if (slot == null)
        return false;
    }
    return true;
  }

  public boolean containsPage(String page) {
    for (String slot : this.memory ) {
      if(slot.equals(page))
        return true;
    }
    return false;
  }

  public int indexOf(String page) {
    for (int i = 0; i < this.memory.length ; i++ ) {
      if (this.memory[i] == page || this.memory[i].equals(page)) {
        return i;
      }
    }

    return -1;
  }

  public String page(int index) {
    return this.memory[index];
  }

  public void printStat() {
    System.out.println("-----------------------------------------------------------");
    System.out.println("Estatísticas de memória:\n");
    System.out.print("  |");
    for (String slot : this.memory ) {
      System.out.print(" " + ((slot != null)?slot:" ") + " |");
    }
    System.out.println("\n\n* Page Faults: " + this.nPageFault);
    System.out.println("* Razão tempo troca de páginas e tempo total execução: " + this.tPageReplace + "/" + this.tExec);
    System.out.println("-----------------------------------------------------------");
  }

}
