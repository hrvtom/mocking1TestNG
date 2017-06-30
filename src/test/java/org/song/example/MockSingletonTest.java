package org.song.example;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

@PrepareForTest({ SingletonScoreGrader.class })
public class MockSingletonTest extends PowerMockTestCase {

	@Test
	public void mockSingleton() {
		final int expectedScore = 120;

		PowerMockito.mockStatic(SingletonScoreGrader.class);
		SingletonScoreGrader singletonMock = Mockito.mock(SingletonScoreGrader.class);
		Mockito.doReturn(expectedScore).when(singletonMock).getScore();

		Mockito.when(SingletonScoreGrader.instance()).thenReturn(singletonMock);

		Assert.assertEquals(SingletonScoreGrader.instance().getScore(), expectedScore);
	}

}
