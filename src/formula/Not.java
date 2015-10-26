package formula;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 24.10.2015.
 */
public class Not extends Sentence {

    private Sentence formula;

    @Override
    public boolean v() {
        return !formula.v();
    }

    @Override
    public void applyInterpretation(Map<Atom, Boolean> interpretation) {
        formula.applyInterpretation(interpretation);
    }

    @Override
    public List<Atom> gatherAtoms(List<Atom> list) {
        formula.gatherAtoms(list);
        return list;
    }

    @Override
    public List<Sentence> gatherLiterals(List<Sentence> list) {
        if (formula instanceof Atom) list.add(this);
        else formula.gatherLiterals(list);
        return list;
    }

    public Not(Sentence formula) {
        this.formula = formula;
    }

    @Override
    public String toString() {
        return "¬(" + formula.toString() + ")";
    }

    /*
     * not implemented!
     */
    @Override
    public Sentence removeLiteral(Sentence literal) {
        if (literal.equals(this))
            return null;
        else return this;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Not && (((Not) obj).formula.equals(formula)));
    }

    public Sentence getFormula() {
        return formula;
    }
}
