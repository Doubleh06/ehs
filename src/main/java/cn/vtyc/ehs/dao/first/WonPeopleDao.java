package cn.vtyc.ehs.dao.first;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.award.People;
import cn.vtyc.ehs.entity.award.WonPeople;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WonPeopleDao extends BaseDao<WonPeople> {


}
