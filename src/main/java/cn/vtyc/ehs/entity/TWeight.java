package cn.vtyc.ehs.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tWeight")
public class TWeight extends BaseEntity {
    private String fmaterial;
    private Integer fweight;
    private Integer ftare;
    private Date fdatetime;
}
