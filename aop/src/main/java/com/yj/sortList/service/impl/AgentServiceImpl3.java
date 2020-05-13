package com.yj.sortList.service.impl;

import com.yj.sortList.entity.Agent;
import com.yj.sortList.service.AgentService;
import com.yj.sortList.util.SetUtil;

import java.util.Arrays;
import java.util.List;

/**
 * array
 */
public class AgentServiceImpl3 implements AgentService {

    @Override
    public List<Agent> sortAgentCalls(List<Agent> agentList){
        //System.out.println("排序前：");
        //SetUtil.printArrayList(agentList);
        int size = agentList.size();
        Agent[] agents = agentList.toArray(new Agent[size]);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i -1; j++) {
                Agent agent1 = agents[j+1];
                Agent agent2 = agents[j];
                if (agent1.getCalls() > agent2.getCalls()){
                    agents[j+1] = agent2;
                    agents[j] = agent1;
                }
            }
        }
        //System.out.println("排序后：");
        List<Agent> list = Arrays.asList(agents);
        //SetUtil.printArrayList(list);
        return list;
    }
}
