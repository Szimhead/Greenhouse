package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controler.Controler1;

public class Window {
	private JFrame frame;
	private JFrame overFrame;
	private JPanel panel;
	private JPanel down;
	private JPanel title;
	private JPanel market;
	private JPanel overPanel;
	private JPanel info;
	private JPanel[] panels;
	private JLabel titleLabel;
	private JLabel value;
	private JLabel money;
	private JLabel valueInfo;
	private JLabel moneyInfo;
	private JLabel days;
	private JLabel waterInfo;
	private JLabel waterInfo1;
	private JLabel tempInfo;
	private JLabel tempInfo1;
	private JLabel[] imgLabel;
	private JLabel[] name;
	private JLabel[] waterLabel;
	private JLabel[] tempLabel;
	private JButton easy;
	private JButton hard;
	private JButton water;
	private JButton warmUp;
	private JButton coolDown;
	private JButton sell;
	private static Image[] cactusForms;
	private static Image[] haworthiaForms;
	private static Image[] alocasiaForms;
	private Font font;
	private Font fontSmall;
	private boolean isDead[];
	private static boolean ended;

	private Controler1 controler;
	
	//*******************************************************************************
	//Tworzenie nowego okna
	public Window() {
		frame=new JFrame("GreenHouse");
		panel=new JPanel();
		down=new JPanel();
		title=new JPanel();
		market=new JPanel();
		info=new JPanel();
		ended=false;
		setForms();

		font=new Font(null, 0, 20);
		fontSmall=new Font(null, 0, 18);

		panel.setLayout(new GridLayout(2, 10));
		
		panels=new JPanel[20];
		
		isDead=new boolean[20];
		
		
		
		easy=new JButton("£ATWY");
		hard=new JButton("TRUDNY");
		
		
		easy.setFont(new Font(null,0,25));
		hard.setFont(new Font(null,0,25));
		
		panel.add(easy);
		panel.add(hard);
	
		water=new JButton("Podlewanie (-70)");
		warmUp=new JButton("Ogrzanie (-94)");
		coolDown=new JButton("Ch³odzenie (-48)");
		sell=new JButton("Sprzedaj 0");
		
		value=new JLabel();
		money=new JLabel();
		valueInfo=new JLabel("<html>Wspó³czynnik op³acalnoœci<br>(im bli¿ej 1, tym lepiej)</html>");
		moneyInfo=new JLabel("Stan konta");
		days=new JLabel("0");
		waterInfo=new JLabel("poziom wody");
		tempInfo=new JLabel("temperatura");
		waterInfo1=new JLabel("poziom wody");
		tempInfo1=new JLabel("temperatura");
		
		imgLabel=new JLabel[20];
		name=new JLabel[20];
		waterLabel=new JLabel[20];
		tempLabel=new JLabel[20];
		
		water.addActionListener(new WaterListener());
		warmUp.addActionListener(new WarmListener());
		coolDown.addActionListener(new ColdListener());
		sell.addActionListener(new SellListener());
		
		water.setFont(fontSmall);
		warmUp.setFont(fontSmall);
		coolDown.setFont(fontSmall);
		sell.setFont(fontSmall);
		valueInfo.setFont(fontSmall);
		moneyInfo.setFont(fontSmall);
		money.setFont(fontSmall);
		value.setFont(fontSmall);
		days.setFont(font);
		waterInfo.setFont(fontSmall);
		tempInfo.setFont(fontSmall);
		waterInfo1.setFont(fontSmall);
		tempInfo1.setFont(fontSmall);
		
		valueInfo.setPreferredSize(new Dimension(250,45));
		valueInfo.setMaximumSize(new Dimension(300,50));

		info.add(waterInfo);
		waterInfo.setBorder(new EmptyBorder(143,20,0,10));
		info.add(tempInfo);
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		waterInfo.setBorder(new EmptyBorder(143,20,0,10));
		tempInfo.setBorder(new EmptyBorder(0,20,170,10));
		waterInfo1.setBorder(new EmptyBorder(0,20,0,10));
		tempInfo1.setBorder(new EmptyBorder(0,20,0,10));

		info.add(waterInfo1);
		info.add(tempInfo1);
		
		down.add(water);
		down.add(warmUp);
		down.add(coolDown);
		
		market.add(moneyInfo);
		market.add(money);
		market.add(valueInfo);
		market.add(value);
		market.add(sell);
		market.add(days);
		money.setBorder(new EmptyBorder(0,0,20,0));
		value.setBorder(new EmptyBorder(0,0,20,0));
		market.setLayout(new BoxLayout(market, BoxLayout.Y_AXIS));
		market.setBorder(new EmptyBorder(20, 20, 0,20));
		
		for(int i=0;i<20;i++) {
			panels[i]=new JPanel();
			panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS));
		
			isDead[i]=false;
		}
		
		down.setVisible(false);
		market.setVisible(false);
		info.setVisible(false);
		
		titleLabel=new JLabel("Greenhouse");
		titleLabel.setFont(font);
		title.add(titleLabel);
		
		panel.setBorder(new EmptyBorder(0, 20, 0,0));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.NORTH,title);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.getContentPane().add(BorderLayout.SOUTH,down);
		frame.getContentPane().add(BorderLayout.EAST, market);
		frame.getContentPane().add(BorderLayout.WEST,info);

		frame.setSize(1400,550);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocation(200, 300);
	}
	//*******************************************************************************
	
	//Wybór poziomu trudnoœci
	public void chooseLevel() {
	
		easy.addActionListener(new EasyListener());
		hard.addActionListener(new HardListener());
	}
	//*******************************************************************************
	
	//Aktywacja grafiki - po wybraniu poziomu
	public void play() {
		panel.remove(easy);
		panel.remove(hard);
		down.setVisible(true);
		market.setVisible(true);
		info.setVisible(true);
	}
	//*******************************************************************************
	
	//Odœwie¿anie elementów okna
	public void view() {
		if(controler.getDays()>7)
			sell.setEnabled(true);
		else
			sell.setEnabled(false);
		value.setText(new DecimalFormat("##.####").format(controler.getValue()));
		money.setText(String.valueOf(controler.getBalance()));
		days.setText(String.valueOf(controler.getDays()));
		sell.setText("Sprzedaj "+new DecimalFormat("####").format(controler.getCurrentProfit()));
		
		if(controler.getBalance()<70)
			water.setEnabled(false);
		else water.setEnabled(true);
		
		if(controler.getBalance()<94)
			warmUp.setEnabled(false);
		else warmUp.setEnabled(true);
		
		if(controler.getBalance()<48)
			coolDown.setEnabled(false);
		else coolDown.setEnabled(true);
		
		
		
		for(int i=0;i<panels.length;i++) {
			
			panels[i].removeAll();
			imgLabel[i]= null;
			name[i]=null;
			waterLabel[i]=null;
			tempLabel[i]=null;
			
			imgLabel[i]= new JLabel(new ImageIcon(this.checkCondition(i)));
			name[i]=new JLabel(controler.plantType(i));
			waterLabel[i]=new JLabel(String.valueOf(controler.waterDif(i)));
			tempLabel[i]=new JLabel(String.valueOf(controler.tempDif(i)));
			
			imgLabel[i].setPreferredSize(new Dimension(30,30));
			imgLabel[i].setMaximumSize(new Dimension(80,120));
			
			imgLabel[i].setFont(fontSmall);
			name[i].setFont(fontSmall);
			waterLabel[i].setFont(fontSmall);
			tempLabel[i].setFont(fontSmall);
			
			panels[i].add(imgLabel[i]);
			panels[i].add(name[i]);
			panels[i].add(waterLabel[i]);
			panels[i].add(tempLabel[i]);
		
			name[i].setForeground(this.check(i,"ratio"));
			waterLabel[i].setForeground(this.check(i,"water"));
			tempLabel[i].setForeground(this.check(i,"temp"));
			
			panel.add(panels[i]);

		}
		
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		
	}
	//*******************************************************************************	

	//Œmieræ roœliny z powodu zbyt niskiego poziomu wody lub zbyt wysokiej temperatury
	public void plantDied() {
		controler.dead();
	}
	//*******************************************************************************		
	
	//Sprawdzenie czy pozosta³y jakieœ ¿ywe roœliny (nie=koniec gry)
	public void anyLeft() {
		boolean left=false;
		int counter=0;
		while(counter<20 && !left) {
			if(!isDead[counter])
				left=true;
			counter++;
		}
		if(!left)
			controler.allDead();
	}
	
	//Ustawianie koloru danego napisu (typ, woda, temperatura) w zale¿noœci od warunków
	public Color check(int i, String toCheck) {
		double down=0,middown=0,midup=0,up=0,tmp=0;
		switch (toCheck) {
		case "ratio":
			down=0.3;
			middown=0.6;
			midup=1.5;
			up=2;
			tmp=controler.getRatio(i);
			break;
		case "water":
			down=-15;
			middown=-10;
			midup=10;
			up=15;
			tmp=controler.waterDif(i);
			break;
		case "temp":
			down=-9;
			middown=-5;
			midup=5;
			up=10;
			tmp=controler.tempDif(i);
			break;
		default: System.out.println("Nieprawd³owe kryterium");
		}
		Color color;
		if((tmp>up || tmp<down) && !isDead[i])
			color=new Color(250,0,0);
		else {
			if(((tmp>midup && tmp<=up) || (tmp>=down && tmp<middown)) && !isDead[i])
				color=new Color(255,153,0);
			else
				color=new Color(0,204,0);
			
		}
		if(isDead[i])
			color=new Color(204,0,0);
		return color;
	}
	//*******************************************************************************	
	
	//Ustawianie obrazka roœliny w zale¿noœci jej stanu
	public Image checkCondition(int i) {
		int waterDifference=controler.waterDif(i);
		int tempDifference=controler.tempDif(i);
		String type=controler.plantType(i);
		Image image = null;
		Image[] typeForms=new Image[3];
		switch (type) {
		case "kaktus":
			typeForms=cactusForms;
			break;
		case "alokazja":
			typeForms=alocasiaForms;
			break;
		case "haworcja":
			typeForms=haworthiaForms;
			break;
		default: System.out.println("\nNieznany typ roœliny");
		}
		
		if((waterDifference>=-10 && tempDifference<15) && !isDead[i])
			image=typeForms[0];
		else
			if(waterDifference<-10 && waterDifference>-15 && !isDead[i])
				image=typeForms[1];
			else
				if((waterDifference<=-15 || tempDifference>=15) && !isDead[i]) {
					image=typeForms[2];
					isDead[i]=true;
					this.plantDied();
				}
		if(isDead[i])
			image=typeForms[2];
		return image;			
	}
	//*******************************************************************************	
	
	//Przypisanie odpowiednich obrazków do tabeli typów roœlin
	public static void setForms() {
		cactusForms=new Image[3];
		haworthiaForms=new Image[3];
		alocasiaForms=new Image[3];
		cactusForms[0]=loadImage("images/cactus0.png");
		cactusForms[1]=loadImage("images/cactus1.png");
		cactusForms[2]=loadImage("images/cactus2.png");
		haworthiaForms[0]=loadImage("images/haworthia0.png");
		haworthiaForms[1]=loadImage("images/haworthia1.png");
		haworthiaForms[2]=loadImage("images/haworthia2.png");
		alocasiaForms[0]=loadImage("images/alocasia0.png");
		alocasiaForms[1]=loadImage("images/alocasia1.png");
		alocasiaForms[2]=loadImage("images/alocasia2.png");
	}
	//*******************************************************************************	
	
	//Wczytanie obrazka do programu
	public static Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage(); 
	}
	//*******************************************************************************	
	
	//Ustawienie kontrolera czêœci widoku
	public void setControler(Controler1 controler) {
		this.controler=controler;
	}
	//*******************************************************************************	
	
	//"Wymiana" roœlin na nowe - wszystkie roœliny ¿ywe
	public void resetPlants() {
		for(int i=0;i<20;i++) {
			isDead[i]=false;
		}
	}
	//*******************************************************************************	
	
	//KONIEC GRY - uruchamiane w sytuacji, gdy wszystkie roœliny umieraj¹
	public void gameOver() {
		if(!ended) {
		ended=true;
		panel.setVisible(false);
		market.setVisible(false);
		down.setVisible(false);
		info.setVisible(false);
		overFrame=new JFrame();
		overPanel=new JPanel();
		overPanel.setLayout(new BoxLayout(overPanel, BoxLayout.Y_AXIS));
		JButton finish=new JButton("Zakoñcz grê");
		finish.addActionListener(new FinishListener());
		JLabel over=new JLabel("KONIEC GRY");
		JLabel survived=new JLabel("Prze¿y³eœ "+String.valueOf(controler.howManyDays())+" dni");
		finish.setFont(new Font(null,0,30));
		survived.setFont(new Font(null,0,35));
		over.setFont(new Font(null,0,70));
		survived.setBorder(new EmptyBorder(0,100,20,0));
		finish.setBorder(new EmptyBorder(0,145,0,145));
		overPanel.add(over);
		overPanel.add(survived);
		overPanel.add(finish);
		overFrame.getContentPane().add(BorderLayout.CENTER,overPanel);
		

		overFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		overFrame.setSize(600,400);
		overFrame.pack();
		overFrame.setVisible(true);
		overFrame.setResizable(false);
		overFrame.setLocation(650,450);
		}
	}
	//*******************************************************************************	
	
	//Obs³uga przycisków
	class EasyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controler.easyLevel();
		}
	}
	
	class HardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controler.hardLevel();
		}
	}
	
	class WaterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controler.waterPressed();
		}
	}
	
	class WarmListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controler.warmPressed();
		}

	}
	
	class ColdListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controler.coldPressed();
		}
	}
	
	class SellListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controler.sellPressed();
		}
	}
	
	class FinishListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	//*******************************************************************************	
	
}
