package com.injective.insurance.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the insurance Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/insurance/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.insurance.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund,
      com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse> getCreateInsuranceFundMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateInsuranceFund",
      requestType = com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund.class,
      responseType = com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund,
      com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse> getCreateInsuranceFundMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund, com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse> getCreateInsuranceFundMethod;
    if ((getCreateInsuranceFundMethod = MsgGrpc.getCreateInsuranceFundMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateInsuranceFundMethod = MsgGrpc.getCreateInsuranceFundMethod) == null) {
          MsgGrpc.getCreateInsuranceFundMethod = getCreateInsuranceFundMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund, com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateInsuranceFund"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateInsuranceFund"))
              .build();
        }
      }
    }
    return getCreateInsuranceFundMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgUnderwrite,
      com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse> getUnderwriteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Underwrite",
      requestType = com.injective.insurance.v1beta1.TxProto.MsgUnderwrite.class,
      responseType = com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgUnderwrite,
      com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse> getUnderwriteMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgUnderwrite, com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse> getUnderwriteMethod;
    if ((getUnderwriteMethod = MsgGrpc.getUnderwriteMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUnderwriteMethod = MsgGrpc.getUnderwriteMethod) == null) {
          MsgGrpc.getUnderwriteMethod = getUnderwriteMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.TxProto.MsgUnderwrite, com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Underwrite"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgUnderwrite.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Underwrite"))
              .build();
        }
      }
    }
    return getUnderwriteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption,
      com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse> getRequestRedemptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestRedemption",
      requestType = com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption.class,
      responseType = com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption,
      com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse> getRequestRedemptionMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption, com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse> getRequestRedemptionMethod;
    if ((getRequestRedemptionMethod = MsgGrpc.getRequestRedemptionMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getRequestRedemptionMethod = MsgGrpc.getRequestRedemptionMethod) == null) {
          MsgGrpc.getRequestRedemptionMethod = getRequestRedemptionMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption, com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestRedemption"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("RequestRedemption"))
              .build();
        }
      }
    }
    return getRequestRedemptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgUpdateParams,
      com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.injective.insurance.v1beta1.TxProto.MsgUpdateParams.class,
      responseType = com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgUpdateParams,
      com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.insurance.v1beta1.TxProto.MsgUpdateParams, com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.insurance.v1beta1.TxProto.MsgUpdateParams, com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateParams"))
              .build();
        }
      }
    }
    return getUpdateParamsMethod;
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
   * <pre>
   * Msg defines the insurance Msg service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * CreateInsuranceFund defines a method for creating an insurance fund
     * </pre>
     */
    default void createInsuranceFund(com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateInsuranceFundMethod(), responseObserver);
    }

    /**
     * <pre>
     * Underwrite defines a method for depositing tokens to underwrite an
     * insurance fund
     * </pre>
     */
    default void underwrite(com.injective.insurance.v1beta1.TxProto.MsgUnderwrite request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnderwriteMethod(), responseObserver);
    }

    /**
     * <pre>
     * RequestRedemption defines a method for requesting a redemption of the
     * sender's insurance fund tokens
     * </pre>
     */
    default void requestRedemption(com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestRedemptionMethod(), responseObserver);
    }

    /**
     */
    default void updateParams(com.injective.insurance.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the insurance Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MsgGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the insurance Msg service.
   * </pre>
   */
  public static final class MsgStub
      extends io.grpc.stub.AbstractAsyncStub<MsgStub> {
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
     * <pre>
     * CreateInsuranceFund defines a method for creating an insurance fund
     * </pre>
     */
    public void createInsuranceFund(com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateInsuranceFundMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Underwrite defines a method for depositing tokens to underwrite an
     * insurance fund
     * </pre>
     */
    public void underwrite(com.injective.insurance.v1beta1.TxProto.MsgUnderwrite request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnderwriteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * RequestRedemption defines a method for requesting a redemption of the
     * sender's insurance fund tokens
     * </pre>
     */
    public void requestRedemption(com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestRedemptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateParams(com.injective.insurance.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the insurance Msg service.
   * </pre>
   */
  public static final class MsgBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MsgBlockingStub> {
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
     * <pre>
     * CreateInsuranceFund defines a method for creating an insurance fund
     * </pre>
     */
    public com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse createInsuranceFund(com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateInsuranceFundMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Underwrite defines a method for depositing tokens to underwrite an
     * insurance fund
     * </pre>
     */
    public com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse underwrite(com.injective.insurance.v1beta1.TxProto.MsgUnderwrite request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnderwriteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * RequestRedemption defines a method for requesting a redemption of the
     * sender's insurance fund tokens
     * </pre>
     */
    public com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse requestRedemption(com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestRedemptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse updateParams(com.injective.insurance.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the insurance Msg service.
   * </pre>
   */
  public static final class MsgFutureStub
      extends io.grpc.stub.AbstractFutureStub<MsgFutureStub> {
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
     * <pre>
     * CreateInsuranceFund defines a method for creating an insurance fund
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse> createInsuranceFund(
        com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateInsuranceFundMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Underwrite defines a method for depositing tokens to underwrite an
     * insurance fund
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse> underwrite(
        com.injective.insurance.v1beta1.TxProto.MsgUnderwrite request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnderwriteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * RequestRedemption defines a method for requesting a redemption of the
     * sender's insurance fund tokens
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse> requestRedemption(
        com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestRedemptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.injective.insurance.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_INSURANCE_FUND = 0;
  private static final int METHODID_UNDERWRITE = 1;
  private static final int METHODID_REQUEST_REDEMPTION = 2;
  private static final int METHODID_UPDATE_PARAMS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_INSURANCE_FUND:
          serviceImpl.createInsuranceFund((com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse>) responseObserver);
          break;
        case METHODID_UNDERWRITE:
          serviceImpl.underwrite((com.injective.insurance.v1beta1.TxProto.MsgUnderwrite) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse>) responseObserver);
          break;
        case METHODID_REQUEST_REDEMPTION:
          serviceImpl.requestRedemption((com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.injective.insurance.v1beta1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateInsuranceFundMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFund,
              com.injective.insurance.v1beta1.TxProto.MsgCreateInsuranceFundResponse>(
                service, METHODID_CREATE_INSURANCE_FUND)))
        .addMethod(
          getUnderwriteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.TxProto.MsgUnderwrite,
              com.injective.insurance.v1beta1.TxProto.MsgUnderwriteResponse>(
                service, METHODID_UNDERWRITE)))
        .addMethod(
          getRequestRedemptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.TxProto.MsgRequestRedemption,
              com.injective.insurance.v1beta1.TxProto.MsgRequestRedemptionResponse>(
                service, METHODID_REQUEST_REDEMPTION)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.insurance.v1beta1.TxProto.MsgUpdateParams,
              com.injective.insurance.v1beta1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.insurance.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getCreateInsuranceFundMethod())
              .addMethod(getUnderwriteMethod())
              .addMethod(getRequestRedemptionMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
