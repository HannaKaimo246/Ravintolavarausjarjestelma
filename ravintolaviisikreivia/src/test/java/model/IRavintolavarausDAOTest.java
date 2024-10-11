package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class IRavintolavarausDAOTest {

	public abstract IRavintolavarausDAO createInstance();
	private static Palaute v;


	@Test
	public final void luoPalaute_True() {
		IRavintolavarausDAO instance = createInstance();
		assertTrue(instance.luoPalaute(v));
	}

	@Test
	public final void testMyMethod_False() {
		IRavintolavarausDAO instance = createInstance();
		assertFalse(instance.luoPalaute(v));
	}


}
