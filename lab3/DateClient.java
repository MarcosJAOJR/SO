/**
 * Client program to send message with math operation and receive the result from server.
 *
 */

import java.net.*;
import java.io.*;

public class DateClient
{
	public static void main(String[] args) throws IOException {
		InputStream in = null;
		BufferedReader bin = null;
		Socket sock = null;
		OutputStream out = null;

		try {
			if(args.length == 0)
				throw new Exception("Nenhum parametro foi passado!");

			String[] params = args[0].split(":");

			if(params.length != 3)
				throw new Exception("Parametros inválidos!");

			sock = new Socket("127.0.0.1",6013);
			
			out = sock.getOutputStream();
      PrintStream pout = new PrintStream(out);
      pout.println(args[0]);
			
			in = sock.getInputStream();
			bin = new BufferedReader(new InputStreamReader(in));

			String line;
			while( (line = bin.readLine()) != null)
				System.out.println(line);

		}
		catch (Exception e) {
			System.err.println(e);
		}
        finally {
            sock.close();
        }
	}
}
