import java.util.ArrayList;
import java.util.HashMap;

class Detection {

  ArrayList<Process> process;
  ArrayList<String> resources;
  HashMap<String, HashMap<String, Integer>> requests;
  HashMap<String, Integer> work;
  ArrayList<Boolean> finish;

  public Detection(ArrayList<Process> process, HashMap<String, Integer> sysAvailable, ArrayList<String> resources, HashMap<String, HashMap<String, Integer>> requests) {
    this.process = process;
    this.resources = resources;
    this.work = sysAvailable;
    this.requests = requests;
  }

  public void run () {

    for (Process p : process ) {
      for (String r : resources ) {

        if (p.getAllocated(r) != 0) {
          p.setFinish(false);
        }
        else {
          p.setFinish(true);
        }
      }
    }

    step2();
  }

  public void step2() {
    for (Process p : process ) {
      if (!p.isFinished()) {
        boolean result = true;
        for (String r : resources) {
          if (requests.get(p.getId()).get(r) > work.get(r)) {
            result = false
          };
        }

        if (result) {
          step2();
        }
      }

    }

  }
}
