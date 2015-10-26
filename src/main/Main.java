package main;

import formula.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Johannes on 24.10.2015.
 */
public class Main {

    private static boolean clashingLiterals(Sentence l1, Sentence l2) {
        if ((l1 instanceof Not && ((Not) l1).getFormula().equals(l2))
            || (l2 instanceof Not && ((Not) l2).getFormula().equals(l1)))
            return true;
        return false;
    }

    private static Sentence clashClauses(Sentence clause1, Sentence clause2) {
        List<Sentence> literals1 = clause1.gatherLiterals(new LinkedList<Sentence>());
        List<Sentence> literals2 = clause2.gatherLiterals(new LinkedList<Sentence>());
        for (Sentence l1: literals1) {
            for (Sentence l2 : literals2) {
                if (clashingLiterals(l1,l2))
                    return resolve(clause1, clause2, l1,l2);
            }
        }
        return null;
    }

    private static Sentence removeDuplicates(Sentence clause) {
        List<Sentence> literals = clause.gatherLiterals(new LinkedList<Sentence>());
        for (Sentence l : literals) System.out.print(l + " ");
        System.out.println();

        for (int i = 0; i < literals.size()-1; i++) {
            for (int j = i+1; j < literals.size(); j++) {
                Sentence l1 = literals.get(i);
                if (l1.equals(literals.get(j))) {
                    System.out.println("duplicate: " + l1);
                    clause = clause.removeLiteral(l1);
                }
            }
        }
        return clause;
    }

    private static Sentence resolve(Sentence clause1, Sentence clause2, Sentence literal1, Sentence literal2) {
        Sentence resolvent;
        Sentence c1 = clause1.removeLiteral(literal1);
        Sentence c2 = clause2.removeLiteral(literal2);
        if (c1 == null) resolvent = c2;
        else if (c2 == null) resolvent = c1;
        else resolvent = new Disjunction(c1, c2);

        resolvent = removeDuplicates(resolvent);

        return resolvent;
    }

    /**
     * TODO implement
     * checks if a formula, given by its clauses is satisfiable using resolution
     * @param clauses
     * @return
     */
    public static boolean satisfiable(List<Sentence> clauses) {
        return false;
    }

    public static void main(String[] args) {
        Atom p = new Atom('p');
        Atom q = new Atom('q');
        List<Atom> list = new ArrayList<Atom>();
        list.add(p);
        list.add(q);

        Sentence formula = new Iff(new Implication(new Not(p), q), new Disjunction(p, q));
        System.out.println("A = " + formula);

        p.setV(true);
        q.setV(true);
        System.out.println("v(p) = true and v(q) = true -> v(A) = " + formula.v());
        p.setV(false);
        q.setV(false);
        System.out.println("v(p) = false and v(q) = false -> v(A) = " + formula.v());

        System.out.println(formula.toString() + " is a tautology: " + formula.isTautology(list));
        System.out.println(formula.toString() + " is a tautology: " + formula.isTautology());

        /* resolution */
        Sentence clause1 = new Disjunction((new Disjunction(p, new Not(q))),new Not(q));
        Sentence clause2 = new Disjunction(p, q);

        System.out.println(clause1.toString() + " becomes " + clause1.removeLiteral(new Not(q)).toString());
        clause1 = removeDuplicates(clause1);
        System.out.println("removed duplicates. " + clause1.toString());

    }
}
