package com.yj.sortList.service.impl;

import com.yj.sortList.entity.Agent;
import com.yj.sortList.service.AgentService;
import com.yj.sortList.util.SetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hashmap
 */
public class AgentServiceImpl1 implements AgentService {

    @Override
    public List<Agent> sortAgentCalls(List<Agent> agentList){
        int size = agentList.size();
        Map<Integer,Agent> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(i,agentList.get(i));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i -1; j++) {
                Agent agent1 = map.get(j + 1 );
                Agent agent2 = map.get(j);
                if (agent1.getCalls() > agent2.getCalls()){
                    map.put(j, agent1);
                    map.put(j+1, agent2);
                }
            }
        }
        List<Agent> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(map.get(i));
        }
        /*System.out.println("排序前：");
        SetUtil.printArrayList(agentList);
        System.out.println("排序后：");
        SetUtil.printArrayList(list);*/
        return list;
    }
}
