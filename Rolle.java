import java.util.*;
public abstract class Rolle
{
    public abstract boolean istWerwolf();
    public boolean istSpieler() {
        return !istWerwolf();
    }
    @Override
    public abstract String toString();
}
