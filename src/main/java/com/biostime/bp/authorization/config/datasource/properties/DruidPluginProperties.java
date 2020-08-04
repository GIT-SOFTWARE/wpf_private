package com.biostime.bp.authorization.config.datasource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 
* 类功能描述: TODO
*
* @version 1.0
* @author 1394
 */
@ConfigurationProperties(prefix="spring.datasource.druid.filter.slf4j")
@Data
public class DruidPluginProperties {
    private boolean afterStatementCreateLogEnable;
    private boolean statementParameterSetLogEnable;
    private boolean afterStatementExecuteLogEnable;
//  打印可执行sql
    private boolean statementExecutableSqlLogEnable;
    private boolean statementParameterClearLogEnable;
    private boolean afterStatementCloseLogEnable;
//  打印预处理sql语句，false为不打印
    private boolean afterStatementPrepareLogEnable;
    private boolean afterStatementPrepareCallLogEnable;
    @Override
    public String toString()
    {
        return "DruidPluginProperties [afterStatementCreateLogEnable="
                + afterStatementCreateLogEnable + ", statementParameterSetLogEnable=" + statementParameterSetLogEnable
                + ", afterStatementExecuteLogEnable=" + afterStatementExecuteLogEnable + ", statementExecutableSqlLogEnable="
                + statementExecutableSqlLogEnable + ", statementParameterClearLogEnable=" + statementParameterClearLogEnable
                + ", afterStatementCloseLogEnable=" + afterStatementCloseLogEnable + ", afterStatementPrepareLogEnable="
                + afterStatementPrepareLogEnable + ", afterStatementPrepareCallLogEnable=" + afterStatementPrepareCallLogEnable
                + "]";
    }
}
