package cn.vtyc.ehs.dao.first;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.award.Award;
import cn.vtyc.ehs.entity.award.People;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PeopleDao extends BaseDao<People> {

    @Select("select name from people where is_win = 0 and department = '${department}'")
    List<String> selectIsWinList(@Param("department") String department);

    @Select("select name from people where department = '${department}'")
    List<String> selectShowList(@Param("department") String department);

    @Select("select name from people")
    List<String> selectAllList();

    @Select("select name from people")
    List<String> selectALLShowList();


    @Select("select name from people where department = '${dept}' and is_win = 0")
    List<String> selectPeopleByDepartment(@Param("dept")String dept);


    @Select("<script>" +
            "update people SET is_win = 1 where name  IN " +
            "<foreach collection='names' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item} " +
            "</foreach>" +
            "</script>")
    void updateWinPeople(@Param("names") List<String> names);
}
