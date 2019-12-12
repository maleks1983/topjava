package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.meal.MealServiceTest;

@ActiveProfiles(value={Profiles.HSQL_DB,Profiles.JPA})

public class JpaUserServiceTest extends UserServiceTest {

}
