package formula;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Created by Johannes on 24.10.2015.
 */
public abstract class Formula {

    public abstract boolean v();

    public abstract void applyInterpretation(Map<Atom, Boolean> interpretation);

    public abstract List<Atom> gatherAtoms(List<Atom> list);

    public abstract List<Formula> gatherLiterals(List<Formula> list);


    /**
     * removes a literal from a disjunctive clause
     * @param literal to be removed
     * @return the new disjunctive clause, null if empty
     */
    public abstract Formula removeLiteral(Formula literal);

    public boolean isTautology(List<Atom> atoms) {
        for (Atom p : atoms) p.setV(false);

        int n = (int) Math.pow(2, atoms.size());
        for (int i = 0; i < n; i++) {
            int z = n / 2;
            for (Atom a : atoms) {
                if (i % z == 0) a.negate();
                z /= 2;
            }
            if (!this.v()) return false;
        }
        return true;
    }

    public boolean isTautology() {
        List<Atom> atoms = gatherAtoms(new LinkedList<Atom>());
        for (Atom p : atoms) p.setV(false);

        int n = (int) Math.pow(2, atoms.size());
        for (int i = 0; i < n; i++) {
            int z = n / 2;
            for (Atom a : atoms) {
                if (i % z == 0) a.negate();
                z /= 2;
            }
            if (!this.v()) return false;
        }
        return true;
    }

    /*private static class FormularFactory {
        private static Iff iff;
        private static Not not;
        private static Conjunction and;
        private static Disjunction or;
        private static Implication implies;

        public Iff iff(PropositionalFormula,PropositionalFormula) {
            if (iff == null) iff = new Iff
        }
    }

    public FormularFactory getFactory() {

    }*/

}




