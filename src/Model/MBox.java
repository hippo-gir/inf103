package Model;

public class MBox 
{
    private int i;
    private int j;
    private String type;

    public MBox(int i, int j, String type)
    {
        this.i = i;
        this.j = j;
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }
}
