/**
 *   Author:  Marcos Augusto
 *   Organization:  UFC
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Escalonador {

  /**
   * Convert a csv file to a process ArrayList
   * @param  csvFilename path to the csv file
   * @return             parsed array of process
   */
  static ArrayList<Process> parseCSV(String csvFilename) throws Exception {

    ArrayList<Process> result = new ArrayList<Process>();

    BufferedReader br = new BufferedReader(new FileReader(csvFilename));

    String line = "";

    while ((line = br.readLine()) != null) {
      String[] csvLine = line.split(",");
      Process process = new Process(
        Integer.parseInt(csvLine[0]),
        Long.valueOf(csvLine[1]).longValue(),
        Integer.parseInt(csvLine[2]),
        Integer.parseInt(csvLine[3])
      );
      result.add(process);
    }

    return result;
  }

  /**
   * main method
   * @param args
   */
  public static void main(String[] args) {

    try {

      if (args.length < 3)
        throw new Exception("\n\nUsage: java escalonador <nome do arquivo>.csv <algorítimo de escalonamento> <tipo de saida>\n");

      String csvFilename = args[0];
      String scheduleAlgName = args[1];
      String outputType = args[2];
      int quantum = 100;

      if (args.length == 4) {
        quantum = Integer.parseInt(args[3]);
      }

      // Convert csv file to process array
      ArrayList<Process> arrProcess = parseCSV(csvFilename);

      switch (scheduleAlgName.toUpperCase()) {
        case "FCFS":
          FCFS fcfs = new FCFS(arrProcess);
          fcfs.run();
          break;
        case "SJF":
          SJF sjf = new SJF(arrProcess);
          sjf.run();
          break;
        case "SJFP":
          SJF sjfp = new SJF(arrProcess);
          sjfp.preemptiveRun();
          break;
        case "PRIORITY":
          Priority priority = new Priority(arrProcess);
          priority.run();
          break;
        case "PRIORITYP":
          Priority priorityp = new Priority(arrProcess);
          priorityp.preemptiveRun();
          break;
        case "RR":
          RR rr = new RR(arrProcess, quantum);
          rr.run();
          break;
        default:
          throw new Exception("Algorítimo de escalonamento inválido");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
