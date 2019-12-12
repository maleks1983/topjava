package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(value={Profiles.POSTGRES_DB,Profiles.DATAJPA})
public class DataJpaMealServiceTest extends MealServiceTest {


}
