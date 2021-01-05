import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class EnterName 
{

	JButton STRT_BUTTON;
	JTextField ENTER_TEXT;
	JFrame ENTER_FRAME;
	JPanel PANEL;
	
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	int SCRN_WIDTH=(int)d.getWidth();
	int SCRN_HEIGHT=(int)d.getHeight();
	

		//It is the first frame. It get the username.
	//check whether user name is valid under come conditions or not.
	public EnterName() 
    {
	    ENTER_FRAME=new JFrame();
		ENTER_FRAME.setIconImage(new ImageIcon("image/logo.png").getImage()); 
  		ENTER_FRAME.setTitle("Game");
	
		PANEL=new JPanel();
		PANEL.setLayout(null);
		PANEL.setBackground(Color.black);
		
	   
		JLabel NAME=new JLabel("ENTER NAME:");
        NAME.setBounds(470,337,140,30);
        NAME.setForeground(Color.white);
	    NAME.setFont(new Font("Arial",Font.ITALIC,19));
          
	    STRT_BUTTON = new JButton("Start");
        STRT_BUTTON.setBounds(620,380,80,24);                 
        STRT_BUTTON.addActionListener(new submitButtonAction());                  
               
	    ENTER_TEXT=new JTextField(20);
        ENTER_TEXT.setBounds(615,340,140,24);
          
	    PANEL.add(STRT_BUTTON);
        PANEL.add(NAME);
        PANEL.add(ENTER_TEXT);
	    ENTER_FRAME.add(PANEL);
		ENTER_FRAME.setLocation(0,0);    
	    ENTER_FRAME.setSize(SCRN_WIDTH , SCRN_HEIGHT);
           
	    ENTER_FRAME.setVisible(true);
	    ENTER_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 	 }

 //This listener checks the username is valid or not.
	public class submitButtonAction implements ActionListener
 	{  	
		public void actionPerformed(ActionEvent e)
        {
			if (e.getSource() == STRT_BUTTON) 
			{
				String data = ENTER_TEXT.getText();
				data=data.trim();
				for(int i=0;i<1;i++)
				{
					if(data.charAt(i)!=' '&&data.charAt(i)!='.'&&data.charAt(i)!=',')
					{
						ENTER_FRAME.dispose();
						Stages st=new Stages();
						st.levelNameLoad(data);
					}
				}
			}
		}
	}


	public static void main(String[] args) 
	{
		new EnterName();
	}
}
