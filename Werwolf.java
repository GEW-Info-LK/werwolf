import java.util.Arrays;
import java.util.stream.Collectors;

public class Werwolf extends Rolle
{
    @Override
    public Action aktion(Action prevAction) {
        var killable = Arrays.stream(Spielleiter.get().spieler)
                .filter(s -> !s.istWerwolf())
                .filter(Spieler::lebt)
                .map(Spieler::getName)
                .collect(Collectors.joining(", "));
        return new Action().voteKill(Prompts.dorfbewohner("Wen möchtest du töten? (" + killable + ")")).combine(prevAction);
    }

    @Override
    public boolean istWerwolf() {
        return true;
    }

    @Override
    public String toString() {
        return "Werwolf";
    }
}
