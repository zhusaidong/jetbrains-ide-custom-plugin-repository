package cn.zhusaidong.idea.plugins.repositories.api.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@Getter
public enum IdeEnum {
    /**
     * 全部
     */
    STAR("*", "all"),
    /**
     * IDEA
     */
    IU("IU", "IntelliJ IDEA Ultimate"),
    IC("IC", "IntelliJ IDEA Community"),
    IE("IE", "IntelliJ IDEA Educational"),
    PS("PS", "PhpStorm"),
    WS("WS", "WebStorm"),
    PY("PY", "PyCharm Professional"),
    PC("PC", "PyCharm Community"),
    PE("PE", "PyCharm Educational"),
    RM("RM", "RubyMine"),
    OC("OC", "AppCode"),
    CL("CL", "CLion"),
    GO("GO", "GoLand"),
    DB("DB", "DataGrip"),
    RD("RD", "Rider"),
    AI("AI", "Android Studio");

    private final String identity;
    private final String name;

    IdeEnum(String identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    public static IdeEnum findByIdentity(String identity) {
        return Arrays.stream(values())
                .filter(idea -> idea.identity.equals(identity))
                .findFirst()
                .orElse(null);
    }
}
