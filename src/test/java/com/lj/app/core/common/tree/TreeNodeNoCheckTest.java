package com.lj.app.core.common.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TreeNodeNoCheckTest {

	private TreeNodeNoCheck treeNodeNoCheck;
	
	@Before
	public void setUp() {
		treeNodeNoCheck = new TreeNodeNoCheck();
	}

	@Test
	public void setIdTest() {
		treeNodeNoCheck.setId("id");
		assertEquals("id",treeNodeNoCheck.getId());
	}

	@Test
	public void testSetText() {
		treeNodeNoCheck.setText("text");
		assertEquals("text",treeNodeNoCheck.getText());
	}

	@Test
	public void testAddChildren() {
		TreeNodeNoCheck treeNodeNoCheck = new TreeNodeNoCheck();
		treeNodeNoCheck.setId("id2");
		treeNodeNoCheck.setText("text2");
		treeNodeNoCheck.addChildren(treeNodeNoCheck);
		assertTrue(treeNodeNoCheck.getChildren().size()==1);
		
		TreeNodeNoCheck childrenNode3 = new TreeNodeNoCheck();
		childrenNode3.setId("id3");
		childrenNode3.setText("text3");
		treeNodeNoCheck.addChildren(childrenNode3);
		assertTrue(treeNodeNoCheck.getChildren().size()==2);
	}

}
