
public class Dorfbewohner extends Rolle
{
    @Override
    public Action aktion(Action prevAction) {
        return new Action();
    }

    public boolean istWerwolf() {
        return false;
    }

    @Override
    public String toString() {
        return "Dorfbewohner";
    }
}
