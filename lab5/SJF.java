import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJF implements IScheduler {

  ArrayList<Process> arrProcess;
  ArrayList<Process> arrReady;


  SJF(ArrayList<Process> arrProcess) {
    this.arrProcess = arrProcess;
    this.arrReady = new ArrayList<Process>();

    Collections.sort(this.arrProcess, new Comparator<Process>() {
      public int compare(Process p1, Process p2) {
        return p1.inTime < p2.inTime ? -1 : 1;
      }
    });
  }

  public void run() {
    System.out.println("SJF sem preempção");
    int i = 0;
    boolean stop = false;
    Process currentProcess = null;
    do {
      // Adiciona cada processo a medida que forem aceitos
      if (!this.arrProcess.isEmpty() && this.arrProcess.get(0).inTime == i) {
        pushReady(arrProcess.remove(0));
      }

      // Se tem processo pronto, executa
      if (!this.arrReady.isEmpty() || (currentProcess != null)) {
        if (currentProcess == null) {
          currentProcess = this.arrReady.remove(0);
        }

        if (currentProcess.execute(1)) {
          currentProcess = null;
        }
      }

      if (this.arrReady.isEmpty() && this.arrProcess.isEmpty()) {
        stop = true;
      }
      else
        i++;

    } while (!stop);
  }

  public void preemptiveRun() {
    System.out.println("SJF com preempção");

    int i = 0;
    boolean stop = false;
    do {
      // Adiciona cada processo a medida que forem aceitos
      if (!this.arrProcess.isEmpty() && this.arrProcess.get(0).inTime == i) {
        pushReady(arrProcess.remove(0));
      }

      // Se tem processo pronto, executa
      if (!this.arrReady.isEmpty()) {
        Process process = this.arrReady.get(0);
        if (process.execute(1)) {
          this.arrReady.remove(0);
        }
      }

      if (this.arrReady.isEmpty() && this.arrProcess.isEmpty()) {
        stop = true;
      }
      else
        i++;

    } while (!stop);
  }

  private void pushReady(Process process) {

    this.arrReady.add(process);

    Collections.sort(this.arrReady, new Comparator<Process>() {
      public int compare(Process p1, Process p2) {
        return p1.burstTime < p2.burstTime ? -1 : 1;
      }
    });
  }
}
