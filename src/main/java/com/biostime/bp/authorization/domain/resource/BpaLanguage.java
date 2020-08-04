package com.biostime.bp.authorization.domain.resource;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_language")
public class BpaLanguage {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 语言类型
     */
    @Column(name = "language_type")
    private String languageType;

    /**
     * 数据类型  1-菜单类型
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 关联ID
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 翻译文本
     */
    private String value;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取语言类型
     *
     * @return language_type - 语言类型
     */
    public String getLanguageType() {
        return languageType;
    }

    /**
     * 设置语言类型
     *
     * @param languageType 语言类型
     */
    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    /**
     * 获取数据类型  1-菜单类型
     *
     * @return data_type - 数据类型  1-菜单类型
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型  1-菜单类型
     *
     * @param dataType 数据类型  1-菜单类型
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取关联ID
     *
     * @return relation_id - 关联ID
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置关联ID
     *
     * @param relationId 关联ID
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取翻译文本
     *
     * @return value - 翻译文本
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置翻译文本
     *
     * @param value 翻译文本
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}