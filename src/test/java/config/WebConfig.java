package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:local.properties",
        "classpath:remote.properties",
//        "file:~/${env}.properties",
//        "file:./${env}.properties"
})
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("browserVersion")
    @DefaultValue("106.0")
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
    @DefaultValue("http://localhost:4444")
    String remoteUrl();

    @Key("videoStorageUrl")
    String videoStorageUrl();
}