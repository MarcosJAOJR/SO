// First-come, first-served

import java.util.ArrayList;

class FCFS implements IScheduler{

  ArrayList<Process> arrProcess;

  FCFS(ArrayList<Process> arrProcess) {
    this.arrProcess = arrProcess;
  }

  public void run() {
    System.out.println("Algorítimo FCFS");
    for (Process p : arrProcess) {
      p.execute(0);
      System.out.println("ID: "+p.id+", Duração: "+p.burstTime);
    }
  }
}
