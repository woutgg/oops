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

package nl.rug.ai.mas.oops.formula;

/**
 * Holds a reference to a Variable object, used for unification of variables.
 */
public class VariableReference<T> {
	private Variable<T> d_var;

	public VariableReference(Variable<T> var) {
		d_var = var;
		d_var.add(this);
	}

	public Variable<T> get() {
		return d_var;
	}

	public void set(Variable<T> var) {
		d_var.remove(this);
		d_var = var;
		d_var.add(this);
	}

	public String toString() {
		return d_var.toString();
	}

	public boolean equals(Object o) {
		try {
			VariableReference<T> other = (VariableReference<T>)o;
			return other.d_var == d_var;
		} catch (ClassCastException e) {
		}
		return false;
	}

	public int hashCode() {
		return 31 + d_var.hashCode();
	}
}
