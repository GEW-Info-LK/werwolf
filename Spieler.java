
public class Spieler
{
    String name;
    Rolle spielrolle;
    
    public Spieler()
    {
        
    }

    public void setName(String eingabeName)
    {
        name = eingabeName;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setSpielrolle(Rolle pRolle)
    {
         spielrolle = pRolle;
    }
    
    public Rolle getRolle()
    {
        return spielrolle;
    }

    public boolean istWerwolf() {
        return spielrolle.istWerwolf();
    }

    @Override
    public String toString() {
        return name + " (" + spielrolle + ")";
    }
}
