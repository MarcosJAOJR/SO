import java.util.HashMap;

class Process {

  // Instâncias para cada recurso
  private HashMap<String, Integer> allocated;
  private HashMap<String, Integer> max;
  private String id;
  private Boolean finish;

  public Process(HashMap<String, Integer> allocated, HashMap<String, Integer> max) {
    this.allocated = allocated;
    this.max = max;
    this.finish = false;
  }

  public Process(HashMap<String, Integer> allocated, HashMap<String, Integer> max, String id) {
    this(allocated, max);
    this.id = id;
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

  public void setFinish(boolean value) {
    this.finish = value;
  }

  public boolean isFinished() {
    return this.finish;
  }

  public String getId() {
    return this.id;
  }
}
