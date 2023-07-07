package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({

        "classpath:config/${env}.properties",
})
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://www.redcollar.ru")
    String baseUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("")
    String remoteUrl();

    @Key("videoStorageUrl")
    String videoStorageUrl();
}