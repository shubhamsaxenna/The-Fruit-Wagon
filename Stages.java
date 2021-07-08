import database.Connect;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

class Stages 
{
	//Declaring variables globally
	//Declaring swing 
	JFrame LEVEL_FRAME , GAME_FRAME , PAUSE_FRAME;
	JLabel STAGE_NUM , BACKGROUND_IMG , SCORE_BACKGROUND , FRUIT_1, FRUIT_2,BOMB, NEW_SCORE, HIGH_SCORE, SCORE , TIME , PLYR_NAME ,BASKET;
	JPanel PANEL;
	JProgressBar PROGRESS_BAR;
	JButton RESUME, RESTRT,EXT;
	
	//Declaring int and string
	int val=0, X_BASKET, Y_BASKET, H_BASKET, W_BASKET, X_FRUIT=200, Y_FRUIT=0, hSValue=0; 
	int INDEX, POINT=0, X_FRUIT_SEC, Y_FRUIT_SEC, X_BOMB=350, Y_BOMB;
	static String PLAYER_NAME;
	
	//Declaring timer and boolean
	Timer TIMER_LEVELNAME , FRUIT_TIMER_1 , FRUIT_TIMER_2, PLAY_TIME, BOMB_TIMER ;
	boolean touched=true , BOL_FRUIT , BOL_FRUIT_SEC, BOL_BOMB=false ;
	
	String bmb[]={"image/bomb.png"};
	String IMG_ARRAY[]={"image/ora.png","image/nim.png","image/apple.png","image/str.png"};

        
	// Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	int SCRN_WIDTH=1366;
	int SCRN_HEIGHT=768;
	
	ResultSet rs;
	PreparedStatement ps;
	Connection con;

	public Stages()
	{
		//class Constructor.
	}

	public void connecting()
	{
		try
	    {
			con=Connect.connect();

			ps=con.prepareStatement("select * from playerdetails");
			rs=ps.executeQuery();
			while(rs.next()!=false)
			{
				hSValue = rs.getInt("score");
			}
		}
		catch(Exception e)
		{
			System.out.println("Connect method error");	
		}				
	}


	//Frame of each level in this function return type is JFrame.
	public JFrame frame()
	{
		JFrame FRAME=new JFrame();
		FRAME.setIconImage(new ImageIcon("image/logo.png").getImage()); 
  		FRAME.setTitle("Game");
		PANEL=new JPanel();
		PANEL.setLayout(null);
		PANEL.setBackground(Color.black);
		
		FRAME.add(PANEL);
		FRAME.setLocation(0,0);    
	    FRAME.setSize(SCRN_WIDTH , SCRN_HEIGHT);
           
	    FRAME.setVisible(true);
	    FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		return FRAME;
	}

	
	//level1 name frame
	public void levelFrame()
	{
 		LEVEL_FRAME=frame();
			
		STAGE_NUM=new JLabel();
		STAGE_NUM.setBounds(500,290,340,80);		  

		PROGRESS_BAR=new JProgressBar(0,100);
		PROGRESS_BAR.setValue(0);
		PROGRESS_BAR.setForeground(Color.blue);
		PROGRESS_BAR.setBackground(Color.black);
		PROGRESS_BAR.setBounds(0,300,400,10);
		
		PANEL.add(STAGE_NUM);
	}
	
	static int LVL_NUM=1;

	/*It loads the level name frame after Enter-Name frame showing.
	  level name is decided depending upon the length of variables in var arg method.*/
	public void levelNameLoad(String name , int...pnt)
	{
		levelFrame();
		PLAYER_NAME=name;			
		int len=pnt.length;
		
		if(LVL_NUM==1)	//if len==0 means there is no int & the level loaded is level 1.
		{
			STAGE_NUM.setIcon(new ImageIcon("image/1.png"));
			TIMER_LEVELNAME=new Timer(1000,new startLoad());
			TIMER_LEVELNAME.start();			
		}
		if(LVL_NUM==2)
		{
			STAGE_NUM.setIcon(new ImageIcon("image/2.png"));
			TIMER_LEVELNAME=new Timer(500,new startLoad());
			TIMER_LEVELNAME.start();			
		}
		if(LVL_NUM==3)
		{
			STAGE_NUM.setIcon(new ImageIcon("image/3.png"));
			TIMER_LEVELNAME=new Timer(500,new startLoad());
			TIMER_LEVELNAME.start();			
		}
		if(LVL_NUM==4)
		{
			STAGE_NUM.setIcon(new ImageIcon("image/4.png"));
			TIMER_LEVELNAME=new Timer(500,new startLoad());
			TIMER_LEVELNAME.start();			
		}
		if(LVL_NUM==5)
		{
			STAGE_NUM.setIcon(new ImageIcon("image/5.png"));
			TIMER_LEVELNAME=new Timer(500,new startLoad());
			TIMER_LEVELNAME.start();			
		}
	}
	
	//levels second part loadfunction
	//show start image after level name
	public int levelStart()
	{
		val=val+25;
		PROGRESS_BAR.setValue(val);
	    if(val==50)
	    {
			STAGE_NUM.setIcon(new ImageIcon("image/st.png"));
	    }
  	    return val; 
	}
	

	//level name show then after .5sec go to levelstart fucntion
	//then goto level 1.
	public class startLoad implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			int res=levelStart();    // calling levelstart() for changing image after levelname.
			if(res==100)
			{
				LEVEL_FRAME.dispose();	
				if (LVL_NUM==1)
				{
					new Stages(PLAYER_NAME);
				}
				else if (LVL_NUM==2)
				{
					new Stages(PLAYER_NAME,POINT);
				}
				else if (LVL_NUM==3)
				{
					new Stages(PLAYER_NAME,POINT, 3);
				}
				else if (LVL_NUM==4)
				{
					new Stages(PLAYER_NAME,POINT, 3, 4);
				}
				else if (LVL_NUM==5)
				{
					new Stages(PLAYER_NAME,POINT, 3, 4, 5);
				}
		  	}
		}
	}
	

	/*main frame()  while game load
	  in this frame fruit and basket are set*/
	public void playFrame()
	{
		GAME_FRAME=frame();
		
		//connecting to database method.
		connecting();
		 

		   	
		//level1 background
		BACKGROUND_IMG=new JLabel(new ImageIcon("image/bg.jpg"));
        BACKGROUND_IMG.setBounds(0,0,1066,768);       
        PANEL.add(BACKGROUND_IMG);
              
		//score card background
		SCORE_BACKGROUND=new JLabel(new ImageIcon("image/scr6.jpg"));
        SCORE_BACKGROUND.setBounds(1067,0,300,700);
        SCORE_BACKGROUND.setLayout(null);
        PANEL.add(SCORE_BACKGROUND);
	
		//adding fruits & wagon
    	FRUIT_1=new JLabel(new ImageIcon("image/str.png"));
        BACKGROUND_IMG.add(FRUIT_1); 												
        FRUIT_2=new JLabel(new ImageIcon("image/apple.png"));
        BACKGROUND_IMG.add(FRUIT_2);
        
		BOMB=new JLabel(new ImageIcon("image/bomb.png"));
        BOMB.setBounds(290,0,50,50);
		BACKGROUND_IMG.add(BOMB); 												
        BASKET=new JLabel(new ImageIcon("image/bas.png"));
        BASKET.setBounds(320,555,150,150);
        BACKGROUND_IMG.add(BASKET);

		//Play time score of user 
		NEW_SCORE=new JLabel("00");
		NEW_SCORE.setFont(new Font("Arial",Font.BOLD,26));
        NEW_SCORE.setForeground(Color.PINK);
        NEW_SCORE.setBounds(130,150,220,35);                         
        SCORE_BACKGROUND.add(NEW_SCORE);

        SCORE=new JLabel("Score: ");
		SCORE.setFont(new Font("Arial",Font.BOLD,22));
        SCORE.setForeground(Color.PINK);
        SCORE.setBounds(60,150,220,35);                         
        SCORE_BACKGROUND.add(SCORE);
	
        HIGH_SCORE=new JLabel("High Score:");
        HIGH_SCORE.setFont(new Font("Arial",Font.BOLD,22));
        HIGH_SCORE.setBounds(60,180,220,35); 
		HIGH_SCORE.setText("HighScore:"+hSValue);
        SCORE_BACKGROUND.add(HIGH_SCORE);

        TIME=new JLabel("02:00");
        TIME.setFont(new Font("Arial",Font.ITALIC,50));
        TIME.setBounds(80,300,280,55);                         
        SCORE_BACKGROUND.add(TIME);
                

		PLYR_NAME=new JLabel(""+PLAYER_NAME);
        PLYR_NAME.setFont(new Font("Arial",Font.ITALIC,36));
        PLYR_NAME.setForeground(Color.BLACK);
        PLYR_NAME.setBounds(90,100,220,45);                         
        SCORE_BACKGROUND.add(PLYR_NAME);
			
		GAME_FRAME.addKeyListener(new basketMove());
                
		FRUIT_TIMER_1=new Timer(80,new fruitDrop());
            
		FRUIT_TIMER_2=new Timer(75,new fruitDropSec());
            
		BOMB_TIMER=new Timer(70,new bomb());
            
	}

	/*Level 1 start from here. It calls playframe(). The Stage Num is depend 
	  upon the number of parameters in it. */
	public Stages(String s)
    { 
		playFrame();
		BOMB.setIcon(new ImageIcon(" "));
		FRUIT_TIMER_1.start();
		FRUIT_TIMER_2.start();
			
		PLAY_TIME=new Timer(500,new watchLevel());
		PLAY_TIME.start();
		GAME_FRAME.addKeyListener(new stageEsc());
	}
	
	public Stages(String name, int pnt)
    {   
		playFrame();
		POINT=pnt;
		NEW_SCORE.setText(""+POINT);
		BACKGROUND_IMG.setIcon(new ImageIcon("image/bg2.jpg"));
           
        FRUIT_TIMER_1.start();
		FRUIT_TIMER_2.start();
		BOMB_TIMER.start();
		PLAY_TIME=new Timer(100,new watchLevel());
        PLAY_TIME.start();
        GAME_FRAME.addKeyListener(new stageEsc());
	}
	
	public Stages(String s, int pnt, int y)
    {
		playFrame();
		POINT=pnt;
		NEW_SCORE.setText(""+POINT);
		BACKGROUND_IMG.setIcon(new ImageIcon("image/bg3.jpg"));
	    
		FRUIT_TIMER_1.start();
		FRUIT_TIMER_2.start();
		BOMB_TIMER.start();
		PLAY_TIME=new Timer(100,new watchLevel());
        PLAY_TIME.start();
        
        GAME_FRAME.addKeyListener(new stageEsc());
	}

	public Stages(String s, int pnt, int y, int z)
	{
		playFrame();
		POINT=pnt;
		NEW_SCORE.setText(""+POINT);
		BACKGROUND_IMG.setIcon(new ImageIcon("image/bg4.png"));
		FRUIT_TIMER_1.start();
		FRUIT_TIMER_2.start();
		BOMB_TIMER.start();
		PLAY_TIME=new Timer(100,new watchLevel());
		PLAY_TIME.start();
        
		GAME_FRAME.addKeyListener(new stageEsc());
	}

	public Stages(String s, int pnt, int y, int z, int k)
	{
		playFrame();
		POINT=pnt;
		NEW_SCORE.setText(""+POINT);
		BACKGROUND_IMG.setIcon(new ImageIcon("image/bg5.jpg"));
		FRUIT_TIMER_1.start();
		FRUIT_TIMER_2.start();
		BOMB_TIMER.start();
		PLAY_TIME=new Timer(100,new watchLevel());
		PLAY_TIME.start();
        
		GAME_FRAME.addKeyListener(new stageEsc());
	}


	//selection of next Location from where fruit drop
	//and selecttion of fruit
	//if fruit touches again realocate the fruit to top
	public void fruit()
	{
	    if(BOL_FRUIT)
		{
			X_FRUIT=(int)(1010*Math.random());
			INDEX=(int)(4*Math.random());
			FRUIT_1.setIcon(new ImageIcon(IMG_ARRAY[INDEX]));
		}
		FRUIT_1.setBounds(X_FRUIT,Y_FRUIT,50,60);
		Y_FRUIT=Y_FRUIT+7;
		BOL_FRUIT=false;
		if(Y_FRUIT>=600)
		{
			Y_FRUIT=0;
			touched=true;
			BOL_FRUIT=true;
	    }
	}
	
	//It checks whethe the basket and fruit touche each other 
	//or not if it touches the fruit then the point incereased 
	public class fruitDrop implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			fruit();
			if(X_FRUIT>=X_BASKET && X_FRUIT<=(X_BASKET+(W_BASKET-10)) && Y_FRUIT>=(690-H_BASKET) && touched)
            {
				POINT=POINT+10;
		        NEW_SCORE.setText(" "+POINT);
			    touched=false;
			    FRUIT_1.setIcon(null);
            }
		}
	}

	//It set the location of Fruit2 and select the next x,y location
	//And increase the location of y.
	public void fruitSecond()
	{
		if(BOL_FRUIT_SEC)
		{
			X_FRUIT_SEC=(int)(1010*Math.random());
			INDEX=(int)(2*Math.random());
			FRUIT_2.setIcon(new ImageIcon(IMG_ARRAY[INDEX]));
		}
		FRUIT_2.setBounds(X_FRUIT_SEC,Y_FRUIT_SEC,50,60);
		Y_FRUIT_SEC = Y_FRUIT_SEC + 7;
		BOL_FRUIT_SEC=false;
		if(Y_FRUIT_SEC >=660)
		{
			Y_FRUIT_SEC =0;
			touched=true;
			BOL_FRUIT_SEC=true;
		}
	}

	//It checks whethe the basket and fruit_2 touch each other 
	//or not if it touches the fruit then the point incereased
	public class fruitDropSec implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			fruitSecond();
			if(X_FRUIT_SEC>=X_BASKET && X_FRUIT_SEC<=((W_BASKET-10)+X_BASKET) && Y_FRUIT_SEC>=(690-H_BASKET)&&touched)
			{
				POINT=POINT+10;
				NEW_SCORE.setText(""+POINT);
				touched=false;
				FRUIT_2.setIcon(null);
			}
        }
	}	
	
	//bomb action. What happen when bomb touches the wagon.
	//It stops the whole game and gives a dialog box.
	public class bomb implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
	    {	
			if (BOL_BOMB)
			{
				X_BOMB=(int)(1010*Math.random());
			}
			
			BOMB.setBounds(X_BOMB,Y_BOMB,50,50);
			Y_BOMB+=7;
			BOL_BOMB=false;
			if(Y_BOMB>=660)
			{
				Y_BOMB=0;
				touched=true;
				BOL_BOMB=true;
			}
            if(X_BOMB >= X_BASKET && X_BOMB<=(X_BASKET+(W_BASKET-10)) && Y_BOMB>=(690-H_BASKET) && touched)
            {
				FRUIT_TIMER_1.stop();
				FRUIT_TIMER_2.stop();
				BOMB_TIMER.stop();
				PLAY_TIME.stop();
				touched=false;
				FRUIT_1.setIcon(null);
				FRUIT_2.setIcon(null);
				BOMB.setIcon(null);

				if(hSValue<POINT)
				{
					try
					{
						ps=con.prepareStatement("insert into playerdetails (name,score) values('"+PLAYER_NAME+"','"+POINT+"')");
						rs= ps.executeQuery();
					} 
					catch(Exception exc)
					{
						System.out.println("Game over method");
					}
				}

				Object[] options = {"New Game","Retry","Exit"};					
				int res=JOptionPane.showOptionDialog(null,"Game Over","Please select a Option",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("image/gameover.png"),options,options[1]);
				if(res==JOptionPane.YES_OPTION)
				{
					LVL_NUM=1;
					levelNameLoad(PLAYER_NAME);
				}
				else if(res==JOptionPane.NO_OPTION)
				{
					levelNameLoad(PLAYER_NAME, LVL_NUM);
			    }
				else if(res==JOptionPane.CANCEL_OPTION)
				{
					System.exit(0);
			    }
			}
		}
	}
	
	//Movement of Basket left or right
	public class basketMove extends KeyAdapter
    {
		public void keyPressed(KeyEvent e)
	    {
			X_BASKET=BASKET.getX();
            Y_BASKET=BASKET.getY();
            W_BASKET=150;
            H_BASKET=150;
			
            int kc=e.getKeyCode();
            if(kc==e.VK_LEFT)
			{
				X_BASKET=X_BASKET-25;
                if(X_BASKET>=-25)
				{
                   	BASKET.setIcon(new ImageIcon("image/bas.png"));
                   	BASKET.setBounds(X_BASKET,Y_BASKET,150,150);
               	}
            }
            else if(kc==e.VK_RIGHT)
			{
				X_BASKET=X_BASKET+25;
                if(X_BASKET<=925)
				{
					BASKET.setIcon(new ImageIcon("image/bas2.png"));
                    BASKET.setBounds(X_BASKET,Y_BASKET,150,150);
                }
			}
		}
	}
    
	//If the time of 2 min is over then everything stops by this method
	//It shows a dialog Box contains 3 menu.
	public int timesUp()
	{
		FRUIT_1.setIcon(null);
	    FRUIT_2.setIcon(null); 
		BOMB.setIcon(null);
		FRUIT_TIMER_1.stop();
		FRUIT_TIMER_2.stop();
		PLAY_TIME.stop();
	    Object[] options = {"Next Level","Retry","Exit"};					
		Object msg[]={"Time Up","Score:"+POINT};
		ImageIcon Icn=new ImageIcon("image/level.png");
         			
		int output=JOptionPane.showOptionDialog(null,msg,"Time's up",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,Icn,options,options[1]);
			
		return output;		
	}

	//It reduce the time of 2 min every second.
	public class watchLevel implements ActionListener
	{
		int timet=120;
        public void actionPerformed(ActionEvent e)
		{
			if (timet>0)
			{
				int min=timet/60;
				int sec=timet%60;
				TIME.setText(min+":"+sec);
				timet--;
			}
			else if(timet==0)
			{
				int output=timesUp();
				if(output == JOptionPane.YES_OPTION)
				{
					GAME_FRAME.dispose();
					if (LVL_NUM==1)
					{
						LVL_NUM=2;
						levelNameLoad(PLAYER_NAME, POINT);
					}
					else if (LVL_NUM==2)
					{
						LVL_NUM=3;
						levelNameLoad(PLAYER_NAME, POINT);
					}
					else if (LVL_NUM==3)
					{
						LVL_NUM=4;
						levelNameLoad(PLAYER_NAME, POINT, 3);
					}
					else if (LVL_NUM==4)
					{
						LVL_NUM=5;
						levelNameLoad(PLAYER_NAME, POINT, 3, 4);
					}
				}
		        
				else if(output == JOptionPane.NO_OPTION)	
				{
					GAME_FRAME.dispose();
					
					if (LVL_NUM==1)
					{
						levelNameLoad(PLAYER_NAME);
					}
					else if (LVL_NUM==2)
					{
						levelNameLoad(PLAYER_NAME, POINT);
					}
					else if (LVL_NUM==3)
					{
						levelNameLoad(PLAYER_NAME, POINT, 3);
					}
					else if (LVL_NUM==4)
					{
						levelNameLoad(PLAYER_NAME, POINT, 3, 4);
					}
					else if (LVL_NUM==5)
					{
						levelNameLoad(PLAYER_NAME, POINT, 3, 4, 5);
					}
				}
				
				else if(output == JOptionPane.CANCEL_OPTION)
				{
					System.exit(0);
				}
			}
		}
	}
	
	//It consist frame of pause menu having three options. 
	public void pauseMenu()
		{
			PAUSE_FRAME=frame();
            JLabel MENU_BACKGROUND=new JLabel(new ImageIcon("Image/progress_bar1.jpg"));
            MENU_BACKGROUND.setBounds(0,0,1366,760);
		    
			RESUME=new JButton("Resume");
			RESUME.setBounds(640,500,110,35);
			MENU_BACKGROUND.add(RESUME);
            
			RESTRT=new JButton("Restart");
            RESTRT.setBounds(640,550,110,35);
            MENU_BACKGROUND.add(RESTRT);
            
			EXT=new JButton("Exit");
            EXT.setBounds(640,600,110,35);
            MENU_BACKGROUND.add(EXT);   
            
			PANEL.add(MENU_BACKGROUND); 
            
		}

	static int esc_lvl=1;
	/*If esc is press in any condition while playing then it stop the everything.
	  Here static variable esc_lvl is defined to know the stage. This variable 
	  value changes in watchLevel method.*/
	public class stageEsc extends KeyAdapter
    {
		public void keyPressed(KeyEvent e)
	    {
			int kc=e.getKeyCode();
            if(kc==e.VK_ESCAPE)
		    {
				pauseMenu();	   
                FRUIT_TIMER_1.stop();
                FRUIT_TIMER_2.stop();
				BOMB_TIMER.stop();
                PLAY_TIME.stop();
				
				RESUME.addActionListener(new stagePauseActionResume());
				RESTRT.addActionListener(new stagePauseActionRestrt());
				EXT.addActionListener(new stagePauseActionExt());
			}
	     }
	}
	
	//After esc press user action will be processed here.
	public class stagePauseActionResume implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == RESUME) 
            {
				FRUIT_TIMER_1.start();
                FRUIT_TIMER_2.start();
				BOMB_TIMER.start();
                PLAY_TIME.start();
				PAUSE_FRAME.dispose();
			}
		}
    }
	 public class stagePauseActionRestrt implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			GAME_FRAME.dispose();
        	PAUSE_FRAME.dispose();
			if (LVL_NUM==1)
			{
				new Stages(PLAYER_NAME);
			}
			else if (LVL_NUM==2)
			{
				new Stages(PLAYER_NAME,POINT);
			}
			else if (LVL_NUM==3)
			{
				levelNameLoad(PLAYER_NAME, POINT, 3);
			}
			else if (LVL_NUM==4)
			{
				levelNameLoad(PLAYER_NAME, POINT, 3, 4);
			}
			else if (LVL_NUM==5)
			{
				levelNameLoad(PLAYER_NAME, POINT, 3, 4, 5);
			}
		}
    }
	 public class stagePauseActionExt implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{
			 if (e.getSource() == EXT) 
             {
			    System.exit(0);
             }
		}
    }
	
	public static void main(String[] args) 
	{
		Stages st=new Stages();
		st.levelNameLoad("asd");
	}
}