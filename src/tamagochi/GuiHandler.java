package tamagochi;

//c_ : combo box
//t_ : text field
//f_ : frame
//l_ : label
//b_ : button
//p_ : panel
//ps_: panels
//pp_: PlayerPanel
//pps_: PlayerPanels
//AL_: ActionListener
//ML_: MouseListener
//N_ : NEW_

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * Handles the tamagochi game and GUI for up to 3 Players
 * @author Daniel Davis
 */
public class GuiHandler{
	
	private final int MAXIMUM_PETS = 3;
	
	//The main panels button indexes
	private final int FEED_BUTTON = 0;
	private final int PLAY_BUTTON = 1;
	private final int SLEEP_BUTTON = 2;
	private final int VISIT_STORE_BUTTON = 3;
	private final int REVIVE_BUTTON = 4;
	private final int N_DAY_BUTTON = 5;
	private final int N_PLAYER_BUTTON = 6;
	private final int N_PET_BUTTON = 7;
	
	//Strings for choosing the number of players
	private final String ONE = "one";
	private final String TWO = "two";
	private final String THREE = "three";
	
	private int daysToPlay;
	private int daysPassed;
	private int maxNumPlayers;
	
	private Player selectedPlayer;
	private int selectedPlayerIndex;
	
	private int selectedPetIndex;
	private Pet selectedPet;
	
	private JPanel p_secondary;
	private JFrame f_secondary;
	
	private ArrayList<String> namesUsed;
	
	private ArrayList<JButton> sideButtons;
	
	ArrayList<PlayerPanel> pps_list;
	PlayerPanel pp_selected;
	int playerCount;
	
	/**
	 * GuiHandler constructor
	 * initialises integer and ArrayList properties of the GuiHandler
	 * then propmpts for the number of users that will play.
	 */
	public GuiHandler()
	{
		daysPassed = 0;
		daysToPlay = 0;
		selectedPlayerIndex  = 0;
		sideButtons = new ArrayList();
		namesUsed = new ArrayList();
		
		pps_list = new ArrayList();
		playerCount = 0;
		
		promptUserNo();
	}
	
	/**
	 * Opens a window that will ask for the number of players that will play
	 * the user will be able to select between one and three
	 */
	private void promptUserNo(){
		f_secondary = new JFrame("Select Maximum Number of Players");
		p_secondary = new JPanel();
		
		f_secondary.setMinimumSize(new Dimension(350,80));
		
		//AL for # of players buttons
		class AL_ToggleButtons implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals(ONE)){
					maxNumPlayers = 1;
				} else if(e.getActionCommand().equals(TWO)){
					maxNumPlayers = 2;
				} else if(e.getActionCommand().equals(THREE)){
					maxNumPlayers = 3;
					
				}
				f_secondary.setVisible(false);
				promptDays();
				initUI();
			}
			
		}
		
		JLabel l_conditions = new JLabel("Select players: ");
		
		// # of players buttons
		JButton tb_one = new JButton(ONE);
		JButton tb_two = new JButton(TWO);
		JButton tb_three = new JButton(THREE);
		
		tb_one.addActionListener(new AL_ToggleButtons());
		tb_two.addActionListener(new AL_ToggleButtons());
		tb_three.addActionListener(new AL_ToggleButtons());
		
		p_secondary.add(l_conditions);
		p_secondary.add(tb_one);
		p_secondary.add(tb_two);
		p_secondary.add(tb_three);
		
		f_secondary.add(p_secondary);
		f_secondary.setVisible(true);
	}
	
	/**
	 * A window that will ask the user how many days to play for
	 * the user will be able to select a multiple of 10 up to 100
	 */
	private void promptDays(){
		
		JPanel p_promptDays = new JPanel();
		
		String[] daySelect = {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
		
		JLabel l_conditions = new JLabel("Select the number of days to play for.");
		JComboBox c_daySelect = new JComboBox(daySelect);
		
		p_promptDays.add(l_conditions);
		p_promptDays.add(c_daySelect);
		
		int retValue = JOptionPane.showOptionDialog(null, p_promptDays, "Days to play for",  
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, null, null);
		
		daysToPlay = 10 * (c_daySelect.getSelectedIndex() + 1);

	}
	
	/**
	 * Initialises the UI given the number of players
	 */
	private void initUI(){
		JFrame f_main = new JFrame("Tamagochi");
		f_main.setMinimumSize(new Dimension(1400,400));
		
        JPanel p_main = new JPanel();
        p_main.setLayout(new GridLayout(1,0,1,1));
        
        JPanel p_button = new JPanel();
        
        p_button.setLayout(new GridLayout(0,1,1,1));
        p_button.setSize(new Dimension(250,250));
        
        sideButtons.add(feedButton());
        sideButtons.add(playButton());
        sideButtons.add(sleepButton());
        sideButtons.add(visitStore());
        sideButtons.add(reviveButton());
        sideButtons.add(newDayButton());
        sideButtons.add(newPlayerButton());
        sideButtons.add(newPetButton());
        
        for(JButton b: sideButtons){
        	p_button.add(b);
        }
        
        p_main.add(p_button);
        
        //Player Panels
        for(int i = 0; i < maxNumPlayers; i += 1){
        	PlayerPanel p_player = new PlayerPanel(3); 
        	pps_list.add(p_player);
        	p_main.add(p_player);
        }
        
        f_main.add(p_main);
        
        f_main.pack();

        f_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_main.setVisible(true);
		
	}
	
	/**
	 * Creates a feed button with a Feed ActionListener
	 * calls the selected Pets feed method with the selected Player's Food properties as parameters
	 * counts as an actionDoneToday
	 * @return b_feed JButton - button with Feed ActionListener
	 */
	private JButton feedButton(){
		JButton b_feed = new JButton("Feed");
		
		class AL_Feed implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				selectedPet.feed(selectedPlayer.getFoodN(), selectedPlayer.getFoodT());
				selectedPet.doSomething();
				
				enableButtons();
				
				updatePetStatus(selectedPlayerIndex, selectedPetIndex);
			}
		}
		
		b_feed.setEnabled(false);
		b_feed.addActionListener(new AL_Feed());
		
		return b_feed;
	}
	
	/**
	 * Creates and returns a PlayButton with a Play AcitonListener
	 * uses the selected pets play method, using the selected Player's Toy's funFactor as a parameter
	 * counts as an actionDoneToday
	 * @return b_play JButton - button with Play ActionListener
	 */
	private JButton playButton(){
		JButton b_play = new JButton("Play");
		
		class AL_Play implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				selectedPet.play(selectedPlayer.getToyFF());
				selectedPet.doSomething();
				
				enableButtons();
				
				updatePetStatus(selectedPlayerIndex, selectedPetIndex);
			}
		}
		
		b_play.setEnabled(false);
		b_play.addActionListener(new AL_Play());
		
		return b_play;
	}
	
	/**
	 * Creates and returns a JButton with Sleep ActionListener
	 * calls the selected Pets Sleep method
	 * counts as an actionDoneToday
	 * @return b_sleep JButton - button with Sleep ActionListener
	 */
	private JButton sleepButton(){
		JButton b_sleep = new JButton("Sleep");
		
		class AL_Sleep implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				selectedPet.sleep();
				selectedPet.doSomething();
				
				enableButtons();
				
				updatePetStatus(selectedPlayerIndex, selectedPetIndex);
			}
		}
		
		b_sleep.setEnabled(false);
		b_sleep.addActionListener(new AL_Sleep());
		
		return b_sleep;
	}
	
	/**
	 * Creates and returns a button with a visitStore ActionListener
	 * when pressed this will open the store for the player to purchase various things
	 * @return b_visitStore JButton - button with VisitStore ActionListener
	 */
	private JButton visitStore(){
		
		JButton b_visitStore = new JButton("Visit Store");
		
		class AL_VisitStore implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				storeMenu();
			}
			
		}
		
		b_visitStore.addActionListener(new AL_VisitStore());
		b_visitStore.setEnabled(false);
		
		return b_visitStore;
	}
	
	/**
	 * Creates and returns a reviveButton ActionListener
	 * when pressed will revive the selected pet
	 * @return b_revive JButton - button with revive ActionListener
	 */
	private JButton reviveButton(){
		JButton b_revive = new JButton("Revive Pet");
		
		class AL_Revive implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				selectedPet.revive();
				updatePetImage(selectedPlayerIndex, selectedPetIndex);
				updatePetStatus(selectedPlayerIndex, selectedPetIndex);
				
			}
		}
		
		b_revive.setEnabled(false);
		b_revive.addActionListener(new AL_Revive());
		
		return b_revive;
	}
	
	/**
	 * Creates and returns a button with a NewDay ActionListener
	 * when pressed will make it a new day:
	 * updating pet statuses, calculating scores and updating the Player and Pet Panels
	 * @return b_newDay JButton - a button with a NewDay ActionListener
	 */
	private JButton newDayButton(){
		JButton b_newDay = new JButton("New Day");
		
		class AL_NewDay implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				for(int i = 0; i < pps_list.size(); i += 1){
					
					int scoreImprovement = 0;
					
					PlayerPanel pp_current = pps_list.get(i);
					Player currentPlayer = pp_current.getPlayer();
					ArrayList<Pet> pets = currentPlayer.pets();
					
					for(int j = 0; j < pets.size(); j += 1){
						Pet pet = pets.get(j);
						if(pet.getIsAlive()){
							scoreImprovement += pet.score();
							pet.tire(pet.getSpecies().getTFactor());
							pet.starve(pet.getSpecies().getHFactor());
							pet.age();
							updatePetStatus(i, j);
							updatePetImage(i, j);
						}
					}
					
					currentPlayer.improveScore(scoreImprovement);
					currentPlayer.increaseMoney(scoreImprovement);
					
					updatePlayerStats(i);
				}
				
				enableButtons();
				
				daysPassed += 1;
				
				if(daysPassed >= daysToPlay){
					promptScoreBoard();
				}
				
			}
		}
		
		b_newDay.setEnabled(false);
		b_newDay.addActionListener(new AL_NewDay());
		return b_newDay;
	}
	
	/**
	 * Creates and returns a button with a New PlayerAction Listener
	 * when clicked will create a new player
	 * @return b_newPlayer JButton - button with NewPlayer ActionListener
	 */
	private JButton newPlayerButton(){
		JButton b_newPlayer = new JButton("New Player");
		
		class AL_NewPlayer implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				newPlayer();
				
			}
		}
		
		b_newPlayer.addActionListener(new AL_NewPlayer());
		return b_newPlayer;
	}
	
	/**
	 * Creates a button with a NewPet ActionListener
	 * when pressed will add a new Pet to the selected PlayerPanel
	 * @return b_newPet JButton - button with NewPet ActionListener
	 */
	private JButton newPetButton(){
		JButton b_newPet = new JButton("New Pet ($100)");
		
		class AL_NewPet implements ActionListener{
			public void actionPerformed(ActionEvent e){
				
				//Makes sure the player doesn't already have 3 pets
				if((selectedPlayer.getMoney() >= 10)){
					newPet();
				} else{
					messageBox("You don't have enough money for that","Insufficient funds");
				}
			}
		}
		
		b_newPet.setEnabled(false);
		b_newPet.addActionListener(new AL_NewPet());
		
		return b_newPet;
	}
	
	/**
	 * Displays a message in a new window
	 * @param message - String message to be displayed in the window
	 * @param messageTitle - String dialog boxes title
	 */
	private void messageBox(String message, String messageTitle){
		JPanel p_messageBox = new JPanel();
		
		JLabel l_message = new JLabel(message);
		
		p_messageBox.add(l_message);
		
		JOptionPane.showOptionDialog(null, p_messageBox, messageTitle,  
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, null, null);	
	}
	
	/**
	 * Updated the status Panel of a PetPanel given the index of the Player and Pet
	 * @param playerIndex int - index of the PlayerPanel whose PetPanel should be updated
	 * @param petIndex int - index of the PetPanel whose status panel should be updated
	 */
	private void updatePetStatus(int playerIndex, int petIndex){
		
		PlayerPanel pp_current = pps_list.get(playerIndex);
		pp_current.updatePetStatus(petIndex);
		
	}
	
	/**
	 * Updates the status panel f a PlayerPanel given the PlayerPanel's index
	 * @param playerIndex - int index of the player being updated
	 */
	private void updatePlayerStats(int playerIndex){
		
		PlayerPanel pp_current = pps_list.get(playerIndex);
		pp_current.updateStatus();
		
	}
	
	/**
	 * Prompts the store menu
	 * the user will be able to choose a selection of things to buy from a comboBox
	 * the purchase will only effect the Player and Pet sho are selected
	 */
	private void storeMenu(){
		JPanel p_storeMenu = new JPanel();
		
		p_storeMenu.setLayout(new GridLayout(2,1,1,1));
		
		int[] costs = {100, 100, 100, 100};
		String[] goodsForSale = {"Food Nutrition ($" + costs[0] + ")",
								"Food Taste ($" + costs[1] + ")",
								"Toy ($" + costs[2] + ")",
								"Pet ($" + costs[3] + ")"};
		
		JLabel l_upgrades = new JLabel("Upgrades:");
		JComboBox c_goodsForSale = new JComboBox(goodsForSale);
		
		p_storeMenu.add(l_upgrades);
		p_storeMenu.add(c_goodsForSale);
		
		int retValue = JOptionPane.showOptionDialog(null, p_storeMenu, "Store",  
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null, null, null);
		switch(retValue){
		case JOptionPane.OK_OPTION:
			
			int selected = c_goodsForSale.getSelectedIndex();
			
			if(selectedPlayer.getMoney() < costs[selected]){
				messageBox("You can't afford to do that right now.","Insufficient Funds");
			}else{
				switch(selected){
					case 0:
						selectedPlayer.reduceMoney(costs[selected]);
						selectedPlayer.improveFoodN(1);
						break;
					case 1:
						selectedPlayer.reduceMoney(costs[selected]);
						selectedPlayer.improveFoodT(1);
						break;
					case 2:
						selectedPlayer.reduceMoney(costs[selected]);
						selectedPlayer.improveToy(1);
						break;
					case 3:
						selectedPlayer.reduceMoney(costs[selected]);
						selectedPet.qualityImprovement();
				}
				
				updatePetStatus(selectedPlayerIndex, selectedPetIndex);
				updatePlayerStats(selectedPlayerIndex);
				
			}
			
			break;
		default :
		}
	}
	
	/**
	 * Updates the picture of a PetPanel given the index of a PlayerPanel and PetPanel
	 * @param i int - the index of the PLayerPanel whose PetPanel should be updated
	 * @param j int - the index of the PetPanel whose picture panel should be updated
	 */
	private void updatePetImage(int i, int j){
		pps_list.get(i).updatePetPicture(j);
	}
	
	/**
	 * Called in the NewPlayer ActionListener
	 * prompts the user for a name between 2-10 characters
	 * if it is outside of the range it is denied /
	 * else it is accepted and new Player and PlayerPanels are created
	 * also a MouseListener is applied to the PlayerPanel that will cause it to become selected when clicked
	 */
	private void newPlayer(){
		
		class ML_PlayerSelection implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();

				if(o instanceof PlayerPanel){
					PlayerPanel pp_current = ((PlayerPanel) o);
					disableBorders();
					
					int playerIndex = pps_list.indexOf(pp_current);
					
					updateSelection(playerIndex,0);
					
					enableButtons();
					enableBorders();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		}
		
		//Creates a box for the user to enter the player's name
		JPanel p_name = new JPanel();
		p_name.setLayout(new GridLayout(2,0,1,1));
		
		JLabel l_conditions = new JLabel("Name must be 2-10 characters.");
		JLabel l_name = new JLabel("Name: ");
		JTextField t_name = new JTextField(8);
		
		p_name.add(l_name);
		p_name.add(t_name);
		p_name.add(l_conditions);
		
		int retVal = JOptionPane.showOptionDialog(null, p_name, "Name your Player",  
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, null, null);
		switch(retVal) {
		case JOptionPane.OK_OPTION:
			String name = t_name.getText();
			
			//Checks the name is the right length before adding the player
			if((name.length() < 11) & (name.length() > 1 & (!namesUsed.contains(name)))){
				
				if(playerCount > 0){
					disableBorders();
				}
				
				namesUsed.add(name);
				
				pps_list.get(playerCount).addPlayer(new Player(name));
				playerCount += 1;
				
				selectedPlayerIndex = (playerCount - 1);
				
				pp_selected = pps_list.get(selectedPlayerIndex);
				selectedPlayer = pp_selected.getPlayer();
				
				pp_selected.addMouseListener(new ML_PlayerSelection());
				
				enableButtons();
				enableBorders();
				
			}else if(namesUsed.contains(name)){
				messageBox("That name is already in use. A new player was not created.", "Re-use warning");
			} 
			else{
				messageBox("The name you had chosed was not between 2 and 10 characters long. A new player was not created.", "Name length warning");
			}
			
			break;
		default: 
			System.out.println("The window was closed, player not created");
		}
	}
	
	/**
	 * Called during the NewPet ActionListener
	 * prompts the user for a name between 2-10 characters and a species from a combobox
	 * if it is outside of the restrictions then it is denied/
	 * else a Pet and PetPanel will be created
	 * also a MouseListener will be applied to the PetPanel that when update the selected pet and player when clicked
	 */
	private void newPet(){
		
		class ML_PetSelection implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if(o instanceof PetPanel){
					
					disableBorders();
					
					PetPanel p_current = ((PetPanel) o);
					
					for(int i = 0; i < playerCount; i += 1){
						PlayerPanel pp_current = pps_list.get(i);
						ArrayList<PetPanel> ps_pet = pp_current.getPetPanels();
						
						if(ps_pet.contains(p_current)){
							int playerIndex = i;
							int petIndex = ps_pet.indexOf(p_current);
							
							updateSelection(playerIndex, petIndex);

						}
						
						
					}
					
					enableButtons();
					enableBorders();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		}
		
		JPanel p_petCreation = new JPanel();
		p_petCreation.setLayout(new GridLayout(3,0,1,1));
		
		String[] species = {Species.NAME_1, Species.NAME_2, Species.NAME_3}; 
		
		//Initialising panels, labels, etc for pet creation window
		JLabel l_nameConditions = new JLabel("Name must be between 2-10 characters.");
		JLabel l_speciesConditions = new JLabel("You can't use existing names.");
		JLabel l_name = new JLabel("Name:");
		JTextField t_name = new JTextField(8);
		JLabel l_species = new JLabel("Species:");
		JComboBox c_species = new JComboBox(species);
		
		p_petCreation.add(l_nameConditions);
		p_petCreation.add(l_speciesConditions);
		p_petCreation.add(l_name);
		p_petCreation.add(l_species);
		p_petCreation.add(t_name);
		p_petCreation.add(c_species);
		
		int retVal = JOptionPane.showOptionDialog(null, p_petCreation, "Create your pet",  
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, null, null);
		switch(retVal) {
		case JOptionPane.OK_OPTION:
			String petName = t_name.getText();
			String selectedSpecies = "deafult";
			
			//Makes sure the selected species is a string - it should always be a string though
			if(c_species.getSelectedItem() instanceof String){
				selectedSpecies = (String) c_species.getSelectedItem();
			}else{
				break;
			}
			
			//Checks the name is in range and not used
			if((petName.length() > 1) & (petName.length() < 11) & (selectedSpecies != "default") & (!namesUsed.contains(petName))){
				//Adds and selects the pet if the conditions are met
				
				namesUsed.add(petName);
				
				pp_selected.addPet(new Pet(petName, new Species(selectedSpecies)));
				selectedPlayer.reduceMoney(10);
				
				updatePlayerStats(selectedPlayerIndex);
				
				updateSelection(selectedPlayerIndex, pp_selected.getPetCount() - 1);
				
				pp_selected.getPetPanel(selectedPetIndex).addMouseListener(new ML_PetSelection());
				
			}else if(namesUsed.contains(petName)){
				messageBox("That name is already in use. No pet was created.", "Re-use warning");
			} else{
				messageBox("The name you had chosed was not between 2 and 10 characters long. No pet was created.", "Name length warning");
			}
			
			break;
		default: 
			System.out.println("The window was closed, player not created");
		}
	}
	
	
	/**
	 * Enables buttons depending on the number of players, pets and pet status
	 */
	public void enableButtons(){
		

		if(playerCount < maxNumPlayers){
			//The maximum number of players is not yet reached
			sideButtons.get(N_PLAYER_BUTTON).setEnabled(true);
		} else{
			//The maximum number of players has been reached
			sideButtons.get(N_PLAYER_BUTTON).setEnabled(false);
			
			//Checking for new day button:
			boolean shouldNewDay = true;
			
			//Does every player have a pet
			for(int i = 0;i < maxNumPlayers; i += 1){
				shouldNewDay &= (pps_list.get(i).getPetCount() > 0);
			}
			
			if(shouldNewDay){
				//Every player has a pet
				sideButtons.get(N_DAY_BUTTON).setEnabled(true);
			}
		}
		
		if(playerCount > 0){
			//At least one player is active
			int petCount = pp_selected.getPetCount();
			
			// -- this will be turned off later if there are too many pets
			sideButtons.get(N_PET_BUTTON).setEnabled(true);
			
			if(petCount <= 0){
				//The selected player has no pets
				sideButtons.get(PLAY_BUTTON).setEnabled(false);
				sideButtons.get(FEED_BUTTON).setEnabled(false);
				sideButtons.get(SLEEP_BUTTON).setEnabled(false);
				sideButtons.get(VISIT_STORE_BUTTON).setEnabled(false);
				sideButtons.get(N_DAY_BUTTON).setEnabled(false);
				sideButtons.get(REVIVE_BUTTON).setEnabled(false);
			} else{
				
				sideButtons.get(VISIT_STORE_BUTTON).setEnabled(true);
				
				if((selectedPet.getThingsDone() >= 2) | !selectedPet.getIsAlive()){
					//The selected pet has done too much today or is dead
					sideButtons.get(PLAY_BUTTON).setEnabled(false);
					sideButtons.get(FEED_BUTTON).setEnabled(false);
					sideButtons.get(SLEEP_BUTTON).setEnabled(false);
				}else {
					//The selected has done less that 2 things today and is not dead
					sideButtons.get(PLAY_BUTTON).setEnabled(true);
					sideButtons.get(FEED_BUTTON).setEnabled(true);
					sideButtons.get(SLEEP_BUTTON).setEnabled(true);
				}
				
				
				if(( selectedPet.getIsAlive()) | (selectedPet.getHasRevived())){
					//Selected pet is alive or has already revived
					sideButtons.get(REVIVE_BUTTON).setEnabled(false);
				} else {
					//Selected pet is dead and has not revived
					sideButtons.get(REVIVE_BUTTON).setEnabled(true);
				}
				
				if(petCount == MAXIMUM_PETS){
					//The maximum number of pets is reached
					sideButtons.get(N_PET_BUTTON).setEnabled(false);
				}
			}
		}
		
	}
	
	/**
	 * Enables the selected Player and Pet borders
	 */
	private void enableBorders(){
		pp_selected.enableBorder();
		if(pp_selected.getPetCount() > 0){
			pp_selected.enablePetBorder(selectedPetIndex);
		}
	}
	
	/**
	 * Disables the selected Player and Pet borders
	 */
	private void disableBorders(){
		pp_selected.disableBorder();
		if(pp_selected.getPetCount() > 0){
			pp_selected.disablePetBorder(selectedPetIndex);
		}
	}
	
	/**
	 * Prompts the scoreboard, showing the final scores of each player
	 */
	private void promptScoreBoard(){
		
		JFrame f_scoreboard = new JFrame("Scoreboard");
		JPanel p_scoreboard = new JPanel();
		
		p_scoreboard.setLayout(new GridLayout(0,1,1,1));
		
		f_scoreboard.setSize(new Dimension(700, 500));
		
		JLabel l_description= new JLabel("The final scores are:");
		
		
		
		p_scoreboard.add(l_description);
		
		for(int i = 0; i < maxNumPlayers; i += 1){
			Player currentPlayer = pps_list.get(i).getPlayer();
			JLabel l_p1Name = new JLabel(currentPlayer.getName() + "'s score: " + currentPlayer.getScore());
			p_scoreboard.add(l_p1Name);
		}
		
		f_scoreboard.add(p_scoreboard);
		
		f_scoreboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_scoreboard.setVisible(true);
	}
	
	/**
	 * Updates selected Pet and Player
	 * @param playerIndex int - the new selected Player index
	 * @param petIndex int - the new selected Pet index
	 */
	private void updateSelection(int playerIndex, int petIndex){
		disableBorders();
		
		selectedPlayerIndex = playerIndex;
		pp_selected = pps_list.get(playerIndex);
		selectedPlayer = pp_selected.getPlayer();
		
		if(pp_selected.getPetCount() > 0){
			
			System.out.println(petIndex);
			selectedPetIndex = petIndex;
			selectedPet = pp_selected.getPet(selectedPetIndex);
		} else{
			selectedPetIndex = 0;
		}
		
		enableBorders();
		enableButtons();
	}
	
	public static void main(String[] args){
		GuiHandler gui = new GuiHandler();
	}
}