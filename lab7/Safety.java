import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Safety {

  // Lista de keys de recursos
  ArrayList<String> resources;
  // Lista de processos do sistema
  ArrayList<Process> process;
  // Instâncias disponíveis de cada recurso
  HashMap<String, Integer> sysAvailable;

  public Safety(ArrayList<Process> process, HashMap<String, Integer> sysAvailable, ArrayList<String> resources) {
    this.process = process;
    this.sysAvailable = sysAvailable;
    this.resources = resources;
  }

  public boolean run() {
    boolean SAFE = false;

    for (String r : resources) {

      // Ordenando os processo por necessidade de recurso
      Collections.sort(process, new Comparator<Process>() {
        @Override
        public int compare(Process p2, Process p1)
        {
          int diff1 = p1.getMax(r) - p1.getAllocated(r);
          int diff2 = p2.getMax(r) - p2.getAllocated(r);
          return  diff1 < diff2 ? 1 : -1;
        }
      });

      for (Process p : process) {
        // Verificação de segurança
        if (p.getMax(r) - p.getAllocated(r) <= sysAvailable.get(r) ) {
          SAFE = true;

          // Após o uso do recurso, libera as instâncias
          sysAvailable.put(r, sysAvailable.get(r)+p.getMax(r));
        }

        // Basta um caso para que a situação seja considerada insegura
        if (!SAFE) {
          return SAFE;
        }
      }

    }

    return SAFE;
  }
}
