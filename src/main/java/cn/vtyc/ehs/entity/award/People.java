package cn.vtyc.ehs.entity.award;

import cn.vtyc.ehs.entity.BaseEntity;
import lombok.Data;

@Data
public class People extends BaseEntity {
    private String name;
    private String department;
    private Integer isWin;


}
