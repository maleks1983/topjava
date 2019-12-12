package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.meal.MealServiceTest;

@ActiveProfiles(value={Profiles.POSTGRES_DB,Profiles.JPA})

public class JpaMealServiceTest extends MealServiceTest {

}
