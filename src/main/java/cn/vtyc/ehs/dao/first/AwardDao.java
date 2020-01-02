package cn.vtyc.ehs.dao.first;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.award.Award;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AwardDao extends BaseDao<Award> {

    @Select("select * from award where level = '${level}'")
    List<Award> getAwardNum(@Param("level") String level);

    @Select("select DISTINCT(level),total_num from award ")
    List<Award> getLevelAndAwardNum();

    @Select("select * from award where department = '${department}'")
    List<Award> getAwardLevel(@Param("department") String department);

    @Select("select nd from award where level = '${level}' and department = '${department}'")
    String getAwardND(@Param("level") String level,@Param("department") String department);

    @Select("select level_desc from award where level = '${level}' and department = '${department}'")
    String getAwardDesc(@Param("level") String level,@Param("department") String department);

    @Select("SELECT department from award GROUP BY department")
    List<String> getAllDepartment();

}
