package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;
@ActiveProfiles(Profiles.JPA)
public class JpaUserRepositoryTest extends UserServiceTest {
}
