package com.lj.app.core.common.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TreeNodeTest {

	private TreeNode treeNode;
	
	@Before
	public void setUp() {
		treeNode = new TreeNode();
	}

	@Test
	public void setIdTest() {
		treeNode.setId("id");
		assertEquals("id",treeNode.getId());
	}

	@Test
	public void setCheckedTest() {
		treeNode.setChecked(1);
		assertEquals(1,treeNode.getChecked());
	}

	@Test
	public void testSetText() {
		treeNode.setText("text");
		assertEquals("text",treeNode.getText());
	}

	@Test
	public void testAddChildren() {
		TreeNode childrenNode = new TreeNode();
		childrenNode.setId("id2");
		childrenNode.setText("text2");
		treeNode.addChildren(childrenNode);
		assertTrue(treeNode.getChildren().size()==1);
		
		TreeNode childrenNode3 = new TreeNode();
		childrenNode3.setId("id3");
		childrenNode3.setText("text3");
		treeNode.addChildren(childrenNode3);
		assertTrue(treeNode.getChildren().size()==2);
	}

}
