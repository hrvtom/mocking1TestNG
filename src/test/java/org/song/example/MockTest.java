package org.song.example;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

//The test class needs to extend the "PowerMockTestCase" class. According to the PowerMockito documentations,
//extending the "PowerMockTestCase" class is just one of the options to make the test class to work,
//but it also mentioned that extending the "PowerMockTestCase" class is the "safe" option
@PrepareForTest({ AFinalClass.class, AStaticClass.class })
public class MockTest extends PowerMockTestCase {

	@Test
	public void mockFinalClassTest() {
		AFinalClass tested = PowerMockito.mock(AFinalClass.class);

		final String testInput = "A test input";
		final String mockedResult = "Mocked final echo result - " + testInput;
		Mockito.when(tested.echoString(testInput)).thenReturn(mockedResult);

		// Assert the mocked result is returned from method call
		Assert.assertEquals(tested.echoString(testInput), mockedResult);
	}

	@Test
	public void mockStaticClassTest() {
		PowerMockito.mockStatic(AStaticClass.class);

		final String testInput = "A test input";
		final String mockedResult = "Mocked static echo result - " + testInput;
		Mockito.when(AStaticClass.echoString(testInput)).thenReturn(mockedResult);

		// Assert the mocked result is returned from method call
		Assert.assertEquals(AStaticClass.echoString(testInput), mockedResult);
	}

//	In the test programs, it is not uncommon that some test cases have final or static methods to mock,
//	while the others do not. It is important that we do not extend the "PowerMockTestCase" class
//	if the test cases do not have final or static methods to mock. TestNG will use different object factory
//	to create the test case instances in the two cases. If we extend the "PowerMockTestCase" class
//	when there is no final nor static methods to work with, the unit tests will not run consistently under Surefire in Maven

	/*
	 * The scope of the Final and Static Mocks
	 */
	private AFinalClass aFinalClass_mock = null;

	@Test
	public void mockFinalClassScopeTest() {
		final String testInput = "A test input";
		final String mockedResult = "Mocked final echo result - " + testInput;

		aFinalClass_mock = PowerMockito.mock(AFinalClass.class);
		Mockito.when(aFinalClass_mock.echoString(testInput)).thenReturn(mockedResult);

		// Assert the mocked result is returned from method call
		Assert.assertEquals(aFinalClass_mock.echoString(testInput), mockedResult);
	}

	@Test(dependsOnMethods = { "mockFinalClassScopeTest" })
	public void mockFinalClassScopeTest_1() {
		final String testInput = "A test input";
		final String mockedResult = "Mocked final echo result - " + testInput;

		// Assert the mocked result is returned from method call
		Assert.assertEquals(aFinalClass_mock.echoString(testInput), mockedResult);
	}

	@Test
	public void mockStaticClassScopeTest() {
		PowerMockito.mockStatic(AStaticClass.class);

		final String testInput = "A test input";
		final String mockedResult = "Mocked static echo result - " + testInput;
		Mockito.when(AStaticClass.echoString(testInput)).thenReturn(mockedResult);

		// Assert the mocked result is returned from method call
		Assert.assertEquals(AStaticClass.echoString(testInput), mockedResult);
	}

	@Test(dependsOnMethods = { "mockStaticClassScopeTest" })
	public void mockStaticClassScopeTest_1() {
		final String testInput = "A test input";
		final String mockedResult = "Mocked static echo result - " + testInput;

		// Assert the mocked result is returned from method call
		Assert.assertEquals(AStaticClass.echoString(testInput), mockedResult);
	}

//    Both the "mockFinalClassTest_1()" and "ockStaticClassTest_1()" methods failed on the assertion;
//    This is because the scope of the mocks is limited to the test method where they are specified. 

	/*
	 * The scope of Regular Mockito Mocks
	 */

//	If a method is neither final nor static, we can simply use Mockito to mock it. The scope of the mock is different
//	from the mocks for final and static methods. Let us take a look at the following example.
//	see RegularTest.java
}
