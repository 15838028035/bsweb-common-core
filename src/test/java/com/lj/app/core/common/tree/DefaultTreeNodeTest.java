package com.lj.app.core.common.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DefaultTreeNodeTest {

	private DefaultTreeNode defaultTreeNode;
	
	@Before
	public void setUp() {
		defaultTreeNode = new DefaultTreeNode();
	}

	@Test
	public void setIdTest() {
		defaultTreeNode.setId("id");
		assertEquals("id",defaultTreeNode.getId());
	}
	
	@Test
	public void setCheckedTest() {
		defaultTreeNode.setChecked(0);
		assertEquals(0,defaultTreeNode.getChecked());
	}
	

	@Test
	public void setParentIdTest() {
		defaultTreeNode.setParentId("parentId");
		assertEquals("parentId",defaultTreeNode.getParentId());
	}

	@Test
	public void setTextTest() {
		defaultTreeNode.setText("text");
		assertEquals("text",defaultTreeNode.getText());
	}

}
