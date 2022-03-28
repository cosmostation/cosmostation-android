package BitCannaGlobal.bcna.bcna;

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
 * <pre>
 * Msg defines the Msg service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: bcna/tx.proto")
public final class MsgGrpc {

  private MsgGrpc() {}

  public static final String SERVICE_NAME = "BitCannaGlobal.bcna.bcna.Msg";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid,
      BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse> getCreateBitcannaidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBitcannaid",
      requestType = BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid.class,
      responseType = BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid,
      BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse> getCreateBitcannaidMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid, BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse> getCreateBitcannaidMethod;
    if ((getCreateBitcannaidMethod = MsgGrpc.getCreateBitcannaidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateBitcannaidMethod = MsgGrpc.getCreateBitcannaidMethod) == null) {
          MsgGrpc.getCreateBitcannaidMethod = getCreateBitcannaidMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid, BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBitcannaid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateBitcannaid"))
              .build();
        }
      }
    }
    return getCreateBitcannaidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid,
      BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse> getUpdateBitcannaidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateBitcannaid",
      requestType = BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid.class,
      responseType = BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid,
      BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse> getUpdateBitcannaidMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid, BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse> getUpdateBitcannaidMethod;
    if ((getUpdateBitcannaidMethod = MsgGrpc.getUpdateBitcannaidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateBitcannaidMethod = MsgGrpc.getUpdateBitcannaidMethod) == null) {
          MsgGrpc.getUpdateBitcannaidMethod = getUpdateBitcannaidMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid, BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBitcannaid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateBitcannaid"))
              .build();
        }
      }
    }
    return getUpdateBitcannaidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid,
      BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse> getDeleteBitcannaidMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteBitcannaid",
      requestType = BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid.class,
      responseType = BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid,
      BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse> getDeleteBitcannaidMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid, BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse> getDeleteBitcannaidMethod;
    if ((getDeleteBitcannaidMethod = MsgGrpc.getDeleteBitcannaidMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteBitcannaidMethod = MsgGrpc.getDeleteBitcannaidMethod) == null) {
          MsgGrpc.getDeleteBitcannaidMethod = getDeleteBitcannaidMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid, BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteBitcannaid"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteBitcannaid"))
              .build();
        }
      }
    }
    return getDeleteBitcannaidMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain,
      BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse> getCreateSupplychainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSupplychain",
      requestType = BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain.class,
      responseType = BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain,
      BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse> getCreateSupplychainMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain, BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse> getCreateSupplychainMethod;
    if ((getCreateSupplychainMethod = MsgGrpc.getCreateSupplychainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getCreateSupplychainMethod = MsgGrpc.getCreateSupplychainMethod) == null) {
          MsgGrpc.getCreateSupplychainMethod = getCreateSupplychainMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain, BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSupplychain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("CreateSupplychain"))
              .build();
        }
      }
    }
    return getCreateSupplychainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain,
      BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse> getUpdateSupplychainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateSupplychain",
      requestType = BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain.class,
      responseType = BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain,
      BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse> getUpdateSupplychainMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain, BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse> getUpdateSupplychainMethod;
    if ((getUpdateSupplychainMethod = MsgGrpc.getUpdateSupplychainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getUpdateSupplychainMethod = MsgGrpc.getUpdateSupplychainMethod) == null) {
          MsgGrpc.getUpdateSupplychainMethod = getUpdateSupplychainMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain, BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateSupplychain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("UpdateSupplychain"))
              .build();
        }
      }
    }
    return getUpdateSupplychainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain,
      BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse> getDeleteSupplychainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteSupplychain",
      requestType = BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain.class,
      responseType = BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain,
      BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse> getDeleteSupplychainMethod() {
    io.grpc.MethodDescriptor<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain, BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse> getDeleteSupplychainMethod;
    if ((getDeleteSupplychainMethod = MsgGrpc.getDeleteSupplychainMethod) == null) {
      synchronized (MsgGrpc.class) {
        if ((getDeleteSupplychainMethod = MsgGrpc.getDeleteSupplychainMethod) == null) {
          MsgGrpc.getDeleteSupplychainMethod = getDeleteSupplychainMethod =
              io.grpc.MethodDescriptor.<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain, BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteSupplychain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MsgMethodDescriptorSupplier("DeleteSupplychain"))
              .build();
        }
      }
    }
    return getDeleteSupplychainMethod;
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
   * Msg defines the Msg service.
   * </pre>
   */
  public static abstract class MsgImplBase implements io.grpc.BindableService {

    /**
     */
    public void createBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateBitcannaidMethod(), responseObserver);
    }

    /**
     */
    public void updateBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateBitcannaidMethod(), responseObserver);
    }

    /**
     */
    public void deleteBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteBitcannaidMethod(), responseObserver);
    }

    /**
     */
    public void createSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateSupplychainMethod(), responseObserver);
    }

    /**
     */
    public void updateSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateSupplychainMethod(), responseObserver);
    }

    /**
     * <pre>
     * this line is used by starport scaffolding # proto/tx/rpc
     * </pre>
     */
    public void deleteSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteSupplychainMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateBitcannaidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid,
                BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse>(
                  this, METHODID_CREATE_BITCANNAID)))
          .addMethod(
            getUpdateBitcannaidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid,
                BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse>(
                  this, METHODID_UPDATE_BITCANNAID)))
          .addMethod(
            getDeleteBitcannaidMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid,
                BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse>(
                  this, METHODID_DELETE_BITCANNAID)))
          .addMethod(
            getCreateSupplychainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain,
                BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse>(
                  this, METHODID_CREATE_SUPPLYCHAIN)))
          .addMethod(
            getUpdateSupplychainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain,
                BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse>(
                  this, METHODID_UPDATE_SUPPLYCHAIN)))
          .addMethod(
            getDeleteSupplychainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain,
                BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse>(
                  this, METHODID_DELETE_SUPPLYCHAIN)))
          .build();
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
   * </pre>
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
    public void createBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateBitcannaidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateBitcannaidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteBitcannaidMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateSupplychainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateSupplychainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * this line is used by starport scaffolding # proto/tx/rpc
     * </pre>
     */
    public void deleteSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain request,
        io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteSupplychainMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
   * </pre>
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
    public BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse createBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid request) {
      return blockingUnaryCall(
          getChannel(), getCreateBitcannaidMethod(), getCallOptions(), request);
    }

    /**
     */
    public BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse updateBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid request) {
      return blockingUnaryCall(
          getChannel(), getUpdateBitcannaidMethod(), getCallOptions(), request);
    }

    /**
     */
    public BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse deleteBitcannaid(BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid request) {
      return blockingUnaryCall(
          getChannel(), getDeleteBitcannaidMethod(), getCallOptions(), request);
    }

    /**
     */
    public BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse createSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain request) {
      return blockingUnaryCall(
          getChannel(), getCreateSupplychainMethod(), getCallOptions(), request);
    }

    /**
     */
    public BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse updateSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain request) {
      return blockingUnaryCall(
          getChannel(), getUpdateSupplychainMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * this line is used by starport scaffolding # proto/tx/rpc
     * </pre>
     */
    public BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse deleteSupplychain(BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain request) {
      return blockingUnaryCall(
          getChannel(), getDeleteSupplychainMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Msg defines the Msg service.
   * </pre>
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
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse> createBitcannaid(
        BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateBitcannaidMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse> updateBitcannaid(
        BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateBitcannaidMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse> deleteBitcannaid(
        BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteBitcannaidMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse> createSupplychain(
        BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateSupplychainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse> updateSupplychain(
        BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateSupplychainMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * this line is used by starport scaffolding # proto/tx/rpc
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse> deleteSupplychain(
        BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteSupplychainMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_BITCANNAID = 0;
  private static final int METHODID_UPDATE_BITCANNAID = 1;
  private static final int METHODID_DELETE_BITCANNAID = 2;
  private static final int METHODID_CREATE_SUPPLYCHAIN = 3;
  private static final int METHODID_UPDATE_SUPPLYCHAIN = 4;
  private static final int METHODID_DELETE_SUPPLYCHAIN = 5;

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
        case METHODID_CREATE_BITCANNAID:
          serviceImpl.createBitcannaid((BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaid) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgCreateBitcannaidResponse>) responseObserver);
          break;
        case METHODID_UPDATE_BITCANNAID:
          serviceImpl.updateBitcannaid((BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaid) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateBitcannaidResponse>) responseObserver);
          break;
        case METHODID_DELETE_BITCANNAID:
          serviceImpl.deleteBitcannaid((BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaid) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteBitcannaidResponse>) responseObserver);
          break;
        case METHODID_CREATE_SUPPLYCHAIN:
          serviceImpl.createSupplychain((BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychain) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgCreateSupplychainResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SUPPLYCHAIN:
          serviceImpl.updateSupplychain((BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychain) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgUpdateSupplychainResponse>) responseObserver);
          break;
        case METHODID_DELETE_SUPPLYCHAIN:
          serviceImpl.deleteSupplychain((BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychain) request,
              (io.grpc.stub.StreamObserver<BitCannaGlobal.bcna.bcna.Tx.MsgDeleteSupplychainResponse>) responseObserver);
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
      return BitCannaGlobal.bcna.bcna.Tx.getDescriptor();
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
              .addMethod(getCreateBitcannaidMethod())
              .addMethod(getUpdateBitcannaidMethod())
              .addMethod(getDeleteBitcannaidMethod())
              .addMethod(getCreateSupplychainMethod())
              .addMethod(getUpdateSupplychainMethod())
              .addMethod(getDeleteSupplychainMethod())
              .build();
        }
      }
    }
    return result;
  }
}
