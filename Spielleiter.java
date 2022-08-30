import java.util.*;
public class Spielleiter
{
    private Spieler [] spielerarray;
    int anzahlWerwolf;
    int anzahlDorf;
    Rolle [] rollen;
    List<Spieler> tode;
    boolean LebenstrankVorhanden = true;
    boolean ToetungstrankVorhanden = true;

    public Spielleiter()
    {
        
    }

    public void spielen()
    {
        spielErzeugen();
        rollenZuweisen();
        rolleWissen();
        while((anzahlWerwolf > anzahlDorf) || (anzahlWerwolf != 0))
        {
            nachtphase();
            tagphase();
        }
        ergebnis();
    }

    public void spielErzeugen()
    {
        spielerarray = new Spieler[8];
        rollen = new Rolle[8];
        anzahlWerwolf = 2;
        anzahlDorf = 6;
        for(int i=0; i<8; i++)
        {
            var name = Prompts.next("Geben Sie den Namen des "+(i+1)+". Spielers ein.");
            spielerarray[i] = new Spieler();
            spielerarray[i].setName(name);
        }
        rollen[0] = new Werwolf();
        rollen[1] = new Werwolf();
        rollen[2] = new Seher();
        rollen[3] = new Hexe();
        rollen[4] = new Dorfbewohner();
        rollen[5] = new Dorfbewohner();
        rollen[6] = new Dorfbewohner();
        rollen[7] = new Dorfbewohner();
    }

    public void nachtphase()
    {
        einschlafen();
        System.out.println();
        werwoelfeErwachen();
        seherErwacht();
        hexeErwacht();
    }
    
    public void tagphase()
    {
        dorfErwacht();
        opfer();
        diskussion();
        anklagen();
        abstimmen();
        erhaengen();
    }
    
    public void rollenZuweisen()
    {
        Random rand = new Random();
        for(int i = 0; i<8; i++)
        {
            int zufall = rand.nextInt(8);
            if(spielerarray[zufall].getRolle() == null)
            {
                spielerarray[zufall].setSpielrolle(rollen[i]);
            }
            else
            {
                i--;
            }
        }
    }
    
    public void rolleWissen()
    {
        System.out.println("Alle schließen die Augen.");
        Prompts.warte();
        for(int i=0; i<8; i++)
        {
            System.out.println("Es darf "+spielerarray[i].getName()+" die Augen öffnen.");
            System.out.println("Du bist "+spielerarray[i].getRolle()+".");
            Prompts.warte();
        }
    }
    
    public void einschlafen()
    {
        System.out.println("Das Dorf schläft ein.");
    }
    
    public void werwoelfeErwachen()
    {
        System.out.println("Die Werwölfe Erwachen.");
        // phase = wach
        System.out.println("Die Werwölfe suchen sich ein Ziel");
           for(int x=0; x<8; x++)
        {
            if(spielerarray[x].istWerwolf())
            {
                System.out.println("Der " +(x+1)+ " Spieler heist");
                System.out.println(spielerarray[x].getName());
                System.out.println("und ist WERWOLF.");
            } else {
                System.out.println("Der " +(x+1)+ " Spieler heisst.");
                System.out.println(spielerarray[x].getName());
            }
        }
    }
    public void seherErwacht()
    {
        System.out.println("Der Seher erwacht.");
    }
    
   
    public void hexeErwacht()
    {
        Spieler hexe = null;
        for(int i = 0; i<8; i++)
        {
            if(spielerarray[i].getRolle() instanceof Hexe)
            {
                hexe = spielerarray[i];
            }
        }
        if (hexe == null) return;

        if (LebenstrankVorhanden) {
            System.out.println(hexe + " erwacht. Das Opfer ist " + tode.get(0) + ". Möchtest du es heilen?");
            String antwort = Prompts.next();
            if (antwort.equals("Ja")) {
                tode.clear();
                LebenstrankVorhanden = false;
            }
        }

        if (ToetungstrankVorhanden) {
            System.out.println ("Möchtest du eine andere Person umbringen und wenn ja, wen?");
            if (Prompts.bool())
            {
                // TODO: tode.add(...)
                ToetungstrankVorhanden = false;
            }
            
        }
    }
    public void dorfErwacht()
    {
        System.out.println("Das Dorf erwacht.");
    }
    
    public void opfer()
    {
        System.out.println("Das Opfer ist ");
    }
    
    public void diskussion()
    {
        System.out.println("Diskutieren sie nun, wen sie an disem Morgen gerne exekutieren würden.");
    }
    
    public void anklagen()
    {
        System.out.println("Nun können die Dordbewohner jemanden anklagen.");
    }
    
    public void abstimmen()
    {
        System.out.println("Die Abstimmung beginnt nun. Zeigen sie bei 3 auf die Person die du rausvoten willst. 1!    2!!    3!!!");
        // & toterSpieler =  Spieler (der gevotet wurde)
        //hallo
    }
    
    public void erhaengen()
    {
        //status von toterSpieler = tot & toterSpieler = null 
    }
    
    public void ergebnis()
    {
        if(anzahlWerwolf > anzahlDorf)
        {
            System.out.println("Die Werwölfe haben triumphiert.");
            
        }   
        if (anzahlWerwolf == 0)
        {
            System.out.println("Das Dorf hat erfolgreich alle Werwölfe exekutiert."); 
            
        }
        System.out.println("Das Spiel ist vorbei. Bis zum nächsten mal!!!");
    }
}
