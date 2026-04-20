package Practice03.team.service;

import Practice03.team.domain.Architect;
import Practice03.team.domain.Designer;
import Practice03.team.domain.Employee;
import Practice03.team.domain.Programmer;

public class TeamService {
    private int counter = 1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;

    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for(int i = 0; i < total; i ++){
            team[i] = this.team[i];
        }

        return team;
    }

    public void addMember(Employee e) throws TeamException{
        //成员已满，无法添加
         if(total >= MAX_MEMBER){
             throw new TeamException("成员已满，无法添加");
         }

         //该成员不是开发人员，无法添加
         if(!(e instanceof Programmer)){
         throw new TeamException("该成员不是开发人员，无法添加");
         }

         Programmer p = (Programmer) e;
         Status status = p.getStatus();
         switch (status){
             case BUSY :
                 throw new TeamException("已在团队中");
             case VOCATION:
                 throw new TeamException("改员工在休假");
         }

         //该员工已经在开发团队中
        boolean is = isExist(p);
        if(is){
            throw  new TeamException("已经在团队中");
        }

        int ProNum,DesNum,ArcNum;
        ProNum = DesNum = ArcNum = 0;
        for(int i = 0; i < total ; i++){
            if(team[i] instanceof Architect){
                ArcNum++;
            }else if(team[i] instanceof Designer){
                DesNum++;
            }else {
                ProNum++;
            }
        }

        if(p instanceof Architect){
            if(ArcNum >= 1){
                throw new TeamException("架构师不能超过1名");
            }
        }else if(p instanceof Designer){
            if(DesNum >= 2){
                throw new TeamException("设计师不能超过2名");
            }
        }else {
            if(ProNum >= 3){
                throw new TeamException("程序员不能超过3名");
            }
        }

        team[total++] = p;
        p.setMemberId(counter++);
        p.setStatus(Status.BUSY );

    }

    public void removeMember(int memberId) throws TeamException{
        int i = 0;
      for(; i < total; i ++){

          if(team[i].getMemberId() == memberId){
           //找到了
            team[i].setStatus(Status.FREE);
            //员工的memberId可以不改
           break;
          }
      }

      if( i == total){
          throw  new TeamException("找不到指定的员工，删除失败");
      }

      //调整数组
        for(int j = i; j < total-1; j ++){
            team[j] = team[j+1];
        }
        team[--total] = null;
    }

    //判断p是否存在于开发团队中
    private boolean isExist(Programmer p){

        for(int i = 0 ;i < total; i++){
            if(team[i].getId() == p.getId())
                return true;
        }
        return false;
    }

}
