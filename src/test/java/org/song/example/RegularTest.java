package org.song.example;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularTest {

	private RegularClass instance = null;

	/*
	 * The scope of Regular Mockito Mocks
	 */

	@Test
	public void mockRegularScopeTest() {
		final String expected = "Expected String";
		instance = Mockito.mock(RegularClass.class);
		Mockito.doReturn(expected).when(instance).Echo(Mockito.anyString());

		Assert.assertEquals(instance.Echo("Song"), expected);
	}

	@Test(dependsOnMethods = { "mockRegularScopeTest" })
	public void mockRegularScopeTest2() {
		final String expected = "Expected String";

		Assert.assertEquals(instance.Echo("Song"), expected);
	}

//	This experiment shows us that the scope of the mocks created by regular Mockito goes beyond the limit of the test method
//	where the mock is created, which is different from the scope of the mocks on final and static method created by PowerMockito.
}
