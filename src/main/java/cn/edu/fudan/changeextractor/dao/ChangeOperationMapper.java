package cn.edu.fudan.changeextractor.dao;

import cn.edu.fudan.changeextractor.model.db.ChangeOperation;

public interface ChangeOperationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_operation
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer changeOperationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_operation
     *
     * @mbg.generated
     */
    int insert(ChangeOperation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_operation
     *
     * @mbg.generated
     */
    int insertSelective(ChangeOperation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_operation
     *
     * @mbg.generated
     */
    ChangeOperation selectByPrimaryKey(Integer changeOperationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_operation
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ChangeOperation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table change_operation
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ChangeOperation record);
}