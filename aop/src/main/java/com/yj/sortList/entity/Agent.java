package com.yj.sortList.entity;

public class Agent  {
    private String name;
    private int calls;

    public Agent(String name , int calls){
        this.name = name;
        this.calls = calls;
    }

    @Override
    public String toString(){
        return "Agent{" +
                "name='" + name + '\'' +
                ", calls=" + calls +
                '}';
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCalls(){
        return calls;
    }

    public void setCalls(int calls){
        this.calls = calls;
    }

    /*@Override
    public Agent clone() throws CloneNotSupportedException{
        Agent agent = new Agent(this.name,this.calls);
        return agent;
    }*/
}
