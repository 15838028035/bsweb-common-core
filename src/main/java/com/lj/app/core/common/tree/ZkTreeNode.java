package com.lj.app.core.common.tree;

import java.util.List;

/**
 * zk树
 *
 */
public class ZkTreeNode {

	private String id;
	private String id2;
	private String name;
	private String nodeType;//节点类型 root, child
	private Boolean isParent = false;
	private Boolean open= true;
	
	private List<ZkTreeNode> nodes;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getId2() {
		return id2;
	}


	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<ZkTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ZkTreeNode> nodes) {
		this.nodes = nodes;
	}
	
}
