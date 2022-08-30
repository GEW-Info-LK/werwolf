import java.util.*;
public class Spielleiter
{
    private Spieler [] spielerarray;
    Scanner scan = new Scanner(System.in);
    int anzahlWerwolf;
    int anzahlDorf;
    Rolle [] rollen;
    Spieler toterSpieler;
    Spieler werwolfOpfer;
    int anzahlLebenTrank = 1; 
    int anzahlToetenTrank = 1; 
    String Opfer; 
    String Opfer2; 
   
    public Spielleiter()
    {
        
    }
    
    public void spielErzeugen()
    {
        spielerarray = new Spieler[8];
        rollen = new Rolle[8];
        anzahlWerwolf = 2;
        anzahlDorf = 6;
        for(int i=0; i<8; i++)
        {
            System.out.println("Geben Sie den Namen des "+(i+1)+". Spielers ein.");
            String name = scan.next();
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
    
    public void nachtphase()
    {
        einschlafen();
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
        scan.next();
        for(int i=0; i<8; i++)
        {
            System.out.println("Es darf "+spielerarray[i].getName()+" die Augen öffnen.");
            System.out.println("Du bist "+spielerarray[i].getRolle().getRollenName()+".");
            scan.next();
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
            System.out.println("Der " +(x+1)+ " Spieler heisst.");
            System.out.println(spielerarray[x].getName());
            
        }
       werwoelfetoeten();
    }
    public void werwoelfetoeten(int spielerzahl)
    {
        werwolfOpfer = spielerarray[spielerzahl];
        
    }
    public void seherErwacht()
    {
        System.out.println("Der Seher erwacht.");
    }
    
   
    public void hexeErwacht()
    {
        /*Spieler hexe;
        for(int i = 0; i<8; i++)
        {
            if(spielerarray[i].getRolle().getRollenName().equals("Hexe"))
            {
                hexe = spielerarray[i];
            }
        }*/
        if (anzahlLebenTrank > 0 || anzahlToetenTrank > 0) 
        {
            System.out.println ("Die Hexe erwacht. Dies ist das Opfer. Möchtest du es heilen?");
            Scanner scan = new Scanner(System.in); 
            String antwort = scan.next(); 
            if (antwort.equals("Ja")) 
            {
                Opfer = "niemand";
                anzahlLebenTrank --; 
            }
            else if (antwort.equals("Nein"))
            {
                anzahlLebenTrank = 1; 
            }
            System.out.println ("Möchtest du eine andere Person umbringen und wenn ja, wen?");
            String antwort2 = scan.next(); 
            if (antwort2.equals("Ja"))
            { 
                new Scanner (System.in); 
                Opfer2 = scan.next();
                anzahlToetenTrank--; 
            }
            else if (antwort.equals("Nein"))
            {
                anzahlToetenTrank = 1; 
            }
        }
        else if (anzahlLebenTrank == 0 && anzahlToetenTrank == 0) 
        {
            System.out.println ("Da alle Tränke der Hexe verbraucht sind, erwacht die Hexe nicht mehr.");
            System.out.println ("Es ist noch " +anzahlToetenTrank+ " Trank zum Töten verfügbar, und noch " +anzahlLebenTrank+ " Trank zum Wiederbeleben verfügbar.");
            
        }
    }
    public void dorfErwacht()
    {
        System.out.println("Das Dorf erwacht.");
    }
    
    public void opfer()
    {
        System.out.println("die Opfer sind Ole und gestorben sind:");
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
