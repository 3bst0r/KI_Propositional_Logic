package formula;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 20.11.2015.
 */
public class ForAll extends Quantifier {

    @Override
    public boolean v() {
        return false;
    }

    @Override
    public void applyInterpretation(Map<Atom, Boolean> interpretation) {

    }

    @Override
    public List<Atom> gatherAtoms(List<Atom> list) {
        return null;
    }

    @Override
    public List<Formula> gatherLiterals(List<Formula> list) {
        return null;
    }

    @Override
    public Formula removeLiteral(Formula literal) {
        return null;
    }

    public ForAll(String boundVariable, Formula formula) {
        super(boundVariable, formula);
    }

    @Override
    public String toString() {
        return "\u2200" + boundVariable + " " + formula.toString();
    }

}
