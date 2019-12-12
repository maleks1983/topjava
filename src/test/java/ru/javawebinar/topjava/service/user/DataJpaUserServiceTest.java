package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;


@ActiveProfiles(value={Profiles.HSQL_DB,Profiles.DATAJPA})

public class DataJpaUserServiceTest extends UserServiceTest {

}
