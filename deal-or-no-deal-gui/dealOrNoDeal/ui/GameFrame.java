/*
*	Samantha L. Misurda
*	Assignment 4
*/

package dealOrNoDeal.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import dealOrNoDeal.DealOrNoDeal;
import dealOrNoDeal.Briefcase;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private DealOrNoDeal gameModel = new DealOrNoDeal();
	private JLabel gameplayLabel = new JLabel();
	private JButton deal = new JButton("Deal");
	private JButton noDeal = new JButton("No Deal");

	private ArrayList<Briefcase> briefcases;

	public GameFrame() {
		super("Deal or No Deal");

        initUIElements();

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

	public void initUIElements() {
		ActionListener buttonListener = new ButtonHandler();
		CaseHandler caseHandler = new CaseHandler();
        
		JPanel topPanel = new JPanel();

        topPanel.add(gameplayLabel);

        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(deal);
        bottomPanel.add(noDeal);

        middlePanel.setLayout(new GridLayout(4, 6));

        briefcases = gameModel.gameSetup();
        
        for(int i = 0; i < briefcases.size(); i++) {
        	middlePanel.add(briefcases.get(i)); 
        	briefcases.get(i).addActionListener(caseHandler);
        }

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER); 
        add(bottomPanel, BorderLayout.SOUTH); 

        deal.addActionListener(buttonListener);
        noDeal.addActionListener(buttonListener);

    }

	/*
	 * Control the game play
	 */
	public void play() {
		gameplayLabel.setText(gameModel.getInstruction());
		gameModel.gameSetup();
		deal.setEnabled(false);
		noDeal.setEnabled(false);
	}

	/*
	 * Handle mouseClicked events of the Briefcase JButtons
	 */
	private class CaseHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(Briefcase bcase: briefcases) {
				if(bcase == e.getSource()) {
					gameModel.selectCase(bcase);
					bcase.setRemoved();
					break;
				}	 
			}
			gameplayLabel.setText(gameModel.getInstruction());
			if(gameModel.isDealPending()) {
				//Enable Deal and No Deal Buttons
				deal.setEnabled(true);
				noDeal.setEnabled(true);

				//Disable all unselected cases
				for(Briefcase bcase: briefcases) {
					if(bcase.isInPlay()) {
						bcase.setEnabled(false);
					}	 
				}
			}

		}
	}

	/*
	 * Handle Deal and No Deal button events
	 */
	private class ButtonHandler implements ActionListener {
		// handle button event
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == deal) {
            	// call deal stuff
            	gameModel.deal();
            	gameplayLabel.setText(gameModel.getResult());
            	
            	deal.setEnabled(false);
				noDeal.setEnabled(false);
        	} 
        	else if(event.getSource() == noDeal) {
        		// Call no deal stuff
            	gameModel.noDeal();

            	for(Briefcase bcase: briefcases) {
					if(bcase.isInPlay()) {
						bcase.setEnabled(true);
					}
				}

				if(gameModel.isGameActive()) {
					deal.setEnabled(false);
					noDeal.setEnabled(false);
					gameplayLabel.setText(gameModel.getInstruction());
				}
				else {
					gameplayLabel.setText(gameModel.getResult());
				}
        	} 
		}
	}

	public static void main(String args[]) {
		GameFrame game = new GameFrame();
		game.setVisible(true);
		game.play();
	}
}