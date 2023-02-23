package Dijkstra;
import java.util.HashSet;

public class ASet implements ASetInterface
{
    private HashSet<VertexInterface> A;

    public ASet()
    {
        this.A = new HashSet<VertexInterface>();
    }

    public void ajouter(VertexInterface x)
    {
     
        A.add(x);
    }

    public boolean appartient(VertexInterface x)
    {
        return A.contains(x);
    }
    
}