package com.example.demo.oop.compagne.desginpattern.task.abstractfactorypattern;

import com.example.demo.oop.compagne.desginpattern.task.decorator.CodingTask;
import com.example.demo.oop.compagne.desginpattern.task.decorator.LoggingRunnable;
import com.example.demo.oop.compagne.desginpattern.task.decorator.TransactionalRunnable;

public interface AbstractFactory {
  
   CodingTask createCodingTask();
   LoggingRunnable createLoggingTask();
   TransactionalRunnable createTransctionalTask();
   
}
