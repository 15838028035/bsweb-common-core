package com.lj.app.core.common.flows;

import org.junit.Test;

import com.lj.app.core.common.flows.model.ProcessModel;

public class DefaultNoGeneratorTest {

	@Test
	public void generateTest() {
		DefaultNoGenerator DefaultNoGenerator = new DefaultNoGenerator();
		ProcessModel model = new ProcessModel();
		String generatorNo = DefaultNoGenerator.generate(model);
		System.out.println("generatorNo="+generatorNo);
	}

}
