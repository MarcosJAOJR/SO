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

  public void incrementAllocated(HashMap<String, Integer> increment) {
    for (String k : allocated.keySet()) {
      this.allocated.put(k, this.allocated.get(k) + increment.get(k));
    }
  }

  public Integer getMax(String resource) {
    return max.get(resource);
  }
}
