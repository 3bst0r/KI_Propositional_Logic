package formula;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 24.10.2015.
 */
public class Iff extends Sentence {

    private Sentence left;
    private Sentence right;

    @Override
    public boolean v() {
        return (new Implication(left, right)).v() && (new Implication(right, left).v());
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
    public List<Sentence> gatherLiterals(List<Sentence> list) {
        left.gatherLiterals(list);
        right.gatherLiterals(list);
        return list;
    }

    /*
     * not implemented!
     */
    @Override
    public Sentence removeLiteral(Sentence literal) {
        return null;
    }

    public Iff(Sentence left, Sentence right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " iff " + right.toString() + ")";
    }

}
