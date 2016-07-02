package com.lj.app.core.common.tree;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleTreeTest {

	@Test
	public void simpleTreeTest() throws Exception {
		SimpleTree tree = new SimpleTree("0","v0",1);
		tree.addNode("11", "v11",1);
		tree.addNode("12", "v12",0);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void addNodeTest() throws Exception {
		SimpleTree tree = new SimpleTree("0","v0",1);
		tree.addNode("11", "v11",1);
		tree.addNode("12", "v12",0);
		TreeNode node = tree.addNode("13", "v13",0);
		tree.addNode("131", "v131", 0,node);
		tree.addNode("132", "v132", 1,node);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void addNodeWithParentNodeTest() throws Exception {
		SimpleTree tree = new SimpleTree("0","v0",1);
		tree.addNode("11", "v11",1);
		tree.addNode("12", "v12",0);
		TreeNode node = tree.addNode("13", "v13",0);
		tree.addNode("131", "v131", 0,node);
		tree.addNode("132", "v132", 1,node);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void processCheckedTest() {
		//TODO: test me
	}

	@Test
	public void toJsonStringTest() throws Exception {
		SimpleTree tree = new SimpleTree("0","v0",1);
		tree.addNode("11", "v11",1);
		tree.addNode("12", "v12",0);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void valueOfTest() throws Exception {
		List<DefaultTreeNode> treeNodeList = new ArrayList<DefaultTreeNode>();
		treeNodeList.add(SimpleTree.createNew("0", "v0", 1, null));
		treeNodeList.add(SimpleTree.createNew("11", "v11", 1, "0"));
		treeNodeList.add(SimpleTree.createNew("12", "v12", 0, "0"));
		treeNodeList.add(SimpleTree.createNew("13", "v13", 0, "0"));
		treeNodeList.add(SimpleTree.createNew("131", "v131", 0, "13"));
		treeNodeList.add(SimpleTree.createNew("132", "v132", 1, "13"));
		SimpleTree tree1 = SimpleTree.valueOf(treeNodeList, "0");
		System.out.println(tree1.toJsonString());
	}

	@Test
	public void createNewTest() throws Exception {
		DefaultTreeNode defaultTreeNode = SimpleTree.createNew("0", "v0", 1, null);
	}

}
