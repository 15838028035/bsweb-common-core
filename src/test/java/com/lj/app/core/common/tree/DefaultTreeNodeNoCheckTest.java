package com.lj.app.core.common.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DefaultTreeNodeNoCheckTest {

	private DefaultTreeNodeNoCheck defaultTreeNodeNoCheck;
	
	@Before
	public void setUp() {
		defaultTreeNodeNoCheck = new DefaultTreeNodeNoCheck();
	}

	@Test
	public void setIdTest() {
		defaultTreeNodeNoCheck.setId("id");
		assertEquals("id",defaultTreeNodeNoCheck.getId());
	}

	@Test
	public void setParentIdTest() {
		defaultTreeNodeNoCheck.setParentId("parentId");
		assertEquals("parentId",defaultTreeNodeNoCheck.getParentId());
	}

	@Test
	public void setTextTest() {
		defaultTreeNodeNoCheck.setText("text");
		assertEquals("text",defaultTreeNodeNoCheck.getText());
	}

}
