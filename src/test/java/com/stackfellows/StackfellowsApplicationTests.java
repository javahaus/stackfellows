package com.stackfellows;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Comment;
import com.stackfellows.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StackfellowsApplicationTests {

	@Test
	void testPostCanBeInstantiated() {
		Post sut = new Post();
		assertNotNull(sut);
	}
	@Test
	void testPostFirstConstructor() {
		String expectedTitle = "My expected title";
		String expectedBody = "My expected body";
		AppUser appUser = new AppUser();
		Post sut = new Post(expectedTitle, expectedBody, appUser);
		assertEquals(expectedTitle, sut.getTitle());
		assertEquals(expectedBody, sut.getBody());
		assertEquals(appUser, sut.getAppUser());
	}

	@Test
	void testPostSecondConstructor() {
		String expectedTitle = "My expected title";
		String expectedBody = "My expected body";
		boolean expectedAnswered = true;
		int expectedVotes = 3000;
		AppUser appUser = new AppUser();
		Post sut = new Post(expectedTitle, expectedBody, expectedAnswered, expectedVotes, appUser);
		assertEquals(expectedTitle, sut.getTitle());
		assertEquals(expectedBody, sut.getBody());
		assertEquals(expectedAnswered, sut.isAnswered());
		assertEquals(expectedVotes, sut.getVotes());
		assertEquals(appUser, sut.getAppUser());
	}

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

	@Test
	void testAppUserCanBeInstantiated(){
		AppUser sut = new AppUser();
		assertTrue(sut != null);
	}

	@Test
	void testAppUserFirstConstructor() {
		String expectedUsername = "testUser";
		String expectedPassword = "12345";
		String expectedFirstName = "firstNameTest";
		String expectedLastName = "lastNameTest";
		String expectedEmail = "Email@domain.net";
		Boolean expectedIsAlum = true;
		String expectedBio = "bioTest";
		String expectedRole = "USERTEST";

		AppUser sut = new AppUser(
				expectedUsername,
				expectedPassword,
				expectedFirstName,
				expectedLastName,
				expectedEmail,
				expectedIsAlum,
				expectedBio,
				expectedRole
		);

		String actualUsername = sut.getUsername();
		String actualFirstName = sut.getFirstName();
		String actualLastName = sut.getLastName();
		String actualEmail = sut.getEmail();
		Boolean actualIsAlum = sut.getIsAlum();
		String actualBio = sut.getBio();
		String actualRole = sut.getRole();

		assertEquals(expectedUsername, actualUsername, "username should be expectedUsername");
		assertEquals(expectedFirstName, actualFirstName, "firstName should be expectedFisrtName");
		assertEquals(expectedLastName, actualLastName, "lastname should be expectedLastName");
		assertEquals(expectedEmail, actualEmail, "email should be expectedEmail");
		assertTrue(actualIsAlum, "isalum should be true");
		assertEquals(expectedBio, actualBio, "actual bio should be expectedBio");
		assertEquals(expectedRole, actualRole, "role should be expectedRole");
	}

	@Test
	void testAppUserSecondConstructor() {
		String expectedUsername = "testUser";
		String expectedPassword = "12345";
		String expectedFirstName = "firstNameTest";
		String expectedLastName = "lastNameTest";
		String expectedEmail = "Email@domain.net";
		Boolean expectedIsAlum = true;
		String expectedBio = "bioTest";


		AppUser sut = new AppUser(
				expectedUsername,
				expectedPassword,
				expectedFirstName,
				expectedLastName,
				expectedEmail,
				expectedIsAlum,
				expectedBio

		);

		String actualUsername = sut.getUsername();
		String actualFirstName = sut.getFirstName();
		String actualLastName = sut.getLastName();
		String actualEmail = sut.getEmail();
		Boolean actualIsAlum = sut.getIsAlum();
		String actualBio = sut.getBio();


		assertEquals(expectedUsername, actualUsername, "username should be expectedUsername");
		assertEquals(expectedFirstName, actualFirstName, "firstName should be expectedFisrtName");
		assertEquals(expectedLastName, actualLastName, "lastname should be expectedLastName");
		assertEquals(expectedEmail, actualEmail, "email should be expectedEmail");
		assertTrue(actualIsAlum, "isalum should be true");
		assertEquals(expectedBio, actualBio, "actual bio should be expectedBio");

	}
}
