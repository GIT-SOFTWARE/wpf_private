package com.biostime.bp.authorization.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局变量类
 *
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/11 16:35
 */
public class GlobalVariable {
	
	/*
	 * 本系统应用编码
	 */
	public static final String APP_CODE = "HH_BPA";
	
    /**
     * 严重性能问题接口处理时间
     */
    public static final int SERIOUS_PERFORMANCE_PROBLEMS_TIME_THRESHOLD = 10000;

    /**
     * 一般性能问题接口处理时间
     */
    public static final int GENERAL_PERFORMANCE_PROBLEMS_TIME_THRESHOLD = 5000;

    /**
     * 有优化空间的接口处理时间
     */
    public static final int NEED_OPTIMIZE_TIME_THRESHOLD = 3000;

    // 前端定义的语言类型
    public static final String LANG_ZH_CN = "zh-cn";
    public static final String LANG_EN_US = "en";
    public static final String LANG_FR = "fr";

    public static final Byte STATUS_ENABLE = Byte.valueOf("1");//有效/启用/是
    public static final Byte STATUS_DISABLE = Byte.valueOf("0");//无效/停用/否
    public static final Byte STATUS_DELETE = Byte.valueOf("2");//软删除

    public static final String HH_SURVEY_SYSTEM = "hh-survey-system";

    public static final Integer STATUS_DISABLE_INT = 0;//无效/停用/否
    public static final Integer STATUS_ENABLE_INT = 1;//有效/启用/是
    public static final Integer STATUS_DELETE_INT = 2;//软删除
    
    public static final Integer TRUE = 1; //对应布尔值 true
    public static final Integer FALSE = 0; //对应布尔值 false

    public static final Integer LOGIC_TYPE_NOTGOTO = 1;
    public static final Integer LOGIC_TYPE_GOTO_QUESTION = 2;
    public static final Integer LOGIC_TYPE_GOTO_END = 3;
    public static final Integer LOGIC_TYPE_GOTO_BY_OPTION = 4;

    public static final String DICT_TYPE_QUESTION_TYPE = "questionType";
    public static final String DICT_TYPE_MANDATORY_TYPE = "mandatoryType";
    public static final String IMPORT_SURVEY_DEFAULT_INFO = "importSurveyDefaultInfo";

    public static final Integer QUESTION_TYPE_RADIO_SELECT = 1;
    public static final Integer QUESTION_TYPE_CHECKBOX_SELECT = 2;
    public static final Integer QUESTION_TYPE_SINGLE_TEXT = 3;
    public static final Integer QUESTION_TYPE_MULTI_TEXT = 4;

    public static final Integer MTS_TYPE = 5;

    public static final Map<Integer, String> REF_TABLE_MAP = new HashMap<>(4);

    public static final Map<Integer, String> REF_FIELD_MAP = new HashMap<>(4);

    static {
        REF_TABLE_MAP.put(1, "ss_survey_question");
        REF_TABLE_MAP.put(2, "ss_question_option");
        REF_FIELD_MAP.put(1, "content_id");
        REF_FIELD_MAP.put(2, "content_id");
    }

    /**
     * 问题逻辑描述字典类型
     */
    public static final String LOGCI_DESC_DICT_TYPE = "logicDescription";

    /**
     * 最大的问题数量
     */
    public static final Integer MAX_QUESTION_NUM = 150;

    /**
     * 最大选项数量
     */
    public static final Integer MAX_OPTION_NUM = 50;

    /**
     * 问题最大长度
     */
    public static final Integer MAX_QUESTION_LENGTH = 1000;

    /**
     * 选项最大长度
     */
    public static final Integer MAX_OPTION_LENGTH = 1000;

    /**
     * 调研问卷超级管理员角色ID
     */
    public static final String SUPER_ADMIN_ROLE_CODE = "1020";

    /**
     * 问卷管理员角色ID
     */
    public static final String SURVEY_ADMIN_ROLE_CODE = "1027";

    /**
     * 跳转到第%d题
     */
    public static final String LOGICAL_DESC_GOTO_QUESTION = "1";

    /**
     * 跳转到问卷结束
     */
    public static final String LOGICAL_DESC_GOTO_END = "2";
    
    /** 
    * @Fields REDIS_KEY_REPWD_VALIDATECODE : 用户重置密码验证码 - rediskey
    */
    public static final String REDIS_KEY_REPWD_VALIDATECODE = "REPWD_VALIDATECODE_%s";
    
    /**
     * 登录验证码key
     */
    public  static final String  REDIS_KEY_LOGIN_VALIDATECODE = "hh:bpa:login:verify:code:%s";
    
    /** 
    * @Fields REDIS_KEY_REPWD_EMAIL_TOKEN : 用户重置密码邮件授权令牌 - rediskey
    */
    public static final String REDIS_KEY_REPWD_EMAIL_TOKEN = "REDIS_KEY_REPWD_EMAIL_TOKEN_%s";

}
