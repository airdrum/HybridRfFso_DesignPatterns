package utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



public class SshClass{
	//create s jsch variable
	static int iPort = 22;
	static int timeout = 120000;
	private JSch mJschSSH = null;

	//create a ssh session
	private Session mSSHSession = null;
	
	//Create a new ssh channel
	private Channel mSSHChannel = null;
	public boolean status =false;
	private String strHost = null;
	private String strUserName = null;
	private String strPassword = null;
	private InputStream mSSHInput = null;
	private OutputStream mSSHOutput = null;
	public SshClass(String strHost, String username,String password) {
			this.strHost = strHost;
			this.strUserName = username;
			this.strPassword = password;
			try {
				openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//this.sendCommand("iperf -c " + this.businessFsoIp + " -u -b100M -i1 -p6000 -t999999 > /dev/null \r\n");
			
		
		
	}
	
	//creating a new SSH connection
	public boolean openConnection() throws IOException {
		//creating a boolean variable
		
		boolean blResult = false;
		
		//creating a new value for jsch
		this.mJschSSH = new JSch();
		
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch.setConfig(config);
		
		
		
		try {
			//get session
			this.mSSHSession = this.mJschSSH.getSession(this.strUserName,this.strHost,iPort);
			
			// set password for ssh
			this.mSSHSession.setPassword(this.strPassword);
			this.mSSHSession.connect(timeout);
			
			//get channel let connect to ssh server
			this.mSSHChannel = this.mSSHSession.openChannel("shell");
			
			//connect to channel
			this.mSSHChannel.connect();
			
			//after successfully connected to ssh, you can get in/out stream
			this.mSSHInput = this.mSSHChannel.getInputStream();
			this.mSSHOutput = this.mSSHChannel.getOutputStream();
			blResult = true;
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (blResult) {
			System.out.println("Successfully connected to " + strHost +"\r\n");
		} else {
			System.out.println("cannot connect to host " + strHost+"\r\n");
		} 
		return blResult;
	}
	
	//creating a new function let send commasnd to ssh server
	public boolean sendCommand(String strCommand) {
		strCommand = strCommand +"\r\n";
		boolean blResult = false;
		this.status = false;
		try {
			if(this.mSSHOutput !=null) {
				//you should use output stream let send data
				this.mSSHOutput.write(strCommand.getBytes());
				
				//clear output stream
				this.mSSHOutput.flush();
				blResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blResult;
	}
	//creating a new function let recv data from ssh server
	public String recvData() {
		String strData = "";
		this.status = false;
		
			try {
				if(this.mSSHInput !=null ) {
					//inhere you can use input stream let check have or handet got a data from 
					//input stream let recv
					int iAvailable = this.mSSHInput.available();
					
					
					while(iAvailable > 0) {
						//creating buffer let recv data
						byte[] btBuffer = new byte[iAvailable];
						
						//check byte read from input stream
						int iByteRead = this.mSSHInput.read(btBuffer);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//check byte read from input stream
						iAvailable = iAvailable - iByteRead;
						strData += new String(btBuffer);

					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.status = true;
		return strData;
		
	}
	
	public boolean getStatus() {
		return this.status;
	}
	//creating a close function let close all in/out stream of ssh
	public void close() {
		if(this.mSSHSession !=null) {
			this.mSSHSession.disconnect();
		}
		
		if(this.mSSHChannel !=null) {
			this.mSSHChannel.isClosed();
		}
		
		if(this.mSSHInput != null) {
			try {
				this.mSSHInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(this.mSSHOutput != null) {
			try {
				this.mSSHOutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.mJschSSH = null;
	}

	

}
