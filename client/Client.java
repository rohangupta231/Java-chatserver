import java.net.Socket;
import java.io.*;
class Client
{
	public static void main(String dt[])
	{
		Socket skt=null;
		InputStreamReader isrout=null;
		BufferedReader brout=null;
		PrintWriter pw=null;
		InputStreamReader isrin=null;
		BufferedReader brin=null,br=null;
		try
		{
			skt=new Socket("127.0.0.1",1234);//reference is created when there is positive feedback from the slient
			System.out.println("Connect to Server");
			isrout= new InputStreamReader(System.in);
			brout=new BufferedReader(isrout);
			br=new BufferedReader(isrout);
			pw=new PrintWriter(skt.getOutputStream(),true);//false for single message
			isrin=new InputStreamReader(skt.getInputStream());
			brin=new BufferedReader(isrin);
			String username="";
			String password="";
			System.out.println("Enter Username");
			username=brout.readLine();
			System.out.println("Enter Password");
			password=brout.readLine();
			pw.println(username+";"+password);
			if(Boolean.parseBoolean(brin.readLine()))
			{
				System.out.println("Access Granted");
				do
				{
					System.out.println("1. Chat");
					System.out.println("2. View History");
					System.out.println("3. Exit");
					System.out.println("\nEnter a Choice 1-3");
					String choice=br.readLine();
					pw.println(choice);
					switch(choice)
					{
						case "1":
						{
							String msg="";
							do
							{
								msg=brout.readLine();
								pw.println((msg));
								msg=brin.readLine();
								System.out.println("Server:"+msg);
							}while(msg.equals("bye")!=true);
							break;
						}
						case "2":
						{
							String ch="";
							while((ch=brin.readLine())!=null)
							{
								System.out.println(ch);
							}
							break;						
						}
						case "3":
						{
							System.out.println("\nThank You!");
							System.exit(0);
							break;
						}	
					}
				}while(true);
			}
			else
			{
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
				
				brin.close();
				isrin.close();
				pw.close();
				br.close();
				brout.close();				
				isrout.close();
				skt.close();
			}
			catch(Exception e)
			{
				System.out.println("Finally "+e);
			}
		}
	}
}