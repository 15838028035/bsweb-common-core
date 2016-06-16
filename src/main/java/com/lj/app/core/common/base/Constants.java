package com.lj.app.core.common.base;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Constants {

	private static Log logger = LogFactory.getLog(Constants.class);
	

	public static class AcctStatus {// type_code=3

		public static String LOCK = "0"; // 加锁
		public static String NORMAL = "1"; // 正常
		public static String EXPIRE = "2"; // 过期
		public static String EXPIRE_LOCK = "3"; // 过期加锁

	}

	/**
	 * 
	 * <p>
	 * 帐号是否可删除
	 * </p>
	 * 
	 * @version 1.0
	 */
	public static class DeleteAcct {
		/**
		 * 不可以
		 */
		public static String NO = "0";
		/**
		 * 可以
		 */
		public static String YES = "1";

	}

	public static class NotifyMode {
		/**
		 * 短信:1
		 */
		public static String SMS_1 = "1";
		/**
		 * 邮件:2
		 */
		public static String EMAIL_2 = "2";
		/**
		 * 系统消息:3
		 */
		public static String Sysmsg_3 = "3";
	}

	/**
	 * @author liuzg2
	 */
	public static class NotifyType {
		/**
		 * 信息冲突:1
		 */
		public static String INFO_CONFLICT_1 = "1";
		/**
		 * Enable密码修改:2
		 */
		public static String ENABLE_PWD_CHG_2 = "2";
	}


	/**
	 * 是否允许删除
	 * 
	 */
	public static class DelEnabled {
		/**
		 * 不允许删除
		 */
		public static String No_0 = "0";
		/**
		 * 允许删除
		 */
		public static String Yes_1 = "1";

	}

	/**
	 * 是否已经处理
	 * 
	 * @author liuzg2
	 * 
	 */
	public static class ProcessedFlag {
		/**
		 * 尚未处理
		 */
		public static String No_0 = "0";
		/**
		 * 已经处理
		 */
		public static String Yes_1 = "1";

	}

	/**
	 * 操作级别编码
	 * 
	 * @author zengym Apr 10, 2009
	 */
	public static class UapOpLevel {
		public static final String LEVEL_1 = "1"; // 一般
		public static final String LEVEL_2 = "2"; // 重要
		public static final String LEVEL_3 = "3"; // 敏感
		public static final String LEVEL_4 = "4"; // 警告
		public static final String LEVEL_5 = "5"; // 严重
	}

	/**
	 * 增删查改编码
	 * 
	 * @author zengym Apr 10, 2009
	 */
	public static class CRUD {
		public static final String OP_READ = "read";
		public static final String OP_CREATE = "create";
		public static final String OP_UPDATE = "update";
		public static final String OP_DELETE = "delete";
	}

	/**
	 * task status
	 * 
	 * @author linxun sep 10, 2009
	 */
	public static class TaskStatus {
		/**
		 * 异常结束
		 */
		public static final String ABNOR_FAIL = "0";
		/**
		 * 执行中
		 */
		public static final String STARTING = "1";
		/**
		 * 已完成
		 */
		public static final String FINISHED = "2";
	}

	/**
	 * task status
	 * 
	 * @author zengyp sep 6, 2010
	 */
	public static class LoginType {
		/**
		 * telnet
		 */
		public static final String TELNET_TYPE = "1";
		/**
		 * ssh2
		 */
		public static final String SSH2_TYPE = "2";
		/**
		 * ftp
		 */
		public static final String FTP_TYPE = "3";
	}

	/**
	 * author notify type 后台任务帐号同步类型
	 * 
	 * @author guochao Oct 15,2010
	 */
	public static class JobSechduAcctFlag {

		public static String _4A_LACK = "12"; // 4A少
		public static String _4A_MORE = "13"; // 4A多
		public static String CONFLICT = "14"; // 冲突
		public static String _4A_LACK_SYNC = "32";// 4A少,已设备为准进行同步
		public static String _4A_MORE_SYNC = "33";// 4A多,已设备为准进行同步
		public static String CONFLICT_SYNC = "34";// 冲突,已设备为准进行同步

	}

	/**
	 * pwd rule 密码策略方式
	 */
	public static class PwdRule {
		/**
		 * 普通
		 */
		public static String ORDINARY = "0";
		/**
		 * 默认
		 */
		public static String DEFAULT = "1";

	}

	public static final String Package = Constants.class.getPackage().getName();
}
