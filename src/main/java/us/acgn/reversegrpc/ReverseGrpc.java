package us.acgn.reversegrpc;

import io.grpc.ServiceDescriptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReverseGrpc {

  private final Class<?> rpcClazz;

  public ReverseGrpc(Class<?> rpcClazz) {
    this.rpcClazz = rpcClazz;
    try {
      initiateFromClazz();
    } catch (Exception e) {
      throw new RuntimeException("Reflection Error", e);
    }
  }

  private void initiateFromClazz()
      throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    ServiceDescriptor sd =
        (ServiceDescriptor) rpcClazz.getMethod("getServiceDescriptor").invoke(null);
    System.out.println(sd.getName());

    Class[] subClasses = rpcClazz.getClasses();
  }

  public <T extends io.grpc.BindableService> T getServerStub(Class<T> clazz) {
    ClassLoader cl = this.getClass().getClassLoader();
    Class[] interfaces = new Class[] {T.class};

    Object object =
        Proxy.newProxyInstance(
            cl,
            interfaces,
            new InvocationHandler() {
              public Object invoke(Object proxy, Method method, Object[] args) {
                String name = method.getName();
                if (name.equals("toString")) {
                  return "toString was called";
                } else if (name.equals("actionPerformed")) {
                  System.out.println("actionPerformed was called");
                  return null;
                }
                throw new RuntimeException("no method found");
              }
            });

    return (T) object;
  }
}
