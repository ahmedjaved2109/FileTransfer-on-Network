import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
public class receiver 
{
	ServerSocket ss;
	Socket s;
	DataInputStream din;
	PrintWriter pw;
	public receiver()
	{
		try
		{
		ss=new ServerSocket(1025);
		s=ss.accept();
		din=new DataInputStream(s.getInputStream());
		String s1=din.readUTF();
		FileWriter fw=new FileWriter(s1);
		pw=new PrintWriter(fw);
		fileReceiver();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void fileReceiver()throws IOException
	{
		while(true)
		{
			String s1=din.readUTF();
			if(s1!=null)
			pw.println(s1);
	   		else
	        	break;
	    } 
	}
	public static void main(String args[])
	{
		new receiver();
	}
} 