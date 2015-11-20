package formula;

import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 20.11.2015.
 */
public class Predicate extends Formula {

    private String name;
    private String[] arguments;

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

    public Predicate(String name, String[] arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "(");
        for (int i = 0; i < arguments.length; i++) {
            sb.append(arguments[i]);
            if (i != arguments.length - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
