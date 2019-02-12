package us.acgn.reversegrpc;

import us.acgn.testservice.TestServiceGrpc.TestServiceImplBase;

public class Main {
  public static void main(String[] args) {
    TestServiceImplBase impl = ReverseGrpcServer.getServerStub(TestServiceImplBase.class);
  }
}
