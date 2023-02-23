package Labyrinthe;
import Dijkstra.*;


public abstract class MBox 
	implements VertexInterface
{
	private int x;
	private int y;

	public MBox(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean appartientA(ASetInterface a)
	{
		return a.appartient(this);
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public String getLabel()
	{
		return "("+ x + "," + y + ")";
	}

	public abstract String getType();
}
