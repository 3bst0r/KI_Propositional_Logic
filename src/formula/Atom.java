package formula;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 24.10.2015.
 */
public class Atom extends Sentence {

    private boolean v;
    private char name;

    public void setV(boolean v) {
        this.v = v;
    }

    public void negate() {
        v = !v;
    }

    @Override
    public boolean v() {
        return v;
    }

    @Override
    public void applyInterpretation(Map<Atom, Boolean> interpretation) {
        Boolean v = interpretation.get(this);
        if (v != null) this.v = v;
    }

    @Override
    public List<Atom> gatherAtoms(List<Atom> list) {
        list.add(this);
        return list;
    }

    @Override
    public List<Sentence> gatherLiterals(List<Sentence> list) {
        list.add(this);
        return list;
    }

    public Atom(char name) {
        this.name = name;
    }

    public Atom(boolean v, char name) {
        this.v = v;
        this.name = name;
    }

    @Override
    public Sentence removeLiteral(Sentence literal) {
        if (literal.equals(this)) return null;
        else return this;
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
