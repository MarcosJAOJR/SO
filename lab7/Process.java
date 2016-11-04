import java.util.HashMap;

class Process {

  // Instâncias para cada recurso
  private HashMap<String, Integer> allocated;
  private HashMap<String, Integer> max;

  public Process(HashMap<String, Integer> allocated, HashMap<String, Integer> max) {
    this.allocated = allocated;
    this.max = max;
  }

  public Integer getAllocated(String resource) {
    return allocated.get(resource);
  }

  public Integer getMax(String resource) {
    return max.get(resource);
  }
}
