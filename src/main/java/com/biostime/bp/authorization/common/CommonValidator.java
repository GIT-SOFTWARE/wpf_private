package com.biostime.bp.authorization.common;

import java.util.Arrays;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月24日 上午9:51:11
 */
public class CommonValidator {
	public static boolean allBlank(String... strings) {
		return Arrays.asList(strings).stream().allMatch(s -> null == s || s.isEmpty());
	}

	public static boolean anyBlank(String... strings) {
		return Arrays.asList(strings).stream().anyMatch(s -> null == s || s.isEmpty());
	}

	/**
	 * 方法描述: 有任何参数为null或者intValue<=0认为是无效的数字
	 *
	 * @param nums
	 * @return
	 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
	 * @createDate 2019年5月24日 上午10:01:20
	 */
	public static boolean anyNaEN(Number... nums) {
		return Arrays.asList(nums).stream().anyMatch(n -> null == n || n.intValue() <= 0);
	}
}
