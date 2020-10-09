package helseapp.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Dager implements Iterable<Dag> {

    final List<Dag> dager = new ArrayList<>();

    public Dager() {
    }
    /*
    public Dager(final double... DagerArray) {
        addDager(DagerArray);
    }
    */

    public Dager(final Dag... dager) {
        addDager(dager);
    }

    public Dager(final Collection<Dag> dager) {
        addDager(dager);
    }

    @Override
    public Iterator<Dag> iterator() {
        return dager.iterator();
    }

    public int getDagCount() {
        return dager.size();
    }

    public Dag getDag(final int num) {
        return dager.get(num);
    }

    public void setDag(final int num, final Dag dag) {
        dager.set(num, dag);
    }

    public int addDag(final Dag dag) {
        final int pos = dager.size();
        dager.add(dag);
        return pos;
    }

    public final int addDager(final Collection<Dag> dager) {
        final int pos = this.dager.size();
        this.dager.addAll(dager);
        return pos;
    }

    public final int addDager(final Dag... dager) {
        return addDager(List.of(dager));
    }

    /*
    public final int addDager(final double... DagerArray) {
        final Collection<Dag> Dager = new ArrayList<>(DagerArray.length / 2);
        for (int i = 0; i < DagerArray.length; i += 2) {
            Dager.add(new Dag(DagerArray[i], DagerArray[i + 1]));
        }
        return addDager(Dager);
    }
    */

    public Dag removeDag(final int num) {
        return dager.remove(num);
    }

}
