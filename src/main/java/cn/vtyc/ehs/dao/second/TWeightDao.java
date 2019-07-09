package cn.vtyc.ehs.dao.second;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.TWeight;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TWeightDao extends BaseDao<TWeight> {

    @Select("select * from tWeight ${sql}")
    List<TWeight> selectTWeightList(@Param("sql") String sql);

//    @Select("select img_url from tWeight where id = ${id}")
//    String selectImgUrlById(@Param("id") Integer id);
    @Select("SELECT FMaterial,SUM(FWeight)as FWeight from tWeight ${sql} GROUP BY FMaterial")
    List<Map> selectSumFWeight (@Param("sql") String sql);
}
