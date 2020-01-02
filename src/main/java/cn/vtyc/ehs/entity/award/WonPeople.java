package cn.vtyc.ehs.entity.award;

import cn.vtyc.ehs.entity.BaseEntity;
import lombok.Data;

@Data
public class WonPeople extends BaseEntity {
    private String dept;
    private String award;
    private String name;


    public WonPeople() {
    }

    public WonPeople(String dept, String award, String name) {
        this.dept = dept;
        this.award = award;
        this.name = name;
    }
}
