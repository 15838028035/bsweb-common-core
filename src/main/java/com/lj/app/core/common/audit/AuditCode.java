package com.lj.app.core.common.audit;

public final class AuditCode {

	/**
	 * 系统配置部分
	 * 
	 */
	public static final class SysCfg {

		public static final String createRole = "1-UAP-20016";// 增加角色
		public static final String updateRole = "1-UAP-20017";// 修改角色
		public static final String deleteRole = "1-UAP-20018";// 删除角色
		public static final String queryRole = "1-UAP-20019";// 查询角色

		/** UapDictionaryNoteadd: 数据字典 增加 */
		public static final String UAP_DICTIONARY_NOTE_ADD = "1-UAP-20413";
		/** UapDictionaryNoteadd: 数据字典 修改 */
		public static final String UAP_DICTIONARY_NOTE_MODIFY = "1-UAP-20414";
		/** UapDictionaryNoteadd: 数据字典 删除 */
		public static final String UAP_DICTIONARY_NOTE_DEL = "1-UAP-20415";
		/** UapDictionaryNoteadd: 数据字典数据 增加 */
		public static final String UAP_DICTIONARY_ADD = "1-UAP-20416";
		/** UapDictionaryNoteadd: 数据字典数据 修改 */
		public static final String UAP_DICTIONARY_MODIFY = "1-UAP-20417";
		/** UapDictionaryNoteadd: 数据字典数据 删除 */
		public static final String UAP_DICTIONARY_DEL = "1-UAP-20418";

		/** UAP_PERMISSION_ADD: 权限菜单 增加 */
		public static final String UAP_PERMISSION_ADD = "1-UAP-20419";
		/** UAP_PERMISSION_MODIFY: 权限菜单 修改 */
		public static final String UAP_PERMISSION_MODIFY = "1-UAP-20420";
		/** UAP_PERMISSION_DEL: 权限菜单 删除 */
		public static final String UAP_PERMISSION_DEL = "1-UAP-20421";
		/** UAP_PERMISSION_CANCEL: 权限菜单 停用 */
		public static final String UAP_PERMISSION_CANCEL = "1-UAP-20422";
		/** UAP_PERMISSION_STAR: 权限菜单 启用 */
		public static final String UAP_PERMISSION_STAR = "1-UAP-20423";
	}

	public static final String tokenCreate = "1-UAP-20595"; // 认证枢纽token申请
	public static final String tokenCheck = "1-UAP-20596"; // 认证枢纽token验证

}