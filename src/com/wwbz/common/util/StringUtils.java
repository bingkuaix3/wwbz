package com.wwbz.common.util;

import java.text.ParseException;
import java.util.Date;

import java.util.Random;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
	
    /**
     * StringUtils工具类方法
     * 获取一定长度的随机字符串，范围0-9，a-z
     * @param length：指定字符串长度
     * @return 一定长度的随机字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

	public static String formartDutyView(String duty) {
		return duty.substring(0, 3);
	}

	public static int getDays(Date date) {
		return (int) ((System.currentTimeMillis() - date.getTime()) / 1000 / 60 / 60 / 24);
	}

	public static boolean Isipv4(String ipv4) {
		if (ipv4 == null || ipv4.length() == 0) {
			return false;// 字符串为空或者空串
		}
		String[] parts = ipv4.split("\\.");// 因为java doc里已经说明, split的参数是reg, 即正则表达式, 如果用"|"分割, 则需使用"\\|"
		if (parts.length != 4) {
			return false;// 分割开的数组根本就不是4个数字
		}
		for (int i = 0; i < parts.length; i++) {
			try {
				int n = Integer.parseInt(parts[i]);
				if (n < 0 || n > 255) {
					return false;// 数字不在正确范围内
				}
			} catch (NumberFormatException e) {
				return false;// 转换数字不正确
			}
		}
		return true;
	}

	public static void main(String[] args) throws ParseException {
		// BigDecimal amount = new BigDecimal("10");
		// BigDecimal balance = new BigDecimal("19");
		// if (amount.compareTo(balance) <= 0) {
		// balance = balance.subtract(amount);
		// } else {
		// amount = amount.subtract(balance);
		// }
		// System.out.println(amount);
		// System.out.println(balance);
		// for (int i = 1; i <= 3; i++) {
		// System.out.println(i);
		// }
		String s = "[\"1\",\"1\",\"2\",\"4\",\"2\",\"1\",\"2\",\"1\",\"2\",\"2\",\"2\",\"4\",\"3\",\"2\",\"1\",\"4\",\"4\",\"2\",\"2\",\"3\",\"2\"]";
		System.out.println(s);
		s = s.replace("[", "");
		s = s.replace("]", "");
		s = s.replace("\"", "");
		System.out.println(s);
	}

}
