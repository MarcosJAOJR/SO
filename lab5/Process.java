import java.util.concurrent.*;

public class Process {

  public int inTime;
  public long id;
  public int burstTime;
  public int priority;
  public int remaningBurstTime;

  Process(int inTime, long id, int burstTime, int priority){
    this.inTime = inTime;
    this.id = id;
    this.burstTime = burstTime;
    this.priority = priority;
    this.remaningBurstTime = burstTime;
  }

  public boolean execute(int time) {
    if (time == 0) {
      remaningBurstTime = 0;
    }
    else {
      remaningBurstTime = remaningBurstTime - time;
    }

    return (remaningBurstTime == 0);
  }

}
