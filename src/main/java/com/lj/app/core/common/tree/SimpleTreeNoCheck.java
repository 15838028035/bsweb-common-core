package com.lj.app.core.common.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;


public class SimpleTreeNoCheck {
	private TreeNodeNoCheck rootNode;
	private boolean flag;//true:已处理过；false:未处理过
	/**
	 * 唯一构造函数
	 * @param rootId
	 * @param rootText
	 * @param checked 值为：0,1,2，如果非法，默认为是0
	 */
	public SimpleTreeNoCheck(String rootId,String rootText) {
		rootNode = new TreeNodeNoCheck();
		rootNode.setId(rootId);
		rootNode.setText(rootText);
		flag = false;
	}
	/**
	 * 增加节点，默认父节点为根节点
	 * @param id
	 * @param text
	 * @return
	 */
	public TreeNodeNoCheck addNode(String id,String text){
		return addNode(id, text, null);
	}
	/**
	 * 增加节点
	 * @param id
	 * @param text
	 * @param checked 值为：0,1,2，如果非法，默认为是0
	 * @param parentNode 如果为null，将默认为根节点
	 * @return
	 */
	public TreeNodeNoCheck addNode(String id,String text,TreeNodeNoCheck parentNode){
		TreeNodeNoCheck node = new TreeNodeNoCheck();
		node.setId(id);
		node.setText(text);
		if(null==parentNode || parentNode.equals(rootNode)){
			rootNode.addChildren(node);
		}
		else{
			parentNode.addChildren(node);
		}
		flag = false;
		
		return node;
	}

	/**
	 * 以json方式返回对象
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String toJsonString() throws Exception{
		JSONObject jsonObject = new JSONObject();
		return jsonObject.fromObject(rootNode).toString();
	}
	/**
	 * 将List&lt;DefaultTreeNodeNoCheck&gt;转换为SimpleTree对象。
	 * @param TreeNodeNoCheckList
	 * @param rootNodeId 
	 * @return
	 */
	public static SimpleTreeNoCheck valueOf(List<DefaultTreeNodeNoCheck> TreeNodeNoCheckList,String rootNodeId)throws Exception{
		if(null==TreeNodeNoCheckList || TreeNodeNoCheckList.isEmpty() || null==rootNodeId){
			return null;
		}
		
		DefaultTreeNodeNoCheck rootNode = findRootNode(TreeNodeNoCheckList, rootNodeId);//根节点
		if(null!=rootNode){
			TreeNodeNoCheckList.remove(rootNode);
			
			SimpleTreeNoCheck simpleTree = new SimpleTreeNoCheck(rootNode.getId(),rootNode.getText());
			List<DefaultTreeNodeNoCheck> whileList = new ArrayList<DefaultTreeNodeNoCheck>();
			whileList.addAll(TreeNodeNoCheckList);
			List<DefaultTreeNodeNoCheck> swapList = new ArrayList<DefaultTreeNodeNoCheck>();
			swapList.addAll(TreeNodeNoCheckList);
			List<TreeNodeNoCheck> parentList = new ArrayList<TreeNodeNoCheck>();
			List<TreeNodeNoCheck> swapParentList = new ArrayList<TreeNodeNoCheck>();
			parentList.add(simpleTree.rootNode);
			while(parentList.size()>0 && !whileList.isEmpty()){//必须是并且关系，否则会死循环（因为里面有的节点可能根本就没有对应的父节点）
				for(int j=0; j<parentList.size(); j++){
					TreeNodeNoCheck parentNode = parentList.get(j);
					String parentNodeId = parentNode.getId();
					for(int i=0; i<whileList.size(); i++){
						DefaultTreeNodeNoCheck tmpNode = whileList.get(i);
						if(parentNodeId.equals(tmpNode.getParentId())){
							TreeNodeNoCheck node = simpleTree.addNode(tmpNode.getId(), tmpNode.getText(), parentNode);
							swapList.remove(tmpNode);
							swapParentList.add(node);
						}
					}
					whileList = new ArrayList<DefaultTreeNodeNoCheck>();
					whileList.addAll(swapList);
					if((whileList.isEmpty())){
						return simpleTree;
					}
				}
				parentList = new ArrayList<TreeNodeNoCheck>();
				parentList.addAll(swapParentList);
				swapParentList = new ArrayList<TreeNodeNoCheck>();
			}
			return simpleTree;
		}
		return null;
	}
	
	/**
	 * 寻找根节点
	 * @param TreeNodeNoCheckList
	 * @param rootNodeId
	 * @return
	 */
	private static DefaultTreeNodeNoCheck findRootNode(List<DefaultTreeNodeNoCheck> TreeNodeNoCheckList,String rootNodeId){
		for(int i=0; i<TreeNodeNoCheckList.size(); i++){
			DefaultTreeNodeNoCheck tmpNode = TreeNodeNoCheckList.get(i);
			if(tmpNode.getId().equals(rootNodeId)){
				return tmpNode;
			}
		}
		return null;
	}
	
	public static DefaultTreeNodeNoCheck createNew(String id,String text, String parentId){
		DefaultTreeNodeNoCheck result = new DefaultTreeNodeNoCheck();
		result.setId(id);
		result.setText(text);
		result.setParentId(parentId);
		return result;
	}
}