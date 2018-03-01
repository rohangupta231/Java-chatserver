import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
class Server
{
	public static void main(String dt[])
	{
		ServerSocket sskt=null;
		Socket skt=null;
		InputStreamReader isrin=null;
		BufferedReader brin=null;
		InputStreamReader isrout=null;
		BufferedReader brout=null;
		PrintWriter pw=null;
		FileWriter fw=null;
		BufferedWriter bw=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_YYYY");
		SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm:ss");
		try
		{
			sskt=new ServerSocket(1234);
			System.out.println("Waiting for client request");
			skt=sskt.accept();
			System.out.println("Connected to Client");
			isrin=new InputStreamReader(skt.getInputStream());
			brin=new BufferedReader(isrin);
			isrout= new InputStreamReader(System.in);
			brout=new BufferedReader(isrout);
			pw=new PrintWriter(skt.getOutputStream(),true);
			Date d=new Date();
			String filedate=sdf.format(d);
			fw=new FileWriter(filedate+".txt",true);
			bw=new BufferedWriter(fw);
			final String username="Rohan";	
			final String password="Rohan";
			String str=brin.readLine();
			String arr[]=str.split(";");
			if((username.equals(arr[0]))&&(password.equals(arr[1])))
			{
				pw.println("true");
				System.out.println("Authenicated User");
				do
				{
					String choice=brin.readLine();
					switch(choice)
					{
						case "1":
						{	
							String msg="";
							do
							{
								msg=brin.readLine();
								System.out.println("Client:"+msg);
								Date d1=new Date();
								String cdate=sdf1.format(d1);
								bw.write(("Cilent: "+cdate+" "+msg));
								bw.newLine();
								msg=brout.readLine();
								pw.println(msg);	
								Date d2=new Date();
								String sdate=sdf1.format(d2);
								bw.write(("Server: "+sdate+" "+msg));
								bw.newLine();
							}while(msg.equals("bye")!=true);
							break;
						}
						case "2":
						{
							FileReader fr=new FileReader(filedate+".txt");
							BufferedReader br=new BufferedReader(fr);
							String ch="";
							while((ch=br.readLine())!=null)
							{
								pw.println(ch);
							}
							br.close();
							fr.close();
							break;
						}
						case "3":
						{
							System.out.println("Client Disconnected");
							System.exit(0);
						}
					}	
				}while(true);	
			}	
			else
			{
				pw.println("false");
				System.out.println("Invalid User");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		finally
		{
			try
			{
				fw.close();
				bw.close();
				pw.close();
				brout.close();
				isrout.close();
				brin.close();
				isrin.close();
				skt.close();
				sskt.close();
			}
			catch(Exception e)
			{
				System.out.println("Finally "+e);
			}
		}
	}
}