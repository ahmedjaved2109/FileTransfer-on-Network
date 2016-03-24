import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class sender extends JFrame implements ActionListener
{
	//JFrame jf;
	JButton jb1,jb2;
	JFileChooser jfc;
	JTextField tf;
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	File f;
	String s1;
	public sender(String s2)
	{
		super(s2);
		setSize(400,400);
		setLayout(null);
		jb1=new JButton("Choose");
		jb2=new JButton("Send");
		jb1.setBounds(30,50,100,50);
		jb2.setBounds(250,150,70,50);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		add(jb1);
		add(jb2);
		tf=new JTextField();
		tf.setBackground(Color.white);
		tf.setBounds(150,50,190,50);
		add(tf);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfc=new JFileChooser();
		try
			{
				s=new Socket("localhost",1025);
				dout=new DataOutputStream(s.getOutputStream());
			}
			catch(Exception e1)
			{
				System.out.println("can't create connection"+e1);
		    }
	}
	public void fileTransfer(String s1)
	{
		try
		{
			
		 	dout.writeUTF(s1);
			dout.flush();
			String s2=f.getAbsolutePath();
	 		FileReader fr=new FileReader(s2);
	 		BufferedReader br=new BufferedReader(fr);
	 		do
	 		{
	 			s1=br.readLine();
	 			if(s1!=null)
	 			{
	 				dout.writeUTF(s1);
	 				dout.flush();
	 			}
	 		
	 		}while(s1!=null);
	    }
	    catch(Exception e)
	    {

	    }

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb1)
		{
			int x=jfc.showOpenDialog(null);
			if(x==JFileChooser.APPROVE_OPTION)
			{
				f=jfc.getSelectedFile();
				String path=f.getPath();
				s1=f.getName();
				System.out.println(s1);
				tf.setText(path);
			}
		}
		if(e.getSource()==jb2)
		{
			
			System.out.println(s1);
			fileTransfer(s1);
		}
	}
	public static void main(String args[])
	{
		new sender("File Transfer");
	}
}