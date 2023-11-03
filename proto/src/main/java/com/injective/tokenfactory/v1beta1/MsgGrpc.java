package com.injective.tokenfactory.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Msg defines the tokefactory module's gRPC message service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.55.1)",
    comments = "Source: injective/tokenfactory/v1beta1/tx.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "injective.tokenfactory.v1beta1.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom,
      com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse> getCreateDenomMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDenom",
      requestType = com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom.class,
      responseType = com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom,
      com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse> getCreateDenomMethod() {
    io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom, com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse> getCreateDenomMethod;
    if ((getCreateDenomMethod = MsgGrpc.getCreateDenomMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateDenomMethod = MsgGrpc.getCreateDenomMethod) == null) {
          MsgGrpc.getCreateDenomMethod = getCreateDenomMethod =
              io.grpc.MethodDescriptor.<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom, com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDenom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateDenom"))
              .build();
        }
      }
    }
    return getCreateDenomMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgMint,
      com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse> getMintMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Mint",
      requestType = com.injective.tokenfactory.v1beta1.TxProto.MsgMint.class,
      responseType = com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgMint,
      com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse> getMintMethod() {
    io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgMint, com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse> getMintMethod;
    if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getMintMethod = MsgGrpc.getMintMethod) == null) {
          MsgGrpc.getMintMethod = getMintMethod =
              io.grpc.MethodDescriptor.<com.injective.tokenfactory.v1beta1.TxProto.MsgMint, com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Mint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgMint.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Mint"))
              .build();
        }
      }
    }
    return getMintMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgBurn,
      com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse> getBurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Burn",
      requestType = com.injective.tokenfactory.v1beta1.TxProto.MsgBurn.class,
      responseType = com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgBurn,
      com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse> getBurnMethod() {
    io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgBurn, com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse> getBurnMethod;
    if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getBurnMethod = MsgGrpc.getBurnMethod) == null) {
          MsgGrpc.getBurnMethod = getBurnMethod =
              io.grpc.MethodDescriptor.<com.injective.tokenfactory.v1beta1.TxProto.MsgBurn, com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Burn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgBurn.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("Burn"))
              .build();
        }
      }
    }
    return getBurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin,
      com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse> getChangeAdminMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangeAdmin",
      requestType = com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin.class,
      responseType = com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin,
      com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse> getChangeAdminMethod() {
    io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin, com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse> getChangeAdminMethod;
    if ((getChangeAdminMethod = MsgGrpc.getChangeAdminMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getChangeAdminMethod = MsgGrpc.getChangeAdminMethod) == null) {
          MsgGrpc.getChangeAdminMethod = getChangeAdminMethod =
              io.grpc.MethodDescriptor.<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin, com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChangeAdmin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("ChangeAdmin"))
              .build();
        }
      }
    }
    return getChangeAdminMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata,
      com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse> getSetDenomMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDenomMetadata",
      requestType = com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata.class,
      responseType = com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata,
      com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse> getSetDenomMetadataMethod() {
    io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata, com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse> getSetDenomMetadataMethod;
    if ((getSetDenomMetadataMethod = MsgGrpc.getSetDenomMetadataMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getSetDenomMetadataMethod = MsgGrpc.getSetDenomMetadataMethod) == null) {
          MsgGrpc.getSetDenomMetadataMethod = getSetDenomMetadataMethod =
              io.grpc.MethodDescriptor.<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata, com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDenomMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("SetDenomMetadata"))
              .build();
        }
      }
    }
    return getSetDenomMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams,
      com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateParams",
      requestType = com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams.class,
      responseType = com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams,
      com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod() {
    io.grpc.MethodDescriptor<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams, com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse> getUpdateParamsMethod;
    if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateParamsMethod = MsgGrpc.getUpdateParamsMethod) == null) {
          MsgGrpc.getUpdateParamsMethod = getUpdateParamsMethod =
              io.grpc.MethodDescriptor.<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams, com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateParams"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse.getDefaultInstance()))
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
   * Msg defines the tokefactory module's gRPC message service.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void createDenom(com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateDenomMethod(), responseObserver);
    }

    /**
     */
    default void mint(com.injective.tokenfactory.v1beta1.TxProto.MsgMint request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMintMethod(), responseObserver);
    }

    /**
     */
    default void burn(com.injective.tokenfactory.v1beta1.TxProto.MsgBurn request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBurnMethod(), responseObserver);
    }

    /**
     */
    default void changeAdmin(com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getChangeAdminMethod(), responseObserver);
    }

    /**
     */
    default void setDenomMetadata(com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetDenomMetadataMethod(), responseObserver);
    }

    /**
     */
    default void updateParams(com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateParamsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Msg.
   * <pre>
   * Msg defines the tokefactory module's gRPC message service.
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
   * Msg defines the tokefactory module's gRPC message service.
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
     */
    public void createDenom(com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateDenomMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mint(com.injective.tokenfactory.v1beta1.TxProto.MsgMint request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void burn(com.injective.tokenfactory.v1beta1.TxProto.MsgBurn request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeAdmin(com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getChangeAdminMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setDenomMetadata(com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetDenomMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateParams(com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams request,
        io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Msg.
   * <pre>
   * Msg defines the tokefactory module's gRPC message service.
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
     */
    public com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse createDenom(com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateDenomMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse mint(com.injective.tokenfactory.v1beta1.TxProto.MsgMint request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMintMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse burn(com.injective.tokenfactory.v1beta1.TxProto.MsgBurn request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBurnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse changeAdmin(com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getChangeAdminMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse setDenomMetadata(com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetDenomMetadataMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse updateParams(com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateParamsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Msg.
   * <pre>
   * Msg defines the tokefactory module's gRPC message service.
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
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse> createDenom(
        com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateDenomMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse> mint(
        com.injective.tokenfactory.v1beta1.TxProto.MsgMint request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMintMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse> burn(
        com.injective.tokenfactory.v1beta1.TxProto.MsgBurn request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBurnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse> changeAdmin(
        com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getChangeAdminMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse> setDenomMetadata(
        com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetDenomMetadataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse> updateParams(
        com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateParamsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DENOM = 0;
  private static final int METHODID_MINT = 1;
  private static final int METHODID_BURN = 2;
  private static final int METHODID_CHANGE_ADMIN = 3;
  private static final int METHODID_SET_DENOM_METADATA = 4;
  private static final int METHODID_UPDATE_PARAMS = 5;

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
        case METHODID_CREATE_DENOM:
          serviceImpl.createDenom((com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom) request,
              (io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse>) responseObserver);
          break;
        case METHODID_MINT:
          serviceImpl.mint((com.injective.tokenfactory.v1beta1.TxProto.MsgMint) request,
              (io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse>) responseObserver);
          break;
        case METHODID_BURN:
          serviceImpl.burn((com.injective.tokenfactory.v1beta1.TxProto.MsgBurn) request,
              (io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse>) responseObserver);
          break;
        case METHODID_CHANGE_ADMIN:
          serviceImpl.changeAdmin((com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin) request,
              (io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse>) responseObserver);
          break;
        case METHODID_SET_DENOM_METADATA:
          serviceImpl.setDenomMetadata((com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata) request,
              (io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PARAMS:
          serviceImpl.updateParams((com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams) request,
              (io.grpc.stub.StreamObserver<com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse>) responseObserver);
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
          getCreateDenomMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenom,
              com.injective.tokenfactory.v1beta1.TxProto.MsgCreateDenomResponse>(
                service, METHODID_CREATE_DENOM)))
        .addMethod(
          getMintMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.tokenfactory.v1beta1.TxProto.MsgMint,
              com.injective.tokenfactory.v1beta1.TxProto.MsgMintResponse>(
                service, METHODID_MINT)))
        .addMethod(
          getBurnMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.tokenfactory.v1beta1.TxProto.MsgBurn,
              com.injective.tokenfactory.v1beta1.TxProto.MsgBurnResponse>(
                service, METHODID_BURN)))
        .addMethod(
          getChangeAdminMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdmin,
              com.injective.tokenfactory.v1beta1.TxProto.MsgChangeAdminResponse>(
                service, METHODID_CHANGE_ADMIN)))
        .addMethod(
          getSetDenomMetadataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadata,
              com.injective.tokenfactory.v1beta1.TxProto.MsgSetDenomMetadataResponse>(
                service, METHODID_SET_DENOM_METADATA)))
        .addMethod(
          getUpdateParamsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParams,
              com.injective.tokenfactory.v1beta1.TxProto.MsgUpdateParamsResponse>(
                service, METHODID_UPDATE_PARAMS)))
        .build();
  }

  private static abstract class MsgBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MsgBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.injective.tokenfactory.v1beta1.TxProto.getDescriptor();
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
              .addMethod(getCreateDenomMethod())
              .addMethod(getMintMethod())
              .addMethod(getBurnMethod())
              .addMethod(getChangeAdminMethod())
              .addMethod(getSetDenomMetadataMethod())
              .addMethod(getUpdateParamsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
