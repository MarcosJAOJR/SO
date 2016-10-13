/**
 * Time-of-day server listening to port 6013.
 *
 */

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

class ClientHanler implements Runnable {

  private Socket con;
  private InputStream in;
  private InputStreamReader inr;
  private BufferedReader bfr;

  /**
   * Contructor method
   * @param  con type Socket
   */
  public ClientHanler (Socket con) {
    this.con = con;
    try {
      in = con.getInputStream();
      inr = new InputStreamReader(in);
      bfr = new BufferedReader(inr);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method run
   */
  public void run() {

    try {

      String msg;
      OutputStream ou = this.con.getOutputStream();
      OutputStreamWriter ouw = new OutputStreamWriter(ou);
      BufferedWriter bfw = new BufferedWriter(ouw);
      bfw.write("Seja bem-vindo! Iniciado em " + (new java.util.Date().toString()) + "\r\n");
      bfw.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

public class DateServerPool
{
  private static Integer count = 0;

	public static void main(String[] args) throws IOException {
		Socket client = null;
		ServerSocket sock = null;

    // Create the thread pool
    ExecutorService pool = Executors.newCachedThreadPool();

		try {
			sock = new ServerSocket(6013);
			// now listen for connections
			while (true) {
				client = sock.accept();
        System.out.println("> Novo cliente! " + (++count) + " " + ((count==1)?"conexão iniciada":"conexões iniciadas"));
				System.out.println("server = " + sock);
				System.out.println("client = " + client);
        pool.execute(new Thread(new ClientHanler(client)));
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
		finally {
			if (sock != null)
				sock.close();
			if (client != null)
				client.close();
      if (pool != null)
        pool.shutdown();
		}
	}
}
