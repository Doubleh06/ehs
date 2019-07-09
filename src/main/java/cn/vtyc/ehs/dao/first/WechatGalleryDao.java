package cn.vtyc.ehs.dao.first;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.Ehs;
import cn.vtyc.ehs.entity.WechatGallery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface WechatGalleryDao extends BaseDao<WechatGallery> {
    @Select("select * from wechat_gallery where wechat_info_type = #{type}")
    List<WechatGallery> selectWechatGalleryByType(@Param("type")Integer type);
}
