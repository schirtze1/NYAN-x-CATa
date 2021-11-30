package org.spiderflow.core.executor.shape;

import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spiderflow.context.SpiderContext;
import org.spiderflow.core.Spider;
import org.spiderflow.core.model.SpiderFlow;
import org.spiderflow.core.service.SpiderFlowService;
import org.spiderflow.core.utils.SpiderFlowUtils;
import org.spiderflow.executor.ShapeExecutor;
import org.spiderflow.model.SpiderNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 子流程执行器
 * @author Administrator
 *
 */
@Component
public class ProcessExecutor implements ShapeExecutor{
	
	public static final String FLOW_ID = "flowId";
	
	@Autowired
	private SpiderFlowService spiderFlowService;
	
	@Autowired
	private Spider spider;
	
	private static Logger logger = LoggerFactory.getLogger(ProcessExecutor.class);

	@Override
	@Transactional
	public void execute(SpiderNode node, SpiderContext context, Map<String,Object> variables) {
		String flowId = node.getStringJsonValue("flowId");
		SpiderFlow spiderFlow = spiderFlowService.get(flowId);
		if(spiderFlow != null){
			if(logger.isDebugEnabled()){
				logger.debug("执行子流程:{}",spiderFlow.getName());
				context.log(String.format("执行子流程:%s", spiderFlow.getName()));
			}
			SpiderNode root = SpiderFlowUtils.loadXMLFromString(spiderFlow.getXml());
			spider.executeNode(context.getThreadPool(),null,root,context,variables);
		}else{
			logger.warn("找不到流程:{}",flowId);
			context.log(String.format("找不到流程:%s", flowId));
		}
		
	}

	@Override
	public String supportShape() {
		return "process";
	}

}
