package com.projectprac.rocketmqspringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable { //实现序列化,底层能将其数组字节化
     String name;
      int age;


}
