package com.stackfellows;

import com.stackfellows.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StackfellowsApplicationTests {

	@Test
	void testPlaceholder() {
	}
	@Test
	void testCommentConstructor() {
		String expectedResult = "Comment title.";
		Comment sut = new Comment();
		sut.setBody(expectedResult);
		String actualResult = sut.getBody();
		assertEquals(expectedResult, actualResult, "Comment should be GET-SET-able.");
	}

}
