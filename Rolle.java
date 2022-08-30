public abstract class Rolle
{
    public abstract Action aktion(Action prevAction);

    public abstract boolean istWerwolf();
    public boolean istSpieler() {
        return !istWerwolf();
    }
    @Override
    public abstract String toString();
}
