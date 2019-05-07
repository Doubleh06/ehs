package cn.vtyc.ehs.entity;

import lombok.Data;

@Data
public class WechatGallery extends BaseEntity {
    private String smallImage;
    private String bigImage;
    private Integer wechatInfoType;

    public WechatGallery() {
    }

    public WechatGallery(String smallImage, String bigImage, Integer wechatInfoType) {
        this.smallImage = smallImage;
        this.bigImage = bigImage;
        this.wechatInfoType = wechatInfoType;
    }
}
