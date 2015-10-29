package formula;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 24.10.2015.
 */
public class Disjunction extends Formula {

    private Formula left;
    private Formula right;

    @Override
    public boolean v() {
        return left.v() || right.v();
    }

    public Disjunction(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void applyInterpretation(Map<Atom, Boolean> interpretation) {
        left.applyInterpretation(interpretation);
        right.applyInterpretation(interpretation);
    }

    @Override
    public List<Atom> gatherAtoms(List<Atom> list) {
        left.gatherAtoms(list);
        right.gatherAtoms(list);
        return list;
    }

    @Override
    public List<Formula> gatherLiterals(List<Formula> list) {
        left.gatherLiterals(list);
        right.gatherLiterals(list);
        return list;
    }


    @Override
    public Formula removeLiteral(Formula literal) {
        Formula l = left.removeLiteral(literal);
        Formula r = right.removeLiteral(literal);
        if (l == null) return r;
        else if (r == null) return l;
        else return this;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " or " + right.toString() + ")";
    }
}
