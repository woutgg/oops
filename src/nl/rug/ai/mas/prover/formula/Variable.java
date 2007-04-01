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

package nl.rug.ai.mas.prover.formula;

import java.util.*;

public class Variable extends Formula {
	private String d_name;
	private Vector<VariableReference> d_refs;
	
	public Variable(String name) {
		d_name = name;
		d_refs = new Vector<VariableReference>();
	}

	public void add(VariableReference r) {
		d_refs.add(r);
	}

	public void merge(Variable other) {
		for (VariableReference r : other.d_refs) {
			r.set(this);
			d_refs.add(r);
		}
		other.d_refs.clear();
	}

	public String getName() {
		return d_name;
	}

	public String toString() {
		return d_name;
	}

	public boolean equals(Object other) {
		return other == this;
	}

	public Substitution match(Formula f) {
		Substitution s = new Substitution();
		s.put(this, f);
		return s;
	}
}