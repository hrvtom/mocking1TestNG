package org.song.example;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

@PrepareForTest({ AFinalClass.class })
public class DoAnswerTest extends PowerMockTestCase {

	@Test
	public void doAnswerTest() {
		final String testInput = "A test input";
		final String mockedResult = "Mocked final echo result - " + testInput;

		AFinalClass aFinalClass_mock = PowerMockito.mock(AFinalClass.class);

		final List<String> answers = new ArrayList<String>();
		final String mockIsUsed = "The mock is used to generate the result";

		Mockito.doAnswer(new Answer<String>() {
//			@Override
			// why this helps ???
			public String answer(InvocationOnMock invocation) throws Throwable {
				answers.add(mockIsUsed);
				return mockedResult;
			}
		}).when(aFinalClass_mock).echoString(testInput);

//		The "Mockito.doAnswer()" method has the same capability as "Mockito.doReturn()"
//		to specify the expected mocked result returned by a method;
//		It is more powerful because it allows us to define a true method to control the generation
//		of the mocked result and keep the calling history of the mocked method.

		// Assert the mocked result is returned from method call
		Assert.assertEquals(aFinalClass_mock.echoString(testInput), mockedResult);

		// In addition to the mocked result, we can keep additional information of
		// the calling of the mock and add complex logic in the mock
		Assert.assertEquals(answers.size(), 1);
		Assert.assertEquals(answers.get(0), mockIsUsed);
	}

}
