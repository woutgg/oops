/**
  * This program (working title: MAS Prover) is an automated tableaux prover
  * for epistemic logic (S5n).
  * Copyright (C) 2007  Elske van der Vaart and Gert van Valkenhoef

  * This program is free software; you can redistribute it and/or modify it
  * under the terms of the GNU General Public License version 2 as published
  * by the Free Software Foundation.

  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.

  * You should have received a copy of the GNU General Public License along
  * with this program; if not, write to the Free Software Foundation, Inc.,
  * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
  */

package nl.rug.ai.mas.oops.tableau;

import java.util.*;
import nl.rug.ai.mas.oops.formula.*;

public class ModalRuleFactory {
	private ModalRuleFactory() {
	}

	public static Vector<Rule> build() {
		Vector<Rule> rules = new Vector<Rule>(12);
		rules.add(buildPosO1());
		rules.add(buildPosO2());
		rules.add(buildPosS1());
		rules.add(buildPosS2());
		rules.add(buildBNecO1());
		rules.add(buildBNecO2());
		rules.add(buildBNecS1());
		rules.add(buildBNecS2());
		rules.add(buildSNecO1());
		rules.add(buildSNecO2());
		rules.add(buildSNecS1());
		rules.add(buildSNecS2());
		return rules;
	}

	public static Rule buildPosO1() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// different agent
		Variable<Agent> j = new Variable<Agent>("j");
		AgentReference jref = new AgentReference(j);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, jref);
		Label rewriteLabel = new LabelInstance(templateLabel, nref, iref);
		Formula templateFormula = new MultiDiamond(iref, fref);
		Formula rewriteFormula = fref;
		Constraint c = new NotEqualConstraint(i, j);
		return new CreateRule("PosO1", new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula), c);
	}

	public static Rule buildPosO2() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// different agent
		Variable<Agent> j = new Variable<Agent>("j");
		AgentReference jref = new AgentReference(j);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, jref);
		Label rewriteLabel = new LabelInstance(templateLabel, nref, iref);
		Formula templateFormula = new Negation(new MultiBox(iref, fref));
		Formula rewriteFormula = new Negation(fref);
		Constraint c = new NotEqualConstraint(i, j);
		return new CreateRule("PosO2", new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula), c);
	}

	public static Rule buildPosS1() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, iref);
		Label rewriteLabel = new LabelInstance(lref, nref, iref);
		Formula templateFormula = new MultiDiamond(iref, fref);
		Formula rewriteFormula = fref;
		return new CreateRule("PosS1", new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula));
	}

	public static Rule buildPosS2() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, iref);
		Label rewriteLabel = new LabelInstance(lref, nref, iref);
		Formula templateFormula = new Negation(new MultiBox(iref, fref));
		Formula rewriteFormula = new Negation(fref);
		return new CreateRule("PosS2", new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula));
	}

	public static Rule buildBNecO1() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// different agent
		Variable<Agent> j = new Variable<Agent>("j");
		AgentReference jref = new AgentReference(j);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, jref);
		Label rewriteLabel = new LabelInstance(templateLabel, nref, iref);
		Formula templateFormula = new MultiBox(iref, fref);
		Formula rewriteFormula = fref;
		Constraint c = new NotEqualConstraint(i, j);
		return new AccessRule("BNecO1",
				new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula), c);
	}

	public static Rule buildBNecO2() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// different agent
		Variable<Agent> j = new Variable<Agent>("j");
		AgentReference jref = new AgentReference(j);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, jref);
		Label rewriteLabel = new LabelInstance(templateLabel, nref, iref);
		Formula templateFormula = new Negation(new MultiDiamond(iref, fref));
		Formula rewriteFormula = new Negation(fref);
		Constraint c = new NotEqualConstraint(i, j);
		return new AccessRule("BNecO2",
				new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula), c);
	}

	public static Rule buildBNecS1() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, iref);
		Label rewriteLabel = new LabelInstance(lref, nref, iref);
		Formula templateFormula = new MultiBox(iref, fref);
		Formula rewriteFormula = fref;
		return new AccessRule("BNecS1",
				new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula));
	}

	public static Rule buildBNecS2() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// new world
		Variable<World> n = new Variable<World>("n");
		WorldReference nref = new WorldReference(n);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, iref);
		Label rewriteLabel = new LabelInstance(lref, nref, iref);
		Formula templateFormula = new Negation(new MultiDiamond(iref, fref));
		Formula rewriteFormula = new Negation(fref);
		return new AccessRule("BNecS2",
				new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula));
	}

	public static Rule buildSNecO1() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// different agent
		Variable<Agent> j = new Variable<Agent>("j");
		AgentReference jref = new AgentReference(j);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, jref);
		Label rewriteLabel = templateLabel;
		Formula templateFormula = new MultiBox(iref, fref);
		Formula rewriteFormula = fref;
		Constraint c = new NotEqualConstraint(i, j);
		return new AccessRule("SNecO1", new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula), c);
	}

	public static Rule buildSNecO2() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// different agent
		Variable<Agent> j = new Variable<Agent>("j");
		AgentReference jref = new AgentReference(j);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, jref);
		Label rewriteLabel = templateLabel;
		Formula templateFormula = new Negation(new MultiDiamond(iref, fref));
		Formula rewriteFormula = new Negation(fref);
		Constraint c = new NotEqualConstraint(i, j);
		return new AccessRule("SNecO2", new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula), c);
	}

	public static Rule buildSNecS1() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, iref);
		Label rewriteLabel = lref; 
		Formula templateFormula = new MultiBox(iref, fref);
		Formula rewriteFormula = fref;
		return new AccessRule("SNecS1",
				new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula));
	}

	public static Rule buildSNecS2() {
		// variables occuring
		// the formula
		Variable<Formula> f = new Variable<Formula>("F");
		FormulaReference fref = new FormulaReference(f);
		// agent for which the modal operator holds
		Variable<Agent> i = new Variable<Agent>("i");
		AgentReference iref = new AgentReference(i);
		// current world
		Variable<World> k = new Variable<World>("k");
		WorldReference kref = new WorldReference(k);
		// superlabel
		Variable<Label> l = new Variable<Label>("L");
		LabelReference lref = new LabelReference(l);

		// formula f. agents i, j. worlds k, n. label l.
		Label templateLabel = new LabelInstance(lref, kref, iref);
		Label rewriteLabel = lref;
		Formula templateFormula = new Negation(new MultiDiamond(iref, fref));
		Formula rewriteFormula = new Negation(fref);
		return new AccessRule("SNecS2", 
				new Node(templateLabel, templateFormula),
				new Node(rewriteLabel, rewriteFormula));
	}
}
