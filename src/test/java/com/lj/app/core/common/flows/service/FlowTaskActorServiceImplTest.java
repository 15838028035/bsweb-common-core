package com.lj.app.core.common.flows.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowTaskActor;

public class FlowTaskActorServiceImplTest extends FlowBaseTest{

	@Autowired
	private FlowTaskActorService flowTaskActorService;
	
	@Test
	public void removeTaskActorTest() throws Exception {
		 List<FlowTaskActor> list = flowTaskActorService.getTaskActorsByTaskId(null);
		 assertNotNull(list);
		 assertTrue(list.size()>0);
		 
		 FlowTaskActor flowTaskActor = list.get(0);
		 
		flowTaskActorService.removeTaskActor(flowTaskActor.getTaskId(), flowTaskActor.getActorId());
		
		list = flowTaskActorService.getTaskActorsByTaskId(flowTaskActor.getId().toString());
		 assertTrue(list==null||list.size()==0);
	}

}
