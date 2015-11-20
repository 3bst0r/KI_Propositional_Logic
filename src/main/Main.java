package main;

import formula.*;

import java.util.*;

/**
 * Created by Johannes on 24.10.2015.
 */
public class Main {

    private static boolean clashingLiterals(Formula l1, Formula l2) {
        if ((l1 instanceof Not && ((Not) l1).getFormula().equals(l2))
            || (l2 instanceof Not && ((Not) l2).getFormula().equals(l1)))
            return true;
        return false;
    }

    private static Formula clashClauses2(Formula clause1, Formula clause2) {
        List<Formula> literals1 = clause1.gatherLiterals(new LinkedList<Formula>());
        List<Formula> literals2 = clause2.gatherLiterals(new LinkedList<Formula>());
        for (Formula l1: literals1) {
            for (Formula l2 : literals2) {
                if (clashingLiterals(l1,l2))
                    return resolve2(clause1, clause2, l1, l2);
            }
        }
        return null;
    }

    private static Formula removeDuplicates(Formula clause) {
        List<Formula> literals = clause.gatherLiterals(new LinkedList<Formula>());
        for (Formula l : literals) System.out.print(l + " ");
        System.out.println();

        for (int i = 0; i < literals.size()-1; i++) {
            for (int j = i+1; j < literals.size(); j++) {
                Formula l1 = literals.get(i);
                if (l1.equals(literals.get(j))) {
                    System.out.println("duplicate: " + l1);
                    clause = clause.removeLiteral(l1);
                }
            }
        }
        return clause;
    }

    private static Formula resolve2(Formula clause1, Formula clause2, Formula literal1, Formula literal2) {
        Formula resolvent;
        Formula c1 = clause1.removeLiteral(literal1);
        Formula c2 = clause2.removeLiteral(literal2);
        if (c1 == null) resolvent = c2;
        else if (c2 == null) resolvent = c1;
        else resolvent = new Disjunction(c1, c2);

        resolvent = removeDuplicates(resolvent);

        return resolvent;
    }

    private static Set<Formula> resolve(Set<Formula> clause1, Set<Formula> clause2) {

        boolean clashing = false;
        for (Formula l1 : clause1)
            for (Formula l2 : clause2)
                if ((l1 instanceof Not && ((Not) l1).getFormula().equals(l2))
                        || (l2 instanceof Not && ((Not) l2).getFormula().equals(l1))) {
                    clause1.remove(l1);
                    clause2.remove(l2);
                    clashing = true;
                }
        if (clashing) {
            Set<Formula> resolvent = new HashSet<Formula>();
            resolvent.addAll(clause1);
            resolvent.addAll(clause2);
            return resolvent;
        } else return null;
    }

    /**
     * TODO implement
     * checks if a formula, given by its clauses is satisfiable using resolution
     * @param c
     * @return
     */
    public static boolean satisfiable(Set<Set<Formula>> c) {
        Set<Set<Formula>> sOld = c;
        for (;;) {
            List<Set<Formula>> clauses = new ArrayList<>(sOld);
            boolean clashingClauses = false;
            for (int i = 0; i < clauses.size() - 1; i++) {
                if (clashingClauses) break;
                for (int j = i + 1; j < clauses.size(); j++) {
                    Set<Formula> C = resolve(clauses.get(i), clauses.get(j));
                    if (C != null) {
                        clashingClauses = true;
                        if (C.isEmpty()) return false;
                        Set<Set<Formula>> sNew = new HashSet<>();
                        sNew.addAll(sOld);
                        sNew.remove(clauses.get(i));
                        sNew.remove(clauses.get(j));
                        sNew.add(C);
                        sOld = sNew;
                        break;
                    }
                }
            }
            if (!clashingClauses) return true;
        }
}

    public static void main(String[] args) {

        Predicate P = new Predicate("P",new String[]{"x","y"});
        Formula f = new Exists("x",P);
        f = new ForAll("y", f);
        System.out.println(f);

    }
}
