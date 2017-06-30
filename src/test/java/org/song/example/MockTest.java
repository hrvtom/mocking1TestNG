package org.song.example;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;


@PrepareForTest({AFinalClass.class, AStaticClass.class})
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
}
