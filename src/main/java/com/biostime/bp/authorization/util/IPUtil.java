package com.biostime.bp.authorization.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IPUtil {

	public static List<IPAddressBean> splitList(String str) {
		String[] strs = str.split(",");

		List<IPAddressBean> addresses = new ArrayList<IPAddressBean>();
		for (String string : strs) {
			try {
				addresses.add(split(string));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return addresses;
	}
	
	public static IPAddressBean split(String str){
		String[] address = str.split(":");
		return new IPAddressBean(str, address[0], Integer.parseInt(address[1]));
		
	}

	public static class IPAddressBean {
		private String address;
		private String hostname;
		private int port;

		public IPAddressBean(String address, String hostname, int port) {
			this.address = address;
			this.hostname = hostname;
			this.port = port;
		}
		public String getAddress() {
			return address;
		}

		public String getHostname() {
			return hostname;
		}

		public int getPort() {
			return port;
		}

	}

	

	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	public static String getIpAddr() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			return getIpAddr(request);
		}
		return null;
	}
}
