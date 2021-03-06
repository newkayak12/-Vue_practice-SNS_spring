package com.vue.vue_practicesns_backend;

import com.vue.vue_practicesns_backend.controller.PostController;
import com.vue.vue_practicesns_backend.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class VuePracticeSnsBackendApplicationTests {
	@Autowired
	UserController userController;
	@Autowired
	PostController postController;
	@Test
	void contextLoads() {
	}

//	@Test
//	void signUp() throws IllegalAccessException, DuplicateException {
//		UserDto dto = UserDto
//				.builder()
//				.userId("lzyjin5")
//				.birth(LocalDate.now())
//				.password("qwer1234!")
//				.phone("01043123123")
//				.userName("YJ")
//				.build();
//		userController.signUp(dto);
//	}
//
//
////	{userData=UserDto(createdDate=null, modifiedDate=null, userNo=14, userId=newkayak12, password=$2a$10$HrnzksnHAqzt9OBBfWp91eajJCNIRXhQPJNXggjbN1jIDCCJ/mx9a, userName=YJ, phone=01012341234, profileImage=null, backgroundImage=null, link=null, birth=2022-02-22, likedPost=null, follower=null, following=null),
////	accessToken= eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRIcm56a3NuSEFxenQ5T0JCZldwOTFlYWpKQ05JUlhoUVBKTlhnZ2piTjFqSURDQ0ovbXg5YSIsInBob25lIjoiMDEwMTIzNDEyMzQiLCJ1c2VyTm8iOjE0LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibmV3a2F5YWsxMiJ9.-u9lYpQjMPvOwLUYOWCrS6vSICt_blQBU_rRh4JbTi8a3akZK_7OlNzJ9aN_QIBK15V6QAPg9eRXntxYBVnVuQ
//
//// reuslt {userData=UserDto(createdDate=null, modifiedDate=null, userNo=15, userId=lzyjin, password=$2a$10$GuZ1KCbPWxAAB8FtlGOWp.1jkWcDDlFCUM2phHNkLyvIAAh4oSWRq, userName=YJ, phone=01043123123, profileImage=null, backgroundImage=null, link=null, birth=2022-02-22, likedPost=null, follower=null, following=null),
//// accessToken= eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRHdVoxS0NiUFd4QUFCOEZ0bEdPV3AuMWprV2NERGxGQ1VNMnBoSE5rTHl2SUFBaDRvU1dScSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.HtZxUuO5Xa83fZ8HL4NHJbhDkppMiaIid6HUeg2L4MsrfGNrdl_l3soHYAoineGzm0zsL3fpZ_6iW-pvjjHf9Q
//	@Test
//	void signIn() throws IllegalAccessException {
//		UserDto dto = UserDto
//				.builder()
//				.userId("lzyjin5")
//				.password("qwer1234!")
//				.build();
////		assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRHdVoxS0NiUFd4QUFCOEZ0bEdPV3AuMWprV2NERGxGQ1VNMnBoSE5rTHl2SUFBaDRvU1dScSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.HtZxUuO5Xa83fZ8HL4NHJbhDkppMiaIid6HUeg2L4MsrfGNrdl_l3soHYAoineGzm0zsL3fpZ_6iW-pvjjHf9Q", userController.signIn(dto).get("accessToken"));
//		log.warn("result:: {}",userController.signIn(new HashMap()));
//	}
//
//	@Test
//	void signOut (){
//		log.warn("result :: {}", userController.signOut());
//	}
//
//	@Test
//	void changeProfile() throws NoSuchElementException {
//
//		log.warn("result {}", userController.changeProfile("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRHdVoxS0NiUFd4QUFCOEZ0bEdPV3AuMWprV2NERGxGQ1VNMnBoSE5rTHl2SUFBaDRvU1dScSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.HtZxUuO5Xa83fZ8HL4NHJbhDkppMiaIid6HUeg2L4MsrfGNrdl_l3soHYAoineGzm0zsL3fpZ_6iW-pvjjHf9Q"
//				,UserDto.builder().link("https://www.apple.co.kr").build(),null,null ));
//	}
//
//	@Test
//	void changePassword() throws NoSuchElementException {
//		Map passwordSet = new HashMap();
//		passwordSet.put("password", "qwer1234!");
//		passwordSet.put("confirmPassword", "qwer1234!");
//		log.warn("result {} ", userController.changePassword("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRHdVoxS0NiUFd4QUFCOEZ0bEdPV3AuMWprV2NERGxGQ1VNMnBoSE5rTHl2SUFBaDRvU1dScSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.HtZxUuO5Xa83fZ8HL4NHJbhDkppMiaIid6HUeg2L4MsrfGNrdl_l3soHYAoineGzm0zsL3fpZ_6iW-pvjjHf9Q",
//				passwordSet));
////	eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRSck9UUk1CRXlJdEUxSUdSSFJIUjFlUEw2cnhxMTNPc1FiTEpxN0VrbmtITVZFMkdGQnVxZSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.iiQcmuNCsWFLUmh0WzVYv85sSLtxXFyvXWconL4FG8xZryZ_9M_KWvb62JGCMDOoSmZ8XpQnRkbSvcgMHqvxMQ
//	}
//
//	@Test
//	void addFollow() throws DuplicateException, NoSuchElementException {
//		Map map = new HashMap();
//		map.put("userNo", 19);
//		log.warn("result {}", userController.addFollow("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRSck9UUk1CRXlJdEUxSUdSSFJIUjFlUEw2cnhxMTNPc1FiTEpxN0VrbmtITVZFMkdGQnVxZSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.iiQcmuNCsWFLUmh0WzVYv85sSLtxXFyvXWconL4FG8xZryZ_9M_KWvb62JGCMDOoSmZ8XpQnRkbSvcgMHqvxMQ", map));
//	}
//	@Test
//	void deleteFollow() throws DuplicateException, NoSuchElementException {
//		Map map = new HashMap();
//		map.put("userNo", 19);
//		log.warn("result {}", userController.deleteFollow("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRSck9UUk1CRXlJdEUxSUdSSFJIUjFlUEw2cnhxMTNPc1FiTEpxN0VrbmtITVZFMkdGQnVxZSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.iiQcmuNCsWFLUmh0WzVYv85sSLtxXFyvXWconL4FG8xZryZ_9M_KWvb62JGCMDOoSmZ8XpQnRkbSvcgMHqvxMQ", map));
//	}
//	@Test
//	void writePost() throws IllegalAccessException {
//		List<String> hashtag = new ArrayList<>();
//		hashtag.add("1");
//		hashtag.add("2");
//		hashtag.add("3");
//		hashtag.add("4");
//		PostDto dto = PostDto.builder()
//				.isMain(false)
//				.outerLink("https://www.apple.co.kr")
//				.movieLink("pnaQ9CbE6P0")
//				.content("TEST6")
//				.hashtag(hashtag)
//				.build();
//		log.warn("result {} ", postController.writePost("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRSck9UUk1CRXlJdEUxSUdSSFJIUjFlUEw2cnhxMTNPc1FiTEpxN0VrbmtITVZFMkdGQnVxZSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.iiQcmuNCsWFLUmh0WzVYv85sSLtxXFyvXWconL4FG8xZryZ_9M_KWvb62JGCMDOoSmZ8XpQnRkbSvcgMHqvxMQ", dto));
//	}
//	@Test
//	void modifyPost () throws IllegalAccessException {
//		PostDto dto = PostDto.builder().PostNo(Long.parseLong("1")).content("TEST1").build();
//		postController.modifyPost("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRSck9UUk1CRXlJdEUxSUdSSFJIUjFlUEw2cnhxMTNPc1FiTEpxN0VrbmtITVZFMkdGQnVxZSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.iiQcmuNCsWFLUmh0WzVYv85sSLtxXFyvXWconL4FG8xZryZ_9M_KWvb62JGCMDOoSmZ8XpQnRkbSvcgMHqvxMQ", dto);
//	}
//	@Test
//	void remove() throws IllegalAccessException {
//		PostDto dto = PostDto.builder().PostNo(Long.parseLong("1")).content("TEST1").build();
//		postController.removePost("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRSck9UUk1CRXlJdEUxSUdSSFJIUjFlUEw2cnhxMTNPc1FiTEpxN0VrbmtITVZFMkdGQnVxZSIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE1LCJiaXJ0aCI6IjIwMjItMDItMjIiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluIn0.iiQcmuNCsWFLUmh0WzVYv85sSLtxXFyvXWconL4FG8xZryZ_9M_KWvb62JGCMDOoSmZ8XpQnRkbSvcgMHqvxMQ", dto);
//	}
//	@Test
//	void fetchPosts(){
////		log.warn("result {}", postController.fetchPosts(Long.parseLong("15"), 1, 3));
//		 postController.fetchPosts(Long.parseLong("15"), 1, 3);
//	}
//	@Test
//	void addLikedPost() throws IllegalAccessException {
//		PostDto dto = PostDto.builder().PostNo(Long.parseLong("3")).build();
//		log.warn("result {}", postController.addLikePost("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRGdGE3eUFybU5FYmpHdFVvYTRLb0ZPdHVRSERVWnRzY2szaFRyV1BuOS55dzQuR0M5akZrQyIsInBob25lIjoiMDEwNDMxMjMxMjMiLCJ1c2VyTm8iOjE5LCJiaXJ0aCI6IjIwMjItMDItMjUiLCJ1c2VyTmFtZSI6IllKIiwidXNlcklkIjoibHp5amluNSJ9.nti0sNXGzy6P1L9cjek3OAKjL-T6nciJiwAqT79kIC4PkUOu44epDXjZeMAqa0UQP7cSp8d8rBCF2_BZL8QGog",dto));
//	}
}
