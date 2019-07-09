package cn.vtyc.ehs.service;


import cn.vtyc.ehs.core.AbstractService;
import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.core.jqGrid.JqGridParam;
import cn.vtyc.ehs.dao.second.TWeightDao;
import cn.vtyc.ehs.dto.EhsJqGridParam;
import cn.vtyc.ehs.dto.TWeightJqGridParam;
import cn.vtyc.ehs.entity.TWeight;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TWeightService extends AbstractService<TWeight> {


    @Autowired
    private TWeightDao tWeightDao;

    @Override
    protected BaseDao<TWeight> getDao() {
        return tWeightDao;
    }

    @Override
    protected List<TWeight> selectByJqGridParam(JqGridParam jqGridParam) {
        EhsJqGridParam param = (EhsJqGridParam) jqGridParam;
        StringBuilder sql = new StringBuilder();
        sql.append("1=1 ");
        if (StringUtils.isNotEmpty(param.getSidx())) {
            sql.append("order by ").append(param.getSidx()).append(" ").append(param.getSord()).append("");
        }
        return tWeightDao.selectBySql("tweight",sql.toString());
    }


    public PageInfo<TWeight> selectByJqGridParam(TWeightJqGridParam param ){
        StringBuilder sql = new StringBuilder();
        sql.append(" where ");
        if(StringUtils.isNotEmpty(param.getDate())) {
            sql.append(" FDateTime between '")
                    .append(param.getDate()).append(" 00:00:00' and '")
                    .append(param.getDate()).append(" 23:59:59'");
        }
        return new PageInfo<>(tWeightDao.selectTWeightList(sql.toString()));
    }
    public List<TWeight> selectListByJqGridParam(TWeightJqGridParam param ){
        StringBuilder sql = new StringBuilder();
        sql.append(" where ");
        if(StringUtils.isNotEmpty(param.getDate())) {
            sql.append(" FDateTime between '")
                    .append(param.getDate()).append(" 00:00:00' and '")
                    .append(param.getDate()).append(" 23:59:59'");
        }
        return tWeightDao.selectTWeightList(sql.toString());
    }

    public PageInfo<Map> selectSumByJqGridParam(TWeightJqGridParam param ){
        StringBuilder sql = new StringBuilder();
        sql.append(" where ");
        if(StringUtils.isNotEmpty(param.getDate())) {
            sql.append(" FDateTime between '")
                    .append(param.getDate()).append(" 00:00:00' and '")
                    .append(param.getDate()).append(" 23:59:59'");
        }
        //计算fweight总和
        return new PageInfo<>(tWeightDao.selectSumFWeight(sql.toString()));

    }
    public List<Map> selectListSumByJqGridParam(TWeightJqGridParam param ){
        StringBuilder sql = new StringBuilder();
        sql.append(" where ");
        if(StringUtils.isNotEmpty(param.getDate())) {
            sql.append(" FDateTime between '")
                    .append(param.getDate()).append(" 00:00:00' and '")
                    .append(param.getDate()).append(" 23:59:59'");
        }
        //计算fweight总和
        return tWeightDao.selectSumFWeight(sql.toString());

    }
}
