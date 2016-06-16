package com.lj.app.core.common.base.app;

public class Constants {

	/**
	 * 从帐号的实体类型默认为USER
	 */
	public final static String USER = "user";
	/**
	 * 默认值字段名称，default_type为sql时，需要重命名为DEFAULT_VALUE
	 */
	public final static String DEFAULT_VALUE_COLUMN = "DEFAULT_VALUE";
	/**
	 * 列翻译结果名称，重命名为TRANSLATE_RESULT
	 */
	public final static String TRANSLATE_RESULT = "TRANSLATE_RESULT";
	/**
	 * 列翻译sql中的特殊标识
	 */
	public final static String TRANSLATE_ID = "#id#";

	/**
	 * 当column_translate不为空时，前台document对象增加的后缀
	 */
	public final static String DESC = "_DESC";
	/**
	 * 虚拟管理平台的编码
	 */
	public final static String UPM = "UPM";

	/**
	 * 属性默认值类型
	 * 
	 */
	public static class DefaultType {
		/**
		 * 默认值为常量
		 */
		public final static String VALUE = "1";
		/**
		 * 默认值为SQL语句，查出来的字段需要重命名(as)为DEFAULT_VALUE
		 */
		public final static String SQL = "2";
	}

	/**
	 * 数据类型
	 * 
	 */
	public static class DataType {
		/**
		 * Radio
		 */
		public final static String RADIO = "1";
		/**
		 * 单选框checkbox
		 */
		public final static String CHECKBOX = "2";
		/**
		 * 多选框checkbox
		 */
		public final static String MULTICHECKBOX = "3";
		/**
		 * 文本框input
		 */
		public final static String INPUT = "4";
		/**
		 * 下拉框select
		 */
		public final static String SELECT = "5";
		/**
		 * 时间控件
		 */
		public final static String TIMESTAMP = "6";
		/**
		 * 文本框textarea
		 */
		public final static String TEXTAREA = "7";
		/**
		 * 弹出页面选择型
		 */
		public final static String DIALOGSELECT = "8";
	}

	/**
	 * checkbox选中状态值
	 * 
	 */
	public static class CheckBoxStatus {
		/**
		 * 选中状态true
		 */
		public final static String CHECKED = "true";
		/**
		 * 选中状态on
		 */
		public final static String ON = "on";
		/**
		 * 未选中状态false
		 */
		public final static String UNCHECKED = "false";
		/**
		 * 未选中状态off
		 */
		public final static String OFF = "off";
	}

	/**
	 * 树结构的根节点的类型
	 * 
	 */
	public static class TreeRootType {
		/**
		 * 单根节点
		 */
		public final static String SINGLE = "0";
		/**
		 * 多根节点
		 */
		public final static String MULTI = "1";
	}

	/**
	 * 树结构查询时的固定字段名
	 * 
	 */
	public static class TreeNote {
		/**
		 * 节点ID
		 */
		public final static String ID = "TREE_NODE_ID";
		/**
		 * 节点名称
		 */
		public final static String TEXT = "TREE_NODE_TEXT";
		/**
		 * 节点父ID
		 */
		public final static String PARENT_ID = "PARENT_ID";
		/**
		 * 节点选中状态
		 */
		public final static String CHECKED = "CHECKED";
	}

	/**
	 * 操作类型
	 * 
	 */
	public static class ModifyType {
		/**
		 * 新增
		 */
		public final static String ADD = "ADD";
		/**
		 * 编辑
		 */
		public final static String UPDATE = "UPDATE";
		/**
		 * 删除
		 */
		public final static String DELETE = "DELETE";
		/**
		 * 变更后全量集合
		 */
		public final static String ALL = "ALL";
	}

	/**
	 * webservice返回结果
	 * 
	 */
	public static class WebServiceResult {
		/**
		 * 返回成功
		 */
		public final static String SUSSESS = "0";
		/**
		 * 返回失败
		 */
		public final static String FAILT = "1";
	}

	/**
	 * 扩展MAP使用的key值
	 * 
	 */
	public static class MapFilterKey {
		/**
		 * 应用编码
		 */
		public final static String AppCode = "appCode";
		/**
		 * 当前登录主帐号对象
		 */
		public final static String UpmMainAcct = "upmMainAcct";
		
		/**
		 * 创建时间
		 */
		public final static String createTime = "createTime";
		/**
		 * 最后更新时间
		 */
		public final static String lastUpdateTime = "lastUpdateTime";
		/**
		 * 本地过滤条件
		 */
		public final static String LocalFilter = "localFilter";
		/**
		 * 是否需要根据管理员范围过滤，true or other
		 */
		public final static String isOnlyAdminData = "adminFilter";
		/**
		 * 根据管理员范围过滤时的sql条件
		 */
		public final static String adminFilterSql = "adminFilterSql";
		/**
		 * 排序条件
		 */
		public final static String SortColumns = "sortColumns";
	}
}
