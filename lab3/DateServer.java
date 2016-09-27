/**
 * Time-of-day server listening to port 6013.
 *
 */
 
import java.net.*;
import java.io.*;

public class DateServer
{
	public static void main(String[] args) throws IOException {
		Socket client = null;
		ServerSocket sock = null;

		try {
			sock = new ServerSocket(6013);
			// now listen for connections
			while (true) {
				client = sock.accept();
				System.out.println("server = " + sock);
				System.out.println("client = " + client);
				
				BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
		    String strParams = bin.readLine();
		    String[] params = strParams.split(":");
		    
		    double result = 0;
		    double num1 = Double.parseDouble(params[1]);
		    double num2 = Double.parseDouble(params[2]);
		    
		    switch(params[0]) {
		      case "+":
		          result = num1 + num2;
		          break;
		      case "-":
            result = num1 - num2;
            break;
		      case "*":
            result = num1 * num2;
            break;
		      case "/":
            result = num1 / num2;
            break;
		    }

				// we have a connection
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
				// write the Date to the socket
				pout.println(String.valueOf(result));

				pout.close();

				client.close();
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
		}
	}
}
