package 老马书城2IO流版;

import java.io.Serializable;

public class book implements Serializable{
    //属性

    //书籍编号
    private int bNo;

    //书籍名称
    private String bName;

    //书籍作者
    private String bAuthor;

    public int getbNo() {
          return bNo;
      }

      public void setbNo(int bNo) {
          this.bNo = bNo;
      }


    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    // 构造器：
     public book(int bNo, String bName, String bAuthor){
      this.bNo = bNo;
      this.bName = bName;
      this.bAuthor = bAuthor;
     }

      public book(){}
}

     
