package model;

import model.entities.Block;
import model.entities.Entity;
import model.entities.Grass;
import model.entities.HummingBird;
import model.entities.Pig;
import model.entities.Pigeon;
import model.entities.Sparrow;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;


public class Level {
	private String backgroundImagePath = "res/images/background.png";
	private Image image;
	private int tabMap[][];
	private int tabMapSizeX = 47;
	private int tabMapSizeY = 22;
	private int blockSize = 26;
	private Rectangle tabCollision[][];
	private String grassImagePath = "res/images/grass.png";
	private Image grass;
	private String blockImagePath = "res/images/block.png";
	private Image block;
	private boolean levelFinish;
	private boolean isLoaded;
	private ArrayList<Entity> entities;
	
	public Level() {
	    ImageIcon ii = new ImageIcon(backgroundImagePath);
	    image = ii.getImage();
	    
    }
	
	public Level(String fichierMapPath) {
		ImageIcon ii = new ImageIcon(backgroundImagePath);
	    image = ii.getImage();
	    ImageIcon gr = new ImageIcon(grassImagePath);
	    grass = gr.getImage();
	    ImageIcon bl = new ImageIcon(blockImagePath);
	    block = bl.getImage();
	   
	    
	    entities = new ArrayList<Entity>();
	    
	    try
	    {
			FileInputStream ips=new FileInputStream(fichierMapPath); 
			isLoaded = true;
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			line = br.readLine();
			while(!line.equals("Map")) {
				int X, Y;
				/*if(line.equals("Pig")) {
					line = br.readLine();
					X = Integer.parseInt(line);
					line = br.readLine();
					Y = Integer.parseInt(line);
					entities.add(new Pig(X,Y));
				}*/
				if(line.equals("Pigeon")) {
					entities.add(new Pigeon());
				}
				if(line.equals("Humming Bird")) {
					entities.add(new HummingBird());
				}
				if(line.equals("Sparrow")) {
					entities.add(new Sparrow());
				}
				line = br.readLine();
			}
			
			tabMap = new int[tabMapSizeY][tabMapSizeX];
			
			for(int i=0;i<tabMapSizeY;i++)
			{	
				line = br.readLine();
				
				for(int j=0; j<tabMapSizeX;j++)
				{
					
					char car = line.charAt(j);	
					String st = String.valueOf(car);
					tabMap[i][j]= Integer.parseInt(st);
					if (tabMap[i][j] == 3) {
						entities.add(new Pig(j*blockSize,i*blockSize));
						tabMap[i][j] = 0;
					}
					if(tabMap[i][j] == 2)
						entities.add(new Block(j*blockSize,i*blockSize,blockSize,blockSize));
					if(tabMap[i][j] == 1)
						entities.add(new Grass(j*blockSize,i*blockSize,blockSize,blockSize));
				}
			}		
	    }
	    // Si jamais on tente de créer un niveau qui n'a aucun fichier texte correspondant
		catch (Exception e)
		{
			//System.out.println(e.toString());
			isLoaded = false;
		}   
	}
	public Image getImage() {
        return image;
    }
	public Image getGrass(){
		return grass;
	}
	public Image getBlock(){
		return block;
	}
	public int[][] getTabMap(){
		return tabMap;
	}
	public int getTabMapSizeX(){
		return tabMapSizeX;
	}
	public int getTabMapSizeY(){
		return tabMapSizeY;
	}	
	public int getBlockSize(){
		return blockSize;
	}
	public ArrayList<Entity> getEntityList() {
		return entities;
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public void setTabMap(int[][] tabMap) {
		this.tabMap = tabMap;
	}
}