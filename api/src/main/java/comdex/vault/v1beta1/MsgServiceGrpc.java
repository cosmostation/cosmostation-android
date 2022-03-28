package comdex.vault.v1beta1;

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
    comments = "Source: comdex/vault/v1beta1/msg.proto")
public final class MsgServiceGrpc {

  private MsgServiceGrpc() {}

  public static final String SERVICE_NAME = "comdex.vault.v1beta1.MsgService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgCreateRequest,
      comdex.vault.v1beta1.Msg.MsgCreateResponse> getMsgCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgCreate",
      requestType = comdex.vault.v1beta1.Msg.MsgCreateRequest.class,
      responseType = comdex.vault.v1beta1.Msg.MsgCreateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgCreateRequest,
      comdex.vault.v1beta1.Msg.MsgCreateResponse> getMsgCreateMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgCreateRequest, comdex.vault.v1beta1.Msg.MsgCreateResponse> getMsgCreateMethod;
    if ((getMsgCreateMethod = MsgServiceGrpc.getMsgCreateMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgCreateMethod = MsgServiceGrpc.getMsgCreateMethod) == null) {
          MsgServiceGrpc.getMsgCreateMethod = getMsgCreateMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Msg.MsgCreateRequest, comdex.vault.v1beta1.Msg.MsgCreateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgCreate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgCreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgCreateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgCreate"))
              .build();
        }
      }
    }
    return getMsgCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgDepositRequest,
      comdex.vault.v1beta1.Msg.MsgDepositResponse> getMsgDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgDeposit",
      requestType = comdex.vault.v1beta1.Msg.MsgDepositRequest.class,
      responseType = comdex.vault.v1beta1.Msg.MsgDepositResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgDepositRequest,
      comdex.vault.v1beta1.Msg.MsgDepositResponse> getMsgDepositMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgDepositRequest, comdex.vault.v1beta1.Msg.MsgDepositResponse> getMsgDepositMethod;
    if ((getMsgDepositMethod = MsgServiceGrpc.getMsgDepositMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgDepositMethod = MsgServiceGrpc.getMsgDepositMethod) == null) {
          MsgServiceGrpc.getMsgDepositMethod = getMsgDepositMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Msg.MsgDepositRequest, comdex.vault.v1beta1.Msg.MsgDepositResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgDeposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgDepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgDepositResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgDeposit"))
              .build();
        }
      }
    }
    return getMsgDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgWithdrawRequest,
      comdex.vault.v1beta1.Msg.MsgWithdrawResponse> getMsgWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgWithdraw",
      requestType = comdex.vault.v1beta1.Msg.MsgWithdrawRequest.class,
      responseType = comdex.vault.v1beta1.Msg.MsgWithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgWithdrawRequest,
      comdex.vault.v1beta1.Msg.MsgWithdrawResponse> getMsgWithdrawMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgWithdrawRequest, comdex.vault.v1beta1.Msg.MsgWithdrawResponse> getMsgWithdrawMethod;
    if ((getMsgWithdrawMethod = MsgServiceGrpc.getMsgWithdrawMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgWithdrawMethod = MsgServiceGrpc.getMsgWithdrawMethod) == null) {
          MsgServiceGrpc.getMsgWithdrawMethod = getMsgWithdrawMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Msg.MsgWithdrawRequest, comdex.vault.v1beta1.Msg.MsgWithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgWithdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgWithdrawRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgWithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgWithdraw"))
              .build();
        }
      }
    }
    return getMsgWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgDrawRequest,
      comdex.vault.v1beta1.Msg.MsgDrawResponse> getMsgDrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgDraw",
      requestType = comdex.vault.v1beta1.Msg.MsgDrawRequest.class,
      responseType = comdex.vault.v1beta1.Msg.MsgDrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgDrawRequest,
      comdex.vault.v1beta1.Msg.MsgDrawResponse> getMsgDrawMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgDrawRequest, comdex.vault.v1beta1.Msg.MsgDrawResponse> getMsgDrawMethod;
    if ((getMsgDrawMethod = MsgServiceGrpc.getMsgDrawMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgDrawMethod = MsgServiceGrpc.getMsgDrawMethod) == null) {
          MsgServiceGrpc.getMsgDrawMethod = getMsgDrawMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Msg.MsgDrawRequest, comdex.vault.v1beta1.Msg.MsgDrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgDraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgDrawRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgDrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgDraw"))
              .build();
        }
      }
    }
    return getMsgDrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgRepayRequest,
      comdex.vault.v1beta1.Msg.MsgRepayResponse> getMsgRepayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgRepay",
      requestType = comdex.vault.v1beta1.Msg.MsgRepayRequest.class,
      responseType = comdex.vault.v1beta1.Msg.MsgRepayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgRepayRequest,
      comdex.vault.v1beta1.Msg.MsgRepayResponse> getMsgRepayMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgRepayRequest, comdex.vault.v1beta1.Msg.MsgRepayResponse> getMsgRepayMethod;
    if ((getMsgRepayMethod = MsgServiceGrpc.getMsgRepayMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgRepayMethod = MsgServiceGrpc.getMsgRepayMethod) == null) {
          MsgServiceGrpc.getMsgRepayMethod = getMsgRepayMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Msg.MsgRepayRequest, comdex.vault.v1beta1.Msg.MsgRepayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgRepay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgRepayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgRepayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgRepay"))
              .build();
        }
      }
    }
    return getMsgRepayMethod;
  }

  private static volatile io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgCloseRequest,
      comdex.vault.v1beta1.Msg.MsgCloseResponse> getMsgCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MsgClose",
      requestType = comdex.vault.v1beta1.Msg.MsgCloseRequest.class,
      responseType = comdex.vault.v1beta1.Msg.MsgCloseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgCloseRequest,
      comdex.vault.v1beta1.Msg.MsgCloseResponse> getMsgCloseMethod() {
    io.grpc.MethodDescriptor<comdex.vault.v1beta1.Msg.MsgCloseRequest, comdex.vault.v1beta1.Msg.MsgCloseResponse> getMsgCloseMethod;
    if ((getMsgCloseMethod = MsgServiceGrpc.getMsgCloseMethod) == null) {
      synchronized (MsgServiceGrpc.class) {
        if ((getMsgCloseMethod = MsgServiceGrpc.getMsgCloseMethod) == null) {
          MsgServiceGrpc.getMsgCloseMethod = getMsgCloseMethod =
              io.grpc.MethodDescriptor.<comdex.vault.v1beta1.Msg.MsgCloseRequest, comdex.vault.v1beta1.Msg.MsgCloseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MsgClose"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgCloseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  comdex.vault.v1beta1.Msg.MsgCloseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgServiceMethodDescriptorSupplier("MsgClose"))
              .build();
        }
      }
    }
    return getMsgCloseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MsgServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceStub>() {
        @java.lang.Override
        public MsgServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceStub(channel, callOptions);
        }
      };
    return MsgServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MsgServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceBlockingStub>() {
        @java.lang.Override
        public MsgServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceBlockingStub(channel, callOptions);
        }
      };
    return MsgServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MsgServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MsgServiceFutureStub>() {
        @java.lang.Override
        public MsgServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MsgServiceFutureStub(channel, callOptions);
        }
      };
    return MsgServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MsgServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void msgCreate(comdex.vault.v1beta1.Msg.MsgCreateRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgCreateMethod(), responseObserver);
    }

    /**
     */
    public void msgDeposit(comdex.vault.v1beta1.Msg.MsgDepositRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgDepositResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgDepositMethod(), responseObserver);
    }

    /**
     */
    public void msgWithdraw(comdex.vault.v1beta1.Msg.MsgWithdrawRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgWithdrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgWithdrawMethod(), responseObserver);
    }

    /**
     */
    public void msgDraw(comdex.vault.v1beta1.Msg.MsgDrawRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgDrawResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgDrawMethod(), responseObserver);
    }

    /**
     */
    public void msgRepay(comdex.vault.v1beta1.Msg.MsgRepayRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgRepayResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgRepayMethod(), responseObserver);
    }

    /**
     */
    public void msgClose(comdex.vault.v1beta1.Msg.MsgCloseRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgCloseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMsgCloseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMsgCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Msg.MsgCreateRequest,
                comdex.vault.v1beta1.Msg.MsgCreateResponse>(
                  this, METHODID_MSG_CREATE)))
          .addMethod(
            getMsgDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Msg.MsgDepositRequest,
                comdex.vault.v1beta1.Msg.MsgDepositResponse>(
                  this, METHODID_MSG_DEPOSIT)))
          .addMethod(
            getMsgWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Msg.MsgWithdrawRequest,
                comdex.vault.v1beta1.Msg.MsgWithdrawResponse>(
                  this, METHODID_MSG_WITHDRAW)))
          .addMethod(
            getMsgDrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Msg.MsgDrawRequest,
                comdex.vault.v1beta1.Msg.MsgDrawResponse>(
                  this, METHODID_MSG_DRAW)))
          .addMethod(
            getMsgRepayMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Msg.MsgRepayRequest,
                comdex.vault.v1beta1.Msg.MsgRepayResponse>(
                  this, METHODID_MSG_REPAY)))
          .addMethod(
            getMsgCloseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                comdex.vault.v1beta1.Msg.MsgCloseRequest,
                comdex.vault.v1beta1.Msg.MsgCloseResponse>(
                  this, METHODID_MSG_CLOSE)))
          .build();
    }
  }

  /**
   */
  public static final class MsgServiceStub extends io.grpc.stub.AbstractAsyncStub<MsgServiceStub> {
    private MsgServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceStub(channel, callOptions);
    }

    /**
     */
    public void msgCreate(comdex.vault.v1beta1.Msg.MsgCreateRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgDeposit(comdex.vault.v1beta1.Msg.MsgDepositRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgDepositResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgWithdraw(comdex.vault.v1beta1.Msg.MsgWithdrawRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgWithdrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgDraw(comdex.vault.v1beta1.Msg.MsgDrawRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgDrawResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgDrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgRepay(comdex.vault.v1beta1.Msg.MsgRepayRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgRepayResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgRepayMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void msgClose(comdex.vault.v1beta1.Msg.MsgCloseRequest request,
        io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgCloseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMsgCloseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MsgServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MsgServiceBlockingStub> {
    private MsgServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public comdex.vault.v1beta1.Msg.MsgCreateResponse msgCreate(comdex.vault.v1beta1.Msg.MsgCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Msg.MsgDepositResponse msgDeposit(comdex.vault.v1beta1.Msg.MsgDepositRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Msg.MsgWithdrawResponse msgWithdraw(comdex.vault.v1beta1.Msg.MsgWithdrawRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgWithdrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Msg.MsgDrawResponse msgDraw(comdex.vault.v1beta1.Msg.MsgDrawRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgDrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Msg.MsgRepayResponse msgRepay(comdex.vault.v1beta1.Msg.MsgRepayRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgRepayMethod(), getCallOptions(), request);
    }

    /**
     */
    public comdex.vault.v1beta1.Msg.MsgCloseResponse msgClose(comdex.vault.v1beta1.Msg.MsgCloseRequest request) {
      return blockingUnaryCall(
          getChannel(), getMsgCloseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MsgServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MsgServiceFutureStub> {
    private MsgServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MsgServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MsgServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Msg.MsgCreateResponse> msgCreate(
        comdex.vault.v1beta1.Msg.MsgCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Msg.MsgDepositResponse> msgDeposit(
        comdex.vault.v1beta1.Msg.MsgDepositRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Msg.MsgWithdrawResponse> msgWithdraw(
        comdex.vault.v1beta1.Msg.MsgWithdrawRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgWithdrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Msg.MsgDrawResponse> msgDraw(
        comdex.vault.v1beta1.Msg.MsgDrawRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgDrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Msg.MsgRepayResponse> msgRepay(
        comdex.vault.v1beta1.Msg.MsgRepayRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgRepayMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<comdex.vault.v1beta1.Msg.MsgCloseResponse> msgClose(
        comdex.vault.v1beta1.Msg.MsgCloseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMsgCloseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MSG_CREATE = 0;
  private static final int METHODID_MSG_DEPOSIT = 1;
  private static final int METHODID_MSG_WITHDRAW = 2;
  private static final int METHODID_MSG_DRAW = 3;
  private static final int METHODID_MSG_REPAY = 4;
  private static final int METHODID_MSG_CLOSE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MsgServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MsgServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MSG_CREATE:
          serviceImpl.msgCreate((comdex.vault.v1beta1.Msg.MsgCreateRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgCreateResponse>) responseObserver);
          break;
        case METHODID_MSG_DEPOSIT:
          serviceImpl.msgDeposit((comdex.vault.v1beta1.Msg.MsgDepositRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgDepositResponse>) responseObserver);
          break;
        case METHODID_MSG_WITHDRAW:
          serviceImpl.msgWithdraw((comdex.vault.v1beta1.Msg.MsgWithdrawRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgWithdrawResponse>) responseObserver);
          break;
        case METHODID_MSG_DRAW:
          serviceImpl.msgDraw((comdex.vault.v1beta1.Msg.MsgDrawRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgDrawResponse>) responseObserver);
          break;
        case METHODID_MSG_REPAY:
          serviceImpl.msgRepay((comdex.vault.v1beta1.Msg.MsgRepayRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgRepayResponse>) responseObserver);
          break;
        case METHODID_MSG_CLOSE:
          serviceImpl.msgClose((comdex.vault.v1beta1.Msg.MsgCloseRequest) request,
              (io.grpc.stub.StreamObserver<comdex.vault.v1beta1.Msg.MsgCloseResponse>) responseObserver);
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

  private static abstract class MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return comdex.vault.v1beta1.Msg.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MsgService");
    }
  }

  private static final class MsgServiceFileDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier {
    MsgServiceFileDescriptorSupplier() {}
  }

  private static final class MsgServiceMethodDescriptorSupplier
      extends MsgServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MsgServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MsgServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MsgServiceFileDescriptorSupplier())
              .addMethod(getMsgCreateMethod())
              .addMethod(getMsgDepositMethod())
              .addMethod(getMsgWithdrawMethod())
              .addMethod(getMsgDrawMethod())
              .addMethod(getMsgRepayMethod())
              .addMethod(getMsgCloseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
