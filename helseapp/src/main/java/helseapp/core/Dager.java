package helseapp.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Dager implements Iterable<Dag> {

    final List<Dag> Dager = new ArrayList<>();

    public Dager() {
    }

    public Dager(final double... DagerArray) {
        addDager(DagerArray);
    }

    public Dager(final Dag... Dager) {
        addDager(Dager);
    }

    public Dager(final Collection<Dag> Dager) {
        addDager(Dager);
    }

    @Override
    public Iterator<Dag> iterator() {
        return Dager.iterator();
    }

    public int getDagCount() {
        return Dager.size();
    }

    public Dag getDag(final int num) {
        return Dager.get(num);
    }

    public void setDag(final int num, final Dag Dag) {
        Dager.set(num, Dag);
    }

    public int addDag(final Dag Dag) {
        final int pos = Dager.size();
        Dager.add(Dag);
        return pos;
    }

    public final int addDager(final Collection<Dag> Dager) {
        final int pos = this.Dager.size();
        this.Dager.addAll(Dager);
        return pos;
    }

    public final int addDager(final Dag... Dager) {
        return addDager(List.of(Dager));
    }

    public final int addDager(final double... DagerArray) {
        final Collection<Dag> Dager = new ArrayList<>(DagerArray.length / 2);
        for (int i = 0; i < DagerArray.length; i += 2) {
            Dager.add(new Dag(DagerArray[i], DagerArray[i + 1]));
        }
        return addDager(Dager);
    }

    public Dag removeDag(final int num) {
        return Dager.remove(num);
    }
}
