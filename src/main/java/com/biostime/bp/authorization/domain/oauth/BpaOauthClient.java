package com.biostime.bp.authorization.domain.oauth;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bpa_oauth_client")
public class BpaOauthClient {
    /**
     * 客户端id
     */
    @Id
    @Column(name = "client_id")
    private Long clientId;

    /**
     * 客户端凭证密钥
     */
    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 客户端名称
     */
    @Column(name = "client_name")
    private String clientName;

    /**
     * 关联的uid
     */
    @Column(name = "relation_uid")
    private Long relationUid;

    /**
     * 0-停用 1-有效 2-已删除
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
     * 获取客户端id
     *
     * @return client_id - 客户端id
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * 设置客户端id
     *
     * @param clientId 客户端id
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取客户端凭证密钥
     *
     * @return client_secret - 客户端凭证密钥
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 设置客户端凭证密钥
     *
     * @param clientSecret 客户端凭证密钥
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * 获取客户端名称
     *
     * @return client_name - 客户端名称
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * 设置客户端名称
     *
     * @param clientName 客户端名称
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * 获取关联的uid
     *
     * @return relation_uid - 关联的uid
     */
    public Long getRelationUid() {
        return relationUid;
    }

    /**
     * 设置关联的uid
     *
     * @param relationUid 关联的uid
     */
    public void setRelationUid(Long relationUid) {
        this.relationUid = relationUid;
    }

    /**
     * 获取0-停用 1-有效 2-已删除
     *
     * @return status - 0-停用 1-有效 2-已删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-停用 1-有效 2-已删除
     *
     * @param status 0-停用 1-有效 2-已删除
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
}