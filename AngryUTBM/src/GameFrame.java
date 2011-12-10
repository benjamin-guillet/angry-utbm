import java.util.ArrayList;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private GameView angryView;
	private GameViewMenu angryViewMenu;
	
	public GameFrame(GameController controller, ArrayList<Entity> entities) {
		angryViewMenu = new GameViewMenu(controller);
		angryView = new GameView(controller, entities);//taille : 794 par 572
		
		// Pour activer ou non le menu, il faut mettre menuOn a true ou false, et interchanger les 2 this.add(...)
		//this.add(angryViewMenu);
		this.add(angryView);
		
		
		this.setTitle("AngryUTBM");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
    }

	public GameView getAngryView() {
		return angryView;
	}
	
	public GameViewMenu getAngryViewMenu()
	{
		return angryViewMenu;
	}
	
	public void setMenu()
	{
		this.setContentPane(angryViewMenu);
		this.setVisible(true);
	}
	
	public void setGame()
	{
		this.setContentPane(angryView);
		this.setVisible(true);
	}
}
