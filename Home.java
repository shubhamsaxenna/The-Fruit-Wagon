import database.Connect;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.*;

class Home 
	{
	JLabel jlabel_BACKGRND, jl_RANK, jl_NAME, jl_SCORE;
	JFrame jf, jScoreBoard ;
    JButton jbtn_NG, jbtn_HS, jbtn_EXT, jbtn_BACK;
	Timer t; 
	
	int n=1, len=0; int i=0;
	
	String PLAYER_DATA[][] = new String[10][3];
	String column[]= {"Rank","Name","Score"};	
	//screen size
	//  Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	 int Scrn_WIDTH=1366;
	 int Scrn_HEIGHT=768;


	//connecting to database.
	public void connecting()
	{
		try
	    {
			Connection con=Connect.connect();
			PreparedStatement st=con.prepareStatement("Select * from playerdetails");
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
				if (rs.getString(2)!=null)
				{
					for( i=n-1; i<=len;i++)
					{
						PLAYER_DATA[i][0]=Integer.toString(rs.getInt(1));					
						PLAYER_DATA[i][1]=rs.getString(2);				
						PLAYER_DATA[i][2]=Integer.toString(rs.getInt(3));					
					}			
				}
				n++;
				len++;
			}
		}				 
        catch(Exception e)
		{
			System.out.println("Connect method error");	
		}				
	}


	Home()
		{
		//Frame 
		 jf=new JFrame();
		 jf.setTitle("The Fruits");	
		 jf.setIconImage(new ImageIcon("image/logo.png").getImage());
		 jf.setResizable(false);
		 		 	
		 //Jpanel 
		 JPanel jp=new JPanel();
		 jp.setLayout(null);
		 jp.setBackground(Color.black);
		
		 //label of brand 
		 jlabel_BACKGRND=new JLabel(new ImageIcon("image/home.jpg"));
		 jlabel_BACKGRND.setBounds(0,0,Scrn_WIDTH,Scrn_HEIGHT);
		 
		 
		 //JButton
		 jbtn_NG=new JButton("New Game");
		 jbtn_NG.setBounds(540,350,199,30);
		 jbtn_NG.setContentAreaFilled(false);
		 jbtn_NG.setBorderPainted(false);
		 jbtn_NG.setFont(new Font("ARIAL", Font.BOLD, 24));
		 jbtn_NG.addActionListener(new menu_Action());                  

		 jbtn_HS =new JButton("High Score");
		 jbtn_HS.setBounds(540,390,199,30);
		 jbtn_HS.setContentAreaFilled(false);
		 jbtn_HS.setBorderPainted(false);
		 jbtn_HS.setFont(new Font("ARIAL", Font.BOLD, 24));
		 jbtn_HS.addActionListener(new menu_Action());                  

		 jbtn_EXT=new JButton("Exit");
		 jbtn_EXT.setBounds(540,435,199,30);
		 jbtn_EXT.setContentAreaFilled(false);
		 jbtn_EXT.setBorderPainted(false);
		 jbtn_EXT.setFont(new Font("ARIAL", Font.BOLD, 24));
		 jbtn_EXT.addActionListener(new menu_Action());                  

		 
		 jf.add(jp); 
		 jp.add(jbtn_HS);
		 jp.add(jbtn_EXT);
		 jp.add(jbtn_NG);
		 jp.add(jlabel_BACKGRND);
		 jf.setLocation(0,0);
		 jf.setSize(Scrn_WIDTH,Scrn_HEIGHT);
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 jf.setVisible(true);
		}
	
	
	public class menu_Action implements ActionListener
	{ 	
		public void actionPerformed(ActionEvent e)
        {
			if (e.getSource() == jbtn_EXT) 
            {
				System.exit(0);
            }
			else if(e.getSource()==jbtn_HS)
			{
				scoreBoard();				
			}
			else if(e.getSource()==jbtn_NG)
			{
			    new EnterName();
				jf.dispose();
 			}
	    }
    }

	public void scoreBoard()
	{
		jScoreBoard=new JFrame();
		jScoreBoard.setUndecorated(true);	
		
		//Jpanel 
		JPanel jp=new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.black);
		
		//label of brand 
		jlabel_BACKGRND=new JLabel(new ImageIcon("image/score bg.jpg"));
		jlabel_BACKGRND.setBounds(0,0,Scrn_WIDTH,Scrn_HEIGHT);
		

		jl_RANK=new JLabel("Rank");
		jl_RANK.setBounds(190,50,70,60);
		jl_RANK.setFont(new Font("ARIAL", Font.BOLD, 24));

		jl_NAME=new JLabel("Name");
		jl_NAME.setBounds(390,50,70,60);
		jl_NAME.setFont(new Font("ARIAL", Font.BOLD, 24));

		jl_SCORE=new JLabel("Score");
		jl_SCORE.setBounds(590,50,70,60);
		jl_SCORE.setFont(new Font("ARIAL", Font.BOLD, 24));

		JTable table=new JTable(PLAYER_DATA, column);
		table.setRowHeight(table.getRowHeight() + 15);
		table.setBackground(new Color(141, 180, 66));
		table.setBounds(90,110,670,300);
		table.setFont(new Font("ARIAL", Font.PLAIN, 22));
		//table.setBorder(null);

		jbtn_BACK=new JButton("Back");
		jbtn_BACK.setBounds(327,435,199,40);
		jbtn_BACK.setForeground(Color.WHITE);
		jbtn_BACK.setBackground(Color.BLACK);
		jbtn_BACK.setBorderPainted(false);

		jbtn_BACK.setFont(new Font("ARIAL", Font.BOLD, 28));
		jbtn_BACK.addActionListener(new scoreBoardButton());                  
		

		jlabel_BACKGRND.add(jl_RANK);
		jlabel_BACKGRND.add(jl_NAME);
		jlabel_BACKGRND.add(jl_SCORE);
		jlabel_BACKGRND.add(table);
		jlabel_BACKGRND.add(jbtn_BACK);
		jp.add(jlabel_BACKGRND);
		jScoreBoard.add(jp); 
		
		jScoreBoard.setLocation(220,100);
		jScoreBoard.setSize(Scrn_WIDTH-500, Scrn_HEIGHT-200);
		jScoreBoard.setVisible(true);
		printData();
	}

	public class scoreBoardButton implements ActionListener
	{ 	
		public void actionPerformed(ActionEvent e)
        {
			if(e.getSource()==jbtn_BACK)
			{
			    jScoreBoard.dispose();
 			}
	    }
    }

	public void printData()
	{
		
	}
	public static void main(String[] args) 
	{
		Home hme= new Home();
		hme.connecting();
	}
}
