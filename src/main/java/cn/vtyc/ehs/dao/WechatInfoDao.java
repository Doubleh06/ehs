package cn.vtyc.ehs.dao;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.WechatGallery;
import cn.vtyc.ehs.entity.WechatInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WechatInfoDao extends BaseDao<WechatInfo> {
    @Select("select * from wechat_info where type = #{type}")
    WechatInfo selectWechatInfoByType(@Param("type") Integer type);
}
