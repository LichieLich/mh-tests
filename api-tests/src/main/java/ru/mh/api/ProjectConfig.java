package ru.mh.api;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    String baseUrl();

    @DefaultValue("en")
    String locale();

    Boolean logging();

    String apiLogin();

    String apiPassword();
}
