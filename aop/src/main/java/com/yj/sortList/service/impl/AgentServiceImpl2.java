package com.yj.sortList.service.impl;

import com.yj.sortList.entity.Agent;
import com.yj.sortList.service.AgentService;
import com.yj.sortList.util.SetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * list.set(index,element)
 */
public class AgentServiceImpl2 implements AgentService {

    @Override
    public List<Agent> sortAgentCalls(List<Agent> agentList){
        //System.out.println("排序前：");
        //SetUtil.printArrayList(agentList);
        int size = agentList.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i -1; j++) {
                Agent agent1 = agentList.get(j+1);
                Agent agent2 = agentList.get(j);
                if (agent1.getCalls() > agent2.getCalls()){
                    agentList.set(j, agent1);
                    agentList.set(j+1, agent2);
                }
            }
        }
        //System.out.println("排序后：");
        //SetUtil.printArrayList(agentList);
        return agentList;
    }
}
