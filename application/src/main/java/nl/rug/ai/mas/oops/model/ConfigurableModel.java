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

package nl.rug.ai.mas.oops.model;

import nl.rug.ai.mas.oops.formula.AgentId;

import java.util.Set;
import java.util.LinkedList;
import java.util.Vector;

public class ConfigurableModel extends KripkeModel {

	Vector<Relation> d_relationsModel;

	public enum Relation {
		TRANSITIVE, REFLEXIVE, SYMMETRIC, SERIAL;
	}

	public ConfigurableModel(Set<AgentId> agents, Vector<Relation> relationsModel) {
		super(agents);
		d_relationsModel = relationsModel;
	}

	@Override
	public ConfigurableModel newModel() {
		return new ConfigurableModel(d_agents, d_relationsModel);
	}

	/**
	 * Add a world to the model. Reflexive arrows are being added here ( if existing in the model )
	 */
	public boolean addWorld(World w) {
		if (!super.addWorld(w)) // the world already existed
		{
			return false;
		}

		if (d_relationsModel.contains(Relation.REFLEXIVE)) {
			addReflexive(w);
		}

		return true;
	}

	/**
	 * Add an arrow. This can include closure under symmetry and transitivity.
	 */
	public boolean addArrow(Arrow r1) {
		if (!super.addArrow(r1)) { // the arrow already existed
			return false;
		}

		if (d_relationsModel.contains(Relation.SYMMETRIC)) {
			addSymmetry(r1);
		}

		if (d_relationsModel.contains(Relation.TRANSITIVE)) {
			addTransitivity(r1);
		}

		return true;
	}

	/**
	 * Convenience function for adding new arrows.
	 */
	public boolean addArrow(AgentId agent, World source, World target) {
		return addArrow(new Arrow(agent, source, target));
	}

	public boolean addReflexive(World w) {
		for (AgentId agent : d_agents) {
			super.addArrow(agent, w, w);
		}

		return true;
	}

	public boolean addSymmetry(Arrow r1) {
		Arrow r2 = new Arrow(r1.getAgent(), r1.getTarget(), r1.getSource());
		super.addArrow(r2);

		return true;
	}

	public boolean addTransitivity(Arrow r1) {
		Arrow r2 = new Arrow(r1.getAgent(), r1.getTarget(), r1.getSource());
		super.addArrow(r2);

		LinkedList<Arrow> fringe = new LinkedList<Arrow>();
		fringe.add(r1);
		fringe.add(r2);

		Arrow r = null;
		while ((r = fringe.poll()) != null) {
			// get all outgoing arrows from the target
			Set<Arrow> arrows = getArrowsFrom(r.getAgent(), r.getTarget());
			// and add the merge of r and each arrow
			for (Arrow s : arrows) {
				Arrow t1 = new Arrow(r.getAgent(), r.getSource(), s.getTarget());
				if (super.addArrow(t1)) { // t1 is a new arrow
					fringe.offer(t1);
				}
			}
			// get all in going arrows to the source
			arrows = getArrowsTo(r.getAgent(), r.getSource());
			for (Arrow s : arrows) {
				Arrow t1 = new Arrow(r.getAgent(), s.getSource(), r.getTarget());
				if (super.addArrow(t1)) { // t1 is a new arrow
					fringe.offer(t1);
				}
			}
		}

		return true;
	}
	
	// Check serial, if world doesn't have outgoing arrow, make an new arrow pointing to it self. 
	public boolean checkSerial()
	{
		if(d_relationsModel.contains(Relation.SERIAL))
		{
			Set<World> worlds = getWorlds();
			Set<Arrow> arrows; 
			
			for(World world: worlds)
			{
				
				for(AgentId agent: d_agents)
				{
					arrows = getArrowsFrom(agent, world);
					
					if(arrows.isEmpty())
					{
						super.addArrow(agent, world, world);
					}
				}			
			}
		}
		
		return true;
	}
}
