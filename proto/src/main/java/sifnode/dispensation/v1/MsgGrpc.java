package sifnode.dispensation.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: sifnode/dispensation/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "sifnode.dispensation.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgCreateDistribution,
      sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse> getCreateDistributionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDistribution",
      requestType = sifnode.dispensation.v1.Tx.MsgCreateDistribution.class,
      responseType = sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgCreateDistribution,
      sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse> getCreateDistributionMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgCreateDistribution, sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse> getCreateDistributionMethod;
    if ((getCreateDistributionMethod = MsgGrpc.getCreateDistributionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDistributionMethod = MsgGrpc.getCreateDistributionMethod) == null) {
          MsgGrpc.getCreateDistributionMethod = getCreateDistributionMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.Tx.MsgCreateDistribution, sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDistribution"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.Tx.MsgCreateDistribution.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDistribution"))
              .build();
        }
      }
    }
    return getCreateDistributionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgCreateUserClaim,
      sifnode.dispensation.v1.Tx.MsgCreateClaimResponse> getCreateUserClaimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUserClaim",
      requestType = sifnode.dispensation.v1.Tx.MsgCreateUserClaim.class,
      responseType = sifnode.dispensation.v1.Tx.MsgCreateClaimResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgCreateUserClaim,
      sifnode.dispensation.v1.Tx.MsgCreateClaimResponse> getCreateUserClaimMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgCreateUserClaim, sifnode.dispensation.v1.Tx.MsgCreateClaimResponse> getCreateUserClaimMethod;
    if ((getCreateUserClaimMethod = MsgGrpc.getCreateUserClaimMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateUserClaimMethod = MsgGrpc.getCreateUserClaimMethod) == null) {
          MsgGrpc.getCreateUserClaimMethod = getCreateUserClaimMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.Tx.MsgCreateUserClaim, sifnode.dispensation.v1.Tx.MsgCreateClaimResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUserClaim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.Tx.MsgCreateUserClaim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.Tx.MsgCreateClaimResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateUserClaim"))
              .build();
        }
      }
    }
    return getCreateUserClaimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgRunDistribution,
      sifnode.dispensation.v1.Tx.MsgRunDistributionResponse> getRunDistributionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RunDistribution",
      requestType = sifnode.dispensation.v1.Tx.MsgRunDistribution.class,
      responseType = sifnode.dispensation.v1.Tx.MsgRunDistributionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgRunDistribution,
      sifnode.dispensation.v1.Tx.MsgRunDistributionResponse> getRunDistributionMethod() {
    io.grpc.MethodDescriptor<sifnode.dispensation.v1.Tx.MsgRunDistribution, sifnode.dispensation.v1.Tx.MsgRunDistributionResponse> getRunDistributionMethod;
    if ((getRunDistributionMethod = MsgGrpc.getRunDistributionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRunDistributionMethod = MsgGrpc.getRunDistributionMethod) == null) {
          MsgGrpc.getRunDistributionMethod = getRunDistributionMethod =
              io.grpc.MethodDescriptor.<sifnode.dispensation.v1.Tx.MsgRunDistribution, sifnode.dispensation.v1.Tx.MsgRunDistributionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RunDistribution"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.Tx.MsgRunDistribution.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sifnode.dispensation.v1.Tx.MsgRunDistributionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RunDistribution"))
              .build();
        }
      }
    }
    return getRunDistributionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgStub>() {
        @java.lang.Override
        public MsgStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgStub(channel, callOptions);
        }
      };
    return MsgStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgBlockingStub>() {
        @java.lang.Override
        public MsgBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgBlockingStub(channel, callOptions);
        }
      };
    return MsgBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgFutureStub>() {
        @java.lang.Override
        public MsgFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgFutureStub(channel, callOptions);
        }
      };
    return MsgFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void createDistribution(sifnode.dispensation.v1.Tx.MsgCreateDistribution request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateDistributionMethod(), responseObserver);
    }

    /**
     */
    public void createUserClaim(sifnode.dispensation.v1.Tx.MsgCreateUserClaim request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgCreateClaimResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateUserClaimMethod(), responseObserver);
    }

    /**
     */
    public void runDistribution(sifnode.dispensation.v1.Tx.MsgRunDistribution request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgRunDistributionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRunDistributionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateDistributionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.Tx.MsgCreateDistribution,
                sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse>(
                  this, METHODID_CREATE_DISTRIBUTION)))
          .addMethod(
            getCreateUserClaimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.Tx.MsgCreateUserClaim,
                sifnode.dispensation.v1.Tx.MsgCreateClaimResponse>(
                  this, METHODID_CREATE_USER_CLAIM)))
          .addMethod(
            getRunDistributionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sifnode.dispensation.v1.Tx.MsgRunDistribution,
                sifnode.dispensation.v1.Tx.MsgRunDistributionResponse>(
                  this, METHODID_RUN_DISTRIBUTION)))
          .build();
    }
  }

  /**
   */
  public static final class MsgStub extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
    private MsgStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgStub(channel, callOptions);
    }

    /**
     */
    public void createDistribution(sifnode.dispensation.v1.Tx.MsgCreateDistribution request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateDistributionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createUserClaim(sifnode.dispensation.v1.Tx.MsgCreateUserClaim request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgCreateClaimResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateUserClaimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void runDistribution(sifnode.dispensation.v1.Tx.MsgRunDistribution request,
        io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgRunDistributionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRunDistributionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MsgBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
    private MsgBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgBlockingStub(channel, callOptions);
    }

    /**
     */
    public sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse createDistribution(sifnode.dispensation.v1.Tx.MsgCreateDistribution request) {
      return blockingUnaryCall(
          getChannel(), getCreateDistributionMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.dispensation.v1.Tx.MsgCreateClaimResponse createUserClaim(sifnode.dispensation.v1.Tx.MsgCreateUserClaim request) {
      return blockingUnaryCall(
          getChannel(), getCreateUserClaimMethod(), getCallOptions(), request);
    }

    /**
     */
    public sifnode.dispensation.v1.Tx.MsgRunDistributionResponse runDistribution(sifnode.dispensation.v1.Tx.MsgRunDistribution request) {
      return blockingUnaryCall(
          getChannel(), getRunDistributionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MsgFutureStub extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
    private MsgFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse> createDistribution(
        sifnode.dispensation.v1.Tx.MsgCreateDistribution request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateDistributionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.Tx.MsgCreateClaimResponse> createUserClaim(
        sifnode.dispensation.v1.Tx.MsgCreateUserClaim request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateUserClaimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sifnode.dispensation.v1.Tx.MsgRunDistributionResponse> runDistribution(
        sifnode.dispensation.v1.Tx.MsgRunDistribution request) {
      return futureUnaryCall(
          getChannel().newCall(getRunDistributionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DISTRIBUTION = 0;
  private static final int METHODID_CREATE_USER_CLAIM = 1;
  private static final int METHODID_RUN_DISTRIBUTION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_DISTRIBUTION:
          serviceImpl.createDistribution((sifnode.dispensation.v1.Tx.MsgCreateDistribution) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgCreateDistributionResponse>) responseObserver);
          break;
        case METHODID_CREATE_USER_CLAIM:
          serviceImpl.createUserClaim((sifnode.dispensation.v1.Tx.MsgCreateUserClaim) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgCreateClaimResponse>) responseObserver);
          break;
        case METHODID_RUN_DISTRIBUTION:
          serviceImpl.runDistribution((sifnode.dispensation.v1.Tx.MsgRunDistribution) request,
              (io.grpc.stub.StreamObserver<sifnode.dispensation.v1.Tx.MsgRunDistributionResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sifnode.dispensation.v1.Tx.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Msg");
    }
  }

  private static final class MsgFileDescriptorSupplier
      extends MsgBaseDescriptorSupplier {
    MsgFileDescriptorSupplier() {}
  }

  private static final class MsgMethodDescriptorSupplier
      extends MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MsgGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgFileDescriptorSupplier())
              .addMethod(getCreateDistributionMethod())
              .addMethod(getCreateUserClaimMethod())
              .addMethod(getRunDistributionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
