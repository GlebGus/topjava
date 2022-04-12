package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;
@ActiveProfiles(Profiles.JPA)
public class JpaMealRepositoryTest extends MealServiceTest {
}
