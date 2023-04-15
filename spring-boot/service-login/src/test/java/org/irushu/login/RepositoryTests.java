package org.irushu.login;

import org.irushu.login.persistence.model.User;
import org.irushu.login.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositoryTests {

	@Mock
	private UserRepository userRepository;

	@Test
	void testUserRepository() {
		when(userRepository.findFirstByUsername(anyString())).thenReturn(new User(1, "thomasLi", "$2a$10$P7uGVteoic43vKmSYbRcIOVOSRO2B5UfIoHIMRkXYK8cCkm6dJhGK","ROLE"));

		User user = userRepository.findFirstByUsername("hello");
		assertEquals( "ROLE", user.getRole());
	}

}
