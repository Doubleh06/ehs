package cn.vtyc.ehs.service;


import cn.vtyc.ehs.core.AbstractService;
import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.core.jqGrid.JqGridParam;
import cn.vtyc.ehs.dao.first.AccidentLevelDao;
import cn.vtyc.ehs.dao.first.AwardDao;
import cn.vtyc.ehs.dto.AccidentTypeJqGridParam;
import cn.vtyc.ehs.dto.EhsJqGridParam;
import cn.vtyc.ehs.entity.AccidentLevel;
import cn.vtyc.ehs.entity.award.Award;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardService extends AbstractService<Award> {


    @Autowired
    private AwardDao awardDao;

    @Override
    protected BaseDao<Award> getDao() {
        return awardDao;
    }

    @Override
    protected List<Award> selectByJqGridParam(JqGridParam jqGridParam) {
        EhsJqGridParam param = (EhsJqGridParam) jqGridParam;
        StringBuilder sql = new StringBuilder();
        sql.append("1=1 ");
        if (StringUtils.isNotEmpty(param.getSidx())) {
            sql.append("order by ").append(param.getSidx()).append(" ").append(param.getSord()).append("");
        }
        return awardDao.selectBySql("award",sql.toString());
    }


    public PageInfo<Award> selectByJqGridParam(){
        return new PageInfo<>(awardDao.selectAll());
    }

    public void delete(Integer id){
        Award award = new Award();
        award.setId(id);
        awardDao.delete(award);
    }
}
