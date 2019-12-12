package ru.javawebinar.topjava;

import org.springframework.test.context.ActiveProfilesResolver;

//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        final String activeProfile=System.getProperty("spring.profiles.active");
        return new String[]{activeProfile == null ? "integration" : activeProfile};
    }
}