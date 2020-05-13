package sortList;

import com.yj.sortList.entity.Agent;
import com.yj.sortList.handler.PerformanceHandler;
import com.yj.sortList.service.AgentService;
import com.yj.sortList.service.impl.AgentServiceImpl1;
import com.yj.sortList.service.impl.AgentServiceImpl2;
import com.yj.sortList.service.impl.AgentServiceImpl3;
import com.yj.sortList.util.SetUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AgentTest{
    @Test
    public void agentServiceImpl1Test(){
        List<Agent> agents1 = prepareAgentList(100);
        List<Agent> agents2 = cloneAgentList(agents1);
        List<Agent> agents3 = cloneAgentList(agents1);

        AgentService service1 = new AgentServiceImpl1();
        AgentService service2 = new AgentServiceImpl2();
        AgentService service3 = new AgentServiceImpl3();

        testPerformance(service1, agents1);
        testPerformance(service2, agents2);
        testPerformance(service3, agents3);
    }


    public void testPerformance(AgentService realService,List<Agent> agents){
        PerformanceHandler handler = new PerformanceHandler(realService);
        ClassLoader classLoader = realService.getClass().getClassLoader();
        Class<?>[] interfaces = realService.getClass().getInterfaces();

        AgentService service = (AgentService)Proxy.newProxyInstance(classLoader,interfaces,handler);
        //System.out.println("动态代理对象的类型：" + service.getClass().getName());
        service.sortAgentCalls(agents);
    }

    public List<Agent> prepareAgentList(int listSize){
        List<Agent> list = new ArrayList<>(listSize);
        Random r = new Random();
        for (int i = 0; i < listSize; i++) {
            list.add(new Agent(i +"",r.nextInt(listSize)));
        }
        return list;
    }

    public List<Agent> cloneAgentList(List<Agent> agents){
        List<Agent> list = new ArrayList<>(agents.size());
        for (Agent a: agents
             ) {
            list.add(a);
        }
        return list;
    }

}
