import java.util.ArrayList;
import java.util.HashMap;

class DeadlockExample {
  public static void main(String[] args) {
    ArrayList<String> resources = new ArrayList<String>();
    resources.add("A");
    resources.add("B");
    resources.add("C");

    ArrayList<Process> process = new ArrayList<Process>();
    Process p0 = new Process(
      new HashMap<String, Integer>() {{ put("A",3); put("B",1); put("C",0); }},
      new HashMap<String, Integer>() {{ put("A",7); put("B",5); put("C",7); }}
    );
    Process p1 = new Process(
      new HashMap<String, Integer>() {{ put("A",7); put("B",0); put("C",1); }},
      new HashMap<String, Integer>() {{ put("A",7); put("B",1); put("C",1); }}
    );
    Process p2 = new Process(
      new HashMap<String, Integer>() {{ put("A",0); put("B",2); put("C",2); }},
      new HashMap<String, Integer>() {{ put("A",10); put("B",4); put("C",3); }}
    );
    process.add(p0);
    process.add(p1);
    process.add(p2);

    HashMap<String, Integer> sysAvailable = new HashMap<String, Integer>() {{ put("A",3); put("B",11); put("C",7); }};

    // Alg. Safety
    Safety safety = new Safety(process, sysAvailable, resources);
    System.out.println("Algorítimo Safety: "+safety.run());

    // Alg. Avoid
    HashMap<String, Integer> requests = new HashMap<String, Integer>() {{ put("A",1); put("B",4); put("C",1); }};
    Avoid avoid = new Avoid(process, sysAvailable, resources, requests, 0);
    System.out.println("Algorítimo Avoid: "+avoid.run());
  }
}
