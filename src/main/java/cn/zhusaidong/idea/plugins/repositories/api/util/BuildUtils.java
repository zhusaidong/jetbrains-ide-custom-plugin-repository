package cn.zhusaidong.idea.plugins.repositories.api.util;

import cn.zhusaidong.idea.plugins.repositories.api.domain.dto.BuildDTO;
import cn.zhusaidong.idea.plugins.repositories.api.enums.IdeEnum;
import lombok.experimental.UtilityClass;

/**
 * @author zhusaidong
 * @since 2023/12/6
 */
@UtilityClass
public class BuildUtils {
    public BuildDTO parse(String build) {
        if (build == null) {
            return null;
        }

        BuildDTO buildDTO = new BuildDTO();
        String[] split = build.split("-", 2);
        if (split.length == 1) {
            buildDTO.setType(IdeEnum.STAR);
            buildDTO.setVersion(split[0]);
        }
        if (split.length == 2) {
            buildDTO.setType(IdeEnum.findByIdentity(split[0]));
            buildDTO.setVersion(split[1]);
        }
        return buildDTO;
    }
}
