package Dijkstra;
import java.util.Hashtable;

public class Previous implements PreviousInterface
{
    private Hashtable<VertexInterface, VertexInterface> p;

    public Previous()
    {
        this.p = new Hashtable<VertexInterface, VertexInterface>();
    }   

    public void set(VertexInterface i, VertexInterface j) //set j comme le pere de i
    {
        p.put(i, j);
    }

    public VertexInterface get(VertexInterface x) 
    {
       VertexInterface pere = p.get(x);
       return pere;
    }
    
}
