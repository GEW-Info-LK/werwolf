import java.util.Objects;

public class Spieler
{
    String name;
    int id;
    boolean lebt = true;
    Rolle spielrolle;
    
    public Spieler(String name, int id) {
        this.name = name;
        this.id = id;
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
        return name;
    }

    public String withRole() {
        return name + " (" + spielrolle + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spieler spieler = (Spieler) o;
        return id == spieler.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean lebt() {
        return lebt;
	}

    public void stirb() {
        if (!lebt) throw new IllegalStateException("toter wurde getötet");
        lebt = false;
    }
}
