import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Spielleiter
{
    Spieler [] spieler;
    int anzahlWerwolf;
    int anzahlDorf;
    List<Spieler> tode;
    boolean LebenstrankVorhanden = true;
    boolean ToetungstrankVorhanden = true;

    private static final Spielleiter instance = new Spielleiter();
    private Spielleiter() { }

    static public Spielleiter get() {
        return instance;
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
        spieler = new Spieler[8];
        anzahlWerwolf = 2;
        anzahlDorf = 6;
        for(int i=0; i<8; i++)
        {
            var name = Prompts.next("Geben Sie den Namen des "+(i+1)+". Spielers ein.");
            spieler[i] = new Spieler(name, i);
        }
    }

    public void rollenZuweisen()
    {
        var rollen = new ArrayList<>(List.of(
                new Werwolf(),
                new Werwolf(),
                new Seher(),
                new Hexe(),
                new Dorfbewohner(),
                new Dorfbewohner(),
                new Dorfbewohner(),
                new Dorfbewohner()
        ));
        Collections.shuffle(rollen);
        IntStream.range(0, 8).forEach(i -> spieler[i].setSpielrolle(rollen.get(i)));
    }

    public void rolleWissen()
    {
        System.out.println("Alle schließen die Augen.");
        Prompts.warte();
        for(int i=0; i<8; i++)
        {
            System.out.println("Es darf "+ spieler[i].getName()+" die Augen öffnen.");
            System.out.println("Du bist "+ spieler[i].getRolle()+".");
            Prompts.warte();
        }
    }

    public void nachtphase()
    {
        tode = new ArrayList<>();

        einschlafen();
        System.out.println();

//        werwoelfeErwachen();
//        seherErwacht();
//        hexeErwacht();

        erwachen(Werwolf.class);
        erwachen(Seher.class);
        erwachen(Hexe.class);
    }

    private void erwachen(Class<? extends Rolle> rolle) {
        var action = new Action();
        for (int i = 0; i < 8; i++) {
            if (rolle.isInstance(spieler[i].getRolle())) {
                System.out.println(spieler[i].name + ", du bist dran.");
                action = spieler[i].getRolle().aktion(action);
            }
        }
        action.computeVoteKill();
        action.playerExec(tode);
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
            if(spieler[x].istWerwolf())
            {
                System.out.println("Der " +(x+1)+ " Spieler heist");
                System.out.println(spieler[x].getName());
                System.out.println("und ist WERWOLF.");
            } else {
                System.out.println("Der " +(x+1)+ " Spieler heisst.");
                System.out.println(spieler[x].getName());
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
            if(spieler[i].getRolle() instanceof Hexe)
            {
                hexe = spieler[i];
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
        if (tode.size() == 0) System.out.println("Es gibt in dieser Nacht kein Opfer.");
        else if (tode.size() == 1) System.out.println(tode.get(0).name + " ist tot.");
        else System.out.println(
            "Ganze "
            + tode.size()
            + " Mitglieder des Dorfes sind tot: "
            + tode.stream().map(Spieler::getName).collect(Collectors.joining(", "))
        );

        for (Spieler opfer: tode) {
            spieler[opfer.id].stirb();
        }

        tode.clear();
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
