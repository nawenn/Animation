package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Message extends JLabel {
	
	private BufferedImage analogSignal;
	private BufferedImage digitalSignal;
	
	//attach different signal images
	public BufferedImage digitalSignal0(){
		BufferedImage digitalSignal0 = null;
		try{
			digitalSignal0 = ImageIO.read(getClass().getResource("/img/digital0.PNG"));
		}catch(IOException e){
			
		}
		return digitalSignal0;
	}
	public BufferedImage digitalSignal1(){
		BufferedImage digitalSignal1 = null;
		try{
			digitalSignal1 = ImageIO.read(getClass().getResource("/img/digital1.png"));
		}catch(IOException e){
			
		}
		return digitalSignal1;
	}
	public BufferedImage analogSignal0(){
		BufferedImage analogSignal0 = null;
		try{
			analogSignal0 = ImageIO.read(getClass().getResource("/img/signal0.PNG"));
		}catch(IOException e){
			
		}
		return analogSignal0;
	}
	
	public BufferedImage analogSignal1(){
		BufferedImage analogSignal1 = null;
		try{
			analogSignal1 = ImageIO.read(getClass().getResource("/img/signal1.PNG"));
		}catch(IOException e){
			
		}
		return analogSignal1;
	}
	

	//hex to binary method
	public String hexToBin(String s) {
		  return new BigInteger(s, 16).toString(2);
		}
	
	public String stringToHex(String message){
		String hexmeaasge = "";
		for (int i = 0; i<message.length(); i++){
			char ch = message.charAt(i);
			int chvalue = (int) ch;
			String hex = Integer.toHexString(chvalue);
			hexmeaasge = hexmeaasge + hex;
		}
		return hexmeaasge;
	}
	
	public String binaryToHex(String bin) {
		   return String.format("%21X", Long.parseLong(bin,2)) ;
		}	
	
	//join two image together
	public BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {
		    int offset = 1;
		    int width = img1.getWidth() + img2.getWidth() + offset;
		    int height = Math.max(img1.getHeight(), img2.getHeight());
		    BufferedImage newImage = new BufferedImage(width, height,
		        BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = newImage.createGraphics();
		    Color oldColor = g2.getColor();
		    g2.setPaint(Color.BLACK);
		    g2.fillRect(0, 0, width, height);
		    g2.setColor(oldColor);
		    g2.drawImage(img1, null, 0, 0);
		    g2.drawImage(img2, null, img1.getWidth() + offset, 0);
		    g2.dispose();
		    return newImage;
		  }
	
	public BufferedImage getDigitalSignal(String bits) {
		digitalSignal = null;
			for (int i = 0; i< bits.length(); i++){
				if(bits.charAt(i) == '1'){
					if(digitalSignal ==null){
						digitalSignal = digitalSignal1();
					}else{
						digitalSignal = joinBufferedImage(digitalSignal, digitalSignal1());
					}
				}if(bits.charAt(i) == '0'){
					if(digitalSignal ==null){
						digitalSignal = digitalSignal0();
					}else{
						digitalSignal = joinBufferedImage(digitalSignal, digitalSignal0());
					}
				}
			}
		
		return digitalSignal;
	}

	public BufferedImage getAnalogSignal(String bits) {
		analogSignal = null;
			for (int i = 0; i< bits.length(); i++){
				if(bits.charAt(i) == '1'){
					if(analogSignal ==null){
						analogSignal = analogSignal1();
					}else{
						analogSignal = joinBufferedImage(analogSignal, analogSignal1());
					}
				}if(bits.charAt(i) == '0'){
					if(analogSignal ==null){
						analogSignal = analogSignal0();
					}else{
						analogSignal = joinBufferedImage(analogSignal, analogSignal0());
					}
				}
			}
		return analogSignal;
	}

	//defined the boarder of each layers.
	public void getLayer(Ball ball){
		if((ball.getX() == 90 && ball.getY()<=382) || 
				((ball.getX() <=1275 && ball.getX()>=1271) && ball.getY()<=382)){
			if(ball.getY() < 100){
				ball.setLayer("Application Layer");
			}else if(ball.getY() <144 && ball.getY() >= 100){
				ball.setLayer("Presentation Layer");
			}else if(ball.getY() <192 && ball.getY() >= 144){
				ball.setLayer("Session Layer");
			}else if(ball.getY() <239 && ball.getY() >= 192){
				ball.setLayer("Transport Layer");
			}else if(ball.getY() <288 && ball.getY() >= 239){
				ball.setLayer("Network Layer");
			}else if(ball.getY() < 336 && ball.getY() >=288){
				ball.setLayer("DataLink Layer");
			}else if(ball.getY() <383 && ball.getY() >= 336){
				ball.setLayer("Phycial Layer");
			}
			}else if(ball.getX()>315 && ball.getX()<=1019){
				ball.setLayer("Analog");
			}else{
				ball.setLayer("Digital");
			}
			
	
	}
	
	public void display(String message, Ball ball){
		String desMac ="80002";
		String souMac ="80002";
		String etherType ="08";
		BufferedImage image = null;
		if(ball.getLayer()!= null){
			
			//drawing a jfram to display messages
			JFrame box = new JFrame();
			Message m = new Message();
			JLabel label = new JLabel();
			if(ball.getLayer().equalsIgnoreCase("Application Layer")||
					ball.getLayer().equalsIgnoreCase("Session Layer") ||
					ball.getLayer().equalsIgnoreCase("Presentation Layer")){
				box.setPreferredSize(new Dimension(1000, 100));
				label = new JLabel(message,SwingConstants.CENTER);
			}else if(ball.getLayer().equalsIgnoreCase("Transport Layer")){
				message = "H1|" + message;
				box.setPreferredSize(new Dimension(1000, 100));
				label = new JLabel(message,SwingConstants.CENTER);
			}else if(ball.getLayer().equalsIgnoreCase("Network Layer")){
				message = "45002805C840002D06906040FFB4FCC0A80104";
				box.setPreferredSize(new Dimension(1000, 100));
				label = new JLabel(message,SwingConstants.CENTER);
			}else if(ball.getLayer().equalsIgnoreCase("DataLink Layer")){
				
				//for data link layer the message should include different Ethernet addresses
				message = desMac+ souMac +etherType+ m.stringToHex(message)+m.stringToHex(crc32());
				box.setPreferredSize(new Dimension(1000, 100));
				label = new JLabel(message,SwingConstants.CENTER);
			}else if(ball.getLayer().equalsIgnoreCase("Phycial Layer")){
				message = desMac+ souMac +etherType+ m.stringToHex(message)+m.stringToHex(crc32());
				label = new JLabel(m.hexToBin(message),SwingConstants.CENTER);
				box.setPreferredSize(new Dimension(1000, 100));
			}else if(ball.getLayer().equalsIgnoreCase("Analog")){
				//display analog signal in the message box.
				message = desMac+ souMac +etherType+ m.stringToHex(message)+m.stringToHex(crc32());
				String bits = m.hexToBin(message);
				box.setPreferredSize(new Dimension(1000, 200));
				image=m.getAnalogSignal(bits);
				label = new JLabel(new ImageIcon(image),SwingConstants.CENTER);
			}else if(ball.getLayer().equalsIgnoreCase("Digital")){
				message = desMac+ souMac +etherType+ m.stringToHex(message)+m.stringToHex(crc32());
				String bits = m.hexToBin(message);
				box.setPreferredSize(new Dimension(1000, 200));
				image=m.getDigitalSignal(bits);
				label = new JLabel(new ImageIcon(image),SwingConstants.CENTER);
			}
			box.setTitle(ball.getName() +" " + ball.getLayer());
			box.getContentPane().add(label,BorderLayout.CENTER);
			box.setVisible(true);
			box.pack();
			box.setLocationRelativeTo(null);
			box.setVisible(true);
			box.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
	}
	
	//crc32 code
	//
	//
	//
    public String crc32()
    {
        int[] data;
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;
        String crcvalue;
        int data_bits = 7, divisor_bits=3, tot_length;
              
        data=new int[data_bits];	       
            data[0] = 1;
            data[1] = 0;
            data[2] = 1;
            data[3] = 1;
            data[4] = 0;
            data[5] = 0;
            data[6] = 1;       
            divisor=new int[divisor_bits];
            divisor[0]=1;
            divisor[1]=0;
            divisor[2]=1;

         tot_length=data_bits+divisor_bits-1;
       
        div=new int[tot_length];
        rem=new int[tot_length];
        crc=new int[tot_length];
        for(int i=0;i<data.length;i++)
            div[i]=data[i];

       
        for(int j=0; j<div.length; j++){
              rem[j] = div[j];
        }
   
        rem=divide(div, divisor, rem);
       
        for(int i=0;i<div.length;i++)           
        {
            crc[i]=(div[i]^rem[i]);
        }
       
        crcvalue=crc[0]+"";
        for (int i = 1; i<crc.length; i++){
        	crcvalue += crc[i]+"";
        }
        return crcvalue;
    }
              
    public int[] divide(int div[],int divisor[], int rem[])
    {
        int cur=0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
                rem[cur+i]=(rem[cur+i]^divisor[i]);
           
            while(rem[cur]==0 && cur!=rem.length-1)
                cur++;
   
            if((rem.length-cur)<divisor.length)
                break;
        }
        return rem;
    }
}
