package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
//        "system:properties",
        "classpath:config/${env}.properties",
//        "file:~/${env}.properties",
//        "file:./${env}.properties"
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
    @DefaultValue("true")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("https:localhost:4444")
    String remoteUrl();

    @Key("videoStorageUrl")
    String videoStorageUrl();
}