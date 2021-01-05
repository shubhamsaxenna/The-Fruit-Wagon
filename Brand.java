import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

class Brand extends JFrame
 {
	JProgressBar JP_BAR;
	int SCRN_WIDTH,SCRN_HEIGHT;
    Timer t; 
    
	Brand()
    {
	 setIconImage(new ImageIcon("image/logo.png").getImage());
	 
	 //Jpanel 
	 JPanel JP=new JPanel();
	 JP.setLayout(null);
	 JP.setBackground(Color.black);
	 setResizable(false);
	 
	 //screen size
	 Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	 SCRN_WIDTH=(int)d.getWidth();
	 SCRN_HEIGHT=(int)d.getHeight();
	 
	 //label of brand 
	 JLabel JLABEL=new JLabel(new ImageIcon("image/jgame.gif"));
	 JLABEL.setBounds(330,250,740,140);
	 
	 //progress bar
	 JP_BAR=new JProgressBar();
	 JP_BAR.setBounds(1,470,1398,15 );
	 JP_BAR.setBackground(Color.white);
	 JP_BAR.setForeground(Color.blue);
	 
	 add(JP);
	 JP.add(JLABEL);
	 setTitle("The Fruits");	
	 setLocation(0,0);
	 setSize(SCRN_WIDTH,SCRN_HEIGHT);
	 
	 setVisible(true);
	 t=new Timer(200,new MyListener());
	 t.start();
	}
	
	
	public class MyListener implements ActionListener
		{
		int val=0;
		int xb=1368;
		public void actionPerformed(ActionEvent e)
	   {
		    val=val+5;
			JP_BAR.setValue(val);
			if(val>120)
			{
				//AudioPlayer.player.stop(audioStream);
				t.stop();
				new ProgressBar();
				dispose();
			}
		}
	}
    
	/*public void song()
		   {
	      try
		    {
		     String gongFile = "sound/j.wav";
		     InputStream in = new FileInputStream(gongFile);
             audioStream = new AudioStream(in);
			 AudioPlayer.player.start(audioStream);
			}
		  catch(Exception e)
			{
				System.out.println(e);
			}
	     }*/
	
	public static void main(String a[])
	{
		new Brand();
	}
}