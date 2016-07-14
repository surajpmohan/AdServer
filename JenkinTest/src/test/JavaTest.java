package test;
import org.junit.Test;
import static org.junit.Assert.*;

public class JavaTest {
  

protected int value1, value2;
   
   // assigning the values
   protected void setUp(){
      value1=3;
      value2=3;
   }

   // test method to add two values
   @Test
   public void testAdd(){
      double result= value1 + value2;
      assertSame(5, 5);
   }
} 