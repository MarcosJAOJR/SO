import java.util.HashMap;
import java.util.ArrayList;

class Avoid {

  ArrayList<String> resources;
  ArrayList<Process> process;
  HashMap<String, Integer> sysAvailable;
  HashMap<String, Integer> requests;
  int processId;

  /**
   * Construtor
   * @param process       Lista de processos do sistema
   * @param sysAvailable  Instâncias disponíveis de cada recurso
   * @param resources     Lista de keys de recursos
   * @param requests      Instâncias solicitadas de cada recurso
   * @param processId     Id do processo ao qual está sendo solicitado as instâncias
   */
  public Avoid(ArrayList<Process> process, HashMap<String, Integer> sysAvailable, ArrayList<String> resources, HashMap<String, Integer> requests, int processId) {
    this.process = process;
    this.sysAvailable = sysAvailable;
    this.resources = resources;
    this.requests = requests;
    this.processId = processId;
  }

  public boolean run() {

    Process p = process.get(processId);

    // Verifica se a requisição é válida
    if (!approve(p)) {
      return false;
    }

    // Incrementa o alocado do processo
    p.incrementAllocated(requests);

    // Remover do disponível no sistema
    for (String k : sysAvailable.keySet()) {
      sysAvailable.put(k, sysAvailable.get(k) - requests.get(k));
    }

    // Verifica se o novo estado é seguro
    Safety safety = new Safety(process, sysAvailable, resources);
    return safety.run();
  }

  private boolean approve(Process process) {
    boolean APPROVE = true;

    for (String r : resources) {

      if ( process.getMax(r) - process.getAllocated(r) < requests.get(r) ) {
        APPROVE = false;
      }

      if ( sysAvailable.get(r) < requests.get(r) ) {
        APPROVE = false;
      }

    }

    return APPROVE;
  }
}
