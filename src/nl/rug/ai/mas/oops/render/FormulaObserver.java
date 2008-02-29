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
package nl.rug.ai.mas.oops.render;

/*
import nl.rug.ai.mas.oops.tableau.Tableau;
import nl.rug.ai.mas.oops.tableau.TableauEvent;
import nl.rug.ai.mas.oops.tableau.TableauObserver;
import nl.rug.ai.mas.oops.tableau.NodeAddedEvent;
import nl.rug.ai.mas.oops.tableau.Node;
import nl.rug.ai.mas.oops.tableau.Label;
*/
import nl.rug.ai.mas.oops.tableau.*;

import nl.rug.ai.mas.oops.formula.Formula;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.util.HashMap;

public class FormulaObserver implements TableauObserver {
	private JFrame d_frame; // root window
	private TidyTree d_tree; // tree display
	private int d_count; // count lines
	private Font d_font;
	private HashMap<Branch, ComponentCell> d_branchMap;
	private NestedMap<Branch, Node, Integer> d_lineMap;
	private static String s_font = "/usr/share/fonts/truetype/ttf-dejavu/DejaVuSans.ttf";

	public FormulaObserver() throws IOException, FontFormatException {
		d_frame = new JFrame("Tableau Observer");
		d_tree = new TidyTree();
		d_count = 0;
		JScrollPane panel = new JScrollPane(d_tree);
		d_frame.add(panel);
		d_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d_frame.pack();
		d_frame.setSize(800, 600);
		d_frame.setVisible(true);

		File ff = new File(s_font);
		d_font = Font.createFont(Font.TRUETYPE_FONT, ff);
		d_font = d_font.deriveFont((float)12);

		d_branchMap = new HashMap<Branch, ComponentCell>();
		d_lineMap = new NestedMap<Branch, Node, Integer>();
	}

	public void update(Tableau t, TableauEvent e) {
		if (e instanceof NodeAddedEvent) {
			NodeAddedEvent event = (NodeAddedEvent)e;
			d_lineMap.put(event.getNode(), ++d_count);
			Branch b = event.getBranch();
			Formula f = event.getNode().getFormula();
			Label l = event.getNode().getLabel();

			// get HTML for formula & label
			FormulaHtml fh = new FormulaHtml();
			f.accept(fh);
			LabelHtml lh = new LabelHtml();
			l.accept(lh);
			/*
			String html = "<html>" + lh.getHtml() + 
				"&nbsp;" + fh.getHtml() + "</html>";
				*/

			JLabel fl = new JLabel("<html>" + fh.getHtml() + "</html>");
			fl.setMinimumSize(fl.getPreferredSize());
			fl.setFont(d_font);
			JLabel ll = new JLabel("<html>" +  lh.getHtml() + "</html>");
			ll.setMinimumSize(ll.getPreferredSize());
			ll.setFont(d_font);
			Justification just = event.getJustification();
			String j = "";
			if (just != null) {
				j = "<html>" + just.getRule().getHtml() + ": " +
					d_lineMap.get(just.getNode()) + "</html>";
			}
			JLabel jl = new JLabel(j);
			jl.setMinimumSize(jl.getPreferredSize());
			jl.setFont(d_font);
			JLabel cl = new JLabel(d_count + ".");
			cl.setMinimumSize(cl.getPreferredSize());
			cl.setFont(d_font);

			ComponentCell branch = d_branchMap.get(b);
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1.0;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridwidth = 4;
			c.ipadx = 8;
			branch.getComponent().add(cl, c);
			branch.getComponent().add(ll, c);
			branch.getComponent().add(fl, c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			branch.getComponent().add(jl, c);
		} else if (e instanceof BranchAddedEvent) {
			BranchAddedEvent event = (BranchAddedEvent)e;
			Branch p = event.getParent();
			Branch b = event.getAdded();
			d_lineMap.addMap(b);

			ComponentCell parent = null;
			if (p != null) {
				parent = d_branchMap.get(p);
			}

			JPanel panel = new JPanel();
			GridBagLayout layout = new GridBagLayout();
			panel.setLayout(layout);
			ComponentCell branch = d_tree.addComponent(panel, parent);
			d_branchMap.put(b, branch);
		} else if (e instanceof BranchClosedEvent) {
			BranchClosedEvent event = (BranchClosedEvent)e;
			ComponentCell branch = d_branchMap.get(event.getBranch());
			JLabel bar = new JLabel("<html>= " + 
				d_lineMap.get(event.getNode2()) + "," +
				d_lineMap.get(event.getNode1()) + "</html>");
			bar.setMinimumSize(bar.getPreferredSize());
			bar.setFont(d_font);

			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1.0;
			c.anchor = GridBagConstraints.CENTER;
			c.gridwidth = GridBagConstraints.REMAINDER;
			branch.getComponent().add(bar, c);
		} else if (e instanceof BranchOpenEvent) {
			BranchOpenEvent event = (BranchOpenEvent)e;
			ComponentCell branch = d_branchMap.get(event.getBranch());
			JLabel arrow = new JLabel("<html>&uarr;</html>");
			arrow.setMinimumSize(arrow.getPreferredSize());
			arrow.setFont(d_font);

			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1.0;
			c.anchor = GridBagConstraints.CENTER;
			c.gridwidth = GridBagConstraints.REMAINDER;
			branch.getComponent().add(arrow, c);
		} else if (e instanceof TableauFinishedEvent) {
			d_tree.revalidate();
			d_tree.repaint();
		} else if (e instanceof BranchDoneEvent) {
			BranchDoneEvent event = (BranchDoneEvent)e;
			d_lineMap.removeMap(event.getBranch());
		}
	}
}
