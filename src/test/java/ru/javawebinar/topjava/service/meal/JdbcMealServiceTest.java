package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(value={Profiles.POSTGRES_DB,Profiles.JDBC})
public class JdbcMealServiceTest extends MealServiceTest {

}
