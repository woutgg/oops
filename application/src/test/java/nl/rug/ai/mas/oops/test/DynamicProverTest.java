package nl.rug.ai.mas.oops.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import nl.rug.ai.mas.oops.ConfigurableProver;
import nl.rug.ai.mas.oops.TableauErrorException;

import org.junit.Test;

public class DynamicProverTest {
	private static String TestA1[] = {"a | ~a", "(a > b) = (~b > ~a)", "((~a > b) & (~a > ~b)) > a", "~(a & b) = (~a | ~b)", "((a > b) & (b > c)) > (a > c)", "((a | b) & (a > c) & (b > c)) > c"};
	private static String TestR1 = "(a & (a > b)) > b";
	private static String TestR2 = "(a | ~a) > #_1 (a | ~a)";
	private static String TestA2 = "(#_1 a & #_1 (a > b)) > #_1 b";
	private static String TestA3 = "#_1 a > a";
	private static String TestD = "#_1 a > %_1 a";
	private static String TestA4 = "( #_1 a ) > ( #_1 (#_1 a) )";
	private static String TestA5 = "( ~#_1 a ) > ( #_1 (~#_1 a) )";
	private static String TestB = "(a | ~a) > ( #_1 (%_1 (a | ~a)) )";
		
	/* 	K Test  */
	@Test public void testK() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.K.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertFalse(prover.provable(TestA3));
			assertFalse(prover.provable(TestD));
			assertFalse(prover.provable(TestA4));
			assertFalse(prover.provable(TestA5));
			assertFalse(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	T Test  */
	@Test public void testT() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.T.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertTrue(prover.provable(TestA3));
			assertTrue(prover.provable(TestD));
			assertFalse(prover.provable(TestA4));
			assertFalse(prover.provable(TestA5));
			assertFalse(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	D Test  */
	@Test public void testD() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.D.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertFalse(prover.provable(TestA3));
			assertTrue(prover.provable(TestD));
			assertFalse(prover.provable(TestA4));
			assertFalse(prover.provable(TestA5));
			assertFalse(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	K4 Test  */
	@Test public void testK4() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.K4.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertFalse(prover.provable(TestA3));
			assertFalse(prover.provable(TestD));
			assertTrue(prover.provable(TestA4));
			assertFalse(prover.provable(TestA5));
			assertFalse(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	K45 Test  */
	@Test public void testK45() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.K45.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertFalse(prover.provable(TestA3));
			assertFalse(prover.provable(TestD));
			assertTrue(prover.provable(TestA4));
			assertTrue(prover.provable(TestA5));
			assertTrue(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	KD45 Test  */
	@Test public void testKD45() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.KD45.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertFalse(prover.provable(TestA3));
			assertTrue(prover.provable(TestD));
			assertTrue(prover.provable(TestA4));
			assertTrue(prover.provable(TestA5));
			assertTrue(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	S4 Test  */
	@Test public void testS4() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.S4.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertTrue(prover.provable(TestA3));
			assertTrue(prover.provable(TestD));
			assertTrue(prover.provable(TestA4));
			assertFalse(prover.provable(TestA5));
			assertFalse(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	S5 Test  */
	@Test public void testS5() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.S5.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertTrue(prover.provable(TestA3));
			assertTrue(prover.provable(TestD));
			assertTrue(prover.provable(TestA4));
			assertTrue(prover.provable(TestA5));
			assertTrue(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
	/* 	B Test  */
	@Test public void testB() {
		try {
			ConfigurableProver prover = ConfigurableProver.AxiomSystem.B.buildProver();
			for (String formula : TestA1) {
				assertTrue(prover.provable(formula));
			}
			assertTrue(prover.provable(TestR1));
			assertTrue(prover.provable(TestR2));
			assertTrue(prover.provable(TestA2));			
			assertFalse(prover.provable(TestA3));
			assertFalse(prover.provable(TestD));
			assertTrue(prover.provable(TestA4));
			assertFalse(prover.provable(TestA5));
			assertTrue(prover.provable(TestB));
		} catch (TableauErrorException e) {
			fail(e.toString());
		}
	}
	
}
