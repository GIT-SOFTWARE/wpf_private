package com.biostime.bp.authorization.domain.log;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_operate_log")
public class BpaOperateLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 操作用户id
     */
    @Column(name = "operate_uid")
    private Long operateUid;

    /**
     * 操作内容
     */
    @Column(name = "operate_content")
    private String operateContent;

    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 操作类型 1-用户重置密码 2-登录成功 3-登录密码错误 4-管理员重置用户密码
     */
    @Column(name = "operate_type")
    private String operateType;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 被操作人用户id
     */
    @Column(name = "to_operate_uid")
    private Long toOperateUid;

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
     * 获取操作用户id
     *
     * @return operate_uid - 操作用户id
     */
    public Long getOperateUid() {
        return operateUid;
    }

    /**
     * 设置操作用户id
     *
     * @param operateUid 操作用户id
     */
    public void setOperateUid(Long operateUid) {
        this.operateUid = operateUid;
    }

    /**
     * 获取操作内容
     *
     * @return operate_content - 操作内容
     */
    public String getOperateContent() {
        return operateContent;
    }

    /**
     * 设置操作内容
     *
     * @param operateContent 操作内容
     */
    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    /**
     * 获取操作时间
     *
     * @return operate_time - 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取操作类型 1-用户重置密码 2-登录成功 3-登录密码错误 4-管理员重置用户密码
     *
     * @return operate_type - 操作类型 1-用户重置密码 2-登录成功 3-登录密码错误 4-管理员重置用户密码
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型 1-用户重置密码 2-登录成功 3-登录密码错误 4-管理员重置用户密码
     *
     * @param operateType 操作类型 1-用户重置密码 2-登录成功 3-登录密码错误 4-管理员重置用户密码
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取被操作人用户id
     *
     * @return to_operate_uid - 被操作人用户id
     */
    public Long getToOperateUid() {
        return toOperateUid;
    }

    /**
     * 设置被操作人用户id
     *
     * @param toOperateUid 被操作人用户id
     */
    public void setToOperateUid(Long toOperateUid) {
        this.toOperateUid = toOperateUid;
    }
}