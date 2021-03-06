import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import sun.audio.*;

class ProgressBar extends JFrame
{
	JProgressBar JP_BAR;
	JLabel JLABEL,JLABEL_BACKGRND;
	
    int SCRN_WIDTH,SCRN_HEIGHT;
    Timer t; 
    JFrame JF=new JFrame();	
	public ProgressBar() 
		{
		//Frame 
		 JF.setTitle("The Fruits");	
		 JF.setIconImage(new ImageIcon("image/logo.png").getImage());
		 JF.setResizable(false);
		 		 	
		 //Jpanel 
		 JPanel JP=new JPanel();
		 JP.setLayout(null);
		 JP.setBackground(Color.black);
		 JF.add(JP);
		 
		 //screen size
		//  Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		 SCRN_WIDTH=1366;
		 SCRN_HEIGHT=768;

		 
		 //label of brand & lable or position of bucket on progress bar
		 JLABEL_BACKGRND=new JLabel(new ImageIcon("image/progress_bar1.jpg"));
		 JLABEL_BACKGRND.setBounds(0,0,SCRN_WIDTH,SCRN_HEIGHT);

		 JLABEL=new JLabel(new ImageIcon("image/bucket.png"));
		 JLABEL.setBounds(SCRN_WIDTH-140,500,140,100);
		 JP.add(JLABEL);
		 JP.add(JLABEL_BACKGRND);

		 
		 
		 //progress bar
		 JP_BAR=new JProgressBar();
		 JP_BAR.setBounds(0,600,1368,11 );
		 JP_BAR.setValue(0);
		 JP_BAR.setBackground(Color.white);
		 JP_BAR.setForeground(Color.blue);
		 JLABEL_BACKGRND.add(JP_BAR);
			
         JF.setLocation(0,0);
		 JF.setSize(SCRN_WIDTH,SCRN_HEIGHT);
		 JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JF.setVisible(true);
		 t=new Timer(100,new MyListener());
		 t.start();
	
		}

	public class MyListener implements ActionListener
		{
		int val=0;
		int xb=1220;
		public void actionPerformed(ActionEvent e)
		   {
		    val=val+5;
			JP_BAR.setValue(val);
			xb=xb-65;
			JLABEL.setBounds(xb,500,140,100);
			if(val>=100)
				{
				t.stop();
				 new Home();
				 JF.dispose();
				}
			}
		} 

	public static void main(String[] args) 
	{
		new ProgressBar();
	}
}
