import java.io.*;
import java.net.*;
public class snws
{
public static void main(String args[])
{
try
{
System.out.println("============== SERVER =============");
String frame = null;
String ack = null;
//1. creating a server socket
ServerSocket ss = new ServerSocket(123);
//2. Wait for connection
System.out.println("Waiting for connection");
Socket con = ss.accept();
System.out.println("Connected with client - IP : " +
con.getInetAddress().getHostAddress());
//3. set Input and output streams
ObjectInputStream in = new ObjectInputStream(con.getInputStream()); ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream()); //4. receive frame length to control for loop
String framelength= (String)in.readObject();
//5. frame receiving and acknowledgment sending process 
int ackno = 0;
for(int i=0;i<Integer.parseInt(framelength);i++)
{
frame = (String)in.readObject();
System.out.println("Frame Received from Client " + frame);
//	swap acknowledge number 
if(ackno == 0)
ackno = 1;
else
ackno = 0;
//	compose acknowledge message ack = "ack" + ackno;
//	send acknowledgment to client out.writeObject(ack); System.out.println("Acknowlegement Sent to Client : " + ack);
}
in.close();
out.close();
ss.close();
}
catch(Exception e)
{
System.out.println("Error:" + e);
}
}
}
