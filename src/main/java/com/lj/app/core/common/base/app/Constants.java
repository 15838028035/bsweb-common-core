package com.lj.app.core.common.base.app;

/**
 * 
 * 常量类
 *
 */
public class Constants {
  /**
   * 虚拟管理平台的编码
   */
  public static final String UPM = "UPM";

  /**
   * 属性默认值类型
   * 
   */
  public static class DefaultType {
    /**
     * 默认值为常量
     */
    public static final String VALUE = "1";
    /**
     * 默认值为SQL语句，查出来的字段需要重命名(as)为DEFAULT_VALUE
     */
    public static final String SQL = "2";
  }

  /**
   * 数据类型
   * 
   */
  public static class DataType {
    /**
     * Radio
     */
    public static final String RADIO = "1";
    /**
     * 单选框checkbox
     */
    public static final String CHECKBOX = "2";
    /**
     * 多选框checkbox
     */
    public static final String MULTICHECKBOX = "3";
    /**
     * 文本框input
     */
    public static final String INPUT = "4";
    /**
     * 下拉框select
     */
    public static final String SELECT = "5";
    /**
     * 时间控件
     */
    public static final String TIMESTAMP = "6";
    /**
     * 文本框textarea
     */
    public static final String TEXTAREA = "7";
    /**
     * 弹出页面选择型
     */
    public static final String DIALOGSELECT = "8";
  }

  /**
   * checkbox选中状态值
   * 
   */
  public static class CheckBoxStatus {
    /**
     * 选中状态true
     */
    public static final String CHECKED = "true";
    /**
     * 选中状态on
     */
    public static final String ON = "on";
    /**
     * 未选中状态false
     */
    public static final String UNCHECKED = "false";
    /**
     * 未选中状态off
     */
    public static final String OFF = "off";
  }

  /**
   * 树结构的根节点的类型
   * 
   */
  public static class TreeRootType {
    /**
     * 单根节点
     */
    public static final String SINGLE = "0";
    /**
     * 多根节点
     */
    public static final String MULTI = "1";
  }

  /**
   * 树结构查询时的固定字段名
   * 
   */
  public static class TreeNote {
    /**
     * 节点ID
     */
    public static final String ID = "TREE_NODE_ID";
    /**
     * 节点名称
     */
    public static final String TEXT = "TREE_NODE_TEXT";
    /**
     * 节点父ID
     */
    public static final String PARENT_ID = "PARENT_ID";
    /**
     * 节点选中状态
     */
    public static final String CHECKED = "CHECKED";
  }

  /**
   * 操作类型
   * 
   */
  public static class ModifyType {
    /**
     * 新增
     */
    public static final String ADD = "ADD";
    /**
     * 编辑
     */
    public static final String UPDATE = "UPDATE";
    /**
     * 删除
     */
    public static final String DELETE = "DELETE";
    /**
     * 变更后全量集合
     */
    public static final String ALL = "ALL";
  }

  /**
   * webservice返回结果
   * 
   */
  public static class WebServiceResult {
    /**
     * 返回成功
     */
    public static final String SUSSESS = "0";
    /**
     * 返回失败
     */
    public static final String FAILT = "1";
  }

  /**
   * 扩展MAP使用的key值
   * 
   */
  public static class MapFilterKey {
    /**
     * 创建时间
     */
    public static final String CREATETIME = "createTime";
    /**
     * 最后更新时间
     */
    public static final String LASTUPDATETIME = "lastUpdateTime";
    /**
     * 是否需要根据管理员范围过滤，true or other
     */
    public static final String ISONLYADMINDATA = "adminFilter";
    /**
     * 根据管理员范围过滤时的sql条件
     */
    public static final String ADMINFILTERSQL = "adminFilterSql";
    /**
     * 排序条件
     */
    public static final String SORTCOLUMNS = "sortColumns";
  }

  /**
   * 菜单类型
   */
  public static class MenuType {
    /**
     * 目录
     */
    public static final String CATALOG = "0";

    /**
     * 菜单
     */
    public static final String MENU = "1";

    /**
     * 按钮
     */
    public static final String BUTTON = "2";

  }

  /**
   * 
   * 帐号状态
   *
   */
  public static class AcctStatus {

    public static final String LOCK = "0"; // 加锁
    public static final String NORMAL = "1"; // 正常
    public static final String EXPIRE = "2"; // 过期
    public static final String EXPIRE_LOCK = "3"; // 过期加锁

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
    public static final String NO = "0";
    /**
     * 可以
     */
    public static final String YES = "1";

  }

  /**
   * 
   * 通知配置
   *
   */
  public static class NotifyMode {
    /**
     * 短信:1
     */
    public static final String SMS_1 = "1";
    /**
     * 邮件:2
     */
    public static final String EMAIL_2 = "2";
    /**
     * 系统消息:3
     */
    public static final String SYSMSG_3 = "3";
  }

  /**
   * 通知类别
   */
  public static class NotifyType {
    /**
     * 信息冲突:1
     */
    public static final String INFO_CONFLICT_1 = "1";
    /**
     * Enable密码修改:2
     */
    public static final String ENABLE_PWD_CHG_2 = "2";
  }

  /**
   * 是否允许删除
   * 
   */
  public static class DelEnabled {
    /**
     * 不允许删除
     */
    public static final String NO_0 = "0";
    /**
     * 允许删除
     */
    public static final String YES_1 = "1";

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
    public static final String NO_0 = "0";
    /**
     * 已经处理
     */
    public static final String YES_1 = "1";

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
   */
  public static class Crud {
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

}
