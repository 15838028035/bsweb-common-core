package com.lj.app.core.common.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BootStrapTreeViewCheck {
	private BootStrapTreeView rootNode;

	/**
	 * 唯一构造函数
	 * @param rootId
	 * @param rootText
	 * @param checked 值为：0,1,2，如果非法，默认为是0
	 */
	public BootStrapTreeViewCheck(String rootId,String rootText) {
		rootNode = new BootStrapTreeView();
		rootNode.setId(rootId);
		rootNode.setNodeId(rootId);
		rootNode.setText(rootText);
	}

	/**
	 * 增加节点，默认父节点为根节点
	 * 
	 * @param id
	 * @param text
	 * @return
	 */
	public BootStrapTreeView addNode(String id,Boolean checked, String text) {
		return addNode(id, text,true, null);
	}

	/**
	 * 增加节点
	 * 
	 * @param id
	 * @param text
	 * @param checked
	 *            值为：0,1,2，如果非法，默认为是0
	 * @param parentNode
	 *            如果为null，将默认为根节点
	 * @return
	 */
	public BootStrapTreeView addNode(String id, String text,Boolean checked, BootStrapTreeView parentNode) {
		BootStrapTreeView node = new BootStrapTreeView();
		node.setId(id);
		node.setNodeId(id);
		node.setText(text);
		Map<String,Boolean> stateMap = node.getState();
		stateMap.put("checked", checked);
		node.setState(stateMap);
		
		if (null == parentNode || parentNode.equals(rootNode)) {
			rootNode.addNodes(node);
		} else {
			parentNode.addNodes(node);
		}

		return node;
	}

	/**
	 * 以json方式返回对象
	 * 
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String toJsonString() throws Exception {
		JSONObject jsonObject = new JSONObject();
		return jsonObject.fromObject(rootNode).toString();
	}
	
	/**
	 * 默认跟节点
	 * @param rootId
	 * @param rootText
	 * @return
	 */
	public static String  defaultBootStrapTreeViewCheck(String rootId,String rootText) {
		BootStrapTreeViewCheck BootStrapTreeViewCheck = new  BootStrapTreeViewCheck( rootId, rootText);
		JSONObject jsonObject = new JSONObject();
		return jsonObject.fromObject(BootStrapTreeViewCheck.getRootNode()).toString();
	}

	/**
	 * 将List&lt;DefaultTreeNodeNoCheck&gt;转换为SimpleTree对象。
	 * 
	 * @param TreeNodeNoCheckList
	 * @param rootNodeId
	 * @return
	 */
	public static BootStrapTreeViewCheck valueOf(List<BootStrapTreeView> bootStrapTreeViewList, String rootNodeId)
			throws Exception {
		if (null == bootStrapTreeViewList || bootStrapTreeViewList.isEmpty() || null == rootNodeId) {
			return null;
		}

		BootStrapTreeView rootNode = findRootNode(bootStrapTreeViewList, rootNodeId);// 根节点
		if (null != rootNode) {
			bootStrapTreeViewList.remove(rootNode);

			BootStrapTreeViewCheck bootStrapTreeViewCheck = new BootStrapTreeViewCheck(rootNode.getId(), rootNode.getText());
			List<BootStrapTreeView> whileList = new ArrayList<BootStrapTreeView>();
			whileList.addAll(bootStrapTreeViewList);
			List<BootStrapTreeView> swapList = new ArrayList<BootStrapTreeView>();
			swapList.addAll(bootStrapTreeViewList);
			List<BootStrapTreeView> parentList = new ArrayList<BootStrapTreeView>();
			List<BootStrapTreeView> swapParentList = new ArrayList<BootStrapTreeView>();
			parentList.add(bootStrapTreeViewCheck.rootNode);
			while (parentList.size() > 0 && !whileList.isEmpty()) {// 必须是并且关系，否则会死循环（因为里面有的节点可能根本就没有对应的父节点）
				for (int j = 0; j < parentList.size(); j++) {
					BootStrapTreeView parentNode = parentList.get(j);
					String parentNodeId = parentNode.getId();
					for (int i = 0; i < whileList.size(); i++) {
						BootStrapTreeView tmpNode = whileList.get(i);
						if (parentNodeId.equals(tmpNode.getParentId())) {
							BootStrapTreeView node = bootStrapTreeViewCheck.addNode(tmpNode.getId(), tmpNode.getText(),Boolean.valueOf(tmpNode.getState().get("checked")), parentNode);
							swapList.remove(tmpNode);
							swapParentList.add(node);
						}
					}
					whileList = new ArrayList<BootStrapTreeView>();
					whileList.addAll(swapList);
					if ((whileList.isEmpty())) {
						return bootStrapTreeViewCheck;
					}
				}
				parentList = new ArrayList<BootStrapTreeView>();
				parentList.addAll(swapParentList);
				swapParentList = new ArrayList<BootStrapTreeView>();
			}
			return bootStrapTreeViewCheck;
		}
		return null;
	}
	
	/**
	 * 将List&lt;DefaultTreeNodeNoCheck&gt;转换为SimpleTree对象。
	 * 
	 * @param TreeNodeNoCheckList
	 * @param rootNodeId
	 * @return
	 */
	public static String  valueOfString(List<BootStrapTreeView> bootStrapTreeViewList, String rootNodeId)
			throws Exception {
		if (null == bootStrapTreeViewList || bootStrapTreeViewList.isEmpty() || null == rootNodeId) {
			return null;
		}

		BootStrapTreeView rootNode = findRootNode(bootStrapTreeViewList, rootNodeId);// 根节点
		if (null != rootNode) {
			// bootStrapTreeViewList.remove(rootNode);

			BootStrapTreeViewCheck bootStrapTreeViewCheck = new BootStrapTreeViewCheck(rootNode.getId(), rootNode.getText());
			List<BootStrapTreeView> whileList = new ArrayList<BootStrapTreeView>();
			whileList.addAll(bootStrapTreeViewList);
			List<BootStrapTreeView> swapList = new ArrayList<BootStrapTreeView>();
			swapList.addAll(bootStrapTreeViewList);
			List<BootStrapTreeView> parentList = new ArrayList<BootStrapTreeView>();
			List<BootStrapTreeView> swapParentList = new ArrayList<BootStrapTreeView>();
			parentList.add(bootStrapTreeViewCheck.rootNode);
			while (parentList.size() > 0 && !whileList.isEmpty()) {// 必须是并且关系，否则会死循环（因为里面有的节点可能根本就没有对应的父节点）
				for (int j = 0; j < parentList.size(); j++) {
					BootStrapTreeView parentNode = parentList.get(j);
					String parentNodeId = parentNode.getId();
					for (int i = 0; i < whileList.size(); i++) {
						BootStrapTreeView tmpNode = whileList.get(i);
						if (parentNodeId.equals(tmpNode.getParentId())) {
							Boolean isCheck =true;
							
							if(tmpNode.getState().get("checked") != null) {
								isCheck= Boolean.valueOf(tmpNode.getState().get("checked"));
							}
							BootStrapTreeView node = bootStrapTreeViewCheck.addNode(tmpNode.getId(), tmpNode.getText(),isCheck, parentNode);
							swapList.remove(tmpNode);
							swapParentList.add(node);
						}
					}
					whileList = new ArrayList<BootStrapTreeView>();
					whileList.addAll(swapList);
					if ((whileList.isEmpty())) {
						return bootStrapTreeViewCheck.toJsonString();
					}
				}
				parentList = new ArrayList<BootStrapTreeView>();
				parentList.addAll(swapParentList);
				swapParentList = new ArrayList<BootStrapTreeView>();
			}
			
			return bootStrapTreeViewCheck.toJsonString();
		}else {
			return  JSONArray.fromObject(bootStrapTreeViewList).toString();
		}
		
	}

	/**
	 * 寻找根节点
	 * 
	 * @param TreeNodeNoCheckList
	 * @param rootNodeId
	 * @return
	 */
	private static BootStrapTreeView findRootNode(List<BootStrapTreeView> BootStrapTreeViewList,
			String rootNodeId) {
		for (int i = 0; i < BootStrapTreeViewList.size(); i++) {
			BootStrapTreeView tmpNode = BootStrapTreeViewList.get(i);
			if (tmpNode.getId().equals(rootNodeId)) {
				return tmpNode;
			}
		}
		
		return null;
	}

	public static BootStrapTreeView createNew(String id, String text, String parentId) {
		return  createNew( id, text, true,  parentId,  "");
	}
	
	public static BootStrapTreeView createNew(String id,String text,boolean checked, String parentId){
		return  createNew( id, text, checked,  parentId,  "");
	}
	
	public static BootStrapTreeView createNew(String id,String text,boolean checked, String parentId, String href){
		BootStrapTreeView result = new BootStrapTreeView();
		result.setId(id);
		result.setNodeId(id);
		result.setText(text);
		Map<String,Boolean> stateMap = result.getState();
		stateMap.put("checked", checked);
		result.setState(stateMap);
		result.setParentId(parentId);
		result.setHref(href);
		return result;
	}

	public BootStrapTreeView getRootNode() {
		return rootNode;
	}

	public void setRootNode(BootStrapTreeView rootNode) {
		this.rootNode = rootNode;
	}
	
	
}
