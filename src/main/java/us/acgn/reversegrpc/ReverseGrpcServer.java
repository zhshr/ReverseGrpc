package us.acgn.reversegrpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReverseGrpcServer {

  public static <T extends io.grpc.BindableService> T getServerStub(Class<T> clazz) {
    try {
      Method bindService = clazz.getMethod("bindService");
      bindService.invoke(null);

      return clazz.getDeclaredConstructor().newInstance();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
    return null;
  }
}
