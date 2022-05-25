package em.authority.v1;

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
    comments = "Source: em/authority/v1/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "em.authority.v1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgCreateIssuer,
      em.authority.v1.Tx.MsgCreateIssuerResponse> getCreateIssuerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateIssuer",
      requestType = em.authority.v1.Tx.MsgCreateIssuer.class,
      responseType = em.authority.v1.Tx.MsgCreateIssuerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgCreateIssuer,
      em.authority.v1.Tx.MsgCreateIssuerResponse> getCreateIssuerMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgCreateIssuer, em.authority.v1.Tx.MsgCreateIssuerResponse> getCreateIssuerMethod;
    if ((getCreateIssuerMethod = MsgGrpc.getCreateIssuerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateIssuerMethod = MsgGrpc.getCreateIssuerMethod) == null) {
          MsgGrpc.getCreateIssuerMethod = getCreateIssuerMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.Tx.MsgCreateIssuer, em.authority.v1.Tx.MsgCreateIssuerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateIssuer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgCreateIssuer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgCreateIssuerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateIssuer"))
              .build();
        }
      }
    }
    return getCreateIssuerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgDestroyIssuer,
      em.authority.v1.Tx.MsgDestroyIssuerResponse> getDestroyIssuerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DestroyIssuer",
      requestType = em.authority.v1.Tx.MsgDestroyIssuer.class,
      responseType = em.authority.v1.Tx.MsgDestroyIssuerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgDestroyIssuer,
      em.authority.v1.Tx.MsgDestroyIssuerResponse> getDestroyIssuerMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgDestroyIssuer, em.authority.v1.Tx.MsgDestroyIssuerResponse> getDestroyIssuerMethod;
    if ((getDestroyIssuerMethod = MsgGrpc.getDestroyIssuerMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDestroyIssuerMethod = MsgGrpc.getDestroyIssuerMethod) == null) {
          MsgGrpc.getDestroyIssuerMethod = getDestroyIssuerMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.Tx.MsgDestroyIssuer, em.authority.v1.Tx.MsgDestroyIssuerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DestroyIssuer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgDestroyIssuer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgDestroyIssuerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DestroyIssuer"))
              .build();
        }
      }
    }
    return getDestroyIssuerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgSetGasPrices,
      em.authority.v1.Tx.MsgSetGasPricesResponse> getSetGasPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetGasPrices",
      requestType = em.authority.v1.Tx.MsgSetGasPrices.class,
      responseType = em.authority.v1.Tx.MsgSetGasPricesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgSetGasPrices,
      em.authority.v1.Tx.MsgSetGasPricesResponse> getSetGasPricesMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgSetGasPrices, em.authority.v1.Tx.MsgSetGasPricesResponse> getSetGasPricesMethod;
    if ((getSetGasPricesMethod = MsgGrpc.getSetGasPricesMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetGasPricesMethod = MsgGrpc.getSetGasPricesMethod) == null) {
          MsgGrpc.getSetGasPricesMethod = getSetGasPricesMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.Tx.MsgSetGasPrices, em.authority.v1.Tx.MsgSetGasPricesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetGasPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgSetGasPrices.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgSetGasPricesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetGasPrices"))
              .build();
        }
      }
    }
    return getSetGasPricesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgReplaceAuthority,
      em.authority.v1.Tx.MsgReplaceAuthorityResponse> getReplaceAuthorityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReplaceAuthority",
      requestType = em.authority.v1.Tx.MsgReplaceAuthority.class,
      responseType = em.authority.v1.Tx.MsgReplaceAuthorityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgReplaceAuthority,
      em.authority.v1.Tx.MsgReplaceAuthorityResponse> getReplaceAuthorityMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgReplaceAuthority, em.authority.v1.Tx.MsgReplaceAuthorityResponse> getReplaceAuthorityMethod;
    if ((getReplaceAuthorityMethod = MsgGrpc.getReplaceAuthorityMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getReplaceAuthorityMethod = MsgGrpc.getReplaceAuthorityMethod) == null) {
          MsgGrpc.getReplaceAuthorityMethod = getReplaceAuthorityMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.Tx.MsgReplaceAuthority, em.authority.v1.Tx.MsgReplaceAuthorityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReplaceAuthority"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgReplaceAuthority.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgReplaceAuthorityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ReplaceAuthority"))
              .build();
        }
      }
    }
    return getReplaceAuthorityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgScheduleUpgrade,
      em.authority.v1.Tx.MsgScheduleUpgradeResponse> getScheduleUpgradeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ScheduleUpgrade",
      requestType = em.authority.v1.Tx.MsgScheduleUpgrade.class,
      responseType = em.authority.v1.Tx.MsgScheduleUpgradeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgScheduleUpgrade,
      em.authority.v1.Tx.MsgScheduleUpgradeResponse> getScheduleUpgradeMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgScheduleUpgrade, em.authority.v1.Tx.MsgScheduleUpgradeResponse> getScheduleUpgradeMethod;
    if ((getScheduleUpgradeMethod = MsgGrpc.getScheduleUpgradeMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getScheduleUpgradeMethod = MsgGrpc.getScheduleUpgradeMethod) == null) {
          MsgGrpc.getScheduleUpgradeMethod = getScheduleUpgradeMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.Tx.MsgScheduleUpgrade, em.authority.v1.Tx.MsgScheduleUpgradeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ScheduleUpgrade"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgScheduleUpgrade.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgScheduleUpgradeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ScheduleUpgrade"))
              .build();
        }
      }
    }
    return getScheduleUpgradeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgSetParameters,
      em.authority.v1.Tx.MsgSetParametersResponse> getSetParametersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetParameters",
      requestType = em.authority.v1.Tx.MsgSetParameters.class,
      responseType = em.authority.v1.Tx.MsgSetParametersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgSetParameters,
      em.authority.v1.Tx.MsgSetParametersResponse> getSetParametersMethod() {
    io.grpc.MethodDescriptor<em.authority.v1.Tx.MsgSetParameters, em.authority.v1.Tx.MsgSetParametersResponse> getSetParametersMethod;
    if ((getSetParametersMethod = MsgGrpc.getSetParametersMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetParametersMethod = MsgGrpc.getSetParametersMethod) == null) {
          MsgGrpc.getSetParametersMethod = getSetParametersMethod =
              io.grpc.MethodDescriptor.<em.authority.v1.Tx.MsgSetParameters, em.authority.v1.Tx.MsgSetParametersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetParameters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgSetParameters.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  em.authority.v1.Tx.MsgSetParametersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetParameters"))
              .build();
        }
      }
    }
    return getSetParametersMethod;
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
    public void createIssuer(em.authority.v1.Tx.MsgCreateIssuer request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgCreateIssuerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateIssuerMethod(), responseObserver);
    }

    /**
     */
    public void destroyIssuer(em.authority.v1.Tx.MsgDestroyIssuer request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgDestroyIssuerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDestroyIssuerMethod(), responseObserver);
    }

    /**
     */
    public void setGasPrices(em.authority.v1.Tx.MsgSetGasPrices request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgSetGasPricesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetGasPricesMethod(), responseObserver);
    }

    /**
     */
    public void replaceAuthority(em.authority.v1.Tx.MsgReplaceAuthority request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgReplaceAuthorityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReplaceAuthorityMethod(), responseObserver);
    }

    /**
     */
    public void scheduleUpgrade(em.authority.v1.Tx.MsgScheduleUpgrade request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgScheduleUpgradeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getScheduleUpgradeMethod(), responseObserver);
    }

    /**
     */
    public void setParameters(em.authority.v1.Tx.MsgSetParameters request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgSetParametersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetParametersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateIssuerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.Tx.MsgCreateIssuer,
                em.authority.v1.Tx.MsgCreateIssuerResponse>(
                  this, METHODID_CREATE_ISSUER)))
          .addMethod(
            getDestroyIssuerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.Tx.MsgDestroyIssuer,
                em.authority.v1.Tx.MsgDestroyIssuerResponse>(
                  this, METHODID_DESTROY_ISSUER)))
          .addMethod(
            getSetGasPricesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.Tx.MsgSetGasPrices,
                em.authority.v1.Tx.MsgSetGasPricesResponse>(
                  this, METHODID_SET_GAS_PRICES)))
          .addMethod(
            getReplaceAuthorityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.Tx.MsgReplaceAuthority,
                em.authority.v1.Tx.MsgReplaceAuthorityResponse>(
                  this, METHODID_REPLACE_AUTHORITY)))
          .addMethod(
            getScheduleUpgradeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.Tx.MsgScheduleUpgrade,
                em.authority.v1.Tx.MsgScheduleUpgradeResponse>(
                  this, METHODID_SCHEDULE_UPGRADE)))
          .addMethod(
            getSetParametersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                em.authority.v1.Tx.MsgSetParameters,
                em.authority.v1.Tx.MsgSetParametersResponse>(
                  this, METHODID_SET_PARAMETERS)))
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
    public void createIssuer(em.authority.v1.Tx.MsgCreateIssuer request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgCreateIssuerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateIssuerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void destroyIssuer(em.authority.v1.Tx.MsgDestroyIssuer request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgDestroyIssuerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDestroyIssuerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setGasPrices(em.authority.v1.Tx.MsgSetGasPrices request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgSetGasPricesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetGasPricesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void replaceAuthority(em.authority.v1.Tx.MsgReplaceAuthority request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgReplaceAuthorityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReplaceAuthorityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void scheduleUpgrade(em.authority.v1.Tx.MsgScheduleUpgrade request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgScheduleUpgradeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScheduleUpgradeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setParameters(em.authority.v1.Tx.MsgSetParameters request,
        io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgSetParametersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetParametersMethod(), getCallOptions()), request, responseObserver);
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
    public em.authority.v1.Tx.MsgCreateIssuerResponse createIssuer(em.authority.v1.Tx.MsgCreateIssuer request) {
      return blockingUnaryCall(
          getChannel(), getCreateIssuerMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.authority.v1.Tx.MsgDestroyIssuerResponse destroyIssuer(em.authority.v1.Tx.MsgDestroyIssuer request) {
      return blockingUnaryCall(
          getChannel(), getDestroyIssuerMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.authority.v1.Tx.MsgSetGasPricesResponse setGasPrices(em.authority.v1.Tx.MsgSetGasPrices request) {
      return blockingUnaryCall(
          getChannel(), getSetGasPricesMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.authority.v1.Tx.MsgReplaceAuthorityResponse replaceAuthority(em.authority.v1.Tx.MsgReplaceAuthority request) {
      return blockingUnaryCall(
          getChannel(), getReplaceAuthorityMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.authority.v1.Tx.MsgScheduleUpgradeResponse scheduleUpgrade(em.authority.v1.Tx.MsgScheduleUpgrade request) {
      return blockingUnaryCall(
          getChannel(), getScheduleUpgradeMethod(), getCallOptions(), request);
    }

    /**
     */
    public em.authority.v1.Tx.MsgSetParametersResponse setParameters(em.authority.v1.Tx.MsgSetParameters request) {
      return blockingUnaryCall(
          getChannel(), getSetParametersMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.Tx.MsgCreateIssuerResponse> createIssuer(
        em.authority.v1.Tx.MsgCreateIssuer request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateIssuerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.Tx.MsgDestroyIssuerResponse> destroyIssuer(
        em.authority.v1.Tx.MsgDestroyIssuer request) {
      return futureUnaryCall(
          getChannel().newCall(getDestroyIssuerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.Tx.MsgSetGasPricesResponse> setGasPrices(
        em.authority.v1.Tx.MsgSetGasPrices request) {
      return futureUnaryCall(
          getChannel().newCall(getSetGasPricesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.Tx.MsgReplaceAuthorityResponse> replaceAuthority(
        em.authority.v1.Tx.MsgReplaceAuthority request) {
      return futureUnaryCall(
          getChannel().newCall(getReplaceAuthorityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.Tx.MsgScheduleUpgradeResponse> scheduleUpgrade(
        em.authority.v1.Tx.MsgScheduleUpgrade request) {
      return futureUnaryCall(
          getChannel().newCall(getScheduleUpgradeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<em.authority.v1.Tx.MsgSetParametersResponse> setParameters(
        em.authority.v1.Tx.MsgSetParameters request) {
      return futureUnaryCall(
          getChannel().newCall(getSetParametersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ISSUER = 0;
  private static final int METHODID_DESTROY_ISSUER = 1;
  private static final int METHODID_SET_GAS_PRICES = 2;
  private static final int METHODID_REPLACE_AUTHORITY = 3;
  private static final int METHODID_SCHEDULE_UPGRADE = 4;
  private static final int METHODID_SET_PARAMETERS = 5;

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
        case METHODID_CREATE_ISSUER:
          serviceImpl.createIssuer((em.authority.v1.Tx.MsgCreateIssuer) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgCreateIssuerResponse>) responseObserver);
          break;
        case METHODID_DESTROY_ISSUER:
          serviceImpl.destroyIssuer((em.authority.v1.Tx.MsgDestroyIssuer) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgDestroyIssuerResponse>) responseObserver);
          break;
        case METHODID_SET_GAS_PRICES:
          serviceImpl.setGasPrices((em.authority.v1.Tx.MsgSetGasPrices) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgSetGasPricesResponse>) responseObserver);
          break;
        case METHODID_REPLACE_AUTHORITY:
          serviceImpl.replaceAuthority((em.authority.v1.Tx.MsgReplaceAuthority) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgReplaceAuthorityResponse>) responseObserver);
          break;
        case METHODID_SCHEDULE_UPGRADE:
          serviceImpl.scheduleUpgrade((em.authority.v1.Tx.MsgScheduleUpgrade) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgScheduleUpgradeResponse>) responseObserver);
          break;
        case METHODID_SET_PARAMETERS:
          serviceImpl.setParameters((em.authority.v1.Tx.MsgSetParameters) request,
              (io.grpc.stub.StreamObserver<em.authority.v1.Tx.MsgSetParametersResponse>) responseObserver);
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
      return em.authority.v1.Tx.getDescriptor();
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
              .addMethod(getCreateIssuerMethod())
              .addMethod(getDestroyIssuerMethod())
              .addMethod(getSetGasPricesMethod())
              .addMethod(getReplaceAuthorityMethod())
              .addMethod(getScheduleUpgradeMethod())
              .addMethod(getSetParametersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
