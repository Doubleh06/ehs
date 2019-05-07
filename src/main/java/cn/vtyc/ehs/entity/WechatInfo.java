package cn.vtyc.ehs.entity;

import lombok.Data;

@Data
public class WechatInfo extends BaseEntity {
    private Integer type;
    private String metaTitle;
    private String title;
    private String content;
}
