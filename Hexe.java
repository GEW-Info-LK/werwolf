
public class Hexe extends Dorfbewohner
{
    @Override
    public Action aktion(Action prevAction) {
        // TODO
        return new Action().combine(prevAction);
    }

    @Override
    public String toString() {
        return "Hexe";
    }
}
