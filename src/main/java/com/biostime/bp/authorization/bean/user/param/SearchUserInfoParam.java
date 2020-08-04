package com.biostime.bp.authorization.bean.user.param;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.biostime.bp.authorization.bean.base.PageParam;

/** 
 * 类功能描述: TODO
 *
 * @version 1.0
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月21日 下午2:12:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SearchUserInfoParam extends PageParam {
	private String userName;
	private String nickName;
	private String account;
	private List<Integer> status;
}
