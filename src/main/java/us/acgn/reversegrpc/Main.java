package us.acgn.reversegrpc;

import us.acgn.testservice.TestServiceGrpc;
import us.acgn.testservice.TestServiceGrpc.TestServiceImplBase;

public class Main {
    public static void main(String[] args) {
//    TestServiceImplBase impl = ReverseGrpcServer.getServerStub(TestServiceImplBase.class);
        ReverseGrpc abc = new ReverseGrpc(TestServiceGrpc.class);
        TestServiceImplBase service = abc.getServerStub(TestServiceImplBase.class);
        service.abc(null, null);
    }
}
