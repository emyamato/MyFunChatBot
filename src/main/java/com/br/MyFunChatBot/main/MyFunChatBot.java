package com.br.MyFunChatBot.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;

import com.br.MyFunChatBot.business.MemeListControl;
import com.br.MyFunChatBot.business.MyFunChatBotControl;
import com.br.MyFunChatBot.business.RankingControl;
import com.br.MyFunChatBot.business.SponsorListControl;
import com.br.MyFunChatBot.business.VotingThread;
import com.br.MyFunChatBot.customizedTypes.CircularArrayList;
import com.br.MyFunChatBot.customizedTypes.CustomDocumentFilter;
import com.br.MyFunChatBot.customizedTypes.CustomizedArrayList;
import com.br.MyFunChatBot.customizedTypes.CustomizedGridBagConstraints;
import com.br.MyFunChatBot.ulils.BotConstants;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.ChatterVO;
import com.br.MyFunChatBot.ulils.ConfigFile;
import com.br.MyFunChatBot.ulils.MemeVO;
import com.br.MyFunChatBot.ulils.MyFunctions;
import com.br.MyFunChatBot.ulils.RankVO;
import com.br.MyFunChatBot.ulils.SponsorVO;
import com.br.MyFunChatBot.ulils.TwitchAccessClass;
import com.br.MyFunChatBot.ulils.VotingVO;


public class MyFunChatBot extends JFrame
{
	private static final long serialVersionUID = 5279294847655276862L;
	
	private static JFrame frame;
	private static JPanel cards;
	
	private static JMenuBar menuBar;

	private static ImageIcon imgTwitch;
	private static ImageIcon imgYoutube;
	private static ImageIcon imgDiscord;
	private static ImageIcon imgFacebook;
	private static ImageIcon imgTwitchIcon;
	private static ImageIcon imgYoutubeIcon;
	private static ImageIcon imgDiscordIcon;
	private static ImageIcon imgFacebookIcon;	

	private static JButton buttonStatusTwitchChannel;
	private static JLabel imgTwitchStatus;
	private static JLabel countTwitchConnectedPeople;
	private static JLabel imgYoutubeStatus;
	private static JLabel countYoutubeConnectedPeople;
	private static JLabel imgDiscordStatus;
	private static JLabel countDiscordConnectedPeople;
	private static JLabel imgFacebookStatus;
	private static JLabel countFacebookConnectedPeople;
	private static JLabel imgMyFunChatBotStatus;
	private static ImageIcon imgStatusConnected;
	private static ImageIcon imgStatusDisconnected;	
	
	private static JTextField textFieldBotSettingPort;
	private static JTextField textFieldBotSettingColor;
	private static JTextArea textAreaBotSettingLinks;
	private static JTextField textFieldBotSettingASCIIEmoji;
	private static JPanel panelSetupBotFeatures;
	
	private static JCheckBox checkBoxCommandModON;
	private static JTextField textFieldCommandPrefix;
	private static JCheckBox checkBoxCommandMod;
	private static JCheckBox checkBoxPing;
	private static JCheckBox checkBoxUptime;
	private static JCheckBox checkBoxGame;
	private static JCheckBox checkBoxRank;
	private static JCheckBox checkBoxLinks;	

	
	private static JCheckBox checkBoxAutoAnswerON;
	private static JButton buttonAddLineAutoAnswerTable;
	private static JButton buttonDelLineAutoAnswerTable;
    private static JTable tableAutoAnswer;
    private static DefaultTableModel modelAutoAnswer;
    private static JScrollPane scrollPaneTableAutoAnswer;
    
    private static JTextField textFieldVotingTitle;
    private static JComboBox<String> comboBoxVoting;
    private static JProgressBar progressBarVoting;
    private static JTextField textFieldVotingOption1;
    private static JTextField textFieldVotingOption2;
    private static JTextField textFieldVotingOption3;
    private static JTextField textFieldVotingOption4;
    private static JTextField textFieldVotingOption5;
    private static JTextField textFieldVotingOption6;
    private static JTextField textFieldVotingTimer;
    private static JButton buttonVotingStart;
    private static JButton buttonVotingApply;
    private static JButton buttonVotingDelete;
    private static long votingCurrentTime;
    
    private static JCheckBox checkBoxPointsOn;
    private static JTextField textFieldPointsName;
    private static JTextField textFieldPointsTimer;
    private static JTextField textFieldPointsPerTime;
    private static JCheckBox checkBoxPointsOnlyMod;
    private static JTextField textFieldPointsExtraMod;
    
    private static JCheckBox checkBoxRankingOn;
    private static JTextField textFieldRankingLevel0Title;
    private static JTextField textFieldRankingLevel0Min;
    private static JTextField textFieldRankingLevel0Bonus;
    private static JTextField textFieldRankingLevel1Title;
    private static JTextField textFieldRankingLevel1Min;    
    private static JTextField textFieldRankingLevel1Bonus;
    private static JTextField textFieldRankingLevel2Title;
    private static JTextField textFieldRankingLevel2Min;    
    private static JTextField textFieldRankingLevel2Bonus;
    private static JTextField textFieldRankingLevel3Title;
    private static JTextField textFieldRankingLevel3Min;    
    private static JTextField textFieldRankingLevel3Bonus;
    private static JTextField textFieldRankingLevel4Title;
    private static JTextField textFieldRankingLevel4Min;    
    private static JTextField textFieldRankingLevel4Bonus;
    private static JTextField textFieldRankingLevel5Title;
    private static JTextField textFieldRankingLevel5Min;    
    private static JTextField textFieldRankingLevel5Bonus;
    
    private static JCheckBox checkBoxLootChestON;
    private static JTextField textFieldLootChestPeriod;
    private static JTextField textFieldLootChestTreasure;
    
	private static JCheckBox checkBoxAutoMessageON;
    private static JTextField textFieldAutoMessageTimer;	
	private static JButton buttonAddLineAutoMessageTable;
	private static JButton buttonDelLineAutoMessageTable;
    private static JTable tableAutoMessage;
    private static DefaultTableModel modelAutoMessage;
    private static JScrollPane scrollPaneTableAutoMessage;
    
    private static JCheckBox checkBoxMemeEnabled;
    private static JTextField textFieldMemeSettingCost;
    private static JTextArea textAreaMemeSettingList;
    
    private static JCheckBox checkBoxSponsorEnabled;
    private static JCheckBox checkBoxSponsorMessageEnabled;
    private static JTextField textFieldSponsorSettingTime;
    private static JTextField textFieldSponsorMessage;
    private static JTextArea textAreaSponsorSettingList;
    
    private static JTable tableRanking;
    private static DefaultTableModel modelRanking;
    private static JScrollPane scrollPaneTableRanking;    

    private static JButton buttonConnectTwitchChannel;
	private static JLabel imgTwitchConnection;
	private static JLabel labelTwitchConnectionStatus;
	private static JLabel labelTwitchConnectionStatusRefresh;
	private static JLabel labelTwitchConnectionChannel;
	private static JLabel labelTwitchConnectionChannelRefresh;

	private static JPanel panelAboutMe;
	
	public MyFunChatBot() {
		initGui();
		loadGuiData();		
	}
		
	private void initGui() {
		CustomizedGridBagConstraints constraints = new CustomizedGridBagConstraints();
		try {
			imgStatusConnected = new ImageIcon(ImageIO.read(new File("img/led-green-black.png")).getScaledInstance(10, 10, 0));
			imgStatusDisconnected = new ImageIcon(ImageIO.read(new File("img/led-red-black.png")).getScaledInstance(10, 10, 0));
			imgTwitch = new ImageIcon(ImageIO.read(new File("img/Twitch_400x400.png")).getScaledInstance(100, 100, 0));
			imgYoutube = new ImageIcon(ImageIO.read(new File("img/Youtube_400x400.png")).getScaledInstance(100, 100, 0));
			imgDiscord = new ImageIcon(ImageIO.read(new File("img/Discord_400x400.png")).getScaledInstance(100, 100, 0));
			imgFacebook = new ImageIcon(ImageIO.read(new File("img/Facebook_400x400.png")).getScaledInstance(100, 100, 0));
			imgTwitchIcon = new ImageIcon(ImageIO.read(new File("img/Twitch_400x400.png")).getScaledInstance(30, 30, 0));
			imgYoutubeIcon = new ImageIcon(ImageIO.read(new File("img/Youtube_400x400.png")).getScaledInstance(30, 30, 0));
			imgDiscordIcon = new ImageIcon(ImageIO.read(new File("img/Discord_400x400.png")).getScaledInstance(30, 30, 0));
			imgFacebookIcon = new ImageIcon(ImageIO.read(new File("img/Facebook_400x400.png")).getScaledInstance(30, 30, 0));
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame = new JFrame("MyFunChatBot");
		frame.setTitle("MyFunChatBot");
		frame.setSize(850, 650);
		frame.setPreferredSize(new Dimension(600,450));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); 
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    	menuBar = new JMenuBar();
    	menuBar.setName("MyFunChatBot");
    	frame.setJMenuBar(menuBar);

    	JMenu menuFile = new JMenu("MyFunChatBot"); 
    	menuBar.add(menuFile);    	

    	JMenuItem menuStatus = new JMenuItem("Status");
    	menuFile.add(menuStatus);    	
    	menuStatus.addActionListener(new ActionListener()	{ 
    		public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (cards.getLayout());
		        cl.show(cards, "status");    			
    		}
    	});
    	
    	JMenuItem menuSave = new JMenuItem("Save");
    	menuFile.add(menuSave);    	
    	menuSave.addActionListener(new ActionListener()	{ 
    		public void actionPerformed(ActionEvent e) {
		        ConfigFile.saveConfig();
		        RankingControl.saveChatterList();
		    	JFrame savedFrame = new JFrame();
		    	savedFrame.setPreferredSize(new Dimension(200,100));
		    	savedFrame.setLocation(frame.getLocation());
		    	JLabel message = new JLabel("<HTML>All configurations <BR/>are saved now!</HTML>",SwingConstants.CENTER);
		    	savedFrame.add(message);
		    	savedFrame.pack();
		    	savedFrame.setVisible(true);
    		}
    	});    	

    	JMenuItem menuExit = new JMenuItem("Exit");
    	menuFile.add(menuExit);    	
    	menuExit.addActionListener(new ActionListener()	{ 
    		public void actionPerformed(ActionEvent e) {
    		    System.exit(0);
    		}
    	});

    	JMenu menuSetup = new JMenu("Setup");
    	menuBar.add(menuSetup);
    	
    	JMenuItem menuSetupBotSetting = new JMenuItem("Bot Settings");
    	menuSetup.add(menuSetupBotSetting);
    	menuSetupBotSetting.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards, "setupBotSettings");
    		}
    	});
    	
    	JMenuItem menuSetupBotFeatures = new JMenuItem("Bot Features");
    	menuSetup.add(menuSetupBotFeatures);
    	menuSetupBotFeatures.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards, "setupBotFeatures");
    		}
    	});

    	JMenuItem menuSetupMemes = new JMenuItem("Memes");
    	menuSetup.add(menuSetupMemes);
    	menuSetupMemes.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards, "setupMemes");
    		}
    	});
    	
    	JMenuItem menuSetupGifs = new JMenuItem("Gifs");
    	menuSetup.add(menuSetupGifs);
    	menuSetupGifs.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards, "setupGifs");
    		}
    	});    	

    	JMenuItem menuSetupRanking = new JMenuItem("Ranking");
    	menuSetup.add(menuSetupRanking);
    	menuSetupRanking.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards, "setupRanking");
    		}
    	});
    	
    	JMenuItem menuSetupSponsors = new JMenuItem("Sponsors");
    	menuSetup.add(menuSetupSponsors);
    	menuSetupSponsors.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards, "setupSponsors");
    		}
    	});
    	
    	JMenu menuConnections = new JMenu("Connections"); 
    	JMenuItem menuTwitchConnection = new JMenuItem("Twitch");
    	menuTwitchConnection.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (cards.getLayout());
		        cl.show(cards, "twitchConnection");        			
    		}
    	});
    	menuConnections.add(menuTwitchConnection);
/*
    	JMenuItem menuYoutubeConnection = new JMenuItem("Youtube");
    	menuYoutubeConnection.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (cards.getLayout());
		        cl.show(cards, "youtubeConnection");        			
    		}
    	});
    	menuConnections.add(menuYoutubeConnection);

    	JMenuItem menuDiscordConnection = new JMenuItem("Discord");
    	menuDiscordConnection.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (cards.getLayout());
		        cl.show(cards, "discordConnection");        			
    		}
    	});
    	menuConnections.add(menuDiscordConnection);
    	
    	JMenuItem menuFacebookConnection = new JMenuItem("Facebook");
    	menuFacebookConnection.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (cards.getLayout());
		        cl.show(cards, "facebookConnection");        			
    		}
    	});
    	menuConnections.add(menuFacebookConnection);
*/
    	menuBar.add(menuConnections);
    	
    	JMenu menuInfo = new JMenu("Info");
    	JMenuItem menuHelp = new JMenuItem("Help");
    	menuInfo.add(menuHelp);
    	JMenuItem menuAboutMe = new JMenuItem("About Me");
    	menuAboutMe.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
		        CardLayout cl = (CardLayout) (cards.getLayout());
		        cl.show(cards, "aboutme");        			
    		}
    	});    	
    	menuInfo.add(menuAboutMe);
    	
    	menuBar.add(menuInfo);
    	
    	cards = new JPanel(new CardLayout());
		frame.getContentPane().add(cards, BorderLayout.CENTER);

//***************************************************************//
//************************ STATUS PANEL *************************//
//***************************************************************//
		
		JPanel panelStatus = new JPanel(new GridBagLayout());
		panelStatus.setBackground(Color.BLUE);

		cards.add(panelStatus,"status");

		panelStatus = new JPanel(new GridBagLayout());
        constraints.insets = new Insets(5,10,5,10);
        Dimension statusDimension = new Dimension(520,65);
        
		JPanel panelTwitchStatus = new JPanel(new GridBagLayout());
        panelTwitchStatus.setBackground(Color.DARK_GRAY);
        panelTwitchStatus.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelTwitchStatus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Twitch Status"));
        panelTwitchStatus.setPreferredSize(statusDimension);
        panelStatus.add(panelTwitchStatus,constraints);   
        
//        constraints.insets = new Insets(5,-100,5,10);
        JLabel imgTwitchStatusLogo = new JLabel(imgTwitchIcon);
        imgTwitchStatusLogo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
        panelTwitchStatus.add(imgTwitchStatusLogo,constraints.setXYPosition(0, 0));
//        constraints.insets = new Insets(5,10,5,10);        
        panelTwitchStatus.add(new JLabel("Twitch Status"),constraints.setXYPosition(1, 0));
        imgTwitchStatus = new JLabel(imgStatusDisconnected);
        panelTwitchStatus.add(imgTwitchStatus,constraints.setXYPosition(2, 0));        
        constraints.insets = new Insets(5,50,5,10);        
        panelTwitchStatus.add(new JLabel("Twitch Viewers"),constraints.setXYPosition(3, 0));
        constraints.insets = new Insets(30,10,30,10); 
        countTwitchConnectedPeople = new JLabel("0");
        panelTwitchStatus.add(countTwitchConnectedPeople,constraints.setXYPosition(4, 0));
        buttonStatusTwitchChannel = new JButton("Connect");
        buttonStatusTwitchChannel.setPreferredSize(new Dimension(100,60));
        panelTwitchStatus.add(buttonStatusTwitchChannel,constraints.setXYPosition(5, 0));
        buttonStatusTwitchChannel.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			if (buttonStatusTwitchChannel.getText().equalsIgnoreCase("CONNECT")) {
    				MyFunChatBotControl.connectToTwitch();
    				buttonStatusTwitchChannel.setText("Disconnect");
    				buttonConnectTwitchChannel.setText("Disconnect");    
    			}
    			else {
    				BotGeneralSetupVO.setTwitchOn(false);
    				changingStatus("TwitchConnectionStatusRefresh","Disconnected");
    				changingStatus("TwitchConnectionChannelRefresh","");
    				changingLedStatus("TWITCH","DISCONNECTED");
    				buttonStatusTwitchChannel.setText("Connect");
    				buttonConnectTwitchChannel.setText("Connect");
    			}
    		}
    	});
        
        
/*
        constraints.gridx = 0;
        constraints.gridy = 0;
        JPanel panelYoutubeStatus = new JPanel(new GridBagLayout());       
        panelYoutubeStatus.setBackground(Color.DARK_GRAY);
        panelYoutubeStatus.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelYoutubeStatus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Youtube Status"));
        panelYoutubeStatus.setPreferredSize(statusDimension);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,-80,5,10);         
        JLabel imgYoutubeStatusLogo = new JLabel(imgYoutubeIcon);
        imgYoutubeStatusLogo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
        panelYoutubeStatus.add(imgYoutubeStatusLogo,constraints);
        constraints.insets = new Insets(5,10,5,10);         
        constraints.gridx = 1;
        constraints.gridy = 0;	
        panelYoutubeStatus.add(new JLabel("Youtube Status"),constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;	        
        imgYoutubeStatus = new JLabel(imgStatusDisconnected);
        panelYoutubeStatus.add(imgYoutubeStatus,constraints);        
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,50,5,10);        
        panelYoutubeStatus.add(new JLabel("Youtube Viewers"),constraints);
        constraints.insets = new Insets(5,10,5,10);        
        constraints.gridx = 4;
        constraints.gridy = 0;	        
        countYoutubeConnectedPeople = new JLabel("0");
        panelYoutubeStatus.add(countYoutubeConnectedPeople,constraints);             
        constraints.gridx = 0;
        constraints.gridy = 1;	 
        panelStatus.add(panelYoutubeStatus,constraints);
        
        JPanel panelDiscordStatus = new JPanel(new GridBagLayout());	        
        panelDiscordStatus.setBackground(Color.DARK_GRAY);
        panelDiscordStatus.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelDiscordStatus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Discord Status"));
        panelDiscordStatus.setPreferredSize(statusDimension);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,-90,5,10);         
        JLabel imgDiscordStatusLogo = new JLabel(imgDiscordIcon);
        imgDiscordStatusLogo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
        panelDiscordStatus.add(imgDiscordStatusLogo,constraints);
        constraints.insets = new Insets(5,10,5,10);         
        constraints.gridx = 1;
        constraints.gridy = 0;	 
        panelDiscordStatus.add(new JLabel("Discord Status"),constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;	        
        imgDiscordStatus = new JLabel(imgStatusDisconnected);
        panelDiscordStatus.add(imgDiscordStatus,constraints);        
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,50,5,10);        
        panelDiscordStatus.add(new JLabel("Discord Viewers"),constraints);
        constraints.insets = new Insets(5,10,5,10);        
        constraints.gridx = 4;
        constraints.gridy = 0;	        
        countDiscordConnectedPeople = new JLabel("0");
        panelDiscordStatus.add(countDiscordConnectedPeople,constraints);        
        constraints.gridx = 0;
        constraints.gridy = 2;
        panelStatus.add(panelDiscordStatus,constraints);
        
		JPanel panelFacebookStatus = new JPanel(new GridBagLayout());			
        panelFacebookStatus.setBackground(Color.DARK_GRAY);
        panelFacebookStatus.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelFacebookStatus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Facebook Status"));
        panelFacebookStatus.setPreferredSize(statusDimension);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,-60,5,10);         
        JLabel imgFacebookStatusLogo = new JLabel(imgFacebookIcon);
        imgFacebookStatusLogo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
        panelFacebookStatus.add(imgFacebookStatusLogo,constraints);
        constraints.insets = new Insets(5,10,5,10);         
        constraints.gridx = 1;
        constraints.gridy = 0;	
        panelFacebookStatus.add(new JLabel("Facebook Status"),constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;	        
        imgFacebookStatus = new JLabel(imgStatusDisconnected);
        panelFacebookStatus.add(imgFacebookStatus,constraints);        
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,50,5,10);        
        panelFacebookStatus.add(new JLabel("Facebook Viewers"),constraints);
        constraints.insets = new Insets(5,10,5,10);        
        constraints.gridx = 4;
        constraints.gridy = 0;	        
        countFacebookConnectedPeople = new JLabel("0");
        panelFacebookStatus.add(countFacebookConnectedPeople,constraints);         
        constraints.gridx = 0;
        constraints.gridy = 3;        
        panelStatus.add(panelFacebookStatus,constraints);
*/
		JPanel panelMyFunChatBotStatus = new JPanel(new GridBagLayout());			
        panelMyFunChatBotStatus.setBackground(Color.DARK_GRAY);
        panelMyFunChatBotStatus.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelMyFunChatBotStatus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "MyFunChatBot Status"));
        panelMyFunChatBotStatus.setPreferredSize(statusDimension);
        imgTwitchStatusLogo = new JLabel(imgTwitchIcon);
        imgTwitchStatusLogo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
        panelMyFunChatBotStatus.add(new JLabel("MyFunChatBot Status"),constraints.setXYPosition(0, 0));
        imgMyFunChatBotStatus = new JLabel(imgStatusDisconnected);
        panelMyFunChatBotStatus.add(imgMyFunChatBotStatus,constraints.setXYPosition(1, 0));        
        constraints.insets = new Insets(5,50,5,10); 
        final JButton buttonMyFunChatBotStatus = new JButton("Start");
        buttonMyFunChatBotStatus.setPreferredSize(new Dimension(80,30));
        panelMyFunChatBotStatus.add(buttonMyFunChatBotStatus,constraints.setXYPosition(2, 0));
        constraints.insets = new Insets(5,10,5,10); 
        panelStatus.add(panelMyFunChatBotStatus,constraints.setXYPosition(0, 4));
        
        cards.add(panelStatus,"status");
        
        buttonMyFunChatBotStatus.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			if (buttonMyFunChatBotStatus.getText().equalsIgnoreCase("Start")) {
    				buttonMyFunChatBotStatus.setText("Stop");
    				MyFunChatBot.changingLedStatus("MYFUNCHATBOT","CONNECTED");
    				setStatusMyFunChatBot(BotConstants.STATUS_MYFUNCHATBOT_ON);
    			}
    			else {
    				buttonMyFunChatBotStatus.setText("Start");
    				MyFunChatBot.changingLedStatus("MYFUNCHATBOT","DISCONNECTED");
    				setStatusMyFunChatBot(BotConstants.STATUS_MYFUNCHATBOT_OFF);
    			}
    		}
    	});
        
        
//***************************************************************//
//********************* SETUP BOT SETTINGS***********************//
//***************************************************************//
		
		JPanel panelSetting = new JPanel(new GridBagLayout());

		cards.add(panelSetting,"setupBotSettings");
		
        constraints.anchor = GridBagConstraints.WEST;
        panelSetting.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "MyFunChatBot Settings"));

        JPanel panelSettingConfig = new JPanel(new GridBagLayout());
        panelSetting.add(panelSettingConfig,constraints.setXYAnchorPosition(0, 0, GridBagConstraints.WEST));
        
        panelSettingConfig.add(new JLabel("<html>Http Port <br/>to Features</html>"),constraints.setXYPosition(0,0));
        textFieldBotSettingPort = new JTextField();
        textFieldBotSettingPort.setPreferredSize(new Dimension(80,25));
        ((AbstractDocument) textFieldBotSettingPort.getDocument()).setDocumentFilter(new CustomDocumentFilter());        
        panelSettingConfig.add(textFieldBotSettingPort,constraints.setXYPosition(1,0));
        panelSettingConfig.add(new JLabel("Border Color"),constraints.setXYPosition(2,0));        
        textFieldBotSettingColor = new JTextField();
        textFieldBotSettingColor.setPreferredSize(new Dimension(25,25));
        textFieldBotSettingColor.setEditable(false);
        panelSettingConfig.add(textFieldBotSettingColor,constraints.setXYPosition(3,0));
        panelSettingConfig.add(new JLabel("ASCII Emoji"),constraints.setXYPosition(4,0));     
        textFieldBotSettingASCIIEmoji = new JTextField();
        textFieldBotSettingASCIIEmoji.setPreferredSize(new Dimension(60,25));
        panelSettingConfig.add(textFieldBotSettingASCIIEmoji,constraints.setXYPosition(5,0));        
        
        JButton buttonBotSettingApply = new JButton("Apply");
        buttonBotSettingApply.setPreferredSize(new Dimension(80,25));
        panelSettingConfig.add(buttonBotSettingApply,constraints.setXYPosition(1,1));
        
        JPanel panelSettingLinks = new JPanel();
        panelSetting.add(panelSettingLinks, constraints.setXYPosition(0,1));
        
        textAreaBotSettingLinks = new JTextArea();
        textAreaBotSettingLinks.setPreferredSize(new Dimension(480,225));
        panelSettingLinks.add(textAreaBotSettingLinks);
        
        textFieldBotSettingColor.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	textFieldBotSettingColor.setBackground(JColorChooser.showDialog(null,"Choose a color", Color.WHITE));
    			BotGeneralSetupVO.setBorderColor(textFieldBotSettingColor.getBackground());            	
            }
        });

        buttonBotSettingApply.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			BotGeneralSetupVO.setHttpPort(Integer.parseInt(textFieldBotSettingPort.getText()));
    			BotGeneralSetupVO.setBorderColor(textFieldBotSettingColor.getBackground());
    			BotGeneralSetupVO.setASCIIEmoji(textFieldBotSettingASCIIEmoji.getText());
    			setSettingRefresh();    			
    		}
    	});
		
//***************************************************************//
//********************* SETUP BOT FEATURES*** *******************//
//***************************************************************//
        panelSetupBotFeatures = new JPanel();

        panelSetupBotFeatures.setBackground(Color.DARK_GRAY);
        panelSetupBotFeatures.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelSetupBotFeatures.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Bot Features"));

        JTabbedPane tabbedPaneBotFeatures = new JTabbedPane();
        tabbedPaneBotFeatures.setPreferredSize(new Dimension(570,370));
        tabbedPaneBotFeatures.setBackground(Color.DARK_GRAY);
        tabbedPaneBotFeatures.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
      //*********************** COMMANDS ********************/
        JPanel panelTabbedCommands = new JPanel(new GridBagLayout());        
        tabbedPaneBotFeatures.addTab("Commands",panelTabbedCommands);
        
        JPanel panelCommandsOnlyMod = new JPanel(new GridBagLayout());
        panelCommandsOnlyMod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Only Moderators"));
        panelCommandsOnlyMod.setPreferredSize(new Dimension(540,140));
        panelTabbedCommands.add(panelCommandsOnlyMod,constraints.setXYPosition(0,0));
        panelCommandsOnlyMod.add(new JLabel("Commands ON"),constraints.setXYPosition(0,0));
        checkBoxCommandModON = new JCheckBox();
        panelCommandsOnlyMod.add(checkBoxCommandModON,constraints.setXYPosition(1,0));
        panelCommandsOnlyMod.add(new JLabel("Command Prefix"),constraints.setXYPosition(2,0));
        textFieldCommandPrefix = new JTextField();
        textFieldCommandPrefix.setPreferredSize(new Dimension(50,25));
        textFieldCommandPrefix.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			BotGeneralSetupVO.setCommandPrefix(textFieldCommandPrefix.getText());				
			}
     	});
        panelCommandsOnlyMod.add(textFieldCommandPrefix,constraints.setXYPosition(3,0));
        panelCommandsOnlyMod.add(new JLabel("Moderators"),constraints.setXYPosition(0,1));
        checkBoxCommandMod = new JCheckBox();
        checkBoxCommandMod.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			BotGeneralSetupVO.setCommandMod(checkBoxCommandMod.isSelected());
     		}
     	});
        panelCommandsOnlyMod.add(checkBoxCommandMod,constraints.setXYPosition(1,1));
        
        panelCommandsOnlyMod.add(new JLabel("Ping"),constraints.setXYPosition(2,1));
        checkBoxPing = new JCheckBox();
        checkBoxPing.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			BotGeneralSetupVO.setCommandPing(checkBoxPing.isSelected());
     		}
     	});
        panelCommandsOnlyMod.add(checkBoxPing,constraints.setXYPosition(3,1));
        
        JPanel panelCommands4All = new JPanel(new GridBagLayout());
        panelCommands4All.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "For All Viewers"));
        panelCommands4All.setPreferredSize(new Dimension(540,160));
        panelTabbedCommands.add(panelCommands4All,constraints.setXYPosition(0,1));       
        panelCommands4All.add(new JLabel("Uptime"),constraints.setXYPosition(0,0));
        checkBoxUptime = new JCheckBox();
        checkBoxUptime.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			BotGeneralSetupVO.setCommandUptime(checkBoxUptime.isSelected());
     		}
     	});
        panelCommands4All.add(checkBoxUptime,constraints.setXYPosition(1,0));
        panelCommands4All.add(new JLabel("Game"),constraints.setXYPosition(2,0));
        checkBoxGame = new JCheckBox();
        checkBoxGame.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			BotGeneralSetupVO.setCommandGame(checkBoxGame.isSelected());
     		}
     	});
        panelCommands4All.add(checkBoxGame,constraints.setXYPosition(3,0));        
        
        panelCommands4All.add(new JLabel("Rank"),constraints.setXYPosition(4,0));
        checkBoxRank = new JCheckBox();
        checkBoxRank.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			BotGeneralSetupVO.setCommandRank(checkBoxRank.isSelected());
     		}
     	});
        panelCommands4All.add(checkBoxRank,constraints.setXYPosition(5,0));
        panelCommands4All.add(new JLabel("Links"),constraints.setXYPosition(0,1));
        checkBoxLinks = new JCheckBox();
        checkBoxLinks.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			BotGeneralSetupVO.setCommandLinks(checkBoxLinks.isSelected());
     		}
     	});
        panelCommands4All.add(checkBoxLinks,constraints.setXYPosition(1,1));      
        
        checkBoxCommandModON.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			if (checkBoxCommandModON.isSelected()){
     				setCommandsTabEnabled(true);
     				BotGeneralSetupVO.setCommandsEnabled(true);
     			}
     			else{
     				setCommandsTabEnabled(false);     			
     				BotGeneralSetupVO.setCommandsEnabled(false);
     			}
     		}
     	});
      //************************ COMMANDS *********************//  
      //*********************** AUTOANSWER ********************//        
        
        JPanel panelTabbedAutoAnswer = new JPanel(new GridBagLayout());
        
        tabbedPaneBotFeatures.addTab("AutoAnswer",panelTabbedAutoAnswer);
        JPanel panelTabbedAutoAnswerOn = new JPanel();
        panelTabbedAutoAnswer.add(panelTabbedAutoAnswerOn,constraints.setXYPosition(0,0));       
        panelTabbedAutoAnswerOn.add(new JLabel("Auto Answer"));   
        checkBoxAutoAnswerON = new JCheckBox();
        panelTabbedAutoAnswerOn.add(checkBoxAutoAnswerON);
        buttonAddLineAutoAnswerTable = new JButton("Add Line");
        panelTabbedAutoAnswerOn.add(buttonAddLineAutoAnswerTable);
        buttonDelLineAutoAnswerTable = new JButton("Remove Line");
        panelTabbedAutoAnswerOn.add(buttonDelLineAutoAnswerTable);        
        JPanel panelTabbedAutoAnswerTable = new JPanel();
        panelTabbedAutoAnswer.add(panelTabbedAutoAnswerTable,constraints.setXYPosition(0,1));
        DefaultTableModel defaultTableModel = new DefaultTableModel(0, 2);
        defaultTableModel.setColumnIdentifiers(new Object[]{"Keywords","Answers"});
        tableAutoAnswer = new JTable(defaultTableModel);
        tableAutoAnswer.setPreferredSize(new Dimension(535,232));
        tableAutoAnswer.setFillsViewportHeight(true);
        tableAutoAnswer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        modelAutoAnswer = (DefaultTableModel)(tableAutoAnswer.getModel());
        scrollPaneTableAutoAnswer = new JScrollPane(tableAutoAnswer);
        scrollPaneTableAutoAnswer.setPreferredSize(new Dimension(535,255));
        tableAutoAnswer.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(tableAutoAnswer.getCellEditor() != null){
					tableAutoAnswer.getCellEditor().stopCellEditing();
				}
				setAutoAnswerTable(modelAutoAnswer);
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
        panelTabbedAutoAnswerTable.add(scrollPaneTableAutoAnswer,constraints);
        buttonAddLineAutoAnswerTable.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			modelAutoAnswer.addRow(new Object[][]{});
     			if(tableAutoAnswer.getRowCount() > 12){
         			tableAutoAnswer.setPreferredSize(new Dimension(535,(232+((tableAutoAnswer.getRowCount()-12)*tableAutoAnswer.getRowHeight()))));     				
     			}
     		}
     	});
        
        buttonDelLineAutoAnswerTable.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			modelAutoAnswer.removeRow(tableAutoAnswer.getSelectedRow());
     			if(tableAutoAnswer.getRowCount() > 11){
         			tableAutoAnswer.setPreferredSize(new Dimension(535,(232+((tableAutoAnswer.getRowCount()-12)*tableAutoAnswer.getRowHeight()))));     				
     			}     			
     		}
     	});
        
        checkBoxAutoAnswerON.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			if (checkBoxAutoAnswerON.isSelected()){
     				setAutoAnswerTabEnabled(true);
     				BotGeneralSetupVO.setAutoAnswerEnabled(true);
     			}
     			else{
     				setAutoAnswerTabEnabled(false);     			
     				BotGeneralSetupVO.setAutoAnswerEnabled(false);
     			}
     		}
     	});
        
        //*********************** AUTOANSWER ********************//
        //************************* VOTING **********************//        
        JPanel panelTabbedVoting = new JPanel(new GridBagLayout());
        tabbedPaneBotFeatures.addTab("Voting",panelTabbedVoting);
        
        JPanel panelVotingStatus = new JPanel();
        panelTabbedVoting.add(panelVotingStatus);
        
        progressBarVoting = new JProgressBar();
        progressBarVoting.setPreferredSize(new Dimension(300,20));
        progressBarVoting.setBackground(Color.LIGHT_GRAY);
        progressBarVoting.setValue(0);
        progressBarVoting.setString("");
        progressBarVoting.setStringPainted(true);
        panelVotingStatus.add(progressBarVoting);
        
        
        comboBoxVoting = new JComboBox<String>();
        comboBoxVoting.setPreferredSize(new Dimension(400,25));
        panelTabbedVoting.add(comboBoxVoting,constraints.setXYPosition(0,1));
        
        JPanel panelVotingTitle = new JPanel(new GridBagLayout());
        panelTabbedVoting.add(panelVotingTitle,constraints.setXYPosition(0,2));
        
        panelVotingTitle.add(new JLabel("Title"),constraints.setXYPosition(0,2));
        textFieldVotingTitle = new JTextField();
        textFieldVotingTitle.setPreferredSize(new Dimension(400,25));
        panelVotingTitle.add(textFieldVotingTitle, constraints.setXYPosition(1,2));

        JPanel panelVotingOptions = new JPanel(new GridBagLayout());
        panelTabbedVoting.add(panelVotingOptions,constraints.setXYPosition(0,3));

        panelVotingOptions.add(new JLabel("Option"),constraints.setXYPosition(0,0));
        textFieldVotingOption1 = new JTextField();
        textFieldVotingOption1.setPreferredSize(new Dimension(150,25));
        panelVotingOptions.add(textFieldVotingOption1, constraints.setXYPosition(1,0));

        panelVotingOptions.add(new JLabel("Option"),constraints.setXYPosition(2,0));
        textFieldVotingOption2 = new JTextField();
        textFieldVotingOption2.setPreferredSize(new Dimension(150,25));
        panelVotingOptions.add(textFieldVotingOption2, constraints.setXYPosition(3,0));

        panelVotingOptions.add(new JLabel("Option"),constraints.setXYPosition(0,1));
        textFieldVotingOption3 = new JTextField();
        textFieldVotingOption3.setPreferredSize(new Dimension(150,25));
        panelVotingOptions.add(textFieldVotingOption3, constraints.setXYPosition(1,1));

        panelVotingOptions.add(new JLabel("Option"),constraints.setXYPosition(2,1));
        textFieldVotingOption4 = new JTextField();
        textFieldVotingOption4.setPreferredSize(new Dimension(150,25));
        panelVotingOptions.add(textFieldVotingOption4, constraints.setXYPosition(3,1));

        panelVotingOptions.add(new JLabel("Option"),constraints.setXYPosition(0,2));
        textFieldVotingOption5 = new JTextField();
        textFieldVotingOption5.setPreferredSize(new Dimension(150,25));
        panelVotingOptions.add(textFieldVotingOption5, constraints.setXYPosition(1,2));
        
        panelVotingOptions.add(new JLabel("Option"),constraints.setXYPosition(2,2));
        textFieldVotingOption6 = new JTextField();
        textFieldVotingOption6.setPreferredSize(new Dimension(150,25));
        panelVotingOptions.add(textFieldVotingOption6, constraints.setXYPosition(3,2));
        
        JPanel panelVotingStart = new JPanel();
        panelTabbedVoting.add(panelVotingStart, constraints.setXYPosition(0,4));
        
        panelVotingStart.add(new JLabel("Voting Timer (Min.)"));
        textFieldVotingTimer = new JTextField("0");
        textFieldVotingTimer.setPreferredSize(new Dimension(50,25));
        panelVotingStart.add(textFieldVotingTimer);        

        JPanel panelVotingButtons = new JPanel();
        panelTabbedVoting.add(panelVotingButtons,constraints.setXYPosition(0,5));
        buttonVotingStart = new JButton("Start");
        buttonVotingStart.setPreferredSize(new Dimension(80,30));
        panelVotingButtons.add(buttonVotingStart);
        buttonVotingApply = new JButton("Apply");
        buttonVotingApply.setPreferredSize(new Dimension(80,30));
        panelVotingButtons.add(buttonVotingApply);
        buttonVotingDelete = new JButton("Delete");
        buttonVotingDelete.setPreferredSize(new Dimension(80,30));
        panelVotingButtons.add(buttonVotingDelete);
        
        buttonVotingStart.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			if (buttonVotingStart.getText().equalsIgnoreCase("Start") && (String)comboBoxVoting.getSelectedItem() != null) {
    				buttonVotingStart.setText("Stop");
    				if(!VotingThread.isRunning()){
    					VotingThread.setVotingVO(BotGeneralSetupVO.getVotingVOHashMap((String)comboBoxVoting.getSelectedItem()));
    					VotingThread.start();
    				}
//    				MyFunChatBotControl.startVoting(BotGeneralSetupVO.getVotingVOHashMap((String)comboBoxVoting.getSelectedItem()));
    			}
    			else {
    				buttonVotingStart.setText("Start");
    				MyFunChatBotControl.stopVoting();    				
    			}
    		}
    	});
        
        buttonVotingApply.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			if(textFieldVotingTimer.getText() == null){
    				textFieldVotingTimer.setText("0");
    			}    			
    			BotGeneralSetupVO.addVotingHashMapVO(new VotingVO(textFieldVotingTitle.getText(),
    									Integer.parseInt(textFieldVotingTimer.getText()),
						    			textFieldVotingOption1.getText(),
						    			textFieldVotingOption2.getText(),
						    			textFieldVotingOption3.getText(),
						    			textFieldVotingOption4.getText(),
						    			textFieldVotingOption5.getText(),
						    			textFieldVotingOption6.getText()));
    			loadVotingVOComboBox();		
    		}
    	});
        
        buttonVotingDelete.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			BotGeneralSetupVO.removeVotingVOHashMap(textFieldVotingTitle.getText());
    			loadVotingVOComboBox();    			
    		}
    	});

        comboBoxVoting.addItemListener(new ItemListener () {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if((String)comboBoxVoting.getSelectedItem() == null){
					return;
				}
                VotingVO votingVO = BotGeneralSetupVO.getVotingVOHashMap((String)comboBoxVoting.getSelectedItem());
                textFieldVotingTitle.setText(votingVO.getTitle());
				textFieldVotingTimer.setText(""+votingVO.getTimer());
    			textFieldVotingOption1.setText(votingVO.getOption1());
    			textFieldVotingOption2.setText(votingVO.getOption2());
    			textFieldVotingOption3.setText(votingVO.getOption3());
    			textFieldVotingOption4.setText(votingVO.getOption4());
    			textFieldVotingOption5.setText(votingVO.getOption5());
    			textFieldVotingOption6.setText(votingVO.getOption6());
    		}
        });

        //************************* VOTING **********************//          
        //************************* POINTS **********************//   
        JPanel panelTabbedPoints = new JPanel(new GridBagLayout());        
        tabbedPaneBotFeatures.addTab("Points",panelTabbedPoints);
        panelTabbedPoints.add(new JLabel("Points"),constraints.setXYAnchorPosition(0,0,GridBagConstraints.WEST));
        checkBoxPointsOn = new JCheckBox();
        panelTabbedPoints.add(checkBoxPointsOn, constraints.setXYPosition(1,0));
        panelTabbedPoints.add(new JLabel("Points Name"), constraints.setXYPosition(0,1));        
        textFieldPointsName = new JTextField();
        textFieldPointsName.setPreferredSize(new Dimension(150,25));
        panelTabbedPoints.add(textFieldPointsName, constraints.setXYPosition(1,1));
        
        panelTabbedPoints.add(new JLabel("Only Moderators"),constraints.setXYPosition(0,2));
        checkBoxPointsOnlyMod = new JCheckBox();
        panelTabbedPoints.add(checkBoxPointsOnlyMod, constraints.setXYPosition(1,2));
        panelTabbedPoints.add(new JLabel("<HTML>Start Points <BR/>(Mods Only)</HTML>"), constraints.setXYPosition(2,2));        
        textFieldPointsExtraMod = new JTextField();
        textFieldPointsExtraMod.setPreferredSize(new Dimension(50,25));
        ((AbstractDocument) textFieldPointsExtraMod.getDocument()).setDocumentFilter(new CustomDocumentFilter());        
        panelTabbedPoints.add(textFieldPointsExtraMod, constraints.setXYPosition(3,2));
        
        panelTabbedPoints.add(new JLabel("Points per Time"), constraints.setXYPosition(0,3));        
        textFieldPointsPerTime = new JTextField();
        textFieldPointsPerTime.setPreferredSize(new Dimension(50,25));
        ((AbstractDocument) textFieldPointsPerTime.getDocument()).setDocumentFilter(new CustomDocumentFilter());        
        panelTabbedPoints.add(textFieldPointsPerTime, constraints.setXYPosition(1,3));
        panelTabbedPoints.add(new JLabel("Points Timer"), constraints.setXYPosition(0,4));
        textFieldPointsTimer = new JTextField();
        textFieldPointsTimer.setPreferredSize(new Dimension(50,25));
        ((AbstractDocument) textFieldPointsTimer.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        panelTabbedPoints.add(textFieldPointsTimer, constraints.setXYPosition(1,4));

        JButton buttonPointsApply = new JButton("Apply");
        buttonPointsApply.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			BotGeneralSetupVO.setPointsEnabled(checkBoxPointsOn.isSelected());
    			BotGeneralSetupVO.setPointsName(textFieldPointsName.getText());
    			BotGeneralSetupVO.setPointsMod(checkBoxPointsOnlyMod.isSelected());
    			BotGeneralSetupVO.setPointsExtraMod(Integer.parseInt(textFieldPointsExtraMod.getText()));
    			BotGeneralSetupVO.setPointsPerTime(Integer.parseInt(textFieldPointsPerTime.getText()));
    			BotGeneralSetupVO.setPointsTimer(Integer.parseInt(textFieldPointsTimer.getText()));
    		}
    	});
        panelTabbedPoints.add(buttonPointsApply,constraints.setXYAnchorPosition(1,5,GridBagConstraints.CENTER));

        //*********************** POINTS ********************//
        //*********************** RANKING ********************//
        
        JPanel panelTabbedRanking = new JPanel(new GridBagLayout());        
        tabbedPaneBotFeatures.addTab("Ranking",panelTabbedRanking);

        JPanel panelRankingOn = new JPanel(new GridBagLayout());
        panelTabbedRanking.add(panelRankingOn, constraints.setXYPosition(0,0));

        panelRankingOn.add(new JLabel("Ranking"));
        checkBoxRankingOn = new JCheckBox();
        panelRankingOn.add(checkBoxRankingOn,constraints.setXYPosition(1,0));
        JButton buttonRankingApply = new JButton("Apply");
        buttonRankingApply.setPreferredSize(new Dimension(80,25));
        panelRankingOn.add(buttonRankingApply,constraints.setXYPosition(2,0));

        JPanel panelRankingLevel = new JPanel(new GridBagLayout());
        panelTabbedRanking.add(panelRankingLevel, constraints.setXYPosition(0,1));        

        panelRankingLevel.add(new JLabel("Level"),constraints.setXYPosition(0,0));
        panelRankingLevel.add(new JLabel("Title"),constraints.setXYPosition(1,0));
        panelRankingLevel.add(new JLabel("Time to Reach"),constraints.setXYPosition(2,0));  
        panelRankingLevel.add(new JLabel("xBonus"),constraints.setXYPosition(3,0));

        panelRankingLevel.add(new JLabel("0"),constraints.setXYPosition(0,1));
   		textFieldRankingLevel0Title = new JTextField();
   		textFieldRankingLevel0Title.setPreferredSize(new Dimension(150,25));
   		panelRankingLevel.add(textFieldRankingLevel0Title, constraints.setXYPosition(1,1));
   		textFieldRankingLevel0Min = new JTextField("0");
   		textFieldRankingLevel0Min.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel0Min.getDocument()).setDocumentFilter(new CustomDocumentFilter()); 
   		textFieldRankingLevel0Min.setEnabled(false);
   		panelRankingLevel.add(textFieldRankingLevel0Min, constraints.setXYPosition(2,1));
   		textFieldRankingLevel0Bonus = new JTextField("0");
   		textFieldRankingLevel0Bonus.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel0Bonus.getDocument()).setDocumentFilter(new CustomDocumentFilter());
   		panelRankingLevel.add(textFieldRankingLevel0Bonus, constraints.setXYPosition(3,1));   		
   		
        panelRankingLevel.add(new JLabel("1"),constraints.setXYPosition(0,2));
   		textFieldRankingLevel1Title = new JTextField();
   		textFieldRankingLevel1Title.setPreferredSize(new Dimension(150,25));
   		panelRankingLevel.add(textFieldRankingLevel1Title, constraints.setXYPosition(1,2));
        textFieldRankingLevel1Min = new JTextField("0");
   		textFieldRankingLevel1Min.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel1Min.getDocument()).setDocumentFilter(new CustomDocumentFilter());
   		panelRankingLevel.add(textFieldRankingLevel1Min, constraints.setXYPosition(2,2));
   		textFieldRankingLevel1Bonus = new JTextField("0");
   		textFieldRankingLevel1Bonus.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel1Bonus.getDocument()).setDocumentFilter(new CustomDocumentFilter());   		
   		panelRankingLevel.add(textFieldRankingLevel1Bonus, constraints.setXYPosition(3,2));
   		
        panelRankingLevel.add(new JLabel("2"),constraints.setXYPosition(0,3));
   		textFieldRankingLevel2Title = new JTextField();
   		textFieldRankingLevel2Title.setPreferredSize(new Dimension(150,25));
   		panelRankingLevel.add(textFieldRankingLevel2Title, constraints.setXYPosition(1,3));
   		textFieldRankingLevel2Min = new JTextField("0");
   		textFieldRankingLevel2Min.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel2Min.getDocument()).setDocumentFilter(new CustomDocumentFilter());
   		panelRankingLevel.add(textFieldRankingLevel2Min, constraints.setXYPosition(2,3));
   		textFieldRankingLevel2Bonus = new JTextField("0");
   		textFieldRankingLevel2Bonus.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel2Bonus.getDocument()).setDocumentFilter(new CustomDocumentFilter());   		
   		panelRankingLevel.add(textFieldRankingLevel2Bonus, constraints.setXYPosition(3,3));   		

        panelRankingLevel.add(new JLabel("3"),constraints.setXYPosition(0,4));
   		textFieldRankingLevel3Title = new JTextField();
   		textFieldRankingLevel3Title.setPreferredSize(new Dimension(150,25));
   		panelRankingLevel.add(textFieldRankingLevel3Title, constraints.setXYPosition(1,4));
   		textFieldRankingLevel3Min = new JTextField("0");
   		textFieldRankingLevel3Min.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel3Min.getDocument()).setDocumentFilter(new CustomDocumentFilter());
   		panelRankingLevel.add(textFieldRankingLevel3Min, constraints.setXYPosition(2,4));
   		textFieldRankingLevel3Bonus = new JTextField("0");
   		textFieldRankingLevel3Bonus.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel3Bonus.getDocument()).setDocumentFilter(new CustomDocumentFilter());   		
   		panelRankingLevel.add(textFieldRankingLevel3Bonus, constraints.setXYPosition(3,4));   		

        panelRankingLevel.add(new JLabel("4"),constraints.setXYPosition(0,5));
   		textFieldRankingLevel4Title = new JTextField();
   		textFieldRankingLevel4Title.setPreferredSize(new Dimension(150,25));
   		panelRankingLevel.add(textFieldRankingLevel4Title, constraints.setXYPosition(1,5));
   		textFieldRankingLevel4Min = new JTextField("0");
   		textFieldRankingLevel4Min.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel4Min.getDocument()).setDocumentFilter(new CustomDocumentFilter());   		
   		panelRankingLevel.add(textFieldRankingLevel4Min, constraints.setXYPosition(2,5));
   		textFieldRankingLevel4Bonus = new JTextField("0");
   		textFieldRankingLevel4Bonus.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel4Bonus.getDocument()).setDocumentFilter(new CustomDocumentFilter());   		
   		panelRankingLevel.add(textFieldRankingLevel4Bonus, constraints.setXYPosition(3,5));   		

        panelRankingLevel.add(new JLabel("5"),constraints.setXYPosition(0,6));
   		textFieldRankingLevel5Title = new JTextField();
   		textFieldRankingLevel5Title.setPreferredSize(new Dimension(150,25));
   		panelRankingLevel.add(textFieldRankingLevel5Title, constraints.setXYPosition(1,6));
   		textFieldRankingLevel5Min = new JTextField("0");
   		textFieldRankingLevel5Min.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel5Min.getDocument()).setDocumentFilter(new CustomDocumentFilter());
   		panelRankingLevel.add(textFieldRankingLevel5Min, constraints.setXYPosition(2,6));
   		textFieldRankingLevel5Bonus = new JTextField("0");
   		textFieldRankingLevel5Bonus.setPreferredSize(new Dimension(50,25));
   		((AbstractDocument) textFieldRankingLevel5Bonus.getDocument()).setDocumentFilter(new CustomDocumentFilter());
   		panelRankingLevel.add(textFieldRankingLevel5Bonus, constraints.setXYPosition(3,6));
   		
   		buttonRankingApply.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			if (textFieldRankingLevel0Title.getText().length()>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel0Min.getText(),"0"))==0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel0Bonus.getText(),"0"))>=0){
        			BotGeneralSetupVO.addRankVOHashMap(new RankVO(0, 
							textFieldRankingLevel0Title.getText(),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel0Min.getText(),"0")),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel0Bonus.getText(),"0"))));
    			}

    			if (textFieldRankingLevel1Title.getText().length()>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel1Min.getText(),"0"))>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel1Bonus.getText(),"0"))>=0){
        			BotGeneralSetupVO.addRankVOHashMap(new RankVO(1, 
							textFieldRankingLevel1Title.getText(),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel1Min.getText(),"0")),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel1Bonus.getText(),"0"))));    				
    			}
    			else{
    				BotGeneralSetupVO.removeRankVOHashMap(1);
    			}

    			if (textFieldRankingLevel2Title.getText().length()>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel2Min.getText(),"0"))>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel2Bonus.getText(),"0"))>=0){
        			BotGeneralSetupVO.addRankVOHashMap(new RankVO(2, 
							textFieldRankingLevel2Title.getText(),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel2Min.getText(),"0")),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel2Bonus.getText(),"0"))));    				
    			}
    			else{
    				BotGeneralSetupVO.removeRankVOHashMap(2);
    			}
    			
    			if (textFieldRankingLevel3Title.getText().length()>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel3Min.getText(),"0"))>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel3Bonus.getText(),"0"))>=0){
        			BotGeneralSetupVO.addRankVOHashMap(new RankVO(3, 
							textFieldRankingLevel3Title.getText(),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel3Min.getText(),"0")),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel3Bonus.getText(),"0"))));    				
    			}
    			else{
    				BotGeneralSetupVO.removeRankVOHashMap(3);
    			}
    			
    			if (textFieldRankingLevel4Title.getText().length()>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel4Min.getText(),"0"))>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel4Bonus.getText(),"0"))>=0){
        			BotGeneralSetupVO.addRankVOHashMap(new RankVO(4, 
							textFieldRankingLevel4Title.getText(),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel4Min.getText(),"0")),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel4Bonus.getText(),"0"))));    				
    			}
    			else{
    				BotGeneralSetupVO.removeRankVOHashMap(4);
    			}
    			
    			if (textFieldRankingLevel5Title.getText().length()>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel5Min.getText(),"0"))>0
    					&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel5Bonus.getText(),"0"))>=0){
        			BotGeneralSetupVO.addRankVOHashMap(new RankVO(5, 
							textFieldRankingLevel5Title.getText(),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel5Min.getText(),"0")),
							Integer.parseInt(""+MyFunctions.nvl(textFieldRankingLevel5Bonus.getText(),"0"))));    				
    			}
    			else{
    				BotGeneralSetupVO.removeRankVOHashMap(5);
    			}
    			
    			setRankingLevels();
    		}
    	});

   		textFieldRankingLevel0Title.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel0Min.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel0Bonus.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel1Title.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel1Min.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel1Bonus.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel2Title.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});   		
   		
   		textFieldRankingLevel2Min.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel2Bonus.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel3Title.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel3Min.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel3Bonus.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel4Title.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel4Min.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel4Bonus.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel5Title.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		textFieldRankingLevel5Min.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});

   		textFieldRankingLevel5Bonus.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			setRankingLevels();				
			}
     	});
   		
   		//*********************** RANKING ********************//        
        
        JPanel panelTabbedMonster = new JPanel();        
        tabbedPaneBotFeatures.addTab("Monster Boss",panelTabbedMonster);
        
        //*********************** LOOT CHEST *********************//   
        JPanel panelTabbedLootChest = new JPanel(new GridBagLayout());
        tabbedPaneBotFeatures.addTab("Loot Chest",panelTabbedLootChest);

        constraints.anchor = GridBagConstraints.WEST;
        panelTabbedLootChest.add(new JLabel("Loot Chest"),constraints.setXYPosition(0,0));
        checkBoxLootChestON = new JCheckBox();
        panelTabbedLootChest.add(checkBoxLootChestON,constraints.setXYPosition(1,0));
        panelTabbedLootChest.add(new JLabel("Loot Chest Time"),constraints.setXYPosition(0,1));
        textFieldLootChestPeriod = new JTextField();
        textFieldLootChestPeriod.setPreferredSize(new Dimension(50,25));
        panelTabbedLootChest.add(textFieldLootChestPeriod, constraints.setXYPosition(1,1));
        
        panelTabbedLootChest.add(new JLabel("Treasure"),constraints.setXYPosition(0,2));
        textFieldLootChestTreasure = new JTextField();
        textFieldLootChestTreasure.setPreferredSize(new Dimension(50,25));
        panelTabbedLootChest.add(textFieldLootChestTreasure, constraints.setXYPosition(1,2)); 
        
        //*********************** LOOT CHEST *********************//         
        //*********************** AUTOMESSAGE ********************//        
        
        JPanel panelTabbedAutoMessage = new JPanel(new GridBagLayout());
        tabbedPaneBotFeatures.addTab("Auto Message",panelTabbedAutoMessage);        
        JPanel panelTabbedAutoMessageOn = new JPanel();
        panelTabbedAutoMessage.add(panelTabbedAutoMessageOn,constraints.setXYAnchorPosition(0,0,GridBagConstraints.CENTER));       
        panelTabbedAutoMessageOn.add(new JLabel("Auto Message"),constraints.setXYPosition(0,0));           
        checkBoxAutoMessageON = new JCheckBox();
        panelTabbedAutoMessageOn.add(checkBoxAutoMessageON,constraints.setXYPosition(0,0));
        panelTabbedAutoMessageOn.add(new JLabel("Timer (min)"),constraints.setXYPosition(0,0));      
        textFieldAutoMessageTimer = new JTextField();
        textFieldAutoMessageTimer.setPreferredSize(new Dimension(50,25));
        textFieldAutoMessageTimer.addKeyListener(new KeyListener() { 
     		@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
     			BotGeneralSetupVO.setAutoMessageTimer(Integer.parseInt(textFieldAutoMessageTimer.getText()));				
			}
     	});
        panelTabbedAutoMessageOn.add(textFieldAutoMessageTimer,constraints.setXYPosition(0,0));
        buttonAddLineAutoMessageTable = new JButton("Add Line");
        panelTabbedAutoMessageOn.add(buttonAddLineAutoMessageTable,constraints.setXYPosition(0,0));
        buttonDelLineAutoMessageTable = new JButton("Remove Line");   
        panelTabbedAutoMessageOn.add(buttonDelLineAutoMessageTable,constraints.setXYPosition(0,0));

        JPanel panelTabbedAutoMessagePlatform = new JPanel();
        panelTabbedAutoMessage.add(panelTabbedAutoMessagePlatform,constraints.setXYPosition(0,1));       
        
        JPanel panelTabbedAutoMessageTable = new JPanel();
        panelTabbedAutoMessage.add(panelTabbedAutoMessageTable,constraints.setXYPosition(0,2));
        DefaultTableModel defaultTableModelAutoMessage = new DefaultTableModel(0, 1);
        defaultTableModelAutoMessage.setColumnIdentifiers(new Object[]{"Messages"});
        tableAutoMessage = new JTable(defaultTableModelAutoMessage);
        tableAutoMessage.setPreferredSize(new Dimension(535,114));
        tableAutoMessage.setFillsViewportHeight(true);
        tableAutoMessage.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        modelAutoMessage = (DefaultTableModel)(tableAutoMessage.getModel());
        scrollPaneTableAutoMessage = new JScrollPane(tableAutoMessage);
        scrollPaneTableAutoMessage.setPreferredSize(new Dimension(535,214));
        tableAutoMessage.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(tableAutoMessage.getCellEditor() != null){
					tableAutoMessage.getCellEditor().stopCellEditing();
				}
				setAutoMessageTable(modelAutoMessage);
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
        panelTabbedAutoMessageTable.add(scrollPaneTableAutoMessage,constraints);
        buttonAddLineAutoMessageTable.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			modelAutoMessage.addRow(new Object[][]{});
     			if(tableAutoMessage.getRowCount() > 9){
         			tableAutoMessage.setPreferredSize(new Dimension(535,(162+((tableAutoMessage.getRowCount()-9)*tableAutoMessage.getRowHeight()))));     				
     			}
     		}
     	});
        
        buttonDelLineAutoMessageTable.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			modelAutoMessage.removeRow(tableAutoMessage.getSelectedRow());
     			if(tableAutoMessage.getRowCount() > 8){
         			tableAutoMessage.setPreferredSize(new Dimension(535,(162+((tableAutoMessage.getRowCount()-9)*tableAutoMessage.getRowHeight()))));     				
     			}     			
     		}
     	});
        
        checkBoxAutoMessageON.addActionListener(new ActionListener() { 
     		public void actionPerformed(ActionEvent e) {
     			if (checkBoxAutoMessageON.isSelected()){
     				setAutoMessageTabEnabled(true);
     				BotGeneralSetupVO.setAutoMessageEnabled(true);
     			}
     			else{
     				setAutoMessageTabEnabled(false);     			
     				BotGeneralSetupVO.setAutoMessageEnabled(false);
     			}
     		}
     	});
        
        //*********************** AUTOMESSAGE ********************//
        
        panelSetupBotFeatures.add(tabbedPaneBotFeatures);
        
        cards.add(panelSetupBotFeatures,"setupBotFeatures");

//***************************************************************//
//************************* SETUP MEME***************************//
//***************************************************************//
      		
      		JPanel panelMemeSetting = new JPanel(new GridBagLayout());

      		cards.add(panelMemeSetting,"setupMemeSettings");
      		
              panelMemeSetting.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Meme Settings"));

              JPanel panelMemeSettingControl = new JPanel(new GridBagLayout());
              panelMemeSetting.add(panelMemeSettingControl,constraints.setXYAnchorPosition(0,0,GridBagConstraints.CENTER));

              panelMemeSettingControl.add(new JLabel("Memes"),constraints.setXYPosition(0,0));
              checkBoxMemeEnabled = new JCheckBox();
              panelMemeSettingControl.add(checkBoxMemeEnabled,constraints.setXYPosition(1,0));
              
              panelMemeSettingControl.add(new JLabel("Meme Points"),constraints.setXYPosition(2,0));

              textFieldMemeSettingCost = new JTextField();
              textFieldMemeSettingCost.setPreferredSize(new Dimension(80,25));
              ((AbstractDocument) textFieldMemeSettingCost.getDocument()).setDocumentFilter(new CustomDocumentFilter());              
              panelMemeSettingControl.add(textFieldMemeSettingCost,constraints.setXYPosition(3,0));

              JButton buttonMemeSettingApply = new JButton("Apply");
              buttonMemeSettingApply.setPreferredSize(new Dimension(80,25));
              panelMemeSettingControl.add(buttonMemeSettingApply,constraints.setXYPosition(4,0));
              
              JPanel panelMemeSettingList = new JPanel();
              panelMemeSetting.add(panelMemeSettingList, constraints.setXYPosition(0,1));
              
              textAreaMemeSettingList = new JTextArea();
              JScrollPane scrollPaneMemeList = new JScrollPane(textAreaMemeSettingList); 
              scrollPaneMemeList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
              scrollPaneMemeList.setPreferredSize(new Dimension(500,300));
              panelMemeSettingList.add(scrollPaneMemeList);

              buttonMemeSettingApply.addActionListener(new ActionListener() { 
          		public void actionPerformed(ActionEvent e) {
          			BotGeneralSetupVO.setBotMemeCost(Integer.parseInt(textFieldMemeSettingCost.getText()));
          			BotGeneralSetupVO.setBotMemeEnabled(checkBoxMemeEnabled.isSelected());
          			MemeListControl.clearMemeList();
          			for (String line : textAreaMemeSettingList.getText().split("\\n")) {
          				if(line.contains("::")) {
          					MemeListControl.setMemeVO(new MemeVO(line.split("::")[0],line.split("::")[1],line.split("::")[2]));
          				}
          			}
          			MemeListControl.saveMemeList();
       			}
              });
              
              cards.add(panelMemeSetting,"setupMemes");
              
//***************************************************************//
//*********************** SETUP SPONSOR**************************//
//***************************************************************//
                  		
			JPanel panelSponsorSetting = new JPanel(new GridBagLayout());
			
			cards.add(panelSponsorSetting,"setupSponsorSettings");
			
			panelSponsorSetting.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sponsor Settings"));
			
			JPanel panelSponsorSettingControl = new JPanel(new GridBagLayout());
			panelSponsorSetting.add(panelSponsorSettingControl,constraints.setXYAnchorPosition(0,0,GridBagConstraints.CENTER));
			
			panelSponsorSettingControl.add(new JLabel("Sponsor"),constraints.setXYPosition(0,0));
			checkBoxSponsorEnabled = new JCheckBox();
			panelSponsorSettingControl.add(checkBoxSponsorEnabled,constraints.setXYPosition(1,0));
			
			panelSponsorSettingControl.add(new JLabel("Sponsor Time"),constraints.setXYPosition(2,0));
			 
			textFieldSponsorSettingTime = new JTextField();
			textFieldSponsorSettingTime.setPreferredSize(new Dimension(80,25));
			((AbstractDocument) textFieldSponsorSettingTime.getDocument()).setDocumentFilter(new CustomDocumentFilter());              
			panelSponsorSettingControl.add(textFieldSponsorSettingTime,constraints.setXYPosition(3,0));

			panelSponsorSettingControl.add(new JLabel("Message"),constraints.setXYPosition(4,0));
			
			checkBoxSponsorMessageEnabled = new JCheckBox();
			panelSponsorSettingControl.add(checkBoxSponsorMessageEnabled,constraints.setXYPosition(5,0));
			
			JButton buttonSponsorSettingApply = new JButton("Apply");
			buttonSponsorSettingApply.setPreferredSize(new Dimension(80,25));
			panelSponsorSettingControl.add(buttonSponsorSettingApply,constraints.setXYPosition(2,1));
			  
			JPanel panelSponsorSettingList = new JPanel(new GridBagLayout());
			panelSponsorSetting.add(panelSponsorSettingList, constraints.setXYPosition(0,1));
			
			panelSponsorSettingList.add(new JLabel("Chat Message. Use {u} for user and {l} for link"),constraints.setXYPosition(0,0));
					  
			textFieldSponsorMessage = new JTextField();
			textFieldSponsorMessage.setPreferredSize(new Dimension(500,25));
			panelSponsorSettingList.add(textFieldSponsorMessage,constraints.setXYPosition(0,1));
			  
			textAreaSponsorSettingList = new JTextArea();
			JScrollPane scrollPaneSponsorList = new JScrollPane(textAreaSponsorSettingList); 
			scrollPaneSponsorList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneSponsorList.setPreferredSize(new Dimension(500,200));
			panelSponsorSettingList.add(scrollPaneSponsorList,constraints.setXYPosition(0,2));
			
			buttonSponsorSettingApply.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
				BotGeneralSetupVO.setBotSponsorTime(Integer.parseInt(textFieldSponsorSettingTime.getText()));
					BotGeneralSetupVO.setBotSponsorEnabled(checkBoxSponsorEnabled.isSelected());
					BotGeneralSetupVO.setBotSponsorMessageEnabled(checkBoxSponsorMessageEnabled.isSelected());
					BotGeneralSetupVO.setBotSponsorMessage(textFieldSponsorMessage.getText());
					SponsorListControl.clearSponsorList();
					for (String line : textAreaSponsorSettingList.getText().split("\\n")) {
						if(line.contains("::")) {
						SponsorListControl.setSponsorVO(new SponsorVO(line.split("::")[0],line.split("::")[1],line.split("::")[2],line.split("::")[3]));
						}
					}
					SponsorListControl.saveSponsorList();
				}
			});
			  
			cards.add(panelSponsorSetting,"setupSponsors");              
              
//***************************************************************//
//********************* SETUP RANKING PANEL *********************//
//***************************************************************//
        JPanel panelSetupRanking = new JPanel(new GridBagLayout());
        
        panelSetupRanking.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelSetupRanking.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Viewers Ranking"));
        
        JPanel panelSetupRankingButtons = new JPanel();
        JButton buttonSetupRankingApply = new JButton("Apply");
        buttonSetupRankingApply.setPreferredSize(new Dimension (80,25));
        panelSetupRankingButtons.add(buttonSetupRankingApply);
        JButton buttonSetupRankingReset = new JButton("Reset");
        buttonSetupRankingReset.setPreferredSize(new Dimension (80,25));        
        panelSetupRankingButtons.add(buttonSetupRankingReset);
        JButton buttonSetupRankingRefresh = new JButton("Refresh");
        buttonSetupRankingRefresh.setPreferredSize(new Dimension (80,25));        
        panelSetupRankingButtons.add(buttonSetupRankingRefresh);
        
        panelSetupRanking.add(panelSetupRankingButtons,constraints.setXYPosition(0,0));

        JPanel panelSetupRankingTable = new JPanel();
        DefaultTableModel defaultTableModelRanking = new DefaultTableModel(0, 5);
        defaultTableModelRanking.setColumnIdentifiers(new Object[]{"Viewer","Platform","Status","Total Time","Rank"});
        tableRanking = new JTable(defaultTableModelRanking){
			private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column) {
            	if(column == 3){
            		return true;
            	}
                return false;               
            }
        };
        tableRanking.setPreferredSize(new Dimension(552,205));
        tableRanking.setFillsViewportHeight(true);
        tableRanking.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableRanking.getEditingColumn();
        TableColumn tableColumn = tableRanking.getColumnModel().getColumn(3);
        JTextField textFieldTableNumber = new JTextField();
        ((AbstractDocument) textFieldTableNumber.getDocument()).setDocumentFilter(new CustomDocumentFilter());        
        tableColumn.setCellEditor(new DefaultCellEditor (textFieldTableNumber));        
        modelRanking = (DefaultTableModel)(tableRanking.getModel());
        scrollPaneTableRanking = new JScrollPane(tableRanking);
        scrollPaneTableRanking.setPreferredSize(new Dimension(552,305));
        panelSetupRankingTable.add(scrollPaneTableRanking,constraints.setXYPosition(0,1));
        panelSetupRanking.add(panelSetupRankingTable,constraints.setXYPosition(0,1));  
        
        cards.add(panelSetupRanking,"setupRanking");
       
        buttonSetupRankingApply.addActionListener(new ActionListener()	{ 
     		public void actionPerformed(ActionEvent e) {
     			setRankingApply();
     			setRankingRefresh();
     		}
     	});        
       
        buttonSetupRankingRefresh.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
    			setRankingRefresh();
        	}
        });

        buttonSetupRankingReset.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		setRankingReset(modelRanking);
        		setRankingRefresh();
        	}
        });

//***************************************************************//
//******************* TWITCH CONNECTION PANEL *******************//
//***************************************************************//
		JPanel panelTwitchConnection = new JPanel(new GridBagLayout());

        constraints.insets = new Insets(10,10,10,10);
		panelTwitchConnection.setBackground(Color.DARK_GRAY);
		panelTwitchConnection.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panelTwitchConnection.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Twitch Connection Setup"));

        constraints.insets = new Insets(5,-140,5,10);         
        imgTwitchConnection = new JLabel(imgTwitch);
        imgTwitchConnection.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
        panelTwitchConnection.add(imgTwitchConnection,constraints.setXYAnchorPosition(0,0,GridBagConstraints.WEST));
        constraints.insets = new Insets(5,10,5,10);		
		labelTwitchConnectionStatus = new JLabel("Status: ");
		panelTwitchConnection.add(labelTwitchConnectionStatus,constraints.setXYPosition(1,1));
		labelTwitchConnectionStatusRefresh = new JLabel("Disconnected");
		panelTwitchConnection.add(labelTwitchConnectionStatusRefresh,constraints.setXYPosition(2,1));
		constraints.insets = new Insets(10,10,50,10);
		labelTwitchConnectionChannel = new JLabel("Channel: ");
		panelTwitchConnection.add(labelTwitchConnectionChannel,constraints.setXYPosition(1,2));
		labelTwitchConnectionChannelRefresh = new JLabel("");
		panelTwitchConnection.add(labelTwitchConnectionChannelRefresh,constraints.setXYPosition(2,2));
		
		constraints.insets = new Insets(5,5,5,5);
        JButton buttonFieldTwitchChannel = new JButton("Reset");
		buttonFieldTwitchChannel.setPreferredSize(new Dimension(100,30));
		panelTwitchConnection.add(buttonFieldTwitchChannel,constraints.setXYPosition(1,3));
		panelTwitchConnection.add(Box.createVerticalStrut(10));
		buttonFieldTwitchChannel.addActionListener(new ActionListener()	{ 
    		public void actionPerformed(ActionEvent e) {
				changingStatus("TwitchConnectionStatusRefresh","Reseting token...");
				changingStatus("TwitchConnectionChannelRefresh","");    			
    			TwitchAccessClass.setUserToken();
    		    TwitchAccessClass.setChannel();
				changingStatus("TwitchConnectionStatusRefresh","Disconnected");
				changingStatus("TwitchConnectionChannelRefresh","");    			
    		}
    	});
		
        buttonConnectTwitchChannel = new JButton("Connect");
		buttonConnectTwitchChannel.setPreferredSize(new Dimension(100,30));
		panelTwitchConnection.add(buttonConnectTwitchChannel,constraints.setXYPosition(1,4));
		buttonConnectTwitchChannel.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) {
    			if (buttonConnectTwitchChannel.getText().equalsIgnoreCase("CONNECT")) {
        			//MyFunChatBotControl.startTwitchBot();
    				MyFunChatBotControl.connectToTwitch();
    				buttonConnectTwitchChannel.setText("Disconnect");   
    				buttonStatusTwitchChannel.setText("Disconnect");  
    			}
    			else {
    				//MyFunChatBotControl.stopTwitchBot();
    				BotGeneralSetupVO.setTwitchOn(false);
    				changingStatus("TwitchConnectionStatusRefresh","Disconnected");
    				changingStatus("TwitchConnectionChannelRefresh","");
    				changingLedStatus("TWITCH","DISCONNECTED");
    				buttonConnectTwitchChannel.setText("Connect");    
    				buttonStatusTwitchChannel.setText("Connect"); 
    			}
    		}
    	});
		
		cards.add(panelTwitchConnection,"twitchConnection");
		
		//***************************************************************//
		//*************************** ABOUT ME **************************//
		//***************************************************************//		
		panelAboutMe = new JPanel(new GridBagLayout());
		panelAboutMe.setBackground(Color.DARK_GRAY);
		panelAboutMe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "About Me"));
		String st = "<html><div align=center>"
                + "<p><font size=\"6\">MyFunChatBot v0.1</font></p><br>"
                + "This app was developed by:<br><br>"
                + "Edu aka Nerd Frustrado<br>"
                + "Rômulo aka Romulino<br><br><br><br></div></html>";
		JLabel labelAboutMe = new JLabel(st);
		panelAboutMe.add(labelAboutMe);
		cards.add(panelAboutMe,"aboutme");
        
        
		frame.pack();
		frame.setVisible(true);

	}
	
	private static void loadGuiData()
	{
		ConfigFile.loadConfig();
		BotGeneralSetupVO.setMyFunChatBotStatusOn(false);		
		textFieldBotSettingPort.setText(""+BotGeneralSetupVO.getHttpPort());
		textFieldBotSettingColor.setBackground(BotGeneralSetupVO.getBorderColor());
		textFieldBotSettingASCIIEmoji.setText(""+BotGeneralSetupVO.getASCIIEmoji());
		setSettingRefresh();		
		labelTwitchConnectionStatusRefresh.setText("Disconnected");
		labelTwitchConnectionChannelRefresh.setText(BotGeneralSetupVO.getTwitchChannel());
		checkBoxCommandModON.setSelected(BotGeneralSetupVO.getCommandsEnabled());
		setCommandsTabEnabled(BotGeneralSetupVO.getCommandsEnabled());
		textFieldCommandPrefix.setText(BotGeneralSetupVO.getCommandPrefix());
		checkBoxCommandMod.setSelected(BotGeneralSetupVO.getcommandMod());;
		checkBoxPing.setSelected(BotGeneralSetupVO.getCommandPing());
		checkBoxUptime.setSelected(BotGeneralSetupVO.getCommandUptime());
		checkBoxGame.setSelected(BotGeneralSetupVO.getCommandGame());
		checkBoxRank.setSelected(BotGeneralSetupVO.getCommandRank());
		checkBoxLinks.setSelected(BotGeneralSetupVO.getCommandLinks());
		checkBoxAutoAnswerON.setSelected(BotGeneralSetupVO.getAutoAnswerEnabled());
		setAutoAnswerTabEnabled(BotGeneralSetupVO.getAutoAnswerEnabled());
		CustomizedArrayList tempCustomizedArrayList = BotGeneralSetupVO.getAutoAnswerArrayList();
		if (tempCustomizedArrayList != null){
			for (int i = 0; i < tempCustomizedArrayList.size(); i++){
				modelAutoAnswer.addRow(tempCustomizedArrayList.get(i));
			}
		}
		checkBoxPointsOn.setSelected(BotGeneralSetupVO.getPointsEnabled());
		textFieldPointsName.setText(BotGeneralSetupVO.getPointsName());
		checkBoxPointsOnlyMod.setSelected(BotGeneralSetupVO.getPointsMod());
		textFieldPointsExtraMod.setText(""+BotGeneralSetupVO.getPointsExtraMod());
		textFieldPointsPerTime.setText(""+BotGeneralSetupVO.getPointsPerTime());
		textFieldPointsTimer.setText(""+BotGeneralSetupVO.getPointsTimer());
		checkBoxAutoMessageON.setSelected(BotGeneralSetupVO.getAutoMessageEnabled());
		textFieldAutoMessageTimer.setText(""+(BotGeneralSetupVO.getAutoMessageTimer()));
		setAutoMessageTabEnabled(BotGeneralSetupVO.getAutoMessageEnabled());
		CircularArrayList<String> tempCircularArrayList = BotGeneralSetupVO.getAutoMessageList();
		if (tempCircularArrayList != null){
			for (int i = 0; i < tempCircularArrayList.size(); i++){
				String[] row = {tempCircularArrayList.get(i)};
				modelAutoMessage.addRow(row);
			}
		}
		loadVotingVOComboBox();
		setRankingLevelRefresh();
		RankingControl.getInstance();		
		setRankingRefresh();
		ArrayList<String> memeList = MemeListControl.getStringMemeList();
		if (memeList != null){
			for (int i = 0; i < memeList.size(); i++){
				textAreaMemeSettingList.append(memeList.get(i)+"\n");
			}
		}
		checkBoxMemeEnabled.setSelected(BotGeneralSetupVO.getBotMemeEnabled());
		textFieldMemeSettingCost.setText(""+BotGeneralSetupVO.getBotMemeCost());
		checkBoxSponsorEnabled.setSelected(BotGeneralSetupVO.getBotSponsorEnabled());
		checkBoxSponsorMessageEnabled.setSelected(BotGeneralSetupVO.getBotSponsorMessageEnabled());
		textFieldSponsorSettingTime.setText(""+BotGeneralSetupVO.getBotSponsorTime());
		textFieldSponsorMessage.setText(BotGeneralSetupVO.getBotSponsorMessage());
		ArrayList<String> sponsorList = SponsorListControl.getStringSponsorList();
		if (sponsorList != null){
			for (int i = 0; i < sponsorList.size(); i++){
				textAreaSponsorSettingList.append(sponsorList.get(i)+"\n");
			}
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            RankingControl.saveChatterList();
	        }
	    }));
	}
	
	private static void setSettingRefresh(){
		String text = "\n";
		text = text.concat("<<LINK MEME>>\n");
		text = text.concat("http:\\localhost:"+BotGeneralSetupVO.getHttpPort()+"\\meme");
		text = text.concat("\n\n<<LINK GIF>>\n");
		text = text.concat("http:\\localhost:"+BotGeneralSetupVO.getHttpPort()+"\\gif");		
		text = text.concat("\n\n<<LINK CHEST>>\n");
		text = text.concat("http:\\localhost:"+BotGeneralSetupVO.getHttpPort()+"\\chest");		
		text = text.concat("\n\n<<LINK SPONSOR>>\n");
		text = text.concat("http:\\localhost:"+BotGeneralSetupVO.getHttpPort()+"\\sponsor");
		textAreaBotSettingLinks.setText(text);
	}
	
	public static void changingStatus(String label, String message)
	{
		if(label.equalsIgnoreCase("TwitchConnectionStatusRefresh")) {
			labelTwitchConnectionStatusRefresh.setText(message);
		}
		else if(label.equalsIgnoreCase("TwitchConnectionChannelRefresh")) {
			labelTwitchConnectionChannelRefresh.setText(message);
		}
	}
	
	public static void changingLedStatus(String label, String status){
		if(label.equalsIgnoreCase("TWITCH")) {
			if(status.equalsIgnoreCase("CONNECTED")) {
				imgTwitchStatus.setIcon(imgStatusConnected);
			}
			else {
				imgTwitchStatus.setIcon(imgStatusDisconnected);				
			}
		}
		else if(label.equalsIgnoreCase("YOUTUBE")) {
			if(status.equalsIgnoreCase("CONNECTED")) {
				imgYoutubeStatus.setIcon(imgStatusConnected);
			}
			else {
				imgYoutubeStatus.setIcon(imgStatusDisconnected);				
			}
		}
		else if(label.equalsIgnoreCase("DISCORD")) {
			if(status.equalsIgnoreCase("CONNECTED")) {
				imgDiscordStatus.setIcon(imgStatusConnected);
			}
			else {
				imgDiscordStatus.setIcon(imgStatusDisconnected);				
			}
		}
		else if(label.equalsIgnoreCase("FACEBOOK")) {
			if(status.equalsIgnoreCase("CONNECTED")) {
				imgFacebookStatus.setIcon(imgStatusConnected);
			}
			else {
				imgFacebookStatus.setIcon(imgStatusDisconnected);				
			}
		}
		else if(label.equalsIgnoreCase("MYFUNCHATBOT")) {
			if(status.equalsIgnoreCase("CONNECTED")) {
				imgMyFunChatBotStatus.setIcon(imgStatusConnected);
			}
			else {
				imgMyFunChatBotStatus.setIcon(imgStatusDisconnected);				
			}
		}

	}
	
	public static void changingCountStatus(String label, String count){
		if(label.equalsIgnoreCase("TWITCH")) {
			countTwitchConnectedPeople.setText(count);
		}
		else if(label.equalsIgnoreCase("YOUTUBE")) {
			countYoutubeConnectedPeople.setText(count);
		}
		else if(label.equalsIgnoreCase("DISCORD")) {
			countDiscordConnectedPeople.setText(count);
		}
		else if(label.equalsIgnoreCase("FACEBOOK")) {
			countFacebookConnectedPeople.setText(count);
		}	
	}
	
	private static void setCommandsTabEnabled(boolean enable){
		textFieldCommandPrefix.setEnabled(enable);
		checkBoxCommandMod.setEnabled(enable);
		checkBoxPing.setEnabled(enable);
		checkBoxUptime.setEnabled(enable);
		checkBoxGame.setEnabled(enable);
		checkBoxRank.setEnabled(enable);
		checkBoxLinks.setEnabled(enable);
	}
	
	private static void setAutoAnswerTabEnabled(boolean enable){
		buttonAddLineAutoAnswerTable.setEnabled(enable);
		buttonDelLineAutoAnswerTable.setEnabled(enable);
		tableAutoAnswer.setEnabled(enable);
	}
	
	private void setAutoAnswerTable(DefaultTableModel defautTablemodel){
		CustomizedArrayList customizedArrayList = new CustomizedArrayList();
		
		for(int i = 0; i < defautTablemodel.getRowCount(); i++){
			customizedArrayList.add((String)defautTablemodel.getValueAt(i, 0), (String)defautTablemodel.getValueAt(i, 1), CustomizedArrayList.TYPE_AUTOANSWER);			
		}
		BotGeneralSetupVO.setAutoAnswerArrayList(customizedArrayList);
	}
	
	private static void setAutoMessageTabEnabled(boolean enable){
		textFieldAutoMessageTimer.setEnabled(enable);
		buttonAddLineAutoMessageTable.setEnabled(enable);
		buttonDelLineAutoMessageTable.setEnabled(enable);
		tableAutoMessage.setEnabled(enable);
	}
	
	private void setAutoMessageTable(DefaultTableModel defautTablemodelAutoMessage){
		CircularArrayList<String> circularArrayList = new CircularArrayList<String>();
		
		for(int i = 0; i < defautTablemodelAutoMessage.getRowCount(); i++){
			circularArrayList.add((String)defautTablemodelAutoMessage.getValueAt(i, 0));			
		}
		BotGeneralSetupVO.setAutoMessageList(circularArrayList);
	}
	
	private static void setRankingRefresh(){
		ChatterVO[] viewersData = RankingControl.getViewersData();
		modelRanking.getDataVector().removeAllElements();
		if(viewersData != null){
			for (int i = 0; i < viewersData.length; i++){
				String[] row = {viewersData[i].getChatter(),BotConstants.getPlatformTranslate(viewersData[i].getPlatform()),BotConstants.getStatusTranslate(viewersData[i].getStatus()),""+viewersData[i].getTotalTime(),BotGeneralSetupVO.getRankTitle(viewersData[i].getTotalTime())};
				modelRanking.addRow(row);
			}
		}
		tableRanking.setPreferredSize(new Dimension(552,(205+((tableRanking.getRowCount()-11)*tableRanking.getRowHeight()))));
	}
	
	private void setRankingApply(){
		for(int i = 0; i < modelRanking.getRowCount(); i++){
			RankingControl.setChatterVO(new ChatterVO((String)modelRanking.getValueAt(i, 0),
											BotConstants.getPlatformTranslate((String)modelRanking.getValueAt(i, 1)),
											BotConstants.getStatusTranslate((String)modelRanking.getValueAt(i, 2)),
											0,
											Integer.parseInt((String)modelRanking.getValueAt(i, 3))));			
		}
	}

	private void setRankingReset(DefaultTableModel defautTablemodelRanking){
		for(int i = 0; i < defautTablemodelRanking.getRowCount(); i++){			
			RankingControl.setChatterVO(new ChatterVO((String)defautTablemodelRanking.getValueAt(i, 0),
											BotConstants.getPlatformTranslate((String)defautTablemodelRanking.getValueAt(i, 1)),
											BotConstants.getStatusTranslate((String)defautTablemodelRanking.getValueAt(i, 2)),
											0,
											0));			
		}
	}
	
	public void setStatusMyFunChatBot(int status){
		if (status == BotConstants.STATUS_MYFUNCHATBOT_ON){
			BotGeneralSetupVO.setMyFunChatBotStatusOn(true);
			if (BotGeneralSetupVO.getTwitchOn()) {
				MyFunChatBotControl.startTwitchChatBot();				
			}

			if(BotGeneralSetupVO.getPointsEnabled()) {
//				MyFunChatBotControl.startPointThread();
			}
			
			if(BotGeneralSetupVO.getAutoMessageEnabled()) {
//				MyFunChatBotControl.startAutoMessage();
			}
			
			if(BotGeneralSetupVO.getBotMemeEnabled()){
				MyFunChatBotControl.startHttpMemeBot();
			}
			
			MyFunChatBotControl.startScheduleService();
/*			
			if(BotGeneralSetupVO.getBotGifEnabled()){
				MyFunChatBotControl.startHttpGifBot();
			}
			
			if(BotGeneralSetupVO.getBotChestEnabled()){
				MyFunChatBotControl.startHttpChestBot();
			}
			
			if(BotGeneralSetupVO.getBotSponsorEnabled()){
				MyFunChatBotControl.startHttpSponsorBot();
			}			
*/			
		}
		else if (status == BotConstants.STATUS_MYFUNCHATBOT_OFF){
			BotGeneralSetupVO.setMyFunChatBotStatusOn(false);			
			MyFunChatBotControl.stopTwitchChatBot();
//			MyFunChatBotControl.stopPointThread();
			//MyFunChatBotControl.stopAutoMessage();
			MyFunChatBotControl.stopHttpMemeBot();			
		}
	}
	
	public static void setVotingProgressBar(long currentTime, long totalTime){
		if(currentTime != votingCurrentTime){
			votingCurrentTime = currentTime;
			progressBarVoting.setValue((int)Math.floorDiv(votingCurrentTime*100,totalTime));
			progressBarVoting.setString(millisInMinutes(totalTime - currentTime));
		}
	}
	
	public static void setVotingOver(){
		buttonVotingStart.setText("Start");
		progressBarVoting.setValue(0);
		progressBarVoting.setString("");
	}

	private static String millisInMinutes(long time){
		return (String.format("%02d",Math.floorDiv(time,60000))+":"+String.format("%02d",(time%60000)/1000));
	}
	
	private static void loadVotingVOComboBox(){
		comboBoxVoting.removeAllItems();
		for (int i = 0; i < BotGeneralSetupVO.getVotingVOHashMapSize();i++){
			comboBoxVoting.addItem(BotGeneralSetupVO.getVotingVOHashMap(i).getTitle());
		}
	}
	
	private static void setRankingLevelRefresh(){
		RankVO rankVO = null;
		for(int i=0; i<BotGeneralSetupVO.getRankSize(); i++){
			rankVO = BotGeneralSetupVO.getRank(i);
			if(i==0 && rankVO != null){
				textFieldRankingLevel0Title.setText(rankVO.getRankName());
			    textFieldRankingLevel0Min.setText(""+rankVO.getRankMin());
			    textFieldRankingLevel0Bonus.setText(""+rankVO.getRankBonus());
			}
			else if(i==1 && rankVO != null){
				textFieldRankingLevel1Title.setText(rankVO.getRankName());
			    textFieldRankingLevel1Min.setText(""+rankVO.getRankMin());
			    textFieldRankingLevel1Bonus.setText(""+rankVO.getRankBonus());
				
			}
			else if(i==2 && rankVO != null){
				textFieldRankingLevel2Title.setText(rankVO.getRankName());
			    textFieldRankingLevel2Min.setText(""+rankVO.getRankMin());
			    textFieldRankingLevel2Bonus.setText(""+rankVO.getRankBonus());
				
			}
			else if(i==3 && rankVO != null){
				textFieldRankingLevel3Title.setText(rankVO.getRankName());
			    textFieldRankingLevel3Min.setText(""+rankVO.getRankMin());
			    textFieldRankingLevel3Bonus.setText(""+rankVO.getRankBonus());
				
			}
			else if(i==4 && rankVO != null){
				textFieldRankingLevel4Title.setText(rankVO.getRankName());
			    textFieldRankingLevel4Min.setText(""+rankVO.getRankMin());
			    textFieldRankingLevel4Bonus.setText(""+rankVO.getRankBonus());				
			}
			else if(i==5 && rankVO != null){
				textFieldRankingLevel5Title.setText(rankVO.getRankName());
			    textFieldRankingLevel5Min.setText(""+rankVO.getRankMin());
			    textFieldRankingLevel5Bonus.setText(""+rankVO.getRankBonus());
			}
		}
		setRankingLevels();
	}
	
	private static void setRankingLevels(){
		if (textFieldRankingLevel0Title.getText().length()>0){
			textFieldRankingLevel1Title.setEnabled(true);
			textFieldRankingLevel1Min.setEnabled(true);
			textFieldRankingLevel1Bonus.setEnabled(true);
		}
		else{
			textFieldRankingLevel1Title.setEnabled(false);
			textFieldRankingLevel1Title.setText("");
			textFieldRankingLevel1Min.setEnabled(false);
			textFieldRankingLevel1Min.setText("0");
			textFieldRankingLevel1Bonus.setEnabled(false);
			textFieldRankingLevel1Bonus.setText("");
		}
		
		if (textFieldRankingLevel1Title.getText().length()>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel1Min.getText(),"0"))>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel1Bonus.getText(),"0"))>=0){
			textFieldRankingLevel2Title.setEnabled(true);
			textFieldRankingLevel2Min.setEnabled(true);
			textFieldRankingLevel2Bonus.setEnabled(true);
		}
		else{
			textFieldRankingLevel2Title.setEnabled(false);
			textFieldRankingLevel2Title.setText("");
			textFieldRankingLevel2Min.setEnabled(false);
			textFieldRankingLevel2Min.setText("0");
			textFieldRankingLevel2Bonus.setEnabled(false);
			textFieldRankingLevel2Bonus.setText("");
		}

		if (textFieldRankingLevel2Title.getText().length()>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel2Min.getText(),"0"))>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel2Bonus.getText(),"0"))>=0){			textFieldRankingLevel3Title.setEnabled(true);
			textFieldRankingLevel3Min.setEnabled(true);
			textFieldRankingLevel3Bonus.setEnabled(true);
		}
		else{
			textFieldRankingLevel3Title.setEnabled(false);
			textFieldRankingLevel3Title.setText("");
			textFieldRankingLevel3Min.setEnabled(false);
			textFieldRankingLevel3Min.setText("0");
			textFieldRankingLevel3Bonus.setEnabled(false);
			textFieldRankingLevel3Bonus.setText("");
		}
		
		if (textFieldRankingLevel3Title.getText().length()>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel3Min.getText(),"0"))>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel3Bonus.getText(),"0"))>=0){
			textFieldRankingLevel4Title.setEnabled(true);
			textFieldRankingLevel4Min.setEnabled(true);
			textFieldRankingLevel4Bonus.setEnabled(true);
		}
		else{
			textFieldRankingLevel4Title.setEnabled(false);
			textFieldRankingLevel4Title.setText("");
			textFieldRankingLevel4Min.setEnabled(false);
			textFieldRankingLevel4Min.setText("0");
			textFieldRankingLevel4Bonus.setEnabled(false);
			textFieldRankingLevel4Bonus.setText("");
		}
		
		if (textFieldRankingLevel4Title.getText().length()>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel4Min.getText(),"0"))>0
				&& Integer.parseInt((String)MyFunctions.nvl(textFieldRankingLevel4Bonus.getText(),"0"))>=0){
			textFieldRankingLevel5Title.setEnabled(true);
			textFieldRankingLevel5Min.setEnabled(true);
			textFieldRankingLevel5Bonus.setEnabled(true);
		}
		else{
			textFieldRankingLevel5Title.setEnabled(false);
			textFieldRankingLevel5Title.setText("");
			textFieldRankingLevel5Min.setEnabled(false);
			textFieldRankingLevel5Min.setText("0");
			textFieldRankingLevel5Bonus.setEnabled(false);
			textFieldRankingLevel5Bonus.setText("");
		}		
	}
	
	public static void main(String[] args) {

		BotGeneralSetupVO.getInstance();
	    try 
	    {
	        UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
	        SwingUtilities.invokeLater(new Runnable() 
	        {
	            public void run() 
	            {
	    	        new MyFunChatBot();
	            }
	        });	        
	    } 
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	    }		
	}	

}
