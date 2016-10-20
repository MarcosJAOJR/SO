import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RR implements IScheduler {

  ArrayList<Process> arrProcess;
  ArrayList<Process> arrReady;
  int quantum;

  RR(ArrayList<Process> arrProcess, int quantum) {
    this.arrProcess = arrProcess;
    this.arrReady = new ArrayList<Process>();
    this.quantum = quantum;

    Collections.sort(this.arrProcess, new Comparator<Process>() {
      public int compare(Process p1, Process p2) {
        return p1.inTime < p2.inTime ? -1 : 1;
      }
    });
  }

  public void run() {
    System.out.println("Algorítimo Round-Robin");

    int i = 0;
    boolean stop = false;
    Process currentProcess = null;
    do {
      // Adiciona cada processo a medida que forem aceitos
      if (!this.arrProcess.isEmpty() && this.arrProcess.get(0).inTime == i) {
        this.arrReady.add(arrProcess.remove(0));
      }

      // Se tem processo pronto, executa
      if (!this.arrReady.isEmpty() || (currentProcess != null)) {
        if (currentProcess == null) {
          currentProcess = this.arrReady.remove(0);
        }

        if (i%quantum == 0) {
          this.arrReady.add(currentProcess);
          currentProcess = null;
        }
        else {
          if (currentProcess.execute(1)) {
            currentProcess = null;
          }
        }
      }

      if (this.arrReady.isEmpty() && this.arrProcess.isEmpty()) {
        stop = true;
      }
      else
        i++;

    } while (!stop);
  }
}
