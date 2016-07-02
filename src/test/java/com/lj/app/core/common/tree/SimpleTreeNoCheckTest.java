package com.lj.app.core.common.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleTreeNoCheckTest {

	@Test
	public void SimpleTreeNoCheckTest() throws Exception {
		SimpleTreeNoCheck tree = new SimpleTreeNoCheck("0","v0");
		tree.addNode("11", "v11");
		tree.addNode("12", "v12");
		TreeNodeNoCheck node = tree.addNode("13", "v13");
		tree.addNode("131", "v131", node);
		tree.addNode("132", "v132", node);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void addNodeTest() throws Exception {
		SimpleTreeNoCheck tree = new SimpleTreeNoCheck("0","v0");
		tree.addNode("11", "v11");
		tree.addNode("12", "v12");
		TreeNodeNoCheck node = tree.addNode("13", "v13");
		tree.addNode("131", "v131", node);
		tree.addNode("132", "v132", node);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void addNodeWithParentNodeTest() throws Exception {
		SimpleTreeNoCheck tree = new SimpleTreeNoCheck("0","v0");
		tree.addNode("11", "v11");
		tree.addNode("12", "v12");
		TreeNodeNoCheck node = tree.addNode("13", "v13");
		tree.addNode("131", "v131", node);
		tree.addNode("132", "v132", node);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void processCheckedTest() {
		//TODO: test me
	}

	@Test
	public void toJsonStringTest() throws Exception {
		SimpleTreeNoCheck tree = new SimpleTreeNoCheck("0","v0");
		tree.addNode("11", "v11");
		tree.addNode("12", "v12");
		TreeNodeNoCheck node = tree.addNode("13", "v13");
		tree.addNode("131", "v131", node);
		tree.addNode("132", "v132", node);
		System.out.println(tree.toJsonString());
	}

	@Test
	public void valueOfTest() throws Exception {
		List<DefaultTreeNodeNoCheck> TreeNodeNoCheckList = new ArrayList<DefaultTreeNodeNoCheck>();
		TreeNodeNoCheckList.add(SimpleTreeNoCheck.createNew("0", "v0",  null));
		TreeNodeNoCheckList.add(SimpleTreeNoCheck.createNew("11", "v11",  "0"));
		TreeNodeNoCheckList.add(SimpleTreeNoCheck.createNew("12", "v12", "0"));
		TreeNodeNoCheckList.add(SimpleTreeNoCheck.createNew("13", "v13",  "0"));
		TreeNodeNoCheckList.add(SimpleTreeNoCheck.createNew("131", "v131", "13"));
		TreeNodeNoCheckList.add(SimpleTreeNoCheck.createNew("132", "v132",  "13"));
		SimpleTreeNoCheck tree1 = SimpleTreeNoCheck.valueOf(TreeNodeNoCheckList,"0");
		System.out.println(tree1.toJsonString());
	}

	@Test
	public void createNewTest() throws Exception {
		DefaultTreeNodeNoCheck defaultTreeNodeNoCheck = SimpleTreeNoCheck.createNew("0", "v0", "1");
	}

}
