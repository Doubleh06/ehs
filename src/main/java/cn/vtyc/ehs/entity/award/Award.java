package cn.vtyc.ehs.entity.award;

import cn.vtyc.ehs.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Award extends BaseEntity {
    private String level;
    private String levelDesc;
    private String department;
    private Integer deptNum;
    private Integer totalNum;

    public Award() {
    }

    public Award(String level, String levelDesc, String department, Integer deptNum) {
        this.level = level;
        this.levelDesc = levelDesc;
        this.department = department;
        this.deptNum = deptNum;
    }
}
